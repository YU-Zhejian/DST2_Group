function indexbam(){
	if ${mysambamba};then
		DO sambamba index -t ${SINGLE_THREAD} -p "${1}"
	elif ${mysamtools};then
		DO samtools index -@ ${SINGLE_THREAD} "${1}"
	fi
}

if [ ! -f "${TARGET}".bam ];then
	[ -f ../1_ReadQC/"${TARGET}_1.fq.gz" ] && [ -f ../1_ReadQC/"${TARGET}_2.fq.gz" ]
	if ${mySTAR};then
		DO STAR --outFileNamePrefix "${TARGET}/" \
		--outSAMtype BAM SortedByCoordinate \
		--outFilterMultimapNmax 1 \
		--outSAMstrandField intronMotif \
		--genomeDir "${GENOME_FASTA_STAR_INDEX}" \
		--runThreadN  ${SINGLE_THREAD}  \
		--outBAMsortingThreadN ${SINGLE_THREAD} \
		--readFilesIn ../1_ReadQC/"${TARGET}_1.fq.gz" ../1_ReadQC/"${TARGET}_2.fq.gz" \
		--readFilesCommand zcat \
		--twopassMode Basic
		ln -s "${TARGET}/Aligned.sortedByCoord.out.bam" "${TARGET}".bam
	elif ${myhisat2};then
		DO hisat2 --threads ${SINGLE_THREAD} \
		-x "${GENOME_FASTA_HISAT2_INDEX}" \
		-1 ../1_ReadQC/"${TARGET}_1.fq.gz" \
		-2 ../1_ReadQC/"${TARGET}_2.fq.gz" \| samtools sort -@ ${SINGLE_THREAD} -l 9 -o "${TARGET}".bam
	fi
	indexbam "${TARGET}".bam
fi

if [ ! -f "${TARGET}"_rdadd.bam ];then
	DO gatk AddOrReplaceReadGroups \
	-I "${TARGET}".bam \
	-O "${TARGET}"_rdadd.bam \
	--RGLB lib1 \
	--RGPL illumina \
	--RGPU unit \
	--RGSM "${TARGET}"
	indexbam "${TARGET}"_rdadd.bam
fi

if [ ! -f "${TARGET}"_dupmark.bam ];then
	if ${mysambamba};then
		DO sambamba markdup -r -p -l 9 -t ${SINGLE_THREAD} "${TARGET}"_rdadd.bam "${TARGET}"_dupmark.bam
	else
		DO gatk MarkDuplicatesSpark \
		-I "${TARGET}"_rdadd.bam \
		-O "${TARGET}"_dupmark.bam \
		--REMOVE_SEQUENCING_DUPLICATES true \
		-M "${TARGET}"_dupmark_metrics.txt
	fi
	indexbam "${TARGET}"_dupmark.bam
fi

if [ ! -f "${TARGET}"_split.bam ];then
	mkdir -p SplitNCigarReads_TMP
	DO gatk SplitNCigarReads \
	--tmp-dir SplitNCigarReads_TMP \
	-R "${GENOME_FASTA}" \
	-I "${TARGET}"_dupmark.bam \
	-O "${TARGET}"_split.bam
	# TODO: -RMQF 255 -RMQT 60 --filter_reads_with_N_cigar option not found
fi

NEXT_STEP="${TARGET}"_split.bam

if ${ENABLE_IndelRealn} ;then
	if [ ! -f "${TARGET}"_realigned.bam ];then
		DO gatk3 -T RealignerTargetCreator \
		-R "${GENOME_FASTA}" \
		-known "${GATK_BASE}/1000G_phase1.snps.high_confidence.hg38.vcf.gz" \
		-known "${GATK_BASE}/Mills_and_1000G_gold_standard.indels.hg38.vcf.gz" \
		-L "${GATK_BASE}/wgs_calling_regions.hg38.interval_list" \
		-o "${TARGET}"_intervals.list \
		-I "${NEXT_STEP}"

		DO gatk3 -T IndelRealigner \
		--filter_bases_not_stored \
		--disable_auto_index_creation_and_locking_when_reading_rods \
		-R "${GENOME_FASTA}" \
		-known "${GATK_BASE}/1000G_phase1.snps.high_confidence.hg38.vcf.gz" \
		-known "${GATK_BASE}/Mills_and_1000G_gold_standard.indels.hg38.vcf.gz" \
		-L "${GATK_BASE}/wgs_calling_regions.hg38.interval_list" \
		-targetIntervals "${TARGET}"_intervals.list \
		-I "${NEXT_STEP}" \
		-o "${TARGET}"_realigned.bam

		indexbam "${TARGET}"_realigned.bam
	fi
	NEXT_STEP="${TARGET}"_realigned.bam
fi

if ${ENABLE_BQSR};then
	if [ ! -f "${TARGET}"_bqsr.bam ];then
		DO gatk BaseRecalibratorSpark \
		-R "${GENOME_FASTA}" \
		-known-sites "${GATK_BASE}/Mills_and_1000G_gold_standard.indels.hg38.vcf.gz" \
		-known-sites "${GATK_BASE}/dbsnp_146.hg38.vcf.gz" \
		-I "${NEXT_STEP}" \
		-O "${TARGET}"_bqsr

		DO gatk ApplyBQSRSpark \
		-R "${GENOME_FASTA}" \
		-I "${TARGET}"_realigned.bam \
		-O "${TARGET}"_bqsr.bam \
		-bqsr "${TARGET}"_bqsr
	fi
	NEXT_STEP="${TARGET}"_bqsr.bam
