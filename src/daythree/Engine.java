package daythree;

import java.util.List;
import java.util.Vector;
import java.util.stream.IntStream;

public class Engine {
	Vector<String> grid;
	Vector<String> numbers = new Vector<>();
	
	public Engine(Vector<String> grid) {
		this.grid = grid;
		this.numbers.addAll(List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "0"));
	}
	
	public Vector<Gear> getGears() {
		Vector<Gear> gears = new Vector<>();
		IntStream.range(0, this.grid.size()).forEach(rowIndex -> {
			String row = this.grid.get(rowIndex);
			
	        for (int columnIdx = 0; columnIdx < row.length(); columnIdx++) {
	            String currentChar = String.valueOf(row.charAt(columnIdx));
	            if (this.numbers.contains(currentChar)) {
	            	Gear gear = new Gear(this.getNumber(rowIndex, columnIdx));
	            	
	            	// But is it a gear?
	            	gear = this.getGearRatio(gear, rowIndex, columnIdx);
	            	gears.add(gear);
	            	
	            	// There's no need to continue checking this number, bounce the index accordingly
	            	columnIdx += gear.getValue().length();
	            }
	        }
		});
		
		return gears;
	}
	
	public Vector<Part> getParts() {
		Vector<Part> parts = new Vector<>();
		IntStream.range(0, this.grid.size()).forEach(rowIndex -> {
			String row = this.grid.get(rowIndex);
			
	        for (int columnIdx = 0; columnIdx < row.length(); columnIdx++) {
	            String currentChar = String.valueOf(row.charAt(columnIdx));
	            if (this.numbers.contains(currentChar)) {
	            	Part part = this.getNumber(rowIndex, columnIdx);
	            	
	            	// But is it a part?
	            	part = this.getSymbol(part, rowIndex, columnIdx);
	            	parts.add(part);
	            	
	            	// There's no need to continue checking this number, bounce the index accordingly
	            	columnIdx += part.getValue().length();
	            }
	        }
		});
		
		return parts;
	}
	
	private String valueAt(int row, int column) throws Exception {
		if (row >= this.grid.size()) {
			throw new Exception("Tried to check past available rows");
		}
		if (column+1 > this.grid.get(row).length()) {
			throw new Exception("Tried to get past the available columns at row " + row);
		}
		return this.grid.get(row).substring(column, column+1);
	}
	
	private Part getNumber(int rowIndex, int startColumnIndex) {
		Vector<String> result = new Vector<>();
		
		int searchIdx = startColumnIndex;
		try {
			while (this.numbers.contains(this.valueAt(rowIndex, searchIdx))) {
				result.add(this.valueAt(rowIndex, searchIdx));
				searchIdx++;
			}
		} catch (Exception e) {
			// No need to shout about it, we've just hit the side of the area somehow.
		}
		
		return new Part(String.join("", result));
	}
	
	private Part getSymbol(Part potentialPart, int row, int startColumn) {
		// This is the area we'd like to check
		int startRow = row - 1;
		int endRow = row + 1;
		int start = startColumn - 1;
		int end = startColumn + potentialPart.getValue().length() + 1;
		// This resets the boundaries in the event we go over the grid
		if (startRow < 0) {
			startRow = 0;
		}
		if (endRow >= this.grid.size()) {
			endRow = this.grid.size() - 1;
		}
		if (start < 0) {
			start = 0;
		}
		if (end > this.grid.get(row).length()) {
			end = this.grid.get(row).length();
		}
		// Now we search for symbols (which is anything except a number and a .)
		Vector<String> symbols = new Vector<>();
		symbols.addAll(this.numbers);
		symbols.add(".");
		for (int x = startRow; x <= endRow; x++) {
			for (int y = start; y < end; y++) {
				String value;
				try {
					value = this.valueAt(x, y);
					if (!symbols.contains(value)) {
						// We found something that isn't a number or a . - so it's a symbol!
						potentialPart.setSymbol(value);
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}

			}
		}
		return potentialPart;
	}
	
	private Gear getGearRatio(Gear potentialPart, int row, int startColumn) {
		// This is the area we'd like to check
		int startRow = row - 1;
		int endRow = row + 1;
		int start = startColumn - 1;
		int end = startColumn + potentialPart.getValue().length() + 1;
		// This resets the boundaries in the event we go over the grid
		if (startRow < 0) {
			startRow = 0;
		}
		if (endRow >= this.grid.size()) {
			endRow = this.grid.size() - 1;
		}
		if (start < 0) {
			start = 0;
		}
		if (end > this.grid.get(row).length()) {
			end = this.grid.get(row).length();
		}
		// Now we search for gear ratios (which is _only_ a *)
		for (int x = startRow; x <= endRow; x++) {
			for (int y = start; y < end; y++) {
				String value;
				try {
					value = this.valueAt(x, y);
					if (value.equals("*")) {
						// We found something that isn't a number or a . - so it's a symbol!
						potentialPart.setSymbol(value, x, y);
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}

			}
		}
		return potentialPart;		
	}
}
