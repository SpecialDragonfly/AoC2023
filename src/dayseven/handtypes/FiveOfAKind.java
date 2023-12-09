package dayseven.handtypes;

public class FiveOfAKind extends AbstractHandType {
	protected int rank = 6;
	
	@Override
	public int getRank() {
		return this.rank;
	}
	
	public String toString() {
		return "Five of a kind";
	}
}
