package dayten;

import java.util.Vector;

import util.FileReader;

public class DayTen {
	public static void main(String[] args) {
		Vector<String> lines = FileReader.readFile("./src/dayten/input.txt");
		AdvancedMap m = new AdvancedMap();
		lines.stream().forEach(l -> {
			m.addRow(l);
		});
		m.show();
		m.getLoop();
		System.out.println(m.findEnclosed());
		
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
