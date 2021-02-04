#!/usr/bin/env bash
# LIBSTR V1
# From https://github.com/YuZJLab/LinuxMiniPrograms, commit  19ec030cdc2dee900577e4df620b2b3b6bfb5a2d, branch BSD
function trimstr() {
	: "${1#"${1%%[![:space:]]*}"}"
	: "${_%"${_##*[![:space:]]}"}"
	printf '%s\n' "$_"
}

function errh() {
	echo -e "\033[31mERROR: ${*}\033[0m" >&2
	exit 1
}

function warnh() {
	echo -e "\033[31mWARNING: ${*}\033[0m" >&2
}

function infoh() {
	echo -e "\033[33m${*}\033[0m" >&2
}
