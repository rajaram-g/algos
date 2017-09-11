package mlogic.algos.struct;

/**
 * FIFO Queue
 * 
 * @author Rajaram G
 *
 */
public class Queue<Key extends Comparable<Key>, Value> {

	/**
	 * First item in the queue
	 */
	private Node<Key, Value> root;

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
	 * @param key
	 * @param value
	 */
	public void enqueue(Key key, Value value) {
		Node<Key, Value> newNode = new Node<Key, Value>(key, value);

		if (this.root == null)
			this.root = newNode;
		else {
			Node<Key, Value> node = this.root;
			while (node.hasNext())
				node = node.next();
			node.setNext(newNode);
		}
		this.size++;
	}

	/**
	 * Removes and returns the top of the queue
	 * 
	 * @return node
	 */
	public Node<Key, Value> dequeue() {
		Node<Key, Value> node = this.root;
		if (node != null) {
			this.root = node.next();
			this.size--;
			return node;
		} else
			return null;
	}

}
