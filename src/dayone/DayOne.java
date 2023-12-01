package dayone;

import java.util.HashMap;
import java.util.Vector;

import util.FileReader;

public class DayOne {
	public static void main(String[] args) {
		Vector<String> data = FileReader.readFile("./src/dayone/input.txt");
		HashMap<String, Integer> values = new HashMap<>();
		values.put("1", 1);
		values.put("2", 2);
		values.put("3", 3);
		values.put("4", 4);
		values.put("5", 5);
		values.put("6", 6);
		values.put("7", 7);
		values.put("8", 8);
		values.put("9", 9);
		values.put("one", 1);
		values.put("two", 2);
		values.put("three", 3);
		values.put("four", 4);
		values.put("five", 5);
		values.put("six", 6);
		values.put("seven", 7);
		values.put("eight", 8);
		values.put("nine", 9);
		int output = data.stream().map(
			line -> Search.exec(line, values)
		).reduce(0, (partial, current) -> partial + current);
		
		System.out.println("Output: " + output);
	}
}
