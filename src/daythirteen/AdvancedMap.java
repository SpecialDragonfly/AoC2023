package daythirteen;

import java.util.Vector;

public class AdvancedMap {
    Vector<String> rows = new Vector<>();
    
    public AdvancedMap() {
    }
    
    public void addRow(String row) {
        this.rows.add(row);
    }
    
    public int getVerticalValues() {
        int colCount = this.rows.get(0).length();
        for (int i = 0; i < colCount - 1; i++) {
            if (this.compareColumn(i, i+1)) {
                if (this.checkReflectedColumns(i)) {
                    return i + 1;
                }
            } else {
                if (distance(this.getColumn(i), this.getColumn(i+1)) == 1) {
                    if (this.checkReflectedColumns(i, true)) {
                        return i + 1;
                    }
                }
            }
        }
        return 0;
    }
    
    public int getHorizontalValue() {
        for (int i = 0; i < rows.size() - 1; i++) {
            if (this.rows.get(i).equals(this.rows.get(i+1))) {
                if (this.checkReflectedRows(i)) {
                    return 100*(i + 1); // return number of rows above horizontal
                }
            } else {
                // Rows didn't match, but did they only have 1 difference between the two?
                if (distance(this.rows.get(i), this.rows.get(i+1)) == 1) {
                    if (this.checkReflectedRows(i, true)) {
                        return 100* (i + 1); // return number of rows above horizontal
                    }
                }
            }
        }
        return 0;
    }
    
    private boolean checkReflectedColumns(int i) {
        return this.checkReflectedColumns(i, false);
    }
    
    private boolean checkReflectedColumns(int i, boolean fixed) {
        int maxSize = this.rows.get(0).length();
        for (int start = 1; start <= i; start++) {
            if (i - start < 0) {
                break;
            }
            if (i + start + 1 >= maxSize) {
                break;
            }
            if (!this.compareColumn(i - start, i + start + 1)) {
                if (!fixed && distance(this.getColumn(i - start), this.getColumn(i + start + 1)) == 1) {
                    // There was one difference between the columns. Fix it, move on.
                    fixed = true;
                } else {
                    return false;    
                }
            }
        }

        // We're only interested if we _did_ fix something.
        return fixed;
    }
    
    private String getColumn(int column) {
        String columnOne = "";
        for (int i = 0; i < this.rows.size(); i++) {
            columnOne += String.valueOf(this.rows.get(i).charAt(column));
        }
        return columnOne;
    }

    private boolean compareColumn(int columnOneIdx, int columnTwoIdx) {
        String columnOne = "";
        String columnTwo = "";
        for (int i = 0; i < this.rows.size(); i++) {
            columnOne += String.valueOf(this.rows.get(i).charAt(columnOneIdx));
            columnTwo += String.valueOf(this.rows.get(i).charAt(columnTwoIdx));
        }
        return columnOne.equals(columnTwo);
    }
    
    /*
     * 1 2 3 4 5 6 7 8
     * 4 comp 5 (i = 3, start = 0)
     * 3(2) comp 6(5) (i = 3, start = 1) comp i -start, i+start+1
     * 2(1) comp 7(6) (i=3, start = 2) comp i-start, i+
     */
    
    private boolean checkReflectedRows(int i) {
        return this.checkReflectedRows(i, false);
    }
    
    private boolean checkReflectedRows(int i, boolean fixed) {
        for (int start = 1; start <= i; start++) {
            if (i - start < 0) {
                break;
            }
            if (i + start + 1 >= this.rows.size()) {
                break;
            }
            if (!this.rows.get(i - start).equals(this.rows.get(i + start + 1))) {
                if (!fixed && distance(this.rows.get(i - start), this.rows.get(i + start + 1)) == 1) {
                    // There was 1 difference between the rows, which we've now fixed, so we can continue.
                    fixed = true;
                } else {
                    return false;    
                }
            }
        }

        // We're only interested in this solution if we _did_ fix something.
        return fixed;
    }
    
    // Dirty Levenshtein that only considers substitutions.
    public int distance(String x, String y) {
        if (x.length() == 0 ) {
            return 0;
        }
        int val = distance(x.substring(1), y.substring(1)) + (x.charAt(0) == y.charAt(0) ? 0 : 1);
        return val;
    }
    
    public String toString() {
        String x = "";
        for (int i = 0; i < this.rows.size(); i++) {
            x += this.rows.get(i).toString() + "\n";
        }
        return x;
    }

}
