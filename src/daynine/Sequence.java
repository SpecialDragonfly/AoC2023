package daynine;

import java.util.Vector;

public class Sequence {

	private Vector<Long> values = new Vector<>(); 
	private Sequence diffSequence = null;
	
	public Sequence(Vector<Long> values2) {
		System.out.println("Creating a sequence " + values2);
		this.values = values2;
		Vector<Long> differences = this.getDifferences();
		if (differences != null) {
			this.diffSequence = new Sequence(differences);	
		}
	}
	
	public long getNextNumberInSequence() {
		if (this.diffSequence == null) {
			return this.values.lastElement();
		}

		return this.values.lastElement() + this.diffSequence.getNextNumberInSequence();
	}
	
	public long getPreviousNumberInSequence() {
		if (this.diffSequence == null) {
			return this.values.firstElement();
		}
		return this.values.firstElement() - this.diffSequence.getPreviousNumberInSequence();
	}
	
	private Vector<Long> getDifferences() {
		Vector<Long> differences = new Vector<>();
		for (int i = 1; i < this.values.size(); i++) {
			differences.add(this.values.get(i) - this.values.get(i - 1));
		}
		
		// If we have any non-zero values, if we have any, return the list of differences
		if (differences.stream().filter(n -> n != 0).toList().size() > 0) {
			return differences;
		}
		
		// If we're here - we only have 0 values.
		return null;
	}

}
