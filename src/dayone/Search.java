package dayone;

import java.util.HashMap;

public class Search {
	
    public static int exec(String line, HashMap<String, Integer> values)
    {
        return Integer.valueOf(
    		String.valueOf(Search.findFirstValue(line, values)) + 
    		String.valueOf(Search.findLastValue(line, values))
		);
    }

    private static int findFirstValue(String line, HashMap<String, Integer> values)
    {
        var wrapper = new Object() { int first = -1; int firstIdx = line.length();};
        
        values.entrySet().stream().forEach(entry -> {
        	String key = entry.getKey();
        	int value = entry.getValue();
        	int idx = line.indexOf(key);
        	if (idx != -1) {
        		if (wrapper.firstIdx > idx) {
        			wrapper.firstIdx = idx;
        			wrapper.first = value;
        		}
        	}
        });

        return wrapper.first;
    }

    private static int findLastValue(String line, HashMap<String, Integer> values)
    {
        var wrapper = new Object() { int last = -1; int lastIdx = -1;};
        
        values.entrySet().stream().forEach(entry -> {
        	String key = entry.getKey();
        	int value = entry.getValue();
        	int idx = line.lastIndexOf(key);
        	if (idx != -1) {
        		if (wrapper.lastIdx < idx) {
        			wrapper.lastIdx = idx;
        			wrapper.last = value;
        		}
        	}
        });

        return wrapper.last;
    }
}
