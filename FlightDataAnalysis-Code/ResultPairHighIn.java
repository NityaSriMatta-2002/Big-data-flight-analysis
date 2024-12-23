public class ResultPairHighIn implements Comparable<ResultPairHighIn>  {
	

	double avg;
	String key;

	ResultPairHighIn(double avg, String key) {
		this.avg = avg;
		this.key = key;
	}

	@Override
	public int compareTo(ResultPairHighIn resultPairHigh) {
		if (this.avg <= resultPairHigh.avg) {
			return 1;
		} else {
			return -1;
		}
	}
}