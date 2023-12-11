package dayeleven;

import java.util.Vector;

import util.FileReader;

public class DayEleven {
	public static void main(String[] args) {
		DayEleven.runPartOne();
		DayEleven.runPartTwo();
	}
	
	public static void runPartTwo() {
		Vector<String> lines = FileReader.readFile("./src/dayeleven/input.txt");
		
		Starmap map = new Starmap();
		lines.stream().forEach(l -> {
			map.addRow(l);
		});
		map.scan();
		map.expand(999999);
		
		System.out.println("Answer: " + map.getDistanceSum());
		
		// Answer: 717878258016
	}
	
	public static void runPartOne() {
		Vector<String> lines = FileReader.readFile("./src/dayeleven/input.txt");
		
		Starmap map = new Starmap();
		lines.stream().forEach(l -> {
			map.addRow(l);
		});
		map.scan();
		map.expand(1);
		
		System.out.println("Answer: " + map.getDistanceSum());	
	}
}
