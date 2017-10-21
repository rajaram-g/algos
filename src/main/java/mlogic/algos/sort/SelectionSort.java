package mlogic.algos.sort;

/**
 * Iterates through the array and exchanges each item with the smallest item in
 * the remaining list. Sort completes in O(n^2) time.
 * 
 * @author Rajaram G
 *
 */
public class SelectionSort<T extends Comparable<T>> extends Sort<T> {

	/**
	 * Sorts the given array using the selection sort algorithm
	 * 
	 * @param <T>
	 *            type of element in array
	 * @param array
	 *            array to be sorted
	 */
	public static <T extends Comparable<T>> void sort(T[] array) {
		SelectionSort<T> sorter = new SelectionSort<T>(array);
		sorter.sort();
	}

	/**
	 * Private constructor
	 * 
	 * @param array
	 *            - array to be sorted
	 */
	protected SelectionSort(T[] array) {
		if (array == null)
			throw new IllegalArgumentException("Input array must not be null");

		this.array = array;
	}

	/**
	 * The outer loop iterates through the array indices. Inner loop iterates
	 * from the outer loop index and swaps each item with the lowest item
	 * thereon.
	 */
	@Override
	public void sort() {

		for (int i = 0; i < this.array.length - 1; i++) {
			int j = i;
			int min = i;
			for (j = i; j < this.array.length; j++) {
				if (compare(min, j) > 0) {
					min = j;
				}
			}
			if (min != i)
				swap(i, min);

		}

	}

}
