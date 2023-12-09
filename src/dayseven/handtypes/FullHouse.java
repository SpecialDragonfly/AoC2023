package dayseven.handtypes;

public class FullHouse extends AbstractHandType {
	private int rank = 4;
	
	@Override
	public int getRank() {
		return this.rank;
	}
	
	public String toString() {
		return "Full House";
	}
}
