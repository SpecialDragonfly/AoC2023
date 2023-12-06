package dayfive;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Vector;

public class Almanac {

	private String from;
	private String to;
	private Vector<Range> ranges = new Vector<>();

	public Almanac(String from, String to) {
		this.from = from;
		this.to = to;
	}

	public String getFrom() {
		return this.from;
	}

	public void addRange(String line) {
		String[] parts = line.split(" ");
		this.ranges.add(new Range(parts[0].trim(), parts[1].trim(), parts[2].trim()));
	}
	
	public HashMap<Long, Long> getFullMapping() {
		HashMap<Long, Long> ret = new HashMap<>();
		this.ranges.forEach(r -> {
			ret.putAll(r.getValues());
		});
		return ret;
	}
	
	public long getMappedLongValue(long value) {
		try {
			return this.ranges.stream()
				.filter(range -> { return range.hasValue(value); })
				.findFirst()
				.get()
				.getLongValueFor(value);	
		} catch (NoSuchElementException ex) {
			return value;
		}
	}
	
	public String getMappedValue(String value) {
		try {
			return this.ranges.stream()
				.filter(range -> { return range.hasValue(value); })
				.findFirst()
				.get()
				.getValueFor(value);	
		} catch (NoSuchElementException ex) {
			return value;
		}
	}
	
	public String getNext() {
		return this.to;
	}

}
