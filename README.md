Hadoop-Triangles
================

Counting triangles in a graph using Hadoop




Command hdfs:

bin/hadoop fs -put <path to file to put> <path in dfs to put file in>

bin/hadoop fs -ls 

bin/hadoop fs -cat <path to file to cat in dfs>

Command to run application:

bin/hadoop jar path/to/jar ClassName path/to/dfs/input path/to/dfs/output

Command to use streaming Hadoop with python mapper and reduce:

/home/ncarey/hadoop/hadoop-2.2.0/bin/hadoop jar /home/ncarey/hadoop/hadoop-2.2.0/share/hadoop/tools/lib/hadoop-streaming-2.2.0.jar -mapper /home/ncarey/gitrepos/Hadoop-Triangles/source/python/map.sh -reducer /home/ncarey/gitrepos/Hadoop-Triangles/source/python/reduce.sh -input /home/ncarey/gitrepos/Hadoop-Triangles/data/simple -output /home/ncarey/gitrepos/Hadoop-Triangles/data/output/streamout


