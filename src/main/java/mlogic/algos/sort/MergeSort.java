package mlogic.algos.sort;

/**
 * Divide and conquer algorithm that splits the array in halves recursively,
 * sorts each half and merges them back in sorted order. Sort completes worst
 * case in O(n log n) time.
 * 
 * Credit to <a href=
 * "http://algs4.cs.princeton.edu/22mergesort/Merge.java.html">Algorithms 4th
 * Edition, By Robert Sedgewick and Kevin Wayne</a>
 * 
 * @author Rajaram G
 *
 */
public class MergeSort<T extends Comparable<T>> extends Sort<T> {

	/**
	 * Auxiliary array
	 */
	private T[] aux;

	/**
	 * Sorts the given array using the merge sort algorithm
	 * 
	 * @param array
	 *            - array to be sorted
	 */
	public static <T extends Comparable<T>> void sort(T[] array) {
		MergeSort<T> sorter = new MergeSort<T>(array);
		sorter.sort();
	}

	/**
	 * Private constructor
	 * 
	 * @param array
	 *            - array to be sorted
	 */
	protected MergeSort(T[] array) {
		if (array == null)
			throw new IllegalArgumentException("Input array must not be null");

		this.array = array;
		this.aux = (T[]) new Comparable[this.array.length];

	}

	/**
	 * This top down implementation divides the array recursively in two's until
	 * a sub-set size of 1 is reached. Then the halves are merged while
	 * maintaining order, recursively on the way up.
	 */
	@Override
	public void sort() {

		for (int i = 0; i < this.array.length; i++)
			this.aux[i] = this.array[i];
		sort(0, this.array.length - 1, this.array, this.aux);

	}

	/**
	 * Divides the array section into two, invokes sort on the two halves and
	 * then merges the sorted halves. Reverse the primary and secondary arrays
	 * at each level of the recursion.
	 * 
	 * @param lo
	 * @param hi
	 * @param alpha
	 * @param beta
	 */
	private void sort(int lo, int hi, T[] alpha, T[] beta) {
		if (hi <= lo)
			return;
		int mid = (hi + lo) / 2;
		sort(lo, mid, beta, alpha);
		sort(mid + 1, hi, beta, alpha);
		merge(lo, mid, hi, alpha, beta);

	}

	/**
	 * Merges two halves of an array section while ensuring the items are in
	 * increasing order.
	 * 
	 * @param lo
	 * @param mid
	 * @param hi
	 */
	private void merge(int lo, int mid, int hi, T[] alpha, T[] beta) {
		int i = lo;
		int j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			// If index is past the mid point, keep adding the
			// elements of second half. There is no need for
			// comparing the two halves anymore.
			if (i > mid)
				alpha[k] = beta[j++];

			// When the right half is maxed out and there are still elements in
			// the left half, keep adding elements of left half. There is no
			// need for comparing the two halves anymore.
			else if (j > hi)
				alpha[k] = beta[i++];

			// If current item from left half is greater than current item from
			// right half then copy the item from right.
			else if (compare(i, j, beta) > 0)
				alpha[k] = beta[j++];

			// Only remaining possibility is that current item from right
			// is greater than or equal to the current item from the left ,
			// so copy form right.
			else
				alpha[k] = beta[i++];

		}

	}

	/**
	 * Compares two elements in the array
	 * 
	 * @param u
	 *            - index of the first item in the heap
	 * @param v
	 *            - index of the second item in the heap
	 * @param array
	 *            - the array in which elements are to be compared
	 * @return a negative integer, zero, or a positive integer indicating if the
	 *         first item is less than, equal to, or greater than the second
	 *         item
	 */
	private int compare(int u, int v, T[] arr) {
		return arr[u].compareTo(arr[v]);
	}

}
