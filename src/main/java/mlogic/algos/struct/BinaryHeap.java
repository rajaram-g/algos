package mlogic.algos.struct;

/**
 * Binary Heap is a binary tree stored in an array. The items are arranged such
 * that ever k'th item is greater than its two children at positions 2k and
 * 2k+1. Primarily used in the heap sort algorithm.
 * 
 * @author Rajaram G
 *
 */
public class BinaryHeap<T extends Comparable<T>> {

	/**
	 * Array to be heapified or tested
	 */
	protected T[] heap;

	/**
	 * Size of the Array
	 */
	protected int N;

	/**
	 * Constructor
	 * 
	 * @param heap
	 *            - the array to be heapified
	 */
	public BinaryHeap(T[] heap) {
		if (heap == null)
			throw new IllegalArgumentException("Input array is null");
		this.heap = heap;
		this.N = heap.length;
	}

	/**
	 * Rearranges the items in the array to form a binary heap
	 * 
	 * @param heap
	 *            - the array to be heapified
	 */
	public static <T extends Comparable<T>> void heapify(T[] heap) {
		BinaryHeap<T> bh = new BinaryHeap<T>(heap);
		bh.heapify();

	}

	/**
	 * Tests if the input array is arranged as a binary heap
	 * 
	 * @param arrayToTest
	 *            - the array to be tested
	 * @return true or false
	 */
	public static <T extends Comparable<T>> boolean isHeap(T[] arrayToTest) {
		BinaryHeap<T> bh = new BinaryHeap<T>(arrayToTest);
		return bh.isHeap();

	}

	/**
	 * Iterates through the first half of the array and "sinks" each item into
	 * its sub-tree.
	 */
	public void heapify() {
		int fp = lastParent();
		for (int u = fp; u >= 0; u--)
			sink(u);

	}

	/**
	 * Sets the size of the heap within the array. Further heap operations will
	 * apply only for items within this size.
	 * 
	 * @param newSize
	 */
	public void setHeapSize(int newSize) {
		if (newSize > this.N)
			throw new IllegalArgumentException("Heap size must not be greater than the current heap size");
		this.N = newSize;
	}

	/**
	 * Swaps the items at the specified indices
	 * 
	 * @param u
	 *            - index of the first item
	 * @param v
	 *            - index of the second item
	 */
	public void swap(int u, int v) {
		T tmp = heap[u];
		heap[u] = heap[v];
		heap[v] = tmp;

	}

	/**
	 * Recursively sinks the current item deeper into its sub-tree so that the
	 * sub-tree rooted at the given index becomes a binary heap.
	 * 
	 * @param u
	 *            - index of item to be sunk
	 */
	public void sink(int u) {
		if (u >= this.N)
			return;
		int v = leftChild(u);
		if (v >= this.N)
			return;
		int m = v;
		int w = rightChild(u);
		if (w < this.N)
			m = max(v, w);
		if (compare(m, u) > 0) {
			swap(u, m);
			sink(m);
		}

	}

	/**
	 * Initiates the heap test at the top of the tree i.e first item
	 * 
	 * @return true or false
	 */
	private boolean isHeap() {
		return isHeap(0);
	}

	/**
	 * Compares two elements in the heap
	 * 
	 * @param u
	 *            - index of the first item in the heap
	 * @param v
	 *            - index of the second item in the heap
	 * @return a negative integer, zero, or a positive integer indicating if the
	 *         first item is less than, equal to, or greater than the second
	 *         item
	 */
	private int compare(int u, int v) {
		return heap[u].compareTo(heap[v]);
	}

	/**
	 * Returns the last item with any children in the heap i.e. the item before
	 * the median
	 * 
	 * @param n
	 * @return last item with children
	 */
	private int lastParent() {
		return (int) this.N / 2 - 1;
	}

	/**
	 * Returns index of the left child
	 * 
	 * @param u
	 *            - index of the parent
	 * @return index of the left child
	 */
	private int leftChild(int u) {
		return u * 2 + 1;
	}

	/**
	 * Returns the index of the right child
	 * 
	 * @param u
	 *            - index of the parent
	 * @return index of the right child
	 */
	private int rightChild(int u) {
		return u * 2 + 2;
	}

	/**
	 * Compares items at the specified at given indices and returns the index
	 * that contains the greater value.
	 * 
	 * @param v
	 *            - index of the first item
	 * @param w
	 *            - index of the second item
	 * @return index of item with greater value
	 */
	private int max(int v, int w) {
		if (compare(v, w) >= 0)
			return v;
		else
			return w;
	}

	/**
	 * Tests of the sub-tree rooted at specified index is a binary heap. Checks
	 * if the value at the root dominates its left and right sub-trees.
	 * 
	 * @param u
	 *            - index specifying root of the sub-tree
	 * @return true or false
	 */
	private boolean isHeap(int u) {
		if (u >= this.N || u < 0)
			throw new IllegalArgumentException("Argument is out of range");

		return isDominant(u, leftChild(u)) && isDominant(u, rightChild(u));

	}

	/**
	 * Compares the value of items at specified parent and child indices and
	 * recursively checks if the sub-tree rooted at the child index is also a
	 * binary heap.
	 * 
	 * @param u
	 *            - index of parent
	 * @param v
	 *            - index of child
	 * @return true or false
	 */
	private boolean isDominant(int u, int v) {
		if (v < this.N) {
			if (compare(u, v) < 0)
				return false;
			return isHeap(v);
		} else
			return true;
	}
}
