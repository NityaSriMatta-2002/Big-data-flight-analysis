import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class probability_mapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	  @Override
	  protected void map(LongWritable key, Text value, Context con)
	  throws IOException, InterruptedException {
	        	
	        String[] splitData = value.toString().split(",");
	        if(!splitData[0].equalsIgnoreCase("year") && splitData[14].equals("0") && splitData[15].equals("0")){
		        con.write(new Text(splitData[8]+" onSchedule"), new IntWritable(1));
		  }
		  con.write(new Text(splitData[8]+ " allFlights"), new IntWritable(1));
	  }
}