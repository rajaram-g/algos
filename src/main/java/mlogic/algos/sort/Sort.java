package mlogic.algos.sort;

/**
 * @author Rajaram G
 *
 * @param <T>
 */
public abstract class Sort<T extends Comparable<T>> {

	/**
	 * The array to be sorted
	 */
	protected T[] array;

	/**
	 * To be implemented by various sorting algorithms
	 */
	protected abstract void sort();

	/**
	 * Swaps the items at the specified indices
	 * 
	 * @param u
	 *            - index of the first item
	 * @param v
	 *            - index of the second item
	 */
	final protected void swap(int u, int v) {
		T tmp = array[u];
		array[u] = array[v];
		array[v] = tmp;

	}

	/**
	 * Compares two elements in the array
	 * 
	 * @param u
	 *            - index of the first item in the heap
	 * @param v
	 *            - index of the second item in the heap
	 * @return a negative integer, zero, or a positive integer indicating if the
	 *         first item is less than, equal to, or greater than the second
	 *         item
	 */
	final protected int compare(int u, int v) {
		return array[u].compareTo(array[v]);
	}

}