package dayfive;

import java.util.Vector;

public class Seed {
	private Vector<String> values = new Vector<>();
	
	public Seed() {
		
	}
	
	public void addPathValue(String value) {
		this.values.add(value);
	}
	
	public String toString() {
		return this.values.toString();
	}
}
