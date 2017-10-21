package mlogic.algos.struct;

/**
 * LIFO Stack
 * 
 * @author Rajaram G
 *
 */
public class Stack<T> {

	/**
	 * Top of the stack
	 */
	private Node<T> root;

	/**
	 * Size of the stack
	 */
	private int size;

	/**
	 * @return size of the stack
	 */
	public Integer size() {
		return this.size;
	}

	/**
	 * Adds an entry to the top of the stack
	 * 
	 * @param item
	 */
	public void push(T item) {
		Node<T> node = new Node<T>();
		node.item = item;

		if (this.root != null)
			node.next = this.root;
		this.root = node;
		this.size++;
	}

	/**
	 * Removes and returns an item from the top of the stack
	 * 
	 * @return node
	 */
	public T pop() {
		Node<T> node = this.root;
		if (node != null) {
			this.root = node.next;
			this.size--;
			return node.item;
		} else
			return null;
	}

}
