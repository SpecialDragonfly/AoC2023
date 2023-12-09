package dayseven.handtypes;

import java.util.HashMap;
import java.util.Vector;

import dayseven.Card;

public class HandTypeFactory {
	private static HashMap<String, Integer> numbers = new HashMap<>();
	public static HandTypeInterface getType(Vector<Card> cards) {
		numbers.clear();
		cards.forEach(c -> {
			int currentValue = numbers.getOrDefault(c.getFaceValue(), 0);
			numbers.put(c.getFaceValue(), currentValue + 1);
		});
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
