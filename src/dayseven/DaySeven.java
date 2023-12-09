package dayseven;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import util.FileReader;

public class DaySeven {
	
	private static void runPartOne() {
		Vector<String> lines = FileReader.readFile("./src/dayseven/input.txt");
		
		HashMap<String, Integer> cardRanking = new HashMap<String, Integer>();
		// A, K, Q, J, T, 9, 8, 7, 6, 5, 4, 3, or 2
		cardRanking.put("A", 14);
		cardRanking.put("K", 13);
		cardRanking.put("Q", 12);
		cardRanking.put("J", 11);
		cardRanking.put("T", 10);
		cardRanking.put("9", 9);
		cardRanking.put("8", 8);
		cardRanking.put("7", 7);
		cardRanking.put("6", 6);
		cardRanking.put("5", 5);
		cardRanking.put("4", 4);
		cardRanking.put("3", 3);
		cardRanking.put("2", 2);
		
		Vector<Hand> hands = new Vector<Hand>();
		lines.stream().forEach(line -> {
			String[] parts = line.trim().split(" ");
			List<String> cards = List.of(parts[0].split(""));
			String bid = parts[1];
			
			hands.add(
				new Hand(
					cards.stream().map(c -> {
						return new Card(cardRanking.get(c), c);
					}).collect(Collectors.toList()), 
					bid
				)
			);
		});
		
		Collections.sort(hands);
		
		int total = 0;
		for (int i = 0; i < hands.size(); i++) {
			Hand h = hands.get(i);
			int winning = h.getBid() * (i + 1);
			total += winning;
			System.out.println(h + " Winning : " + winning);
		}
		System.out.println("Answer: " + total);
	}
	
	private static void runPartTwo() {
		Vector<String> lines = FileReader.readFile("./src/dayseven/input.txt");
		
		HashMap<String, Integer> cardRanking = new HashMap<String, Integer>();
		// A, K, Q, J, T, 9, 8, 7, 6, 5, 4, 3, or 2
		cardRanking.put("A", 14);
		cardRanking.put("K", 13);
		cardRanking.put("Q", 12);
		cardRanking.put("J", -1);
		cardRanking.put("T", 10);
		cardRanking.put("9", 9);
		cardRanking.put("8", 8);
		cardRanking.put("7", 7);
		cardRanking.put("6", 6);
		cardRanking.put("5", 5);
		cardRanking.put("4", 4);
		cardRanking.put("3", 3);
		cardRanking.put("2", 2);
		
		Vector<AltHand> hands = new Vector<AltHand>();
		lines.stream().forEach(line -> {
			String[] parts = line.trim().split(" ");
			List<String> cards = List.of(parts[0].split(""));
			String bid = parts[1];
			
			hands.add(
				new AltHand(
					cards.stream().map(c -> {
						return new Card(cardRanking.get(c), c);
					}).collect(Collectors.toList()), 
					bid
				)
			);
		});
		
		Collections.sort(hands);
		
		int total = 0;
		for (int i = 0; i < hands.size(); i++) {
			AltHand h = hands.get(i);
			int winning = h.getBid() * (i + 1);
			total += winning;
			System.out.println(h + " Winning : " + winning);
		}
		System.out.println("Answer: " + total);
	
		// 254412181

	}

	
	public static void main(String[] args) {
		DaySeven.runPartOne();
		DaySeven.runPartTwo();
	}
}
