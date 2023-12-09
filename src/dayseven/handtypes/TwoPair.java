package dayseven.handtypes;

public class TwoPair extends AbstractHandType {
	private int rank = 2;
	
	@Override
	public int getRank() {
		return this.rank;
	}
	
	public String toString() {
		return "Two Pair";
	}
}
