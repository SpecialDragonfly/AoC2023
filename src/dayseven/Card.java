package dayseven;

public class Card implements Comparable<Card> {
	
	private int ranking;
	private String faceValue;

	public Card(int ranking, String faceValue) {
		this.ranking = ranking;
		this.faceValue = faceValue;
	}
	
	@Override
	public int compareTo(Card o) {
		if (this.ranking == o.getRanking()) {
			return 0;
		}
		return this.ranking > o.getRanking() ? 1 : -1;
	}

	public int getRanking() {
		return ranking;
	}

	public String getFaceValue() {
		return faceValue;
	}
	
	public String toString() {
		return this.faceValue;
	}
}
