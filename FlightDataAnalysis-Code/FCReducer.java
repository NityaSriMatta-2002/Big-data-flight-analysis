import java.io.IOException;
import java.util.TreeMap;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class FCReducer extends Reducer<Text, IntWritable,Text, IntWritable>{

	TreeMap<Integer, String> sort = new TreeMap();
	@Override
	protected void reduce(Text key, Iterable<IntWritable> value,
			Reducer<Text, IntWritable, Text, IntWritable>.Context con)
			throws IOException, InterruptedException {
		int sum = 0;
		for (IntWritable val : value) {
			sum += val.get();
		}

		sort.put(sum, key.toString());
		
	}
	
	
	protected void cleanup(Context con){
		int sum = sort.lastKey();
		String key = sort.get(sum);
		if(key.equalsIgnoreCase("a")){
			try {
				con.write(new Text("CARRIER"), new IntWritable(sum));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else if(key.toString().equalsIgnoreCase("B")){
			try {
				con.write(new Text("WEATHER"), new IntWritable(sum));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else if(key.toString().equalsIgnoreCase("C")){
			try {
				con.write(new Text("NAS"), new IntWritable(sum));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else if(key.toString().equalsIgnoreCase("D")){
			try {
				con.write(new Text("SECURITY"), new IntWritable(sum));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	}
	
	
	    