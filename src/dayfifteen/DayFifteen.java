package dayfifteen;

import java.util.HashMap;
import java.util.Vector;

import util.FileReader;

public class DayFifteen {
	
	public static void main(String[] args) {
		//DayFifteen.runPartOne();
		DayFifteen.runPartTwo();
	}
	
	private static void runPartTwo() {
		Vector<String> lines = FileReader.readFile("./src/dayfifteen/input.txt");
		// There is only 1 line here...
		String line = lines.get(0);
		String[] instructions = line.split(",");
		HashMap<Long, Box> boxes = new HashMap<Long, Box>();
		for (int i = 0; i < instructions.length; i++) {
			String operation = instructions[i].indexOf("=") >= 0 ? "=" : "-";
			String[] parts = instructions[i].split("[=-]");
			long boxNumber = Box.getHash(parts[0]);
			if (!boxes.containsKey(boxNumber)) {
				boxes.put(boxNumber, new Box());
			}
			if (operation.equals("=")) {
				Lens lens = new Lens(parts[0], Integer.valueOf(parts[1]));
				boxes.get(boxNumber).addLens(lens);
			} else {
				boxes.get(boxNumber).removeLens(parts[0]);
			}
		}	
		System.out.println(boxes.keySet()
			.stream()
			.reduce((long) 0, (acc, key) -> 
				acc + (key+1)*boxes.get(key).getFusingPower()
			)
		);
		// Answer: 268497		
	}
	
	private static void runPartOne() {
		// use test.txt to check that the HASH function is working.
		
		Vector<String> lines = FileReader.readFile("./src/dayfifteen/input.txt");
		// There is only 1 line here...
		String line = lines.get(0);
		long sum = 0;
		long current = 0;
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == ',') {
				sum += current;
				current = 0;
				continue;
			}
			current += ((int)line.charAt(i));
			current *= 17;
			current %= 256;
		}
		sum += current;
		
		System.out.println(sum);
	}
}
