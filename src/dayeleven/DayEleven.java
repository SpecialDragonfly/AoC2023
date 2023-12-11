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
		System.out.println(map);
		map.scan();
		map.expand(999999);
		
		Vector<Point> galaxies = map.getGalaxies();
		long sum = 0;
		for (int i = 0; i < galaxies.size() - 1; i++) {
			for (int j = i+1; j < galaxies.size(); j++) {
				long distance = galaxies.get(i).distanceTo(galaxies.get(j));
				sum += distance;
			}
		}
		System.out.println("Answer: " + sum);
		
		// Answer: 717878258016
	}
	
	public static void runPartOne() {
		Vector<String> lines = FileReader.readFile("./src/dayeleven/test.txt");
		
		Starmap map = new Starmap();
		lines.stream().forEach(l -> {
			map.addRow(l);
		});
		System.out.println(map);
		map.scan();
		map.expand(1);
		
		Vector<Point> galaxies = map.getGalaxies();
		long sum = 0;
		for (int i = 0; i < galaxies.size() - 1; i++) {
			for (int j = i+1; j < galaxies.size(); j++) {
				long distance = galaxies.get(i).distanceTo(galaxies.get(j));
				sum += distance;
			}
		}
		System.out.println("Answer: " + sum);		
	}
}
