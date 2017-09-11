package mlogic.algos.dictionary;

import mlogic.algos.struct.DoublyLinkedList;
import mlogic.algos.struct.Node;

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
	private DoublyLinkedList<String, String> dictionary;

	/**
	 * Constructor
	 */
	public UnsortedLinkedListDictionary() {
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
	 * a new entry at the head of the list.
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
	 * Gets the maximum key from the list through traversal.
	 * 
	 * @return key
	 */
	public String maximum() {
		Node<String, String> node = this.dictionary.root();
		if (node == null)
			return null;
		Node<String, String> max = node;
		while (node.hasNext()) {
			node = node.next();
			if (node.key().compareTo(max.key()) > 0)
				max = node;
		}
		return max.key();
	}

	/**
	 * Gets the minimum key from the list through traversal.
	 * 
	 * @return key
	 */
	public String minimum() {
		Node<String, String> node = this.dictionary.root();
		if (node == null)
			return null;
		Node<String, String> min = node;
		while (node.hasNext()) {
			node = node.next();
			if (node.key().compareTo(min.key()) < 0)
				min = node;
		}
		return min.key();
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
		Node<String, String> mark = this.dictionary.getNode(key);
		if (mark == null)
			return null;
		Node<String, String> node = this.dictionary.root();
		Node<String, String> pred = null;
		while (node != null) {
			if (node.key().compareTo(mark.key()) < 0) {
				if (pred == null || node.key().compareTo(pred.key()) > 0)
					pred = node;
			}
			node = node.next();
		}
		if (pred != null)
			return pred.key();
		else
			return null;
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
		Node<String, String> mark = this.dictionary.getNode(key);
		if (mark == null)
			return null;
		Node<String, String> node = this.dictionary.root();
		Node<String, String> succ = null;
		while (node != null) {
			if (node.key().compareTo(mark.key()) > 0) {
				if (succ == null || node.key().compareTo(succ.key()) < 0)
					succ = node;
			}
			node = node.next();
		}
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
