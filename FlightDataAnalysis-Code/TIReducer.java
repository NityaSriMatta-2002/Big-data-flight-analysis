import java.io.IOException;
import java.util.TreeSet;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TIReducer extends Reducer<Text, IntWritable,Text,DoubleWritable>{
	TreeSet<ResultPairHighIn> sortedOutputHigh = new TreeSet<>();
	TreeSet<ResultPairLowIn> sortedOutputLow = new TreeSet<>();
	

	@Override
	protected void reduce(Text key, Iterable<IntWritable> value,
			Reducer<Text, IntWritable, Text, DoubleWritable>.Context con)
			throws IOException, InterruptedException {
		double sum = 0;
		int i = 0;
		for (IntWritable val : value) {
			sum += (double)val.get();
			i++;
		}
		double average = sum/i;
		
		sortedOutputHigh.add(new ResultPairHighIn(average, key.toString()));
		if (sortedOutputHigh.size() > 3) {
			sortedOutputHigh.pollLast();
		}
		
		sortedOutputLow.add(new ResultPairLowIn(average, key.toString()));
		if (sortedOutputLow.size() > 3) {
			sortedOutputLow.pollLast();
		}
		
	}
	protected void cleanup(Context context)
            throws IOException,
            InterruptedException {
		context.write(new Text("Airports with Longest Taxi In Time are:"), new DoubleWritable());
		
		while(!sortedOutputHigh.isEmpty()){
			ResultPairHighIn p1= sortedOutputHigh.pollFirst();
			context.write(new Text(p1.key), new DoubleWritable(p1.avg));
		}
		context.write(new Text(), new DoubleWritable());
		context.write(new Text("Airports with Shortest Taxi In Time are:"), new DoubleWritable());
		
		while(!sortedOutputLow.isEmpty()){
			ResultPairLowIn p2= sortedOutputLow.pollFirst();
			context.write(new Text(p2.key), new DoubleWritable(p2.avg));
		}
	}
}