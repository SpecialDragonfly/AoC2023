package dayeight;

public class KeyedNode extends Node {
	private String location;

	public KeyedNode(String location, String left, String right) {
		super(left, right);
		this.location = location;
	}
	
	public String getLocation() {
		return this.location;
	}
	
	public String toString() {
		return this.location + " => " + super.toString();
	}
}
