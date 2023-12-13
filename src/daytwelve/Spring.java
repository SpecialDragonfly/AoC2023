package daytwelve;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Spring {
	private Vector<String> spring;
	private Condition condition;

	public Spring(String spring, Condition condition) {
		this.spring = new Vector<String>(List.of(spring.split("")));
		this.condition = condition;
	}
	
	public String toString() {
		return this.getParts() + "(" + this.condition.toString() + ")";
	}
	
	private ArrayList<Vector<String>> getParts() {
		ArrayList<Vector<String>> parts = new ArrayList<Vector<String>>();
		Vector<String> x = new Vector<>();
		for (int i = 0; i < this.spring.size(); i++) {
			if (this.spring.get(i).equals(".")) {
				if (!x.isEmpty()) {
					parts.add(x);
					x = new Vector<>();
				}
				continue;
			}
			x.add(this.spring.get(i));
		}
		
		return parts;
	}
	
	private ArrayList<Vector<String>> filteredPartsToConditions() {
		Vector<Integer> sizes = this.condition.getSizes();
		ArrayList<Vector<String>> parts = this.getParts();
		for (int i = 0; i < sizes.size(); i++) {
			if (parts.size() <= i) {
				// We don't have sufficient parts to work out the issues.
				break;
			}
			if (sizes.get(i) > parts.get(i).size()) {
				// bin this part, try again.
				parts.remove(i);
				i--; // repeat this loop with the next pairing.
			}
		}
		
		return parts;
	}
	
	public int findPermutations() {
		// If the size of the spring is exactly the size needed for the conditions, return 1.
		if (this.spring.size() == this.condition.getMinLength()) {
			return 1;
		}
		
		ArrayList<Vector<String>> parts = this.filteredPartsToConditions();
		// Now check whether each part is actually the same size as the corresponding size.
		// Sizes are the source of truth here, we can have multiple parts, but fewer sizes.
		boolean exact = true;
		Vector<Integer> sizes = this.condition.getSizes();
		if (parts.size() == sizes.size()) {
			// Our parts and sizes are mapped 1:1
			// So check for permutations in each part.
			int perm = 1;
			for (int i = 0; i < parts.size(); i++) {
				perm = perm * (parts.get(i).size() - sizes.get(i) + 1); 
			}
			return perm;
		}
		
		for (int i = 0; i < sizes.size(); i++) {
			if (sizes.get(i) == parts.get(i).size()) {
				exact = exact && true;
			} else if (sizes.get(i) < parts.get(i).size()) {
				exact = exact && false;
			}
		}
		if (exact) {
			return 1;
		}
		// We have permutations insufficient parts.
		
		
		
		return 0;
	}
}
