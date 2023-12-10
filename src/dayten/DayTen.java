package dayten;

import java.util.Vector;

import util.FileReader;

public class DayTen {
	public static void main(String[] args) {
		Vector<String> lines = FileReader.readFile("./src/dayten/test5.txt");
		AdvancedMap m = new AdvancedMap();
		lines.stream().forEach(l -> {
			m.addRow(l);
		});
		Vector<Tile> loop = m.getLoop();
		System.out.println("Size of map: " + m.getSize());
		System.out.println("Size of loop: " + (loop.size() - 1));
		System.out.println("Remaining: " + ((m.getSize() - loop.size()) + 1));
		m.show();
		System.out.println("Area: " + m.area());
		System.out.println("Picks Theorum: " + m.enclosedPointCount());
		
		// 580 too high
	}
	
	public static void runPartOne() {
		Vector<String> lines = FileReader.readFile("./src/dayten/input.txt");
		Map m = new Map();
		lines.stream().forEach(l -> {
			m.addRow(l);
		});
		
		Vector<String> loop = m.getLoop();
		int max = (loop.size() - 1)/2;
		System.out.println("Answer: " + max);		
	}
}
