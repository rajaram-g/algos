package mlogic.algos.struct;

/**
 * Represents a vertex in a graph or item in a linked list. Has references to
 * the next and previous items in the list
 * 
 * @author Rajaram G
 *
 */
public class Node<Key extends Comparable<Key>, Value> {

	/**
	 * Reference to the previous item in list - used for Doubly Linked Lists
	 */
	private Node<Key, Value> previous;

	/**
	 * Reference to the next item in list
	 */
	private Node<Key, Value> next;

	/**
	 * Node key
	 */
	private Key key;

	/**
	 * Node value
	 */
	private Value value;

	/**
	 * Constructor
	 * 
	 * @param key
	 * @param value
	 */
	public Node(Key key, Value value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Returns the node key
	 * 
	 * @return key
	 */
	public Key key() {
		return this.key;
	}

	/**
	 * Returns the node value
	 * 
	 * @return value
	 */
	public Value value() {
		return this.value;
	}

	/**
	 * Updates the node value
	 * 
	 * @param value
	 */
	public void setValue(Value value) {
		this.value = value;
	}

	/**
	 * Does this node have a predecessor?
	 */
	public boolean hasPrevious() {
		return this.previous != null;
	}

	/**
	 * Does this node have a successor?
	 */
	public boolean hasNext() {
		return this.next != null;
	}

	/**
	 * Returns the next node
	 * 
	 * @return node
	 */
	public Node<Key, Value> next() {
		return this.next;
	}

	/**
	 * Sets the next node
	 * 
	 * @param newNode
	 */
	public void setNext(Node<Key, Value> newNode) {
		this.next = newNode;

	}

	/**
	 * Returns the previous node
	 * 
	 * @return node
	 */
	public Node<Key, Value> previous() {
		return this.previous;
	}

	/**
	 * Sets the previous node
	 * 
	 * @param newNode
	 */
	public void setPrevious(Node<Key, Value> newNode) {
		this.previous = newNode;

	}

}
