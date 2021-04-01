#!/usr/bin/env bash
set -uex
cd "$(readlink -f "$(dirname "${0}")")"
[ -d VCF ] || {
	echo "NO folder VCF"
	exit 1
}
midkr -p loc/
cp -r VCF/* loc/
cd loc
cat ../../sample.conf | grep -v '^$' | grep -v '^#' | cut -f 2 -d ' ' | while read line; do
	echo "Transcripting ${line}"
	ls "${line}"*.vcf &>>/dev/null || continue
	for fn in "${line}"*.snp.vcf; do
		echo "Transcripting ${fn}"
		bash ../exec/vcf2loc.sh "${fn}" "$(echo ${fn##*_} | sed 's;.vcf;;')"
	done
	cat *.loc >"${line}"_allloc
	echo "Counting ${line}"
	python ../exec/count_loc.py "${line}"_allloc
	echo "Plotting ${line}"
	Rscript ../exec/VennPlot.R "${line}"_allloc "${line}"
	echo "Cleaning ${line}"
	rm -f *.loc "${line}"_allloc "${line}"*.vcf
	echo "${line} finished"
done
cd ..
