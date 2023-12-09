package dayseven.handtypes;

public class OnePair extends AbstractHandType {
	private int rank = 1;
	
	@Override
	public int getRank() {
		return this.rank;
	}
	
	public String toString() {
		return "One Pair";
	}
}
