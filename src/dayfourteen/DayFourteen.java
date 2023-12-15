package dayfourteen;

import java.util.Vector;
import util.FileReader;

public class DayFourteen {
	public static void main(String[] args) {
		Vector<String> lines = FileReader.readFile("./src/dayfourteen/input.txt");
		Dish d = new Dish();
		lines.stream().forEach(l -> {
			d.addRow(l);
		});
		System.out.println(d + "\n" + d.getLoad());
		d.spin(10000);
		
		// For part 2, run the spin for 10000 cycles and then a repeating pattern will occur.
		// Find when the repeating pattern starts, subtract that from the requested answer and modulo the number of steps
		// in the cycle.
		// In my case 1000000000 - 97 % 11 = 2, so I needed the 2nd value in the cycle.
		// Answer: 96105
	}
}
