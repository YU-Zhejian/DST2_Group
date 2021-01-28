#!/usr/bin/env bash
if ${mybsub};then
	bsub="bsub"
elif ${myylsjs};then
	bsub="ylsjs init"
else
	bsub="bash"
fi
