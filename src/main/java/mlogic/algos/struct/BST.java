package mlogic.algos.struct;

/**
 * Specifies the methods to be implemented by any Binary Search Tree
 * implementation
 * 
 * @author Rajaram G
 *
 * @param <Key>
 *            Key by which to index and retrieve objects in the tree
 * @param <Value>
 *            The object of interest
 */
public interface BST<Key extends Comparable<Key>, Value> {

	/**
	 * @return size of the tree
	 */
	Integer size();

	/**
	 * @return height of the tree
	 */
	Integer height();

	/**
	 * @return string representing the tree (root and all its descendants)
	 */
	String toString();

	/**
	 * Traverses the binary tree using the key. If key is found, updates the
	 * corresponding node with the new value if the key is found.
	 * 
	 * @param key
	 * @param value
	 */
	void put(Key key, Value value);

	/**
	 * Traverses the binary tree using the key, returns the corresponding value
	 * if the key is found, or null if the key is not found.
	 * 
	 * @param key
	 * @return node matching the specified key
	 */
	BinaryNode<Key, Value> get(Key key);

	/**
	 * Traverses the binary tree using the key If key is found, removes the
	 * corresponding node wand re-balances the tree. Does nothing if the key is
	 * not found.
	 * 
	 * @param key
	 */
	void remove(Key key);

	/**
	 * Returns the right-most node in the tree
	 * 
	 * @return value
	 */
	BinaryNode<Key, Value> maximum();

	/**
	 * Returns the left-most node in the tree
	 * 
	 * @return value
	 */
	BinaryNode<Key, Value> minimum();

	/**
	 * Finds a node using the given key and returns the node just to the left of
	 * it in the tree. Returns null if the given key is not found or it is the
	 * left most entry in the tree.
	 * 
	 * @param node
	 * @return node containing key and value for predecessor
	 */
	BinaryNode<Key, Value> predecessor(BinaryNode<Key, Value> node);

	/**
	 * Finds a node using the given key and returns the node just to the right
	 * of it in the tree. Returns null if the given key is not found or it is
	 * the right most entry in the tree.
	 * 
	 * @param node
	 * @return node containing key and value for successor
	 */
	BinaryNode<Key, Value> successor(BinaryNode<Key, Value> node);

}