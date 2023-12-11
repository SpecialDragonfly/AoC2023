package dayeleven;

import java.util.Vector;

public class Starmap {
	private Vector<String> rows = new Vector<>();
	private Vector<Point> galaxies = new Vector<>();
	
	public void addRow(String row) {
		this.rows.add(row);
	}
	
	public void scan() {
		for (int rowIdx = 0; rowIdx < this.rows.size(); rowIdx++) {
			String row = this.rows.get(rowIdx);
			for (int colIdx = 0; colIdx < row.length(); colIdx++) {
				if (row.charAt(colIdx) != '.') {
					this.galaxies.add(new Point(rowIdx, colIdx));
				}
			}
		}
	}
	
	public String toString() {
		String x = "";
		for (int i = 0; i < this.rows.size(); i++) {
			x += this.rows.get(i) + "\n";
		}
		return x;
	}

	public void expand(int addition) {
		// Loop through all the rows.
		long currentAddition = 0;
		for (int rowIdx = 0; rowIdx < this.rows.size(); rowIdx++) {
			String row = this.rows.get(rowIdx);
			if (row.replaceAll("\\.", "").trim().length() == 0) {
				this.updateGalaxyRow(rowIdx + currentAddition, addition);
				currentAddition += addition;
			}
		}
		
		// Loop through all the columns.
		int rows = this.rows.size();
		int columns = this.rows.get(0).length();
		currentAddition = 0;
		for (int j = 0; j < columns; j++) {
			boolean found = false;
			for (int i = 0; i < rows; i++) {
				if (this.rows.get(i).charAt(j) != '.') {
					found = true;
					break;
				}
			}
			if (!found) {
				this.updateGalaxiesColumn(j + currentAddition, addition);
				currentAddition += addition;
			}
		}
	}
	
	public long getDistanceSum() {
		long sum = 0;
		for (int i = 0; i < this.galaxies.size() - 1; i++) {
			for (int j = i+1; j < this.galaxies.size(); j++) {
				long distance = this.galaxies.get(i).distanceTo(this.galaxies.get(j));
				sum += distance;
			}
		}
		
		return sum;
	}
	
	private void updateGalaxiesColumn(long minY, int addition) {
		for (int i = 0; i < this.galaxies.size(); i++) {
			if (this.galaxies.get(i).getY() > minY) {
				Point p = this.galaxies.get(i);
				this.galaxies.set(i, new Point(p.getX(), p.getY() + addition));
			}
		}
	}
	
	private void updateGalaxyRow(long minX, int addition) {
		for (int i = 0; i < this.galaxies.size(); i++) {
			if (this.galaxies.get(i).getX() > minX) {
				Point p = this.galaxies.get(i);
				this.galaxies.set(i, new Point(p.getX() + addition, p.getY()));
			}
		}
	}
}
