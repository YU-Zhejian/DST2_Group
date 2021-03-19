if [ ! -f "${TARGET}".bam ];then
	[ -f ../1_ReadQC/"${TARGET}_1.fq.gz" ] && [ -f ../1_ReadQC/"${TARGET}_2.fq.gz" ]
	DO hisat2 --threads ${SINGLE_THREAD} -x "${GENOME_FASTA_HISAT2_INDEX}" -1 ../1_ReadQC/"${TARGET}_1.fq.gz" -2 ../1_ReadQC/"${TARGET}_2.fq.gz" \| samtools sort -@ ${SINGLE_THREAD} -l 9 -o "${TARGET}".bam
	DO samtools index -@ ${SINGLE_THREAD} "${TARGET}".bam
fi
if [ ! -f "${TARGET}"_rdadd.bam ];then
	DO gatk AddOrReplaceReadGroups \
	-I "${TARGET}".bam \
	-O "${TARGET}"_rdadd.bam \
	--RGLB lib1 \
	--RGPL illumina \
	--RGPU unit \
	--RGSM "${TARGET}"
	DO samtools index -@ ${SINGLE_THREAD} "${TARGET}"_rdadd.bam
fi

if [ ! -f "${TARGET}"_dupmark.bam ];then
	DO gatk MarkDuplicates \
	-I "${TARGET}"_rdadd.bam \
	-O "${TARGET}"_dupmark.bam \
	--REMOVE_SEQUENCING_DUPLICATES true \
	-M "${TARGET}"_dupmark_metrics.txt
	DO samtools index -@ ${SINGLE_THREAD} "${TARGET}"_dupmark.bam
fi

NEXT_STEP="${TARGET}"_dupmark.bam

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

		DO samtools index -@ ${SINGLE_THREAD} "${TARGET}"_realigned.bam
	fi
	NEXT_STEP="${TARGET}"_realigned.bam
fi
if ${ENABLE_BQSR};then
	if [ ! -f "${TARGET}"_bqsr.bam ];then
		DO gatk BaseRecalibrator \
		-R "${GENOME_FASTA}" \
		-known-sites "${GATK_BASE}/Mills_and_1000G_gold_standard.indels.hg38.vcf.gz" \
		-known-sites "${GATK_BASE}/dbsnp_146.hg38.vcf.gz" \
		-I "${NEXT_STEP}" \
		-O "${TARGET}"_bqsr

		DO gatk ApplyBQSR \
		-R "${GENOME_FASTA}" \
		-I "${TARGET}"_realigned.bam \
		-O "${TARGET}"_bqsr.bam \
		-bqsr "${TARGET}"_bqsr
	fi
	NEXT_STEP="${TARGET}"_bqsr.bam
fi

if [ ! -f "${TARGET}"_split.bam ];then
	DO gatk SplitNCigarReads \
	-R "${GENOME_FASTA}" \
	-I "${NEXT_STEP}" \
	-O "${TARGET}"_split.bam \
	-RMQF 255 -RMQT 60 --filter_reads_with_N_cigar
fi

if [ ! -f "${TARGET}"_FINAL.bam ];then
	DO samtools view -@ ${SINGLE_THREAD} "${TARGET}"_split.bam -q 30 -b -o "${TARGET}"_FINAL.bam
	DO samtools index -@ ${SINGLE_THREAD} "${TARGET}"_FINAL.bam
fi
if [ ! -f "${TARGET}"_all.vcf ];then
	DO gatk HaplotypeCaller \
	-R "${GENOME_FASTA}" \
	-I "${TARGET}"__FINAL.bam \
	-O "${TARGET}"_all.vcf
fi
if [ ! -f "${TARGET}"_snp.vcf ];then
	DO gatk SelectVariants --select-type-to-include SNP -R "${GENOME_FASTA}" --select-type-to-include MNP -V "${TARGET}"_all.vcf -O "${TARGET}"_snp.vcf
fi
