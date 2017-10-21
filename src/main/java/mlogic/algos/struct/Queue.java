package mlogic.algos.struct;

/**
 * FIFO Queue
 * 
 * @author Rajaram G
 *
 */
public class Queue<T> {

	/**
	 * First item in the queue
	 */
	private Node<T> root;

	/**
	 * Size of queue
	 */
	private int size = 0;

	/**
	 * Returns the size of the queue
	 */
	public Integer size() {
		return this.size;
	}

	/**
	 * Adds an entry to the bottom of the queue
	 * 
	 * @param item
	 */
	public void enqueue(T item) {
		Node<T> newNode = new Node<T>();
		newNode.item = item;

		if (this.root == null)
			this.root = newNode;
		else {
			Node<T> node = this.root;
			while (node.next != null)
				node = node.next;
			node.next = newNode;
		}
		this.size++;
	}

	/**
	 * Removes and returns the top of the queue
	 * 
	 * @return node
	 */
	public T dequeue() {
		Node<T> node = this.root;
		if (node != null) {
			this.root = node.next;
			this.size--;
			return node.item;
		} else
			return null;
	}

}
