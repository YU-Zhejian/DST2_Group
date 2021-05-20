#!/usr/bin/env bash
if ${ENABLE_JCS}; then
	if [ -z "${bsub:-}" ];then
		if ${mybsub}; then
			bsub="bsub"
		elif ${myylsjs}; then
			bsub="ylsjs init"
		else
			bsub="bash"
		fi
	fi
else
	bsub="bash"
fi
