#!/usr/bin/env python
import sys

all_loc = open(sys.argv[1], "r")
thr = int(sys.argv[2])
cnt = 0
cur = ""
lines = ""
while True:
	line = all_loc.readline()
	loc_line = line.strip().split("\t")
	try:
		loc_str = "\t".join([loc_line[0], loc_line[1], loc_line[3], loc_line[4]])
	except:
		break
	# print(loc_str,cur)
	if line == "":
		break
	elif loc_str != cur:
		lines = ""
		cur = loc_str
		cnt = 1
	else:
		lines += line
		cnt += 1
	if cnt == thr:
		print(lines.strip())
all_loc.close()
