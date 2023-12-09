package dayseven.handtypes;

public class FourOfAKind extends AbstractHandType {
	private int rank = 5;
	
	@Override
	public int getRank() {
		return this.rank;
	}
	
	public String toString() {
		return "Four of a kind";
	}
}
