#!/usr/bin/env bash
# TODO: Fit with current system
exit 0
set -ue
cd "$(readlink -f "$(dirname "${0}")")"
[ -d VCF ] || { echo "NO folder VCF";exit 1; }
cp -r VCF loc
cd loc
cat ../sample.conf | grep -v '^$' | grep -v '^#' | cut -f 2 -d ' ' | while read line; do
    echo "Transcripting ${line}"
    ls "${line}"*.vcf &>>/dev/null || continue
    for fn in "${line}"*_FINAL.vcf; do
        echo "Transcripting ${fn}"
        bash ../exec/vcf2loc.sh "${fn}" "$(echo ${fn##*_} | sed 's;.vcf;;')"
    done
    cat *.loc >"${line}"_allloc
    echo "Counting ${line}"
    python ../exec/cntloc.py "${line}"_allloc
    echo "Plotting ${line}"
    Rscript ../exec/VennPlot.R "${line}"_allloc "${line}"
    echo "Cleaning ${line}"
    rm -f *.loc "${line}"_allloc "${line}"*.vcf
    echo "${line} finished"
done
rm -f *.vcf
