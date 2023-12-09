package dayseven.handtypes;

public abstract class AbstractHandType implements HandTypeInterface, Comparable<HandTypeInterface>{
	@Override
	public int compareTo(HandTypeInterface o) {
		if (o.getRank() == this.getRank()) {
			return 0;
		}
		return this.getRank() > o.getRank() ? 1 : -1;
	}
}
