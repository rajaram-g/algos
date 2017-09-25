package mlogic.algos.sort;

/**
 * Divide and conquer algorithm that splits the array by choosing a random
 * partition key, moving all items lesser than the partition key to its left,
 * and then recursively applying quick sort to each partition until the array is
 * sorted. Sort completes worst case in O(n log n) time.
 * 
 * @author Rajaram G
 *
 */
public class QuickSort<T extends Comparable<T>> extends Sort<T> {

	/**
	 * Sorts the given array using the quick sort algorithm
	 * 
	 * @param array
	 *            - array to be sorted
	 */
	public static <T extends Comparable<T>> void sort(T[] array) {
		QuickSort<T> sorter = new QuickSort<T>(array);
		sorter.sort();
	}

	/**
	 * Private constructor
	 * 
	 * @param array
	 *            - array to be sorted
	 */
	protected QuickSort(T[] array) {
		if (array == null)
			throw new IllegalArgumentException("Input array must not be null");

		this.array = array;

	}

	/**
	 * Initiates quick sort for the entire array
	 */
	@Override
	public void sort() {

		// RandomizationHelper.shuffle(this.array);

		quicksort(0, this.array.length - 1);

	}

	/**
	 * Applies quick sort algorithm to a range of items within an array. Select
	 * the first item as the partition key. Scan from left to find an item
	 * greater in value than the key. Scan from right to find an item lesser in
	 * value. If the former precedes the latter, exchange the two and repeat.
	 * Stop scanning when no more items are greater than the key or the right
	 * and left scans cross over. Exchange the key item with the right most item
	 * lesser in value to the key. Apply quick sort recursively to the sub-sets
	 * left and right of the partition key.
	 * 
	 * @param lo
	 *            - index of first item in range
	 * @param hi
	 *            - index of last item in range
	 */
	private void quicksort(int lo, int hi) {
		if (hi <= lo)
			return;
		int i = lo;
		int j = hi + 1;

		while (true) {
			while (compare(lo, ++i) > 0) {
				if (i == hi)
					break;
			}

			while (compare(lo, --j) < 0) {
				if (j == lo)
					break;
			}

			// Needs swap
			if (i < j) {
				swap(i, j);
				continue;
			}

			// Cross over
			if (i > j) {
				swap(lo, j);
				quicksort(lo, j - 1);
				quicksort(j + 1, hi);
				break;
			}

			// All items less than key
			if (i == hi) {
				swap(lo, hi);
				quicksort(lo, hi - 1);
				break;
			}

			// All items more than key
			if (j == lo) {
				quicksort(lo + 1, hi);
				break;
			}

		}

	}

}
