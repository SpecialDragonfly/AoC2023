package daythree;

public class Gear extends Part {
	private int symbolRow;
	private int symbolColumn;
	
	public Gear(String value) {
		super(value);
	}
	
	public Gear(Part part) {
		super(part.getValue());
	}

	public boolean isGear() {
		return this.symbol != null && this.symbol.equals("*");
	}
	
	public void setSymbol(String symbol, int symbolRow, int symbolColumn) {
		this.symbol = symbol;
		this.symbolRow = symbolRow;
		this.symbolColumn = symbolColumn;
	}
	
	public int getRow() {
		return this.symbolRow;
	}
	
	public int getColumn() {
		return this.symbolColumn;
	}
}
