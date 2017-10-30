package mlogic.algos.struct;

import java.util.NoSuchElementException;

import mlogic.algos.exceptions.ElementAlreadyExistsException;

/**
 * Specifies the methods to be implemented by any Binary Search Tree
 * implementation
 * 
 * @author Rajaram G
 *
 * @param <T>
 *            Item to be stored in the tree
 */
public interface BST<T extends Comparable<T>> {

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
	 * Inserts item if it does not already exist in the binary search tree.
	 * 
	 * @param item
	 */
	void put(T item) throws ElementAlreadyExistsException;

	/**
	 * Traverses the binary tree using the item and returns the containing node
	 * if the item is found.
	 * 
	 * @param item
	 * @return node containing the specified item
	 */
	BinaryNode<T> get(T item) throws NoSuchElementException;

	/**
	 * Traverses the binary tree using item and removes the containing node and
	 * re-balances the tree.
	 * 
	 * @param item
	 */
	void remove(T item) throws NoSuchElementException;;

	/**
	 * Returns the right-most node in the tree
	 * 
	 * @return value
	 */
	BinaryNode<T> maximum();

	/**
	 * Returns the left-most node in the tree
	 * 
	 * @return value
	 */
	BinaryNode<T> minimum();

	/**
	 * Finds a node using the given item and returns the node just to the left
	 * of it in the tree. Returns null if the given item is not found or it is
	 * the left most entry in the tree.
	 * 
	 * @param node
	 * @return node containing predecessor item
	 */
	BinaryNode<T> predecessor(BinaryNode<T> node);

	/**
	 * Finds a node using the given item and returns the node just to the right
	 * of it in the tree. Returns null if the given item is not found or it is
	 * the right most entry in the tree.
	 * 
	 * @param node
	 * @return node containing successor item
	 */
	BinaryNode<T> successor(BinaryNode<T> node);

}