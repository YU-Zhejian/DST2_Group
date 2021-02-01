#!/usr/bin/env bash
set -eu
DN="$(readlink -f "$(dirname "${0}")")"
cd "${DN}"
. ../etc/path.sh
. ../lib/libexec.sh
cat ../sample.conf | grep -v '^$' | grep -v '^#' | cut -f 2 -d ' ' | while read line; do
	echo "Initializing cutadapt for ${line}"
	cat ../etc/head.sh ../lib/head.sh exec/hisat2.sh |\
	sed "s;TARGET=TARGET;TARGET=\"$(echo ${line})\";" |\
	sed "s;WD=WD;WD=$(pwd);" |\
	${bsub}
done
