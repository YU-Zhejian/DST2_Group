#!/usr/bin/env bash
# TODO: Data retrival using ENA APIs.
set -eu
DN="$(readlink -f "$(dirname "${0}")")"
cd "${DN}"
. ../etc/path.sh
. ../lib/libexec.sh
function my_rename() {
	if ls *.fastq* &>>/dev/null; then
		for fn in *.fastq*; do
			mv -v "${fn}" "${fn//.fastq/.fq}"
		done
	fi
	if ls *.fq &>>/dev/null; then
		for fn in *.fq; do
			cat ../lib/head.sh exec/gzip.sh |\
			sed "s;TARGET=TARGET;TARGET=$(echo ${fn});" |\
			${bsub}
		done
	fi
	if ls *.fq.gz &>>/dev/null; then
		for fn in *.fq.gz; do
			cat ../lib/head.sh exec/fastqc.sh |\
			sed "s;TARGET=TARGET;TARGET=$(echo ${fn});" |\
			${bsub}
		done
	fi
	exit
}

if ${myascp} && [ -e ascp.conf ]; then
	echo "Will use ascp to accelerate download."
	cat ascp.conf | while read line; do
		ascp -QT -l 300m -P33001 -i asperaweb_id_dsa.openssh era-fasp@"${line}"
	done
	my_rename
fi
for prog in aria2c axel wget curl; do
	if eval "\${my${prog}}" && [ -e ftp.conf ]; then
		echo "Will use ${prog} to accelerate download."
		cat ftp.conf | while read line; do
			"${prog}" "${line}"
		done
		my_rename
	fi
done
