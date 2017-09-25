package mlogic.algos.sort;

import mlogic.algos.struct.BinaryHeap;

/**
 * Uses a binary heap to sort an array in O(n log n) time without using any
 * additional memory.
 * 
 * @author Rajaram G
 *
 */
public class HeapSort<T extends Comparable<T>> extends Sort<T> {

	/**
	 * The size of the binary heap within the array
	 */
	private int heapSize;

	/**
	 * Sorts the given array using a binary heap.
	 * 
	 * @param array
	 *            - array to be sorted
	 */
	public static <T extends Comparable<T>> void sort(T[] array) {
		HeapSort<T> sorter = new HeapSort<T>(array);
		sorter.sort();
	}

	/**
	 * Private constructor
	 * 
	 * @param array
	 *            - array to be sorted
	 */
	protected HeapSort(T[] array) {
		if (array == null)
			throw new IllegalArgumentException("Input array must not be null");

		this.array = array;
		this.heapSize = this.array.length;
	}

	/**
	 * Constructs the binary heap and moves the largest item to the back end.
	 * Reduces the heap size by one and repeats the sequence, until we are left
	 * with a sorted array.
	 */
	@Override
	public void sort() {
		BinaryHeap<T> heapifier = new BinaryHeap<T>(array);
		heapifier.heapify();
		while (this.heapSize - 1 > 0) {
			heapifier.swap(0, this.heapSize - 1);
			heapifier.setHeapSize(--this.heapSize);
			heapifier.sink(0);
		}

	}

}
