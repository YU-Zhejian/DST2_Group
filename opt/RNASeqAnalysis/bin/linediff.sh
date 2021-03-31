#!/usr/bin/env bash
[ -f "${1}" ] && [ -f "${2}" ]
echo "Item in ${1} but not ${2}:"
cat "${1}" "${2}" "${2}" | sort | uniq -u
echo "Item in ${2} but not ${1}:"
cat "${2}" "${1}" "${1}" | sort | uniq -u
