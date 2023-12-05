package dayfive;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class SeedCollection implements Iterable {
	private HashMap<Long, Long> seeds;
	
	public SeedCollection(List<String> seeds) {
		for (int i = 0; i < seeds.size(); i+=2) {
			this.seeds.put(Long.valueOf(seeds.get(i)), Long.valueOf(seeds.get(i+1)));
		}
	}
	
	public Stream<Long> getRange() {
		return this.seeds.keySet().stream();
	}

	@Override
	public Iterator iterator() {
		
		// TODO Auto-generated method stub
		return null;
	}
}
