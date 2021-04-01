mkdir -p VCF
# Function to select SNPs only
function selectSNP() {
	if which vcftools &>>/dev/null; then
		DO vcftools --vcf "${1}" --remove-indels --recode --recode-INFO-all --stdout \> "${1/.vcf/.snp.vcf}"
	else
		DO gatk SelectVariants \
			--select-type-to-include SNP \
			-R "${GENOME_FASTA}" \
			--select-type-to-include MNP \
			-V "${1}" \
			-O "${1/.vcf/.snp.vcf}"
	fi
}

if [ ! -f VCF/"${TARGET}"_all.snp.vcf ]; then
	# TODO: Spark have issues
	if [ ! -f VCF/"${TARGET}"_HaplotypeCaller.snp.vcf ] && ${ENABLE_HAPLOTYPECALLER}; then
		DO gatk HaplotypeCaller \
			-R "${GENOME_FASTA}" \
			-I ../2_Alignment_AlignmentQC/"${TARGET}"_FINAL.bam \
			-O VCF/"${TARGET}"_HaplotypeCaller.vcf
		selectSNP VCF/"${TARGET}"_HaplotypeCaller.vcf
	fi

	if [ ! -f VCF/"${TARGET}"_FREEBAYES.snp.vcf ] && ${ENABLE_FREEBAYES}; then
		DO freebayes \
			-f "${GENOME_FASTA}" \
			../2_Alignment_AlignmentQC/"${TARGET}"_FINAL.bam \
			\> VCF/"${TARGET}"_FREEBAYES.vcf
		selectSNP VCF/"${TARGET}"_FREEBAYES.vcf
	fi
	# TODO: LoFreq have errors
	# TODO: Re-test with lofreq version 3 at https://github.com/andreas-wilm/lofreq
	#	if [ ! -f VCF/"${TARGET}"_LOFREQ.snp.vcf ] && ${ENABLE_LOFREQ};then
	#		DO lofreq call-parallel --pp-threads ${SINGLE_THREAD} \
	#		-f "${GENOME_FASTA}" \
	#		-o VCF/"${TARGET}"_LOFREQ.vcf \
	#		../2_Alignment_AlignmentQC/"${TARGET}"_FINAL.bam
	#		sed -i 's;#CHROM\tPOS\tID\tREF\tALT\tQUAL\tFILTER\tINFO$;#CHROM\tPOS\tID\tREF\tALT\tQUAL\tFILTER\tINFO\tFORMAT\t'"$(echo ${TARGET})"';' VCF/"${TARGET}"_LOFREQ.vcf
	#		sed -i 's;$;\t\t;' VCF/"${TARGET}"_LOFREQ.vcf
	#		selectSNP VCF/"${TARGET}"_LOFREQ.vcf
	#	fi
	#	if [ ! -f VCF/"${TARGET}"_VARSCAN.snp.vcf ] && ${ENABLE_VARSCAN}; then
	#		# TODO: Multi-thread?
	#		DO samtools mpileup -B \
	#		-f "${GENOME_FASTA}" \
	#		../2_Alignment_AlignmentQC/"${TARGET}"_FINAL.bam \|\
	#		varscan mpileup2snp \> VCF/"${TARGET}"_VARSCAN.vcf
	#	fi
	#	if [ ! -f VCF/"${TARGET}"_MUTECT2.snp.vcf ] && ${ENABLE_MUTECT2}; then
	#		# TODO: Multi-thread?
	#		DO samtools pileup \
	#		-f "${GENOME_FASTA}" \
	#		../2_Alignment_AlignmentQC/"${TARGET}"_FINAL.bam \> "${TARGET}"_pileups.txt
	#		DO gatk CalculateContamination \
	#		-I "${TARGET}"_pileups.txt \
	#		-O "${TARGET}"_contamination.txt
	#		DO gatk Mutect2 \
	#		-R "${GENOME_FASTA}" \
	#		-I ../2_Alignment_AlignmentQC/"${TARGET}"_FINAL.bam \
	#		-O "${TARGET}"_MUTECT2.raw.vcf
	#		DO gatk FilterMutectCalls \
	#		-V "${TARGET}"_MUTECT2.raw.vcf \
	#		--contamination-table "${TARGET}"_contamination.txt \
	#		-O VCF/"${TARGET}"_MUTECT2.vcf
	#		selectSNP VCF/"${TARGET}"_MUTECT2.vcf
	#	fi
	# To run Strelka with CNV/SVs identified by Manta
	if [ ! -f VCF/"${TARGET}"_STRELKA.snp.vcf ] && ${ENABLE_STRELKA}; then
		DO configManta.py \
			--bam ../2_Alignment_AlignmentQC/"${TARGET}"_FINAL.bam \
			--referenceFasta "${GENOME_FASTA}" \
			--runDir ./"${TARGET}"_MANTA
		DO ./"${TARGET}"_MANTA/runWorkflow.py -m local -j ${SINGLE_THREAD}

		DO configureStrelkaGermlineWorkflow.py \
			--bam ../2_Alignment_AlignmentQC/"${TARGET}"_FINAL.bam \
			--referenceFasta "${GENOME_FASTA}" \
			--indelCandidates ./"${TARGET}"_MANTA/results/variants/candidateSmallIndels.vcf.gz \
			--runDir ./"${TARGET}"_STRELKA
		DO ./"${TARGET}"_STRELKA/runWorkflow.py -m local -j ${SINGLE_THREAD}

		cat "${TARGET}"_STRELKA/results/variants/variants.vcf.gz | gzip -dc | grep -e '^#' -e 'PASS' >VCF/"${TARGET}"_STRELKA.snp.vcf
	fi
	# To select variants identified by more than ${VAR_THR} caller
	cat VCF/"${TARGET}"*.snp.vcf | grep -v '^#' | sort -n --parallel=${SINGLE_THREAD} >VCF/"${TARGET}".all_raw.vcf
	python exec/filter_loc.py "${PWD}"/VCF/"${TARGET}".all_raw.vcf ${VAR_THR} >VCF/"${TARGET}"_all_pre.vcf
	head_fn=""
	for fn in VCF/*.snp.vcf; do
		cat "${fn}" | grep '^#' >"${fn/.vcf/.head.vcf}"
		head_fn="${head_fn} -I ${fn/.vcf/.head.vcf} "
	done
	DO gatk MergeVcfs \
		"${head_fn}" \
		-O VCF/"${TARGET}"_all_heads.vcf
	cat VCF/"${TARGET}"_all_heads.vcf VCF/"${TARGET}"_all_pre.vcf >VCF/"${TARGET}"_all_pre_headed.vcf
	DO gatk SortVcf -I VCF/"${TARGET}"_all_pre_headed.vcf -O VCF/"${TARGET}"_all_pre_sort.vcf
	# To merge different VCF formats into one
	DO gatk MergeVcfs \
		-I VCF/"${TARGET}"_all_pre_sort.vcf \
		-O VCF/"${TARGET}"_all.snp.vcf \
		-D "${GENOME_FASTA/.fa/.dict}"
fi
