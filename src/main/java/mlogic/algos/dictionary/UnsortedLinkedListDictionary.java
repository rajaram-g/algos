package mlogic.algos.dictionary;

import mlogic.algos.struct.DoublyLinkedList;
import mlogic.algos.struct.List;
import mlogic.algos.struct.Tuple;

/**
 * Dictionary implementation based on an unsorted doubly linked list
 * 
 * @author Rajaram G
 *
 */
public class UnsortedLinkedListDictionary implements Dictionary<String, String> {

	/**
	 * Doubly linked list to store the dictionary entries
	 */
	// private DoublyLinkedList<String, String> dictionary;
	private List<Tuple<String, String>> dictionary;

	/**
	 * Constructor
	 */
	public UnsortedLinkedListDictionary() {
		// this.dictionary = new DoublyLinkedList<String, String>();
		this.dictionary = new DoublyLinkedList<Tuple<String, String>>();
	}

	/**
	 * Gets an entry from the list through traversal
	 * 
	 * @param key
	 * @return value
	 */
	public String get(String key) {
		if (key == null)
			return null;
		Tuple<String, String> find = new Tuple<String, String>(key, null);
		Tuple<String, String> found = this.dictionary.get(find);
		if (found == null)
			return null;
		return found.value;
	}

	/**
	 * Puts an entry into the list. First check if the key exists by traversing
	 * the list. If key exists, then update the corresponding value, else insert
	 * a new entry at the head of the list.
	 * 
	 * @param key
	 * @param value
	 */
	public void put(String key, String value) {
		if (key == null)
			return;
		this.dictionary.put(new Tuple<String, String>(key, value));
	}

	/**
	 * Removes an entry from the list. First check if the key exists by
	 * traversing the linked list. If key exists, then remove the entry, else do
	 * nothing.
	 * 
	 * @param key
	 */
	public void remove(String key) {
		if (key == null)
			return;
		this.dictionary.remove(new Tuple<String, String>(key, null));
	}

	/**
	 * Gets the maximum key from the list through traversal.
	 * 
	 * @return key
	 */
	public String maximum() {
		boolean first = true;
		String max = null;
		for (Tuple<String, String> item : this.dictionary) {
			if (first) {
				max = item.key;
				first = false;
			} else if (max.compareTo(item.key) < 0)
				max = item.key;
		}
		return max;
	}

	/**
	 * Gets the minimum key from the list through traversal.
	 * 
	 * @return key
	 */
	public String minimum() {
		boolean first = true;
		String min = null;
		for (Tuple<String, String> item : this.dictionary) {
			if (first) {
				min = item.key;
				first = false;
			} else if (min.compareTo(item.key) > 0)
				min = item.key;
		}
		return min;

	}

	/**
	 * Gets the largest key in the dictionary that is smaller than the input
	 * key. Returns null if the input key is not present or is the first entry.
	 * <p>
	 * Requires two traversals through the list, one to locate the input key and
	 * second to locate its predecessor.
	 * <p>
	 * TODO: Return predecessor even if input key is not present i.e. floor
	 * 
	 * @return key
	 */
	public String predecessor(String key) {
		if (key == null)
			return null;
		Tuple<String, String> found = this.dictionary.get(new Tuple<String, String>(key, null));
		if (found == null)
			return null;

		boolean first = true;
		String pred = null;
		for (Tuple<String, String> item : this.dictionary) {
			if (item.key.compareTo(key) < 0)
				if (pred == null || item.key.compareTo(pred) > 0)
					pred = item.key;
		}
		return pred;

	}

	/**
	 * Gets the smallest key in the dictionary that is greater than the input
	 * key. Returns null if the input key is not present or is the last entry.
	 * <p>
	 * Requires two traversals through the list, one to locate the input key and
	 * second to locate its successor.
	 * <p>
	 * TODO: Return successor even if input key is not present i.e. ceiling
	 * 
	 * @return key
	 */
	public String successor(String key) {
		if (key == null)
			return null;
		Tuple<String, String> found = this.dictionary.get(new Tuple<String, String>(key, null));
		if (found == null)
			return null;

		boolean first = true;
		String successor = null;
		for (Tuple<String, String> item : this.dictionary) {
			if (item.key.compareTo(key) > 0)
				if (successor == null || item.key.compareTo(successor) < 0)
					successor = item.key;
		}
		return successor;

	}

	/**
	 * Returns the size of the dictionary
	 * 
	 * @return size
	 */
	public Integer size() {
		return this.dictionary.size();
	}

}
