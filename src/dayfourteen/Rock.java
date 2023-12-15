package dayfourteen;

public class Rock {

	private String o;

	public Rock(String o) {
		this.o = o;
	}
	
	public String toString() {
		return this.o;
	}
	
	public boolean equals(String o) {
		return this.o.equals(o);
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
		return this.o.equals(((Rock)obj).getType());
	}
	
	public String getType() {
		return this.o;
	}

	public int hashCode() {
		switch (this.o) {
		case "O": return 1;
		case ".": return 2;
		case "#": return 4;
		}
		return 0;
	}
}
