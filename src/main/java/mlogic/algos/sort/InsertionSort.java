package mlogic.algos.sort;

/**
 * Iterates through the array and places each new item in its proper order among
 * the items already sorted. Sort completes in O(n^2) time.
 * 
 * @author Rajaram G
 *
 */
public class InsertionSort<T extends Comparable<T>> extends Sort<T> {

	/**
	 * Sorts the given array using the insertion sort algorithm
	 * 
	 * @param array
	 *            - array to be sorted
	 */
	public static <T extends Comparable<T>> void sort(T[] array) {
		InsertionSort<T> sorter = new InsertionSort<T>(array);
		sorter.sort();
	}

	/**
	 * Private constructor
	 * 
	 * @param array
	 *            - array to be sorted
	 */
	protected InsertionSort(T[] array) {
		if (array == null)
			throw new IllegalArgumentException("Input array must not be null");

		this.array = array;
	}

	/**
	 * The outer loop iterates through the array indices starting with 1. Inner
	 * loop iterates backward from outer loop index and swaps each item with its
	 * predecessor if lesser. Inner loop exits when it reaches the first item or
	 * at the first instance when an item and its predecessor are in order.
	 */
	@Override
	public void sort() {

		for (int i = 1; i < this.array.length; i++) {
			int j = i;
			while (j >= 1 && compare(j, j - 1) < 0) {
				swap(j, j - 1);
				j = j - 1;
			}

		}

	}

}
