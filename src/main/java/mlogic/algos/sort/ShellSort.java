package mlogic.algos.sort;

/**
 * Incrementally sorts items that are far apart before doing a final insertion
 * sort. Improves speed due to the property that insertion sort is faster on
 * partially-sorted lists. Achieves close to linear performance in average case.
 * 
 * @author Rajaram G
 *
 */
public class ShellSort<T extends Comparable<T>> extends Sort<T> {

	/**
	 * Sorts the given array using the shell sort algorithm
	 * 
	 * @param array
	 *            - array to be sorted
	 */
	public static <T extends Comparable<T>> void sort(T[] array) {
		ShellSort<T> sorter = new ShellSort<T>(array);
		sorter.sort();
	}

	/**
	 * Private constructor
	 * 
	 * @param array
	 *            - array to be sorted
	 */
	protected ShellSort(T[] array) {
		if (array == null)
			throw new IllegalArgumentException("Input array must not be null");

		this.array = array;
	}

	/**
	 * After choosing a step value (h), the set formed by every hth item is
	 * sorted (h-sort). H-sort is repeated for decreasing values of h until a
	 * final pass with h=1.
	 */
	@Override
	public void sort() {
		int n = this.array.length;
		int h = 1;
		if (n > 3)
			h = (int) Math.log((double) n);

		while (h >= 1) {
			for (int i = h; i < n; i += h) {
				int j = i;
				while (j >= h && compare(j, j - h) < 0) {
					swap(j, j - h);
					j = j - h;
				}
			}
			h--;
		}

	}

}
