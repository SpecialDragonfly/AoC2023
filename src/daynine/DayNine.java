package daynine;

import java.util.List;
import java.util.Vector;

import util.FileReader;

public class DayNine {
	public static void main(String[] args) {
		Vector<String> lines = FileReader.readFile("./src/daynine/input.txt");
		Vector<Sequence> sequences = new Vector<>();
		lines.stream().forEach(l -> {
			Vector<Long> values = new Vector<>();
			List.of(l.split(" ")).stream().forEach(number -> {
				values.add(Long.valueOf(number));
			});
			sequences.add(new Sequence(values));
		});
		
		System.out.println("Part 1: " + sequences.stream()
			.map(s -> s.getNextNumberInSequence())
			.reduce((long) 0, (a, b) -> a + b)
		);
		
		System.out.println("Part 2: " + sequences.stream()
			.map(s -> s.getPreviousNumberInSequence())
			.reduce((long) 0, (a, b) -> a + b));
	}
}
