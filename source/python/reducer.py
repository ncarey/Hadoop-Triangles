import sys

if __name__ == '__main__':

  reduce_map = {}  

  line = sys.stdin.readline()
  while line != "":
    if line in reduce_map:
      reduce_map[line] += 1
    else:
      reduce_map[line] = 1
    line = sys.stdin.readline()

  for fof in reduce_map:
    if reduce_map[fof] > 1:
      print fof

