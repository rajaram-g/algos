/**
 * 
 */
package mlogic.algos.dictionary;

import java.util.Arrays;

import mlogic.algos.struct.Tuple;

/**
 * Dictionary implementation based on a sorted array
 * 
 * @author Rajaram G
 *
 */
public class SortedArrayDictionary implements Dictionary<String, String> {

	/**
	 * Initial size of the array (also the size of increments)
	 */
	private static Integer INITIAL_SIZE = 1000;

	/**
	 * Array of tuples to store the dictionary entries. The array is initialized
	 * and incremented in fixed amounts, and hence the length will almost always
	 * be greater than the current size of the dictionary.
	 */
	private Tuple<String, String>[] tuples;

	/**
	 * Tracks the actual size of the dictionary
	 */
	private Integer size = 0;

	/**
	 * Constructor - allocates the array with a fixed size
	 */
	public SortedArrayDictionary() {
		tuples = new Tuple[INITIAL_SIZE];

	}

	/**
	 * Gets an entry from the array using binary search
	 * 
	 * @param key
	 * @return value
	 */
	public String get(String key) {
		if (key != null) {
			int pos = Arrays.binarySearch(tuples, 0, this.size, new Tuple(key, ""));
			if (pos >= 0)
				return this.tuples[pos].value;
			else
				return null;

		}
		return null;
	}

	/**
	 * Puts an entry into the array. First check if the key exists using binary
	 * search. If key exists, then update the corresponding value, else insert a
	 * new entry in proper order.
	 * <p>
	 * If the array is already full, then increase the array size before
	 * inserting the new entry.
	 * 
	 * @param key
	 * @param value
	 */
	public void put(String key, String value) {
		if (key == null)
			return;
		if (this.size == 0) {
			tuples[0] = new Tuple(key, value);
			this.size++;
			return;

		}

		Tuple t = new Tuple(key, value);
		int pos = Arrays.binarySearch(tuples, 0, this.size, t);
		if (pos >= 0) {
			tuples[pos].value = value;
			return;
		}
		this.size++;
		if (this.size > this.tuples.length)
			resize();

		// if (pos >= 0) {
		// insertAt(pos, t);
		// } else {
		insertAt(-pos - 1, t);
		// }

	}

	/**
	 * Removes an entry from the array. First check if the key exists using
	 * binary search. If key exists, then remove the entry, else do nothing.
	 * 
	 * TODO: Reduce array size if dictionary size falls below the step size
	 * 
	 * @param key
	 */
	public void remove(String key) {
		if (key == null || this.size == 0)
			return;
		int pos = Arrays.binarySearch(tuples, 0, this.size, new Tuple(key, ""));
		if (pos >= 0)
			spliceAt(pos);
	}

	/**
	 * Gets the maximum key from the array.
	 * <p>
	 * As the array is maintained in sorted order through puts and removes, this
	 * will always be the last entry in the dictionary.
	 * 
	 * @return key
	 */
	public String maximum() {
		if (this.size == 0)
			return null;

		return this.tuples[this.size - 1].key;
	}

	/**
	 * Gets the minimum key from the array.
	 * <p>
	 * As the array is maintained in sorted order through puts and removes, this
	 * will always be the first entry in the dictionary.
	 * 
	 * @return key
	 */
	public String minimum() {
		if (this.size == 0)
			return null;

		return this.tuples[0].key;
	}

	/**
	 * Gets the largest key in the dictionary that is smaller than the input
	 * key. Returns null if the input key is not present or is the first entry.
	 * <p>
	 * As the array is maintained in sorted order, the predecessor will be found
	 * at a position one less than the position of the input key.
	 * 
	 * @return key
	 */
	public String predecessor(String key) {
		if (key == null || this.size == 0)
			return null;
		int pos = Arrays.binarySearch(tuples, 0, this.size, new Tuple(key, ""));
		if (pos > 0)
			return this.tuples[pos - 1].key;
		else
			return null;
	}

	/**
	 * Gets the smallest key in the dictionary that is greater than the input
	 * key. Returns null if the input key is not present or is the last entry.
	 * <p>
	 * As the array is maintained in sorted order, the successor will be found
	 * at a position one more than the position of the input key.
	 * 
	 * @return key
	 */
	public String successor(String key) {
		if (key == null || this.size == 0)
			return null;
		int pos = Arrays.binarySearch(tuples, 0, this.size, new Tuple(key, ""));
		if (pos >= 0 && pos < this.size - 1)
			return this.tuples[pos + 1].key;
		else
			return null;
	}

	/**
	 * Returns the size of the dictionary
	 * 
	 * @return size
	 */
	public Integer size() {
		return this.size;
	}

	/**
	 * Increases the array size. Creates a new array of the target size and
	 * copies the data from the original to the new array.
	 */
	private void resize() {
		Tuple[] tmp = new Tuple[this.tuples.length + INITIAL_SIZE];
		System.arraycopy(tuples, 0, tmp, 0, this.tuples.length);
		this.tuples = tmp;

	}

	/**
	 * Eliminates the entry at given position, by shifting all the following
	 * entries one place to the left.
	 */
	private void spliceAt(int i) {
		if (i < this.size - 1) {
			System.arraycopy(tuples, i + 1, tuples, i, this.size - i - 1);
		}
		tuples[this.size - 1] = null;
		this.size--;

	}

	/**
	 * Inserts a new entry at given position, by shifting all the following
	 * entries one place to the right.
	 */
	private void insertAt(int i, Tuple tuple) {
		if (i > this.size)
			return;
		if (i < this.size - 1) {
			System.arraycopy(tuples, i, tuples, i + 1, this.size - 1 - i);
		}
		tuples[i] = tuple;
	}

}
