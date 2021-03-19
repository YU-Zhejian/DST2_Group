#!/usr/bin/env bash
set -eu
DN="$(readlink -f "$(dirname "${0}")")"
cd "${DN}"
. ../etc/path.sh
. ../etc/head.sh
. ../lib/head.sh
. ../lib/libexec.sh
cat CUTADAPT.conf | grep -v '^$' | grep -v '^#' | while read line; do
	echo "Initializing cutadapt for ${line}"
	cat ../etc/head.sh ../etc/path.sh ../lib/head.sh exec/cutadapt.sh |\
	sed "s;TARGET=TARGET;TARGET=\"$(echo ${line})\";" |\
	sed "s;WD=WD;WD=$(pwd);" |\
	${bsub} -n:"cutadapt ${line}"
done
