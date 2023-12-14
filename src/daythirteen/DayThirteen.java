package daythirteen;

import java.util.Vector;

import util.FileReader;

public class DayThirteen {
    
    private static void runPartOne() {
        Vector<String> lines = FileReader.readFile("./src/daythirteen/input.txt");
        Vector<Map> maps = new Vector<>();
        Map m = new Map();
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).equals("")) {
                maps.add(m);
                m = new Map();
                continue;
            }
            m.addRow(lines.get(i));
        }
        maps.add(m);
        
        int sum = 0;
        for (int i = 0; i < maps.size(); i++) {
            int a = maps.get(i).getHorizontalValue();
            int b = maps.get(i).getVerticalValues();
            sum += a + b;
        }
        System.out.println(sum);
    }
    
    public static void main(String[] args) {
        DayThirteen.runPartOne();
        DayThirteen.runPartTwo();
    }
    
    public static void runPartTwo() {
        Vector<String> lines = FileReader.readFile("./src/daythirteen/input.txt");
        Vector<AdvancedMap> maps = new Vector<>();
        AdvancedMap m = new AdvancedMap();
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).equals("")) {
                maps.add(m);
                m = new AdvancedMap();
                continue;
            }
            m.addRow(lines.get(i));
        }
        maps.add(m);
        
        int sum = 0;
        for (int i = 0; i < maps.size(); i++) {
            int a = maps.get(i).getHorizontalValue();
            int b = maps.get(i).getVerticalValues();
            sum += a + b;
        }
        System.out.println(sum);

        // 30063 -> too high
        // 30005 -> too high
        // 31363 -> too high    
        // 13996 -> not even close
        // Answer: 22906
    }
}
