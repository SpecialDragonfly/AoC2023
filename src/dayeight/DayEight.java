package dayeight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

import util.FileReader;

public class DayEight {
	public static void runPartOne() {
		Vector<String> lines = FileReader.readFile("./src/dayeight/input.txt");
		
		HashMap<String, Node> maps = new HashMap<>();
		Vector<String> path = new Vector<>();
		Collections.addAll(path, lines.get(0).split(""));
		lines.stream().forEach(l -> {
			if (l.contains("=")) {
				String[] parts = l.split(" = ");
				String[] leftRight = parts[1].substring(1, parts[1].length() - 1).split(", ");
				maps.put(parts[0].trim(), new Node(leftRight[0], leftRight[1]));
			}
		});
		
		Node map = maps.get("AAA");
		int steps = 0;
		int pathIndex = 0;
		while (true) {
			if (path.get(pathIndex).equals("L")) {
				if (map.getLeft().equals("ZZZ")) {
					steps++;
					break;
				}
				map = maps.get(map.getLeft());
			} else {
				if (map.getRight().equals("ZZZ")) {
					steps++;
					break;
				}
				map = maps.get(map.getRight());
			}
			steps++;
			pathIndex++;
			if (pathIndex >= path.size()) {
				pathIndex = 0;
			}
		}
		System.out.println("Part 1: " + steps);		
	}
	
	public static void runPartTwo() {
		Vector<String> lines = FileReader.readFile("./src/dayeight/input.txt");
		
		HashMap<String, KeyedNode> maps = new HashMap<>();
		Vector<String> path = new Vector<>();
		Collections.addAll(path, lines.get(0).split(""));
		ArrayList<KeyedNode> startingNodes = new ArrayList<>();
		lines.stream().forEach(l -> {
			if (l.contains("=")) {
				String[] parts = l.split(" = ");
				String[] leftRight = parts[1].substring(1, parts[1].length() - 1).split(", ");
				KeyedNode n = new KeyedNode(parts[0].trim(), leftRight[0], leftRight[1]);
				if (parts[0].endsWith("A")) {
					startingNodes.add(n);
				}
				maps.put(parts[0].trim(), n);
			}
		});

		Vector<Integer> totalSteps = new Vector<>();

		for (int i = 0; i < startingNodes.size(); i++) {
			int steps = 0;
			int pathIndex = 0;
			KeyedNode n = startingNodes.get(i);
			while (true) {
				steps++;
				String nextKey = path.get(pathIndex).equals("L") ? n.getLeft() : n.getRight();

				n = maps.get(nextKey);
				startingNodes.set(i, n);
				pathIndex++;
				// If we've reached the end of the path
				if (pathIndex >= path.size()) {
					// Have we reached an exit condition?
					if (nextKey.endsWith("Z")) {
						totalSteps.add(steps);
						break;													
					}
					pathIndex = 0;
				}
			}
		}
		
		long lcm = totalSteps.get(0);
		for (int i = 1; i < totalSteps.size(); i++) {
			lcm = lcm(lcm, totalSteps.get(i));
		}
		System.out.println("Part 2: " + lcm);
		
		// Answer: 13740108158591				
	}
	
	public static void main(String[] args) {
		DayEight.runPartOne();
		DayEight.runPartTwo();
	}
	
	public static long gcd(long number1, long number2) {
	    if (number1 == 0 || number2 == 0) {
	        return number1 + number2;
	    }
        long absNumber1 = Math.abs(number1);
        long absNumber2 = Math.abs(number2);
        long biggerValue = Math.max(absNumber1, absNumber2);
        long smallerValue = Math.min(absNumber1, absNumber2);
        return gcd(Math.floorMod(biggerValue, smallerValue), smallerValue);
	}
	
	public static long lcm(long number1, long number2) {
	    if (number1 == 0 || number2 == 0) {
	        return 0;
	    }
        return Math.abs(number1 * number2) / gcd(number1, number2);
	}
}
