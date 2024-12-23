public class ResultPairLowIn implements Comparable<ResultPairLowIn>  {
	

	double avg;
	String key;

	ResultPairLowIn(double avg, String key) {
		this.avg = avg;
		this.key = key;
	}

	@Override
	public int compareTo(ResultPairLowIn resultPairLow) {
		if (this.avg >= resultPairLow.avg) {
			return 1;
		} else {
			return -1;
		}
	}
}