package mlogic.algos.sort;

/**
 * Iterates through the array, compares each item with its successor and if
 * greater, swaps places. After reaching the end, repeats the iteration from the
 * first item. Sort is complete after achieving one pass through the array
 * without any swaps. Sort completes in O(n^2) time.
 * 
 * @author Rajaram G
 *
 */
public class BubbleSort<T extends Comparable<T>> extends Sort<T> {

	/**
	 * Sorts the given array using the bubble sort algorithm
	 * 
	 * @param <T>
	 *            type of element in array
	 * @param array
	 *            array to be sorted
	 */
	public static <T extends Comparable<T>> void sort(T[] array) {
		BubbleSort<T> sorter = new BubbleSort<T>(array);
		sorter.sort();
	}

	/**
	 * Private constructor
	 * 
	 * @param array
	 *            - array to be sorted
	 */
	protected BubbleSort(T[] array) {
		if (array == null)
			throw new IllegalArgumentException("Input array must not be null");

		this.array = array;
	}

	/**
	 * Iterates through the array, compares each item with its successor and if
	 * greater, swaps places. After reaching the end, repeats the iteration from
	 * the first item. Sort is complete after achieving one pass through the
	 * array without any swaps.
	 */
	@Override
	public void sort() {

		boolean complete = false;
		while (!complete) {
			complete = true;
			for (int i = 0; i < this.array.length - 1; i++) {
				if (compare(i, i + 1) > 0) {
					swap(i, i + 1);
					complete = false;
				}
			}
		}
	}

}
