#!/usr/bin/env bash
#Example: vcf2loc.sh manta.vcf manta
if [ -f "${1}" ]; then
	cat "${1}" | cut -f 1,2,4,5 | grep -v '^#' | sed 's;\t;_;g' | sed "s/$/\t${2}/" >"$(echo ${1} | sed "s/.vcf/.loc/")"
else
	echo -e "\e[033mInvalid file '${1}'.\e[0m"
	exit 1
fi
