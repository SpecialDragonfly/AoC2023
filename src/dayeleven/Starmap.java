package dayeleven;

import java.util.Vector;

public class Starmap {
	private Vector<String> rows = new Vector<>();
	private Vector<Point> galaxies = new Vector<>();
	
	public void addRow(String row) {
		this.rows.add(row);
	}
	
	public void expand() {
		int maxRows = this.rows.size();
		for (int i = 0; i < maxRows; i++) {
			String row = this.rows.get(i);
			if (row.replaceAll("\\.", "").trim().length() == 0) {
				this.rows.insertElementAt(row, i);
				i++;
				maxRows++;
			}
		}
		int rows = this.rows.size();
		int columns = this.rows.get(0).length();
		for (int j = 0; j < columns; j++) {
			boolean found = false;
			for (int i = 0; i < rows; i++) {
				if (this.rows.get(i).charAt(j) != '.') {
					found = true;
					break;
				}
			}
			if (!found) {
				// This column needs to expand.
				for (int i = 0; i < rows; i++) {
					String currentRow = this.rows.get(i);
					currentRow = currentRow.substring(0, j) + "." + currentRow.substring(j);
					this.rows.set(i, currentRow);	
				}
				j++; // We've just added a column behind us, so our index moves on one.
				columns++; // We now also have more columns.
			}
		}
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
	
	public Vector<Point> getGalaxies() {
		return this.galaxies;
	}
	
	public String toString() {
		String x = "";
		for (int i = 0; i < this.rows.size(); i++) {
			x += this.rows.get(i) + "\n";
		}
		return x;
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

	public void expand(int addition) {
		// Loop through all the rows.
		// Every time we duplicate a row, all the points after that row get +1000000 to their X coord
		long currentAddition = 0;
		for (int rowIdx = 0; rowIdx < this.rows.size(); rowIdx++) {
			String row = this.rows.get(rowIdx);
			if (row.replaceAll("\\.", "").trim().length() == 0) {
				this.updateGalaxyRow(rowIdx + currentAddition, addition);
				currentAddition += addition;
			}
		}
		
		// Loop through all the columns.
		// Every time we duplicate a column, all the points after that column get +1000000 to their Y coord
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
}
