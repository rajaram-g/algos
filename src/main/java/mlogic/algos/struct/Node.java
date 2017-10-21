package mlogic.algos.struct;

/**
 * Container for an item in a linked list and the reference to the next node
 * 
 * @author Rajaram G
 *
 */
public class Node<T> {

	/**
	 * Reference to the previous item in list - used for Doubly Linked Lists
	 */
	public Node<T> previous;

	/**
	 * Reference to the next item
	 */
	public Node<T> next;

	/**
	 * Item in stack
	 */
	public T item;

}
