#!/usr/bin/env bash
# TODO: Data retrival using ENA APIs.
set -eu
DN="$(readlink -f "$(dirname "${0}")")"
cd "${DN}"
. ../etc/path.sh
. ../lib/libexec.sh
function my_rename() {
	printf ".fastq -> .fq"
	if ls *.fastq* &>>/dev/null; then
		for fn in *.fastq*; do
			mv -v "${fn}" "${fn//.fastq/.fq}"
		done
	fi
	echo "  [done]"
	printf ".fq -> .fq.gz"
	if ls *.fq &>>/dev/null; then
		for fn in *.fq; do
			cat ../lib/head.sh exec/gzip.sh |\
			sed "s;TARGET=TARGET;TARGET=$(echo ${fn});" |\
			sed "s;WD=WD;WD=$(pwd);" |\
			${bsub}
		done
	fi
	echo "  [done]"
	if ls *.fq.gz &>>/dev/null; then
		for fn in *.fq.gz; do
			echo "Initializing fastqc for ${fn}"
			cat ../lib/head.sh exec/fastqc.sh |\
			sed "s;TARGET=TARGET;TARGET=$(echo ${fn});" |\
			sed "s;WD=WD;WD=$(pwd);" |\
			${bsub}
		done
	fi
	exit
}
function tail_name() {
	basename -s .fastq "$(basename -s .fq "$(basename -s .gz "${1}")")"
}
base_url='https://www.ebi.ac.uk/ena/portal/api/search?result=read_run&dataPortal=ENA&fields=fastq_ftp,fastq_aspera,fastq_md5&limit=0&query=run_accession='
if [ ! -f accession.tsv ];then
cat ../sample.conf | grep -v '^$' | grep -v '^\#' | while read accession;do
	echo "Parsing ${accession}"
	printf '\n#' >> accession.tsv
	wget -O tmpt "${base_url}${accession}"
	rm tmpt
	cat tmpt>> accession.tsv
done
fi

if ${myascp}; then
	echo "Will use ascp to accelerate download."
	cat accession.tsv | grep -v '^$' | grep -v '^\#' | cut -f 3 | tr ';' '\n' | while read line; do
		! ls "$(tail_name "${line}")".* &>/dev/null && ascp -QT -l 300m -P33001 -i asperaweb_id_dsa.openssh era-fasp@"${line}"
	done
	my_rename
fi
for prog in aria2c axel wget curl; do
	if eval "\${my${prog}}"; then
		echo "Will use ${prog} to accelerate download."
		cat accession.tsv | grep -v '^$' | grep -v '^\#' | cut -f 2 | tr ';' '\n' | while read line; do
			printf "ftp://${line}"
			! ls "$(tail_name "${line}")".* &>/dev/null && "${prog}" "ftp://${line}"
			echo "  [done]"
		done

		my_rename
	fi
done
