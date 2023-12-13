package daytwelve;

import java.util.Arrays;
import java.util.Vector;

public class Condition {

	private Vector<Integer> parts = new Vector<>();
	
	public Condition(String[] split) {
		Arrays.stream(split).forEach(v -> {
			this.parts.add(Integer.valueOf(v));
		});
	}
	
	public int getMinLength() {
		return parts.stream().reduce(0, (a, b) -> a + b) + (this.parts.size() - 1);
	}

	public Vector<Integer> getSizes() {
		return this.parts;
	}
	
	public String toString() {
		String x = "";
		for (int i = 0; i < this.parts.size(); i++) {
			x += this.parts.get(i) + " ";
		}
		return x.trim();
	}
}
