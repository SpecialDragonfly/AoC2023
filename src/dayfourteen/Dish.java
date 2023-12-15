package dayfourteen;

import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

public class Dish {
	private Vector<Vector<Rock>> rows = new Vector<>();
	
	public void addRow(String row) {
		Vector<Rock> r = new Vector<>();
		Arrays.stream(row.split("")).forEach(o -> {
			r.add(new Rock(o));
		});
		this.rows.add(r);
	}
	
	public int numberOfRocks(int row)
	{
		return this.rows.get(row).stream().filter(s -> s.equals("O")).toArray().length;
	}
	
	public void spin(int iterations) {
		for (int i = 0; i < iterations; i++) {
			// All rocks up
			this.moveUp();
			
			// All rocks left
			this.moveLeft();
			
			// All rocks down
			this.moveDown();
			
			// All rocks right
			this.moveRight();
			System.out.println("After iteration " + i + " load is " + this.getLoad());
		}
	}
	
	private void moveLeft() {
		for (int i = 0; i < this.rows.size(); i++) {
			Vector<Rock> row = this.moveRowLeft(i, this.rows.get(i));
			if (!row.toString().equals(this.rows.get(i).toString())) {
				this.rows.set(i, row);
			}
		}
	}
	
	private Vector<Rock> moveRowLeft(int i, Vector<Rock> row) {
		boolean moved = true;
		if (this.numberOfRocks(i) == 0) {
			return row;
		}
		while (moved) {
			moved = false;
			for (int j = row.indexOf(new Rock("O")) - 1; j < row.size() - 1; j++) {
				if (j < 0) {
					// Our first element was a rock, so continue;
					continue;
				}
				// Do nothing.
				if (row.get(j).equals(".") && row.get(j + 1).equals("O")) {
					row.set(j, row.get(j+1));
					row.set(j+1, new Rock("."));
					moved = true;
				}
			}
		}
		return row;
	}
	
	// This isn't working. Move left on the reversed row appears to do nothing.
	private void moveRight() {
		for (int i = 0; i < this.rows.size(); i++) {
			Vector<Rock> row = this.rows.get(i);
			Collections.reverse(row);
			row = this.moveRowLeft(i, row);
			Collections.reverse(row);
		}
	}
	
	private void moveUp() {
		boolean repeatNeeded = true;
		while (repeatNeeded) {
			repeatNeeded = false;
			for (int i = 1; i < this.rows.size(); i++) {
				Vector<Rock> currentRow = this.rows.get(i);
				Vector<Rock> previousRow = this.rows.get(i-1);
				if (this.numberOfRocks(i) > 0) {
					// Move rocks
					boolean rockMoved = false;
					for (int j = 0; j < currentRow.size(); j++) {
						// Can we move the rock?
						if (currentRow.get(j).equals("O") && previousRow.get(j).equals(".")) {
							previousRow.set(j, currentRow.get(j));
							currentRow.set(j, new Rock("."));
							rockMoved = true;
						}
					}
					if (rockMoved) {
						this.rows.set(i, currentRow);
						this.rows.set(i-1, previousRow);
						repeatNeeded = true;
					}
				}
			}
		}		
	}
	
	private void moveDown() {
		boolean repeatNeeded = true;
		while (repeatNeeded) {
			repeatNeeded = false;
			for (int i = this.rows.size() - 1; i > 0; i--) {
				Vector<Rock> currentRow = this.rows.get(i);
				Vector<Rock> previousRow = this.rows.get(i-1);
				if (this.numberOfRocks(i - 1) > 0) {
					// Move rocks
					boolean rockMoved = false;
					for (int j = 0; j < currentRow.size(); j++) {
						// Can we move the rock?
						if (previousRow.get(j).equals("O") && currentRow.get(j).equals(".")) {
							currentRow.set(j, previousRow.get(j));
							previousRow.set(j, new Rock("."));
							rockMoved = true;
						}
					}
					if (rockMoved) {
						this.rows.set(i, currentRow);
						this.rows.set(i-1, previousRow);
						repeatNeeded = true;
					}
				}
			}
		}		
	}
	
	public void rebalance() {
		boolean repeatNeeded = true;
		while (repeatNeeded) {
			repeatNeeded = false;
			for (int i = 1; i < this.rows.size(); i++) {
				Vector<Rock> currentRow = this.rows.get(i);
				Vector<Rock> previousRow = this.rows.get(i-1);
				if (this.numberOfRocks(i) > 0) {
					// Move rocks
					boolean rockMoved = false;
					for (int j = 0; j < currentRow.size(); j++) {
						// Can we move the rock?
						if (currentRow.get(j).equals("O") && previousRow.get(j).equals(".")) {
							previousRow.set(j, currentRow.get(j));
							currentRow.set(j, new Rock("."));
							rockMoved = true;
						}
					}
					if (rockMoved) {
						this.rows.set(i, currentRow);
						this.rows.set(i-1, previousRow);
						repeatNeeded = true;
					}
				}
			}
		}
	}
	
	public int getLoad() {
		int sum = 0;
		for (int i = 0; i < this.rows.size(); i++) {
			int distance = this.rows.size() - i;
			sum += (distance * this.numberOfRocks(i));
		}
		
		return sum;
	}
	
	public String toString() {
		String x = "";
		for (int i = 0; i < this.rows.size(); i++) {
			for (int j = 0; j < this.rows.get(i).size(); j++) {
				x += this.rows.get(i).get(j).toString();
			}
			x += "\n";
		}
		return x;
	}
}
