#!/usr/bin/env bash
set -eu
DN="$(readlink -f "$(dirname "${0}")")"
cd "${DN}"
. ../etc/path.sh
. ../etc/head.sh
. ../lib/head.sh
. ../lib/libexec.sh
# TODO: SINGLE_THREAD
function STAR_INDEX() {
	true # TODO
}
function HISAT2_INDEX() {
	[ -d "${1}"_HISAT_REF/ ] && return
	mkdir "${1}"_HISAT_REF/
	hisat2-build "${1}".fa "${1}"_HISAT_REF/"${1}"
}
function BOWTIE_INDEX() {
	[ -d "${1}"_BOWTIE_REF/ ] && return
	mkdir "${1}"_BOWTIE_REF/
	bowtie-build "${1}".fa "${1}"_BOWTIE_REF/"${1}"
}
function BOWTIE2_INDEX() {
	[ -d "${1}"_BOWTIE2_REF/ ] && return
	mkdir "${1}"_BOWTIE2_REF/
	bowtie2-build "${1}".fa "${1}"_BOWTIE2_REF/"${1}"
}
function TOPHAT2_INDEX() {
	BOWTIE2_INDEX "${@}"
}
for fn in "${@}";do
	[ -f "${fn}".fa ] || continue
	${mySTAR} && STAR_INDEX "${fn}"
	${myhisat2} && HISAT2_INDEX "${fn}"
	${mybowtie} && BOWTIE_INDEX "${fn}"
	${mybowtie2} && BOWTIE2_INDEX "${fn}"
	${mytophat2} && TOPHAT2_INDEX "${fn}"
done
