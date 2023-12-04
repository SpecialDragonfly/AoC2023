package daythree;

public class Part {

	private String value;
	protected String symbol = null;


	public Part(String value) {
		this.value = value;
	}
	
	public Part(String value, String symbol) {
		this.value = value;
		this.symbol = symbol;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public String getSymbol() {
		return this.symbol;
	}

	public boolean isGear() {
		return this.symbol.equals("*");
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public boolean isPart() {
		return this.symbol != null;
	}
	
	public String toString() {
		return this.value;
	}

}
