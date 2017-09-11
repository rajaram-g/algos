package mlogic.algos.struct;

/**
 * LIFO Stack
 * 
 * @author Rajaram G
 *
 */
public class Stack<Key extends Comparable<Key>, Value> {

	/**
	 * Top of the stack
	 */
	private Node<Key, Value> root;

	/**
	 * Size of the stack
	 */
	private int size;

	/**
	 * Returns the size of the stack
	 */
	public Integer size() {
		return this.size;
	}

	/**
	 * Adds an entry to the top of the stack
	 * 
	 * @param key
	 * @param value
	 */
	public void push(Key key, Value value) {
		Node<Key, Value> node = new Node<Key, Value>(key, value);

		if (this.root != null)
			node.setNext(this.root);
		this.root = node;
		this.size++;
	}

	/**
	 * Removes and returns a node from the top of the stack
	 * 
	 * @return node
	 */
	public Node<Key, Value> pop() {
		Node<Key, Value> node = this.root;
		if (node != null) {
			this.root = node.next();
			this.size--;
			return node;
		} else
			return null;
	}

}
