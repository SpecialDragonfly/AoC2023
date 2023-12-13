package daytwelve;

import java.util.Vector;

import util.FileReader;

public class DayTwelve {
	public static void main(String[] args) {
		Vector<String> lines = FileReader.readFile("./src/daytwelve/test2.txt");
		Vector<Spring> springs = new Vector<>();
		lines.stream().forEach(l -> {
			String[] parts = l.split(" ");
			springs.add(new Spring(parts[0], new Condition(parts[1].split(","))));
		});
		springs.stream().forEach(s -> {
			System.out.println(s + " : " + s.findPermutations());
		});
	}
}
