package mlogic.algos.dictionary;

import mlogic.algos.struct.BST;
import mlogic.algos.struct.BinaryNode;
import mlogic.algos.struct.BinarySearchTree;
import mlogic.algos.struct.Tuple;

/**
 * Dictionary implementation based on an unbalanced Binary Search Tree.
 *
 * @author Rajaram G
 *
 */
public class BSTDictionary implements Dictionary<String, String> {

	private class Entry implements Comparable<Entry> {

		private String key;

		private String value;

		/**
		 * @param key
		 * @param value
		 */
		public Entry(String key, String value) {
			this.key = key;
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		@Override
		public int compareTo(Entry o) {
			return this.key.compareTo(o.key);
		}

		@Override
		public boolean equals(Object o) {

			if (o instanceof Entry && this.key.equals(((Entry) o).key))
				return true;
			return false;
		}

	}

	/**
	 * Binary tree structure to store the entries
	 */
	protected BST<Tuple<String, String>> dictionary;

	/**
	 * Constructor
	 */
	public BSTDictionary() {
		this.dictionary = new BinarySearchTree<Tuple<String, String>>();
	}

	/**
	 * Traverses the binary tree using the key, returns the corresponding value
	 * if the key is found, or null if the key is not found.
	 * 
	 * @param key
	 * @return value
	 */
	@Override
	public String get(String key) {
		BinaryNode<Tuple<String, String>> node = this.dictionary.get(new Tuple<String, String>(key, null));
		if (node != null)
			return node.item.value;
		else
			return null;

	}

	/**
	 * Traverses the binary tree using the key. If key is found, updates the
	 * corresponding node with the new value. If the key is not found, inserts a
	 * new leaf node and re-balances the tree.
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
	 * Traverses the binary tree using the key. If key is found, removes the
	 * corresponding node and re-balances the tree. Does nothing if the key is
	 * not found.
	 * 
	 * @param key
	 */
	public void remove(String key) {
		if (key == null)
			return;
		this.dictionary.remove(new Tuple<String, String>(key, null));
	}

	/**
	 * Returns the value from the right-most node in the tree.
	 * 
	 * @return value
	 */
	public String maximum() {
		BinaryNode<Tuple<String, String>> node = this.dictionary.maximum();
		if (node != null)
			return node.item.key;
		else
			return null;
	}

	/**
	 * Returns the value from the left-most node in the tree.
	 * 
	 * @return value
	 */
	public String minimum() {
		BinaryNode<Tuple<String, String>> node = this.dictionary.minimum();
		if (node != null)
			return node.item.key;
		else
			return null;
	}

	/**
	 * Finds a node using the given key and returns value from the node just to
	 * the left of it in the tree. Returns null if the given key is not found or
	 * it is the left most entry in the tree.
	 * <p>
	 * TODO: Return predecessor, even if input key is not present in the tree
	 * i.e. get floor
	 * 
	 * @param key
	 * @return value
	 */
	public String predecessor(String key) {
		if (key == null)
			return null;
		BinaryNode<Tuple<String, String>> mark = this.dictionary.get(new Tuple<String, String>(key, null));
		if (mark == null)
			return null;
		BinaryNode<Tuple<String, String>> pred = this.dictionary.predecessor(mark);

		if (pred != null)
			return pred.item.key;
		else
			return null;
	}

	/**
	 * Finds a node using the given key and returns value from the node just to
	 * the right of it in the tree. Returns null if the given key is not found
	 * or it is the right most entry in the tree.
	 * <p>
	 * TODO: Return successor, even if input key is not present in the tree i.e.
	 * get ceiling
	 * 
	 * @param key
	 * @return value
	 */
	public String successor(String key) {
		if (key == null)
			return null;
		BinaryNode<Tuple<String, String>> mark = this.dictionary.get(new Tuple<String, String>(key, null));
		if (mark == null)
			return null;
		BinaryNode<Tuple<String, String>> succ = this.dictionary.successor(mark);
		if (succ != null)
			return succ.item.key;
		else
			return null;
	}

	/**
	 * Returns the size of the dictionary
	 */
	public Integer size() {
		return this.dictionary.size();
	}

	/**
	 * Prints a string describing the node and its descendants
	 */
	public void print() {
		System.out.println(this.dictionary.toString());

	}

}
