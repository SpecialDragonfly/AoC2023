package dayfifteen;

public class Lens {
	private String label;
	private int focalLength;
	
	public Lens(String label, int focalLength) {
		this.label = label;
		this.focalLength = focalLength;
	}

	public boolean equals(String o) {
		return this.label.equals(o);
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
		return this.label.equals(((Lens)obj).getLabel());
	}

	public String getLabel() {
		return this.label;
	}
	
	public int hashCode() {
		return this.label.hashCode();
	}
	
	public String toString() {
		return "[" + this.label + " " + this.focalLength + "]";
	}

	public int getFocalLength() {
		return this.focalLength;
	}
}
