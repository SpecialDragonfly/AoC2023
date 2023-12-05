package dayfive;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import util.FileReader;

public class DayFive {
	public static void main(String[] args) {
		Vector<String> lines = FileReader.readFile("./src/dayfive/input.txt");
		HashMap<String, Almanac> almanacs = new HashMap<>();
		Vector<String> seeds = new Vector<>();
		
		Almanac a = null;
		for (int idx = 0; idx < lines.size(); idx++) {
			String line = lines.get(idx);
			if (line.startsWith("seeds: ")) {
				seeds.addAll(Arrays.stream(line.substring(7).split(" ")).map(v -> v.trim()).toList());
			} else if (line.endsWith("map:")) {
				String[] parts = line.substring(0, line.length() - 5).split("-to-");
				String from = parts[0].trim();
				String to = parts[1].trim();
				a = new Almanac(from, to);
			} else if (line.trim().equals("")) {
				if (a != null) {
					almanacs.put(a.getFrom(), a);	
				}
			} else {
				a.addRange(line);
			}
		}
		almanacs.put(a.getFrom(), a);
		
		System.out.println("Almanacs built. Seed count: " + seeds.size());
		
		Vector<Long> allSeeds = new Vector<>();
		for (int i = 0; i < seeds.size(); i+=2) {
			long seed = Long.valueOf(seeds.get(i));
			long range = Long.valueOf(seeds.get(i+1));
			System.out.println("Creating " + range + " values on top of " + seed);
			for (long j = 0; j < range; j++) {
				allSeeds.add(seed + j);
			}
		}
		System.out.println("Seeds: " + allSeeds.size());
		
		Almanac almanac = null;
		String next = "seed";
		while (!next.equals("location")) {
			almanac = almanacs.get(next);
			System.out.println("Checking almanac " + almanac.getFrom());
			next = almanac.getNext();
			
			HashMap<Long, Long> fullMapping = almanac.getFullMapping();
			List<Long> keysInSeeds = fullMapping.keySet().stream().filter(k -> allSeeds.contains(k)).toList();
			List<Long> unmappedSeeds = allSeeds.stream().filter(s -> !fullMapping.containsKey(s)).toList();
			allSeeds.clear();
			allSeeds.addAll(unmappedSeeds);
			keysInSeeds.forEach(k -> {
				allSeeds.add(fullMapping.get(k));
			});
		}
		
		System.out.println(Collections.min(allSeeds));
	}
	
	public void runPartOne() {
		Vector<String> lines = FileReader.readFile("./src/dayfive/input.txt");
		HashMap<String, Almanac> almanacs = new HashMap<>();
		Vector<String> seeds = new Vector<>();
		
		Almanac a = null;
		for (int idx = 0; idx < lines.size(); idx++) {
			String line = lines.get(idx);
			if (line.startsWith("seeds: ")) {
				seeds.addAll(Arrays.stream(line.substring(7).split(" ")).map(v -> v.trim()).toList());
			} else if (line.endsWith("map:")) {
				String[] parts = line.substring(0, line.length() - 5).split("-to-");
				String from = parts[0].trim();
				String to = parts[1].trim();
				a = new Almanac(from, to);
			} else if (line.trim().equals("")) {
				if (a != null) {
					almanacs.put(a.getFrom(), a);	
				}
			} else {
				a.addRange(line);
			}
		}
		almanacs.put(a.getFrom(), a);
		
		Vector<String> locations = new Vector<>();
		seeds.forEach(seed -> {
			System.out.println("Seed: " + seed);
			String next = "seed";
			Almanac almanac = null;
			String value = seed;
			
			while (!next.equals("location")) {
				almanac = almanacs.get(next);
				next = almanac.getNext();
				value = almanac.getMappedValue(value);
			}
			locations.add(value);
		});
		System.out.println(locations.stream().mapToLong(l -> Long.valueOf(l)).min().getAsLong());
		System.out.println(locations);
		System.out.println(Collections.min(locations));
		
		// 650599855		
	}
}
