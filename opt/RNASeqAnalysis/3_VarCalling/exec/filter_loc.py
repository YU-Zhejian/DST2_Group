#!/usr/bin/env python
import sys

all_loc = open(sys.argv[1], "r")
thr = int(sys.argv[2])
cnt = 0
cur = ""
while True:
	line = all_loc.readline()
	if line == "":
		break
	elif line != cur:
		cur = line
		cnt = 1
	else:
		cnt += 1
	if cnt == thr:
		print(line)
all_loc.close()
