package dayten;

import java.util.Vector;

public class Map {
	Vector<String> rows = new Vector<>();
	int startRow = 0;
	int startColumn = 0;
	
	public Map() {
		
	}
	
	public void addRow(String row) {
		this.rows.add(row);
		if (row.contains("S")) {
			this.startRow = this.rows.size() - 1;
			this.startColumn = row.indexOf("S");
		}
	}
	
	public Vector<String> getLoop() {
		Vector<String> loop = new Vector<>();
		
		int row = this.startRow;
		int column = this.startColumn;
		String fromDirection = "X";
		
		String tile = this.getTile(row, column);
		loop.add(tile);
		while (true) {
			String direction = this.getAvailableDirections(row, column, fromDirection);
			if (direction.equals("N")) {
				row = row - 1;
				fromDirection = "S";
			}
			if (direction.equals("S")) {
				row = row + 1;
				fromDirection = "N";
			}
			if (direction.equals("E")) {
				column = column + 1;
				fromDirection = "W";
			}
			if (direction.equals("W")) {
				column = column - 1;
				fromDirection = "E";
			}
			tile = getTile(row, column);
			loop.add(tile);
			if (tile.equals("S") || direction.equals("X")) {
				break;
			}
		}
		
		return loop;
	}
	
	private String getTile(int row, int column) {
		return this.rows.get(row).substring(column, column+1);
	}
	
	private String getAvailableDirections(int row, int column, String fromDirection) {
		String tile = this.getTile(row, column);
		if (tile.equals("S")) {
			boolean north = this.lookNorth(row, column);
			boolean south = this.lookSouth(row, column);
			boolean east = this.lookEast(row, column);
			boolean west = this.lookWest(row, column);
			// look north for |, 7, F
			// look south for |, L, J
			// look east for -, J, 7
			// look west for L, F
			if (north) {
				return "N";
			}
			if (south) {
				return "S";
			}
			if (east) {
				return "E";
			}
			if (west) {
				return "W";
			}
		} else if (tile.equals("|")) {
			// look north for |, 7, F
			// look south for |, L, J
			boolean north = this.lookNorth(row, column);
			boolean south = this.lookSouth(row, column);
			if (north && !fromDirection.equals("N")) {
				return "N";
			}
			if (south && !fromDirection.equals("S")) {
				return "S";
			}
		} else if (tile.equals("-")) {
			// look east for -, J, 7
			// look west for L, F		
			boolean east = this.lookEast(row, column);
			boolean west = this.lookWest(row, column);
			if (east && !fromDirection.equals("E")) {
				return "E";
			}
			if (west && !fromDirection.equals("W")) {
				return "W";
			}
		} else if (tile.equals("7")) {
			// look south for |, L, J
			// look west for L, F
			boolean south = this.lookSouth(row, column);
			boolean west = this.lookWest(row, column);
			if (south && !fromDirection.equals("S")) {
				return "S";
			}
			if (west && !fromDirection.equals("W")) {
				return "W";
			}
		} else if (tile.equals("F")) {
			// look south for |, L, J
			// look east for -, J, 7
			boolean south = this.lookSouth(row, column);
			boolean east = this.lookEast(row, column);
			if (south && !fromDirection.equals("S")) {
				return "S";
			}
			if (east && !fromDirection.equals("E")) {
				return "E";
			}
		} else if (tile.equals("L")) {
			// look north for |, 7, F
			// look east for -, J, 7
			boolean north = this.lookNorth(row, column);
			boolean east = this.lookEast(row, column);
			if (north && !fromDirection.equals("N")) {
				return "N";
			}
			if (east && !fromDirection.equals("E")) {
				return "E";
			}
		} else if (tile.equals("J")) {
			// look north for |, 7, F
			// look west for L, F
			boolean north = this.lookNorth(row, column);
			boolean west = this.lookWest(row, column);
			if (north && !fromDirection.equals("N")) {
				return "N";
			}
			if (west && !fromDirection.equals("W")) {
				return "W";
			}
		}
		return "X";
	}
	
	private boolean lookWest(int row, int column) {
		if (column - 1 < 0) {
			return false;
		}
		// look west for L, F
		String west = this.rows.get(row).substring(column - 1, column);
		return west.equals("-") || west.equals("L") || west.equals("F");
	}

	private boolean lookEast(int row, int column) {
		// look south for |, L, J
		// look east for -, J, 7
		// look west for L, F
		String east = this.rows.get(row).substring(column + 1, column+2);
		return east.equals("-") || east.equals("J") || east.equals("7");
	}

	private boolean lookNorth(int row, int column) {
		if (row - 1 < 0) {
			return false;
		}
		String north = this.rows.get(row-1).substring(column, column+1);
		return north.equals("|") || north.equals("7") || north.equals("F");
	}

	private boolean lookSouth(int row, int column) {
		if (row + 1 >= this.rows.size()) {
			return false;
		}
		// look south for |, L, J
		// look east for -, J, 7
		// look west for L, F
		String south = this.rows.get(row+1).substring(column, column+1);
		return south.equals("|") || south.equals("L") || south.equals("J");
	}
}
