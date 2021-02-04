if [ ! -f "${TARGET}".bam ];then
	[ -f ../1_ReadQC/"${TARGET}_1.fq.gz" ] && [ -f ../1_ReadQC/"${TARGET}_2.fq.gz" ]
	DO hisat2 -x "${GENOME_FASTA_HISAT2_INDEX}" -1 ../1_ReadQC/"${TARGET}_1.fq.gz" -2 ../1_ReadQC/"${TARGET}_2.fq.gz" \| samtools sort -l 9 -o "${TARGET}".bam
	DO samtools index "${TARGET}".bam
fi
if [ ! -f "${TARGET}"_rdadd.bam ];then
	DO gatk AddOrReplaceReadGroups \
	-I "${TARGET}".bam \
	-O SRR54376{i}_rdadd.bam \
	--RGLB lib1 \
	--RGPL illumina \
	--RGPU unit \
	--RGSM "${TARGET}"
	DO samtools index "${TARGET}"_rdadd.bam
fi

if [ ! -f "${TARGET}"_dupmark.bam ];then
	DO gatk MarkDuplicates \
	-I "${TARGET}"_rdadd.bam \
	-O "${TARGET}"_dupmark.bam \
	--REMOVE_SEQUENCING_DUPLICATES true \
	-M "${TARGET}"_dupmark_metrics.txt
	DO samtools index "${TARGET}"_dupmark.bam

	NEXT_STEP="${TARGET}"_dupmark.bam
fi

if ${ENABLE_BQSR};then
	DO gatk BaseRecalibrator \
	-R "${GENOME_FASTA}" \
	-known-sites "${GATK_BASE}/Mills_and_1000G_gold_standard.indels.hg38.vcf.gz" \
	-known-sites "${GATK_BASE}/dbsnp_146.hg38.vcf.gz" \ # Newest DBSNP provided in GATK bundle.
	-I "${NEXT_STEP}" -O "${TARGET}"_bqsr

	DO gatk ApplyBQSR \
	-R "${GENOME_FASTA}" \
	-I "${TARGET}"_realigned.bam \
	-O "${TARGET}"_bqsr.bam \
	-bqsr "${TARGET}"_bqsr

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
	DO samtools view "${TARGET}"_split.bam -q 30 -b -o "${TARGET}"_FINAL.bam
	DO samtools index "${TARGET}"_FINAL.bam
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
