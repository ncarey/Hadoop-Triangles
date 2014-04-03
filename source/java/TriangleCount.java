import java.io.IOException;
import java.util.*;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;
	
public class TriangleCount {


	public static class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();

		public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException 
		{

			String line = value.toString();
			String [] split = line.split("\\s+");
			if(split.length > 0){
				String host = split[0];
                        	for(int i = 0; i < split.length; i++){
					for(int j = 0; j < split.length; j++){
						for(int h = 0; h < split.length; h++){
							if(((i != j && j != h) && i != h) && ((split[i] == host || split[j] == host) || split[h] == host)) {
								word.set("<" + split[i] + "," + split[j] + "," + split[h] + ">");
								output.collect(word,one);
							}
						}
					}
				}
			}
		}
	}
	

	public static class Reduce extends MapReduceBase implements Reducer<Text, IntWritable, Text, NullWritable> {
		private final static NullWritable nw = NullWritable.get();
		
		public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, NullWritable> output, Reporter reporter) throws IOException {


			int sum = 0;
			while (values.hasNext()) {
				sum += values.next().get();
			}
			if(sum > 1){
				output.collect(key, nw);
			}
		}
	}
	

	public static void main(String[] args) throws Exception {
		JobConf conf = new JobConf(TriangleCount.class);
		conf.setJobName("trianglecount");
	
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(NullWritable.class);

		conf.setMapOutputKeyClass(Text.class);
		conf.setMapOutputValueClass(IntWritable.class);
	
		conf.setMapperClass(Map.class);
		conf.setReducerClass(Reduce.class);
	
		conf.setInputFormat(TextInputFormat.class);
		conf.setOutputFormat(TextOutputFormat.class);
	
		FileInputFormat.setInputPaths(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));
	
		JobClient.runJob(conf);
	}
}
