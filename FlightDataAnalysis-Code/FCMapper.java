import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


	 public class FCMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	        @Override
	        protected void map(LongWritable key, Text value, Context con)
	                throws IOException, InterruptedException {
	         String[] splitData = value.toString().split(",");
	     
	         if(splitData[21].equals("1") && !splitData[22].equals("NA")){
	         con.write(new Text(splitData[22]), new IntWritable(1) );
	         }
	        }
	 }