import sys

if __name__ == '__main__':
  
  line = sys.stdin.readline()
  while line != "":
    splitline = line.split()
    #emit all triple combinations
    for i in range(0, len(splitline)):
      for j in range(0, len(splitline)):
        for h in range(0, len(splitline)):
          if i != j and j != h and i != h:     
            print "{0} {1} {2}".format(splitline[i], splitline[j], splitline[h])

 
    line = sys.stdin.readline()
