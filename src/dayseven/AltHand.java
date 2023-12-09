package dayseven;

import java.util.Collection;
import java.util.Vector;

import dayseven.handtypes.HandTypeFactory;
import dayseven.handtypes.HandTypeInterface;

public class AltHand implements Comparable<AltHand> {
	public Vector<Card> cards = new Vector<Card>();
	HandTypeInterface handtype;
	private String bid;
	
	public AltHand(Collection<Card> cards, String bid) {
		this.bid = bid;
		this.cards.addAll(cards);
		// Then get type.
		this.handtype = HandTypeFactory.getTypeWithJoker(this.cards);
	}
	
	public HandTypeInterface getHandType() {
		return this.handtype;
	}

	@Override
	public int compareTo(AltHand o) {
		if (this.handtype.getRank() > o.handtype.getRank()) {
			return 1;
		}
		if (this.handtype.getRank() < o.handtype.getRank()) {
			return -1;
		}
		// Else check each card in turn.
		for (int i = 0; i < this.cards.size(); i++) {
			if (this.cards.get(i).compareTo(o.cards.get(i)) == 1) {
				return 1;
			}
			if (this.cards.get(i).compareTo(o.cards.get(i)) == -1) {
				return -1;
			}
			// If the cards are identical, move to the next card.
		}
		// Getting here would mean both hands are identical.
		return 0;
	}
	
	public String toString() {
		return this.cards.toString() + " -> " + this.handtype.toString();
	}

	public int getBid() {
		return Integer.valueOf(this.bid);
	}
}
