package dayfour;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import util.FileReader;

public class DayFour {
	public static void main(String[] args) {
		DayFour.runPartOne();
		DayFour.runPartTwo();
	}
	
	private static Set<String> getWinningNumbers(String line) {
		String[] parts = line.split("\\|");
		List<String> winning = Arrays.stream(parts[0].trim().split(" ")).filter(x -> !x.trim().equals("")).toList();
		List<String> chosen = Arrays.stream(parts[1].trim().split(" ")).filter(x -> !x.trim().equals("")).toList();
		Set<String> set1 = new HashSet<String>(winning);
		Set<String> set2 = new HashSet<String>(chosen);
		set1.retainAll(set2);
		return set1;
	}
	
	public static void runPartTwo() {
		Vector<String> lines = FileReader.readFile("./src/dayfour/input.txt");
		HashMap<Integer, Integer> timesWon = new HashMap<>();
		lines.stream().forEach(line -> {
			line = line.substring(5);
			String[] cardParts = line.split(":");
			int cardNumber = Integer.valueOf(cardParts[0].trim());
			int duplicatedCards = DayFour.getWinningNumbers(cardParts[1]).size(); // How many cards are duplicated?
			int numberOfTimesDuplicated = 1 + timesWon.getOrDefault(Integer.valueOf(cardNumber), 0);
			
			for (int i = 1; i <= duplicatedCards; i++) {
				timesWon.put(cardNumber + i, timesWon.getOrDefault(cardNumber + i, 0) + numberOfTimesDuplicated);
			}
		});
		System.out.println(timesWon.values().stream().reduce(0, (acc, val) -> acc + val) + lines.size());		
	}
	
	public static void runPartOne() {
		Vector<String> lines = FileReader.readFile("./src/dayfour/input.txt");
		
		// For each line
		// Remove 8 characters
		// Split the remaining on a |
		// The first half is the winning numbers
		// The second half is the chosen numbers
		// Array intersect the two
		// Raise 2 ^ count(intersection) - 1
		// Sum the results.
		
		Vector<Integer> values = new Vector<>();
		lines.stream().forEach(line -> {
			line = line.substring(8);
			int winningNumbersCount = DayFour.getWinningNumbers(line).size();
			int a = 0;
			if (winningNumbersCount > 0) {
				a = 1 << (winningNumbersCount - 1);
			}
			
			values.add(a);
		});
		System.out.println(values.stream().reduce(0, (acc, val) -> acc + val));
	}
}
