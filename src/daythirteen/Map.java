package daythirteen;

import java.util.Vector;

public class Map {
    Vector<String> rows = new Vector<>();
    public Map() {
        
    }

    public void addRow(String l) {
        this.rows.add(l);
    }
    
    public int getVerticalValues() {
        int colCount = this.rows.get(0).length();
        for (int i = 0; i < colCount - 1; i++) {
            if (this.compareColumn(i, i+1)) {
                if (this.checkReflectedColumns(i)) {
                    return i+1;
                }
            }
        }
        return 0;
    }
    
    public int getHorizontalValue() {
        for (int i = 0; i < rows.size() - 1; i++) {
            if (this.rows.get(i).equals(this.rows.get(i+1))) {
                if (this.checkReflectedRows(i)) {
                    return 100*(i+1);
                }
            }
        }
        
        return 0;
    }
    
    private boolean checkReflectedColumns(int i) {
        int maxSize = this.rows.get(0).length();
        for (int start = 1; start <= i; start++) {
            if (i - start < 0) {
                break;
            }
            if (i + start + 1 >= maxSize) {
                break;
            }
            if (!this.compareColumn(i - start, i + start + 1)) {
                return false;
            }
        }

        return true;
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
        for (int start = 1; start <= i; start++) {
            if (i - start < 0) {
                break;
            }
            if (i + start + 1 >= this.rows.size()) {
                break;
            }
            if (!this.rows.get(i - start).equals(this.rows.get(i + start + 1))) {
                return false;    
            }
        }

        return true;
    }
    
    public String toString() {
        String x = "";
        for (int i = 0; i < this.rows.size(); i++) {
            x += this.rows.get(i).toString() + "\n";
        }
        return x;
    }
}
