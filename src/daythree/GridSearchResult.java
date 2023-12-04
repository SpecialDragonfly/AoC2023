package daythree;

import java.util.Vector;

public class GridSearchResult {
	Vector<String> result;
	public GridSearchResult() {
		this.result = new Vector<>();
	}
	
	public void appendValue(String value) {
		this.result.add(value);
	}
	
	public int getValue() {
		return Integer.valueOf(String.join("", this.result));
	}
	
	public int length() {
		return this.result.size();
	}
}
