package mlogic.algos.dictionary;

import mlogic.algos.struct.Tuple;

/**
 * Dictionary implementation based on an unsorted array
 * 
 * @author Rajaram G
 *
 */
public class UnsortedArrayDictionary implements Dictionary<String, String> {

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
	public UnsortedArrayDictionary() {
		tuples = new Tuple[INITIAL_SIZE];
	}

	/**
	 * Get an entry from the array using linear search
	 * 
	 * @param key
	 * @return value
	 */
	public String get(String key) {
		if (key != null)
			for (int i = 0; i < this.size; i++)
				if (this.tuples[i].key.equals(key))
					return this.tuples[i].value;
		return null;
	}

	/**
	 * Put an entry into the array. First check if the key exists using binary
	 * search. If key exists, then update the corresponding value, else insert a
	 * new entry at the end.
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
		if (updateIfExists(key, value))
			return;
		this.size++;
		if (this.size > this.tuples.length)
			resize();
		this.tuples[this.size - 1] = new Tuple(key, value);

	}

	/**
	 * Updates entry if key exists in dictionary
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	private boolean updateIfExists(String key, String value) {
		for (int i = 0; i < this.size; i++)
			if (this.tuples[i].key.equals(key)) {
				this.tuples[i].value = value;
				return true;
			}
		return false;
	}

	/**
	 * Removes an entry from the array. First check if the key exists using
	 * linear search. If key exists, then remove the entry, else do nothing.
	 * 
	 * TODO: Reduce array size if dictionary size falls below the step size
	 * 
	 * @param key
	 */
	public void remove(String key) {
		if (key == null)
			return;
		for (int i = 0; i < this.size; i++)
			if (this.tuples[i].key.equals(key)) {
				spliceAt(i);
				return;
			}
	}

	/**
	 * Gets the maximum key from the array using linear search.
	 * 
	 * @return key
	 */
	public String maximum() {
		if (this.size == 0)
			return null;

		String max = "";
		for (int i = 0; i < this.size; i++)
			if (this.tuples[i].key.compareTo(max) >= 0) {
				max = this.tuples[i].key;
			}

		return max;
	}

	/**
	 * Gets the minimum key from the array using linear search.
	 * 
	 * @return key
	 */
	public String minimum() {
		if (this.size == 0)
			return null;

		// String min = this.keys[0];
		String min = this.tuples[0].key;
		for (int i = 1; i < this.size; i++)
			if (this.tuples[i].key.compareTo(min) <= 0) {
				min = this.tuples[i].key;
				// if (this.keys[i].compareTo(min) <= 0) {
				// min = this.keys[i];
			}

		return min;
	}

	/**
	 * Gets the largest key in the dictionary that is smaller than the input
	 * key. Returns null if the input key is lesser than or equal to the first
	 * entry.
	 * 
	 * @return key
	 */
	public String predecessor(String key) {
		if (key == null)
			return null;
		String pred = null;
		for (int i = 0; i < this.size; i++) {
			if (this.tuples[i].key.compareTo(key) >= 0)
				continue;
			if (pred == null)
				pred = this.tuples[i].key;
			else {
				if (this.tuples[i].key.compareTo(pred) > 0)
					pred = this.tuples[i].key;
			}

		}
		return pred;
	}

	/**
	 * Gets the smallest key in the dictionary that is greater than the input
	 * key. Returns null if the input key is greater than or equal to the last
	 * entry.
	 * 
	 * @return key
	 */
	public String successor(String key) {
		if (key == null)
			return null;
		String next = null;
		for (int i = 0; i < this.size; i++) {
			if (this.tuples[i].key.compareTo(key) <= 0)
				continue;
			if (next == null)
				next = this.tuples[i].key;
			else {
				if (this.tuples[i].key.compareTo(next) < 0)
					next = this.tuples[i].key;
			}

		}
		return next;
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
	 * copies the data from the original to the new.
	 */
	private void resize() {
		Tuple[] tmp = new Tuple[this.tuples.length + INITIAL_SIZE];
		System.arraycopy(tuples, 0, tmp, 0, this.tuples.length);
		this.tuples = tmp;

	}

	/**
	 * Eliminates the entry at i, by shifting all the following entries one
	 * place to the left .
	 */
	private void spliceAt(int i) {
		if (i < this.size - 1) {
			System.arraycopy(tuples, i + 1, tuples, i, this.size - i - 1);
		}
		tuples[this.size - 1] = null;
		this.size--;
	}

}
