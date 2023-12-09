package dayseven.handtypes;

public class ThreeOfAKind extends AbstractHandType {
	private int rank = 3;
	
	@Override
	public int getRank() {
		return this.rank;
	}
	
	public String toString() {
		return "Three of a Kind";
	}
}
