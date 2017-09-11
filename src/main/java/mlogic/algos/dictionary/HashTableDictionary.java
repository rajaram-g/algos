package mlogic.algos.dictionary;

import mlogic.algos.struct.ChainedHashTable;
import mlogic.algos.struct.HashTable;
import mlogic.algos.struct.Tuple;

/**
 * Dictionary implementation based on a chained HashTable. Provides average
 * O(n/m) performance for gets and puts, where n is the number of items and m is
 * the number of buckets.
 *
 * @author Rajaram G
 *
 */
public class HashTableDictionary implements Dictionary<String, String> {

	/**
	 * Hash table to store the entries
	 */
	protected HashTable<String, String> dictionary;

	/**
	 * Constructor
	 */
	public HashTableDictionary() {
		this.dictionary = new ChainedHashTable<String, String>();
	}

	/**
	 * Looks up hash table using the key, returns the corresponding value if the
	 * key is found, or null if the key is not found.
	 * <p>
	 * Operation takes O(n/m) in the average case and O(n) in the worst case.
	 * 
	 * @param key
	 * @return value
	 */
	@Override
	public String get(String key) {
		if (key == null)
			return null;
		return this.dictionary.get(key);

	}

	/**
	 * Looks up hash table using the key. If key is found, updates the
	 * corresponding entry with the new value. If the key is not found, creates
	 * a new entry.
	 * <p>
	 * Operation takes O(n/m) in the average case and O(n) in the worst case.
	 * 
	 * @param key
	 * @param value
	 */
	public void put(String key, String value) {
		if (key == null)
			return;
		this.dictionary.put(key, value);
	}

	/**
	 * Looks up hash table using the key. If key is found, removes the
	 * corresponding entry. Does nothing if the key is not found.
	 * <p>
	 * Operation takes O(n/m) in the average case and O(n) in the worst case.
	 * 
	 * @param key
	 */
	public void remove(String key) {
		if (key == null)
			return;
		this.dictionary.remove(key);
	}

	/**
	 * Finds the maximum value using linear search.
	 * <p>
	 * Operation takes O(n + m) ??
	 * 
	 * @return value
	 */
	public String maximum() {
		Tuple<String, String> tuple = this.dictionary.maximum();
		if (tuple != null)
			return tuple.key;
		else
			return null;
	}

	/**
	 * Finds the minimum value using linear search.
	 * <p>
	 * Operation takes O(n + m) ??
	 * 
	 * @return value
	 */
	public String minimum() {
		Tuple<String, String> tuple = this.dictionary.minimum();
		if (tuple != null)
			return tuple.key;
		else
			return null;
	}

	/**
	 * Finds the predecessor using linear search.
	 * <p>
	 * Operation takes O(n)
	 * 
	 * @param key
	 * @return predecessor key
	 */
	public String predecessor(String key) {
		if (key == null)
			return null;
		Tuple<String, String> pred = this.dictionary.predecessor(key);

		if (pred != null)
			return pred.key;
		else
			return null;
	}

	/**
	 * Finds the successor using linear search.
	 * <p>
	 * Operation takes O(n)
	 * 
	 * @param key
	 * @return value
	 */
	public String successor(String key) {
		if (key == null)
			return null;
		Tuple<String, String> successor = this.dictionary.successor(key);

		if (successor != null)
			return successor.key;
		else
			return null;
	}

	/**
	 * Finds the size of the dictionary
	 */
	public Integer size() {
		return this.dictionary.size();
	}

}
