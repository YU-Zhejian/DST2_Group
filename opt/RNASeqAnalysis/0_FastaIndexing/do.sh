#!/usr/bin/env bash
set -eu
DN="$(readlink -f "$(dirname "${0}")")"
cd "${DN}"
. ../etc/path.sh
. ../lib/libexec.sh
for prog in bowtie2 star tophat2 bowtie; do
	if eval "\${my${prog}}"; then
		echo "Will build index of ${prog}."
		cat ../lib/head.sh exec/${prog}.sh |\
		sed "s;TARGET=TARGET;TARGET=$(echo ${fn});" |\
		${bsub}
		exit
	fi
done
