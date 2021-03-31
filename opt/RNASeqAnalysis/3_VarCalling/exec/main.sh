mkdir -p VCF
# FUnction to select SNPs only
function selectSNP(){
	DO gatk SelectVariants \
	--select-type-to-include SNP \
	-R "${GENOME_FASTA}" \
	--select-type-to-include MNP \
	-V ../3_VarCalling/"${1}" \
	-O "${1/.vcf/.snp.vcf}"
}
if [ ! -f VCF/"${TARGET}"_all.vcf ];then
	# TODO: Spark have issues
	if [ ! -f "${TARGET}"_HaplotypeCaller.snp.vcf ] && ${ENABLE_HAPLOTYPECALLER};then
		DO gatk HaplotypeCaller \
		-R "${GENOME_FASTA}" \
		-I ../2_Alignment_AlignmentQC/"${TARGET}"_FINAL.bam \
		-O VCF/"${TARGET}"_HaplotypeCaller.vcf
		selectSNP VCF/"${TARGET}"_HaplotypeCaller.vcf
	fi

	if [ ! -f "${TARGET}"_FREEBAYES.snp.vcf ] && ${ENABLE_FREEBAYES};then
		DO fasta_generate_regions.py ref.fa.fai 100000 \| freebayes-parallel ${SINGLE_THREAD} \
		-f "${GENOME_FASTA}" \
		../2_Alignment_AlignmentQC/"${TARGET}"_FINAL.bam \
		\> VCF/"${TARGET}"_FREEBAYES.vcf
		selectSNP VCF/"${TARGET}"_FREEBAYES.vcf
	fi

	if [ ! -f "${TARGET}"_LOFREQ.snp.vcf ] && ${ENABLE_LOFREQ};then
		DO lofreq call-parallel --pp-threads ${SINGLE_THREAD} \
		-f "${GENOME_FASTA}" \
		-o VCF/"${TARGET}"_LOFREQ.vcf \
		../2_Alignment_AlignmentQC/"${TARGET}"_FINAL.bam
		selectSNP VCF/"${TARGET}"_LOFREQ.vcf
	fi
	# To run Strelka with CNV/SVs identified by Manta
	if [ ! -f "${TARGET}"_STRELKA.snp.vcf ] && ${ENABLE_STRELKA};then
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

		cat "${TARGET}"_STRELKA/results/variants/somatic.snvs.vcf.gz | gzip -dc > VCF/"${TARGET}"_STRELKA.snp.vcf
	fi
	# To select variants identified by more than ${VAR_THR} caller
	cat VCF/"${TARGET}"*.snp.vcf | grep -v '^#'| cut -f 1,2,4,5 | sort > "${TARGET}".all_loc
	python exec/filter_loc.py "${PWD}"/"${TARGET}".all_loc ${VAR_THR}| cut -f 1,2 | while read line;do
		# TODO: Should have bugs
		cat VCF/"${TARGET}"*.snp.vcf | grep "${line}" >> VCF/"${TARGET}"_all_pre.vcf || true
	done
	# To merge different VCF formats into one
	DO gatk MergeVcfs \
	-I VCF/"${TARGET}"_all_pre.vcf \
	-O VCF/"${TARGET}"_all.vcf \
	-D "${GENOME_FASTA/.fa/.dict}"
fi
