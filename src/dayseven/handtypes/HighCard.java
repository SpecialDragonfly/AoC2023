package dayseven.handtypes;

public class HighCard extends AbstractHandType {
	private int rank = 0;
	
	@Override
	public int getRank() {
		return this.rank;
	}
	
	public String toString() {
		return "Highcard";
	}
}
