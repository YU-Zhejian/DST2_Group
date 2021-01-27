#!/usr/bin/env bash
# BADD V2
# From https://github.com/YuZJLab/LinuxMiniPrograms, commit  19ec030cdc2dee900577e4df620b2b3b6bfb5a2d, branch BSD
set -evu
DN="$(readlink -f "$(dirname "${0}")")"
cd "${DN}"
grep_cmd="find . 2> /dev/null | grep -v '.git'"
cat "${DN}"/.gitignore | grep -v '^$' | grep -v '^#' > gitignore.tmp
 while read line; do
	grep_cmd="${grep_cmd} | grep -v '${line}'"
done < gitignore.tmp
rm gitignore.tmp
dos2unix $(eval "${grep_cmd}" | xargs)
