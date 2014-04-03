Hadoop-Triangles
================

Counting triangles in a graph using Hadoop

This project contains two versions of a graph triangle counting program

The main version is a standard java implementation of map and reduce for
counting triangles in a graph

The secondary version is a python script implementation of a mapper and a reducer
for use with the Streaming Hadooop jar

To Compile the Java version into a Jar for execution on Amazon's MapReduce:

	Step 1: compile source into jar
        - cd /home/ncarey/gitrepos/Hadoop-Triangles/source/java
	- javac -classpath /home/ncarey/hadoop/hadoop-1.2.1/hadoop-core-1.2.1.jar -d trianglecount_classes TriangleCount.java
	- jar -cvf TriangleCount.jar -C trianglecount_classes/ . 
		- DONT FORGET PERIOD AT THE END	

	Step 2: Test on local hadoop installation:
	- /home/ncarey/hadoop/hadoop-1.2.1/bin/start-all.sh
	- /home/ncarey/hadoop/hadoop-1.2.1/bin/hadoop jar /home/ncarey/gitrepos/Hadoop-Triangles/source/java/TriangleCount.jar TriangleCount /usr/ncarey/input /usr/ncarey/output
	- note that TriangleCount.jar takes three args: TriangleCount, dfs input directory, dfs output directory
	- Some relevant dfs commands:
		- bin/hadoop fs -put <path to file to put> <path in dfs to put file in>
		- bin/hadoop fs -ls
		- bin/hadoop fs -cat <path to file to cat in dfs>

	Step 3: Run on Amazon MapReduce Cluster
	- Start a MapReduce cluster of at least 4 instances
	- Give the cluster a Custom Jar job.
		- Specify location of jar as s3://ncarey90/input/jars/TriangleCount.jar
		- Arguements to Jar will be TriangleCount s3://ncarey90/input/1000 s3://ncarey90/output/1000

Reference commands:

To put jar and data into s3:

s3cmd put TriangleCount.jar s3://ncarey90/input/jars/TriangleCount.jar

s3cmd put /home/ncarey/gitrepos/Hadoop-Triangles/data/1000/ s3://ncarey90/input/1000


Command to run application:

bin/hadoop jar path/to/jar ClassName path/to/dfs/input path/to/dfs/output

Command to use streaming Hadoop with python mapper and reduce:

/home/ncarey/hadoop/hadoop-2.2.0/bin/hadoop jar /home/ncarey/hadoop/hadoop-2.2.0/share/hadoop/tools/lib/hadoop-streaming-2.2.0.jar -mapper /home/ncarey/gitrepos/Hadoop-Triangles/source/python/map.sh -reducer /home/ncarey/gitrepos/Hadoop-Triangles/source/python/reduce.sh -input /home/ncarey/gitrepos/Hadoop-Triangles/data/simple -output /home/ncarey/gitrepos/Hadoop-Triangles/data/output/streamout

output of streaming job located at s3://ncarey90/output/streamout/streamout/
output of Amazon MapReduce job located at s3://ncarey90/output/friends_1000
