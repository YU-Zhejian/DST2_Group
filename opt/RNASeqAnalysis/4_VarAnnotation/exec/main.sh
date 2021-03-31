NEXT_STEP="../3_VarCalling/VCF/${TARGET}_all.snp.vcf"
if ${ENABLE_VQSR};then
	if [ ! -f "${TARGET}"_snp.vqsr.vcf ];then
		LIBDO_TOP_PID_tmp=${LIBDO_TOP_PID:-}
		unset LIBDO_TOP_PID
		RET=0
		if [ ! -f "${TARGET}_.HC.snps.tranches" ];then
			DO gatk VariantRecalibrator \
			-R "${GENOME_FASTA}" \
			-V "${NEXT_STEP}" \
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
			-O "${TARGET}"_HC.snps.recal
			RET=${?}
		fi
		LIBDO_TOP_PID=${LIBDO_TOP_PID_tmp}
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

fi

if [ ! -f "${TARGET}"_snp.hdfilter.vcf ];then
	gatk VariantFiltration \
	-R "${GENOME_FASTA}" \
	-V "${NEXT_STEP}" \
	-O "${TARGET}"_snp.hdfilter.vcf \
	--filter-name "GATK_SNP" \
	--filter-expression "QD < 2.0 || MQ < 40.0 || FS > 60.0 || SOR > 3.0 || MQRankSum < -12.5 || ReadPosRankSum < -8.0"
fi
if [ ! -f "${TARGET}"_snp.FINAL.vcf ];then
	grep PASS "${TARGET}"_snp.hdfilter.vcf >> "${TARGET}"_snp.FINAL.vcf
fi
if [ ! -f "${TARGET}_annovar.hg38_multianno.txt" ];then
	DO convert2annovar.pl -format vcf4 "${TARGET}"_snp.FINAL.vcf \> "${TARGET}"_snp.FINAL.avinput
	DO table_annovar.pl "${TARGET}"_snp.FINAL.avinput \
	"${ANNOVAR_HUMANDB}" -buildver hg38 \
	-out "${TARGET}_annovar" \
	-thread ${SINGLE_THREAD} \
	-protocol refGene,cytoBand,1000g2015aug_all,1000g2015aug_afr,1000g2015aug_amr,1000g2015aug_eas,1000g2015aug_eur,1000g2015aug_sas,exac03,avsnp150,esp6500siv2_all,esp6500siv2_ea,esp6500siv2_aa,gnomad_exome,dbnsfp35a,gnomad_genome,clinvar_20180603,cosmic70,icgc28,intervar_20180118 \
	-operation 'g,r,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f' \
	-arg '-hgvs,,,,,,,,,,,,,,,,,,,' \
	-nastring . -polish
fi
grep exonic "${TARGET}_annovar.hg38_multianno.txt" > "${TARGET}_medguide.txt".txt
