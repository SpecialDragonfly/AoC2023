package dayfifteen;

import java.util.LinkedList;

public class Box {
	LinkedList<Lens> slots = new LinkedList<>();
	public Box() {
	}
	
	public void addLens(Lens lens) {
		// Always goes at the end of the box.
		if (this.slots.contains(lens)) {
			this.slots.set(this.slots.indexOf(lens), lens);
		} else {
			this.slots.addLast(lens);
		}
	}
	
	public static long getHash(String instruction) {
		long current = 0;
		for (int i = 0; i < instruction.length(); i++) {
			current += (int)instruction.charAt(i);
			current *= 17;
			current %= 256;
		}
		return current;
	}

	public void removeLens(String label) {
		// If the box doesn't contain the label, move on.
		if (!this.slots.contains(new Lens(label, 0))) {
			return;
		}
		
		// This removes the element and automatically moves everything forward.
		this.slots.removeIf(l -> l.equals(label));
	}
	
	public String toString() {
		if (this.slots.isEmpty()) {
			return "";
		}
		
		String x = "";
		for (int i = 0; i < this.slots.size(); i++) {
			x += this.slots.get(i).toString();
		}
		return x;
	}
	
	public long getFusingPower() {
		long sum = 0;
		for (int i = 0; i < this.slots.size(); i++) {
			sum += ((i+1)*this.slots.get(i).getFocalLength());
		}
		
		return sum;
	}
}
