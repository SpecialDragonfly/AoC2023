package dayseven.handtypes;

import java.util.HashMap;
import java.util.Vector;

import dayseven.Card;

public class HandTypeFactory {
	private static HashMap<String, Integer> numbers = new HashMap<>();
	
	public static HandTypeInterface getTypeWithJoker(Vector<Card> cards) {
		HandTypeFactory.populate(cards);
		// Do we have any jokers?
		if (numbers.containsKey("J")) {
			// How many do we have?
			// If we have 1 joker - then the lowest hand is a pair
			// If we have 2 jokers - then the lowest hand is 3 of a kind
			// If we have 3 jokers - then the lowest hand is 4 of a kind
			// If we have 4 jokers - then the lowest hand is 5 of a kind
			int quantity = numbers.get("J");
			numbers.remove("J"); // Don't consider J as a valid 'number' for the purposes of jokers.
			if (quantity == 5) {
				return new FiveOfAKind();
			}
			if (quantity == 4) {
				return new FiveOfAKind();
			}
			if (quantity == 3) {
				if (numbers.containsValue(2)) {
					return new FiveOfAKind();
				}
				return new FourOfAKind();
			}
			// So quantity is now 2 or 1
			if (quantity == 2) {
				if (numbers.containsValue(3)) {
					return new FiveOfAKind();	
				}
				if (numbers.containsValue(2)) {
					return new FourOfAKind();
				}
				// We can't return a full house with 2 jokers, because the 3 would become 5.
				// Alternatively the 4 would be 5 of a kind.
				return new ThreeOfAKind();
			} else if (quantity == 1) {
				if (numbers.containsValue(4)) {
					return new FiveOfAKind();
				}
				if (numbers.containsValue(3)) {
					return new FourOfAKind();
				}
				if (numbers.containsValue(2)) {
					// How many do we have?
					if (numbers.values().stream().filter(v -> v == 2).toList().size() == 2) {
						return new FullHouse();
					} else {
						return new ThreeOfAKind();
					}
				}
			}
			return new OnePair();
		}
		return HandTypeFactory.get();
	}
	
	public static HandTypeInterface getType(Vector<Card> cards) {
		HandTypeFactory.populate(cards);
		return HandTypeFactory.get();
	}
	
	private static void populate(Vector<Card> cards) {
		numbers.clear();
		cards.forEach(c -> {
			int currentValue = numbers.getOrDefault(c.getFaceValue(), 0);
			numbers.put(c.getFaceValue(), currentValue + 1);
		});
	}
	
	private static HandTypeInterface get() {
		if (numbers.containsValue(5)) {
			return new FiveOfAKind();
		}
		if (numbers.containsValue(4)) {
			return new FourOfAKind();
		}
		if (numbers.containsValue(3) && numbers.containsValue(2)) {
			return new FullHouse();
		}
		if (numbers.containsValue(3)) {
			return new ThreeOfAKind();
		}
		if (numbers.containsValue(2)) {
			// one pair or two?
			if (numbers.values().stream().filter(v -> v == 2).toList().size() == 2) {
				return new TwoPair();
			} else {
				return new OnePair();
			}
		}
		return new HighCard();		
	}
}
