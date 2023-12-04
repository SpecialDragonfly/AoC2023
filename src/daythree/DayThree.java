package daythree;

import java.awt.Point;
import java.util.HashMap;
import java.util.Vector;
import util.FileReader;

public class DayThree {
	public static void main(String[] args) {
		//DayThree.runPartOne();
		DayThree.runPartTwo();
	}
	
	public static void runPartOne() {
		Vector<String> lines = FileReader.readFile("./src/daythree/input.txt");
		
		Engine engine = new Engine(lines);
		
		System.out.println(engine.getParts().stream()
			.filter(p -> p.isPart())
			.mapToInt(p -> Integer.valueOf(p.getValue()))
			.reduce(0, (acc, part) -> acc + part)
		);
		
		// Part 1: 551094		
	}
	
	public static void runPartTwo()
	{
		Vector<String> lines = FileReader.readFile("./src/daythree/input.txt");
		
		Engine engine = new Engine(lines);		
		Vector<Gear> gears = engine.getGears();
		HashMap<Point, Vector<Gear>> points = new HashMap<>();
		for (Gear gear : gears) {
			if (gear.isGear()) {
				Vector<Gear> existing = points.getOrDefault(new Point(gear.getRow(), gear.getColumn()), new Vector<Gear>());
				existing.add(gear);
				points.put(new Point(gear.getRow(), gear.getColumn()), existing);				
			}
		}
		
		// Take all the values from the hashmap which are vectors<gear>
		// for each vector<gear> map it to an integer value and then multiply them together.
		// We then have a collection of integers, that we add together.
		System.out.println(
			points.values().stream().filter(v -> v.size() > 1)
				.mapToInt(v -> v.stream()
					.mapToInt(w -> Integer.valueOf(w.getValue()))
					.reduce(1, (acc, z) -> acc * z)
				)
				.reduce(0, (acc, zz) -> acc + zz)
		);
		
		// Part 2: 80179647
	}
}
