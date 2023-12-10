package dayten;

public class Tile {

	private String faceValue = "";
	private boolean kept = false;
	
	public Tile(String l) {
		this.faceValue = l;
	}

	public void setKept() {
		this.kept = true;
	}
	
	public boolean isOnPath() {
		return this.kept;
	}
	
	public boolean equals(String s) {
		return this.faceValue.equals(s);
	}

	public void setDead() {
		this.faceValue = ".";
	}
	
	public void setMaybe() {
		this.faceValue = "@";
	}
	
	public String toString() {
		return this.faceValue;
	}
}
