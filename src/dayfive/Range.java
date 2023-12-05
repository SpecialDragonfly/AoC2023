package dayfive;

import java.util.HashMap;

public class Range {
	private long map;
	private long start;
	private long end;

	public Range(String map, String start, String end) {
		this.map = Long.valueOf(map);
		this.start = Long.valueOf(start);
		this.end = Long.valueOf(end);
	}
	
	public HashMap<Long, Long> getValues() {
		HashMap<Long, Long> ret = new HashMap<>();
		for (long i = this.start; i <= this.start + this.end - 1; i++) {
			ret.put(i, this.map + (i - this.start));
		}
		return ret;
	}

	public String getValueFor(String value) {
		long val = Long.valueOf(value);
		return String.valueOf(this.map + (val - this.start));
	}

	public boolean hasValue(String value) {
		long val = Long.valueOf(value);
		boolean ret = val >= this.start && val <= (this.start + this.end - 1);
		return ret;
	}
}
