#!/usr/bin/env python
import os
import sys

if not os.path.isfile(sys.argv[1]):
	exit(1)
fns = open(sys.argv[1]).readlines()
fna = []
for item in fns:
	fna.append(item.split('\t'))
mycnt = {}
for item in fna:
	if not item[0] in mycnt:
		try:
			mycnt[item[0]] = [item[1]]
		except:
			print("ERR: " + item[0])
	else:
		mycnt[item[0]].append(item[1])
numcnt = {}
for item in mycnt:
	thislen = len(mycnt[item])
	if thislen in numcnt:
		numcnt[thislen] = numcnt[thislen] + 1
	else:
		numcnt[thislen] = 1
for item in numcnt:
	print(item, numcnt[item])
