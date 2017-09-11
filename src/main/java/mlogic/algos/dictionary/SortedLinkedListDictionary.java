package mlogic.algos.dictionary;

import mlogic.algos.struct.DoublyLinkedList;
import mlogic.algos.struct.Node;

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
	private DoublyLinkedList<String, String> dictionary;

	/**
	 * Constructor
	 */
	public SortedLinkedListDictionary() {
		this.dictionary = new DoublyLinkedList<String, String>();
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

		return this.dictionary.get(key);
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
		this.dictionary.orderedPut(key, value);
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
		this.dictionary.remove(key);
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
		Node<String, String> node = this.dictionary.root();
		if (node == null)
			return null;
		while (true) {
			if (node.next() != null)
				node = node.next();
			else
				break;
		}
		return node.key();
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
		Node<String, String> node = this.dictionary.root();
		if (node == null)
			return null;
		else
			return node.key();
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
		Node<String, String> mark = this.dictionary.getNode(key);
		if (mark == null)
			return null;
		Node<String, String> pred = mark.previous();

		if (pred != null)
			return pred.key();
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
		Node<String, String> mark = this.dictionary.getNode(key);
		if (mark == null)
			return null;
		Node<String, String> succ = mark.next();
		if (succ != null)
			return succ.key();
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