fi

if [ ! -f "${TARGET}"_FINAL.bam ];then
	DO samtools view -@ ${SINGLE_THREAD} "${NEXT_STEP}" -q 30 -b -o "${TARGET}"_FINAL.bam
	indexbam "${TARGET}"_FINAL.bam
fi

if [ ! -f "${TARGET}"_all.vcf ];then
	# TODO: Spark have issues
	DO gatk HaplotypeCaller \
	-R "${GENOME_FASTA}" \
	-I "${TARGET}"_FINAL.bam \
	-O "${TARGET}"_all.vcf
fi

if [ ! -f "${TARGET}"_snp.vcf ];then
	DO gatk SelectVariants --select-type-to-include SNP -R "${GENOME_FASTA}" --select-type-to-include MNP -V "${TARGET}"_all.vcf -O "${TARGET}"_snp.vcf
	NEXT_STEP="${TARGET}"_snp.vcf
fi

if ${ENABLE_VQSR};then
	if [ ! -f "${TARGET}"_snp.vqsr.vcf ];then
	LIBDO_TOP_PID_tmp=${LIBDO_TOP_PID:-}
	unset LIBDO_TOP_PID
	RET=0
	if [ ! -f "${TARGET}_.HC.snps.tranches" ];then
		DO gatk VariantRecalibrator \
		-R "${GENOME_FASTA}" \
		-V "${TARGET}"_snp.vcf \
		--use-annotation DP \
		--use-annotation QD \
		--use-annotation FS \
		--use-annotation SOR \
		--use-annotation ReadPosRankSum \
		--use-annotation MQRankSum \
		--truth-sensitivity-tranche 100.0 \
		--truth-sensitivity-tranche 99.9 \
		--truth-sensitivity-tranche 99.0 \
		--truth-sensitivity-tranche 95.0 \
		--truth-sensitivity-tranche 90.0 \
		--resource:hapmap,known=false,training=true,truth=true,prior=15.0 "${GATK_BASE}/hapmap_3.3.hg38.vcf.gz" \
		--resource:omini,known=false,training=true,truth=false,prior=12.0 "${GATK_BASE}/1000G_omni2.5.hg38.vcf.gz" \
		--resource:1000G,known=false,training=true,truth=false,prior=10.0 "${GATK_BASE}/1000G_phase1.snps.high_confidence.hg38.vcf.gz" \
		--resource:dbsnp,known=true,training=false,truth=false,prior=2.0 "${GATK_BASE}/dbsnp_146.hg38.vcf.gz" \
		--mode SNP \
		--rscript-file "${TARGET}"_HC.snps.plots.R \
		--tranches-file  "${TARGET}"_.HC.snps.tranches \
		-O  "${TARGET}"_HC.snps.recal
		RET=${?}
	fi
	if [ ${RET} -eq 0 ];then
		DO gatk ApplyVQSR \
		-R "${GENOME_FASTA}" \
		-V "${TARGET}"_snp.vcf \
		--truth-sensitivity-filter-level 99.0 \
		--tranches-file  "${TARGET}"_.HC.snps.tranches \
		--recal-file "${TARGET}"_HC.snps.recal \
		--mode SNP \
		-O "${TARGET}"_snp.vqsr.vcf
		NEXT_STEP="${TARGET}"_snp.vqsr.vcf
	fi
	fi
	LIBDO_TOP_PID=${LIBDO_TOP_PID_tmp}
fi

if [ ! -f "${TARGET}"_snp.hdfilter.vcf ];then
	gatk VariantFiltration \
	-R "${GENOME_FASTA}" \
	-V "${NEXT_STEP}" \
	-O "${TARGET}"_snp.hdfilter.vcf \
	--filter-name "GATK_SNP" \
	--filter-expression "QD < 2.0 || MQ < 40.0 || FS > 60.0 || SOR > 3.0 || MQRankSum < -12.5 || ReadPosRankSum < -8.0"
fi
grep PASS "${TARGET}"_snp.hdfilter.vcf >> "${TARGET}"_snp.FINAL.vcf
# TODO: 404 for cytoBand
# TODO: CD: esp6500siv2_all esp6500siv2_ea esp6500siv2_aa icgc21
#DO table_annovar.pl "${TARGET}"_snp.FINAL.vcf "${ANNOVAR_HUMANDB}" -buildver hg19 -out "${TARGET}_annovar" -remove -protocol refGene,cytoBand,1000g2015aug_all,1000g2015aug_afr,1000g2015aug_amr,1000g2015aug_eas,1000g2015aug_eur,1000g2015aug_sas,exac03,avsnp150,esp6500siv2_all,esp6500siv2_ea,esp6500siv2_aa,gnomad_exome,dbnsfp35a,gnomad_genome,clinvar_20180603,cosmic70,icgc21,intervar_20180118 -operation 'g,r,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f'  -arg '-hgvs',,,,,,,,,,,,,,,,,,, -nastring . -polish –vcfinput
# TODO: Fix bugs
#grep exonic annovar_output.txt > medguide.txt
