import static java.lang.Integer.*;

import java.io.*;
import java.util.*;

public class Constraints {

	public final static String SUCCESS = "SATISFIABLE";
	public final static String FAILURE = "NOT SATISFIABLE";
	public final static String EQUALS_OP = "==";
	public final static String VARIABLE_PREFIX = "x";

	public static void main(String[] args) {
		System.out.print(satisfiable(processInput(System.in)) ? SUCCESS : FAILURE);
	}

	/**
	 * get all constraints for things that are equal, build disjoint union data
	 * structure for each not equal constraint, check that the two elements
	 * aren't in the same union, output the union number for each set.
	 * 
	 * overall runtime: O(n + m log n)
	 */
	public static boolean satisfiable(ConstraintsInput input) {

		// creation of this structure takes O(n)
		final DisjointUnionStruct constraints = new DisjointUnionStruct(input.getNumberVariables());

		// this step is O(m * log n)
		// each data can only change a max of log n times.
		for (int[] pair : input.getEqualConstraints())
			constraints.union(pair[0], pair[1]);

		// this step is n * O(1)
		for (int[] pair : input.getNotEqualConstraints())
			if (constraints.same(pair[0], pair[1]))
				return false;

		return true;
	}

	/**
	 * Error handler wrapper
	 */
	public static ConstraintsInput processInput(InputStream in) {
		try {
			return new ConstraintsInput(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Processed and packaged input
	 */
	private static class ConstraintsInput {
		private final List<int[]> eq;
		private final List<int[]> ne;
		private final int numberVariables;

		private ConstraintsInput(InputStream in) throws IOException {
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String[] params = reader.readLine().trim().split("\\s");
			numberVariables = parseInt(params[0]);
			int numberConstraints = parseInt(params[1]);
			eq = new ArrayList<>(numberConstraints);
			ne = new ArrayList<>(numberConstraints);

			for (int line = 0; line < numberConstraints; line++) {
				String[] data = reader.readLine().replace(VARIABLE_PREFIX, "").split("\\s");
				add(EQUALS_OP.equals(data[1]) ? eq : ne, pair(parseInt(data[0]) - 1, parseInt(data[2]) - 1));
			}
			reader.close();
		}

		public List<int[]> getEqualConstraints() {
			return eq;
		}

		public List<int[]> getNotEqualConstraints() {
			return ne;
		}

		public int getNumberVariables() {
			return numberVariables;
		}
	}

	/**
	 * Disjoint Union Data structure implemented only with built-in Java arrays.
	 * 
	 * @author cthoyt
	 */
	public static class DisjointUnionStruct {

		/**
		 * set[i]: which set # is element i in?
		 */
		int[] set;
		/**
		 * root[i]: what is the index of the first element in the linked list of
		 * this element's set? useful in joining of two sets
		 */
		int[] root;
		/**
		 * tail[i]: what is the index of the last element in the linked list of
		 * this element's set? useful in joining of two sets
		 */
		int[] tail;
		/**
		 * preceding[i]: what is the index of the previous element in the linked
		 * list of this element's set? if there is no previous element, this is
		 * -1
		 */
		int[] preceding;
		/**
		 * succeeding[i]: what is the index of the next element in the linked
		 * list of this element's set? if there is no previous element, this is
		 * -1
		 */
		int[] succeeding;
		/**
		 * size[i]: what is the size of this element's set? useful in
		 * determining which set to merge to which
		 */
		int[] size;

		/**
		 * In a disjoint union data structure, there can be no more disjoint
		 * subsets than if every element is in its own subset.
		 * 
		 * In this implementation, each set can only contain integers. This is
		 * due to the constraints of Java's arrays. With a Map data structure,
		 * any data type could be used.
		 * 
		 * @param startNumberSets
		 *            The original number of sets.
		 */
		public DisjointUnionStruct(int startNumberSets) {
			set = new int[startNumberSets];
			preceding = new int[startNumberSets];
			succeeding = new int[startNumberSets];
			root = new int[startNumberSets];
			tail = new int[startNumberSets];
			size = new int[startNumberSets];

			for (int i = 0; i < startNumberSets; i++) {
				set[i] = i;
				root[i] = i;
				tail[i] = i;
				preceding[i] = -1;
				succeeding[i] = -1;
				size[i] = 1;
			}
		}

		/**
		 * @param element
		 *            The label of the element
		 * @return Returns the set number of this element
		 */
		public int find(int element) {
			return set[element];
		}

		/**
		 * 
		 * @param e1
		 *            The label of the first element
		 * @param e2
		 *            The label of the second element
		 * @return Returns if the first and second element are in the same set
		 */
		public boolean same(int e1, int e2) {
			return set[e1] == set[e2];
		}

		/**
		 * Combines the sets of the first element and the second element.
		 * Maintains the label for the larger set and changes the label for each
		 * of the elements in the smaller set.
		 * 
		 * @param left
		 *            The label of the first element
		 * @param right
		 *            The label of the second element
		 */
		public void union(int left, int right) {
			// Quit if they're already part of the same union
			if (set[left] == set[right]) {
				return;
			} else if (size[left] < size[right]) {
				union(right, left);
			} else {
				// increment the size of the left set
				size[left] += size[right];

				// point the end of the left list forwards to the start of the
				// right list
				succeeding[tail[left]] = root[right];

				// point the start of the right list back to the end of the left
				// list
				preceding[root[right]] = tail[left];

				// update all tails in left set to be the tail of the right set
				int l = root[left];
				int newTail = tail[right];
				do {
					tail[l] = newTail;
				} while (-1 != (l = succeeding[l]));

				// update all roots in the right set to be the root of the left
				// set and update all sets in the right set to be the left set
				int r = root[right];
				int newRoot = root[left];
				int newSet = set[newRoot];
				do {
					root[r] = newRoot;
					set[r] = newSet;
				} while (-1 != (r = succeeding[r]));
			}
		}
	}

	/**
	 * Build an integer pair in an array
	 */
	public static int[] pair(int left, int right) {
		return new int[] { left, right };
	}

	/**
	 * Stateless list appender
	 */
	public static <N> boolean add(List<N> list, N element) {
		return list.add(element);
	}
}
