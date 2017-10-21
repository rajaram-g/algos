package mlogic.algos.dictionary;

import java.util.Iterator;

import mlogic.algos.struct.Node;
import mlogic.algos.struct.SortedDoublyLinkedList;
import mlogic.algos.struct.Tuple;

/**
 * Dictionary implementation based on a sorted doubly-linked list
 * 
 * @author Rajaram G
 *
 */
public class SortedLinkedListDictionary implements Dictionary<String, String> {

	/**
	 * Doubly linked list to store the dictionary entries
	 */
	private SortedDoublyLinkedList<Tuple<String, String>> dictionary;

	/**
	 * Constructor
	 */
	public SortedLinkedListDictionary() {
		this.dictionary = new SortedDoublyLinkedList<Tuple<String, String>>();
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
	 * a new entry in proper order.
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
	 * Gets the maximum key from the list.
	 * <p>
	 * As the array is maintained in sorted order through puts and removes, this
	 * will always be the last entry in the dictionary.
	 * 
	 * @return key
	 */
	public String maximum() {
		Iterator<Tuple<String, String>> iter = this.dictionary.iterator();
		Tuple<String, String> item = null;
		while (iter.hasNext())
			item = iter.next();
		return item.key;
	}

	/**
	 * Gets the minimum key from the list.
	 * <p>
	 * As the array is maintained in sorted order through puts and removes, this
	 * will always be the first entry in the dictionary.
	 * 
	 * @return key
	 */
	public String minimum() {
		Iterator<Tuple<String, String>> iter = this.dictionary.iterator();
		Tuple<String, String> item = null;
		if (iter.hasNext())
			return iter.next().key;
		else
			return null;
	}

	/**
	 * Gets the largest key in the dictionary that is smaller than the input
	 * key. Returns null if the input key is not present or is the first entry.
	 * <p>
	 * As the list is maintained in sorted order, the predecessor will be found
	 * at the node previous to the node corresponding to the input key.
	 * 
	 * @return key
	 */
	public String predecessor(String key) {
		if (key == null)
			return null;
		Node<Tuple<String, String>> node = this.dictionary.getNode(new Tuple<String, String>(key, null));
		if (node == null)
			return null;

		Node<Tuple<String, String>> pred = node.previous;

		if (pred != null)
			return pred.item.key;
		else
			return null;
	}

	/**
	 * Gets the smallest key in the dictionary that is greater than the input
	 * key. Returns null if the input key is not present or is the last entry.
	 * <p>
	 * As the list is maintained in sorted order, the successor will be found at
	 * the node next after the node corresponding to the input key.
	 * 
	 * @return key
	 */
	public String successor(String key) {
		if (key == null)
			return null;
		Node<Tuple<String, String>> node = this.dictionary.getNode(new Tuple<String, String>(key, null));
		if (node == null)
			return null;

		Node<Tuple<String, String>> next = node.next;

		if (next != null)
			return next.item.key;
		else
			return null;

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
