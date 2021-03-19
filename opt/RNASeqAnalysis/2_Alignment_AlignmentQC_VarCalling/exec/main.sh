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
	DO gatk HaplotypeCallerSpark \
	-R "${GENOME_FASTA}" \
	-I "${TARGET}"__FINAL.bam \
	-O "${TARGET}"_all.vcf
fi
if [ ! -f "${TARGET}"_snp.vcf ];then
	DO gatk SelectVariants --select-type-to-include SNP -R "${GENOME_FASTA}" --select-type-to-include MNP -V "${TARGET}"_all.vcf -O "${TARGET}"_snp.vcf
fi
