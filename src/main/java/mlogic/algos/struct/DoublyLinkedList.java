package mlogic.algos.struct;

import mlogic.algos.exceptions.LimitException;

/**
 * Doubly linked list with put, get and remove operations. Each node includes a
 * reference to the next and previous items in the list.
 * 
 * @author Rajaram G
 *
 */
public class DoublyLinkedList<T> extends SinglyLinkedList<T> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see mlogic.algos.struct.List#put(java.lang.Object)
	 */
	@Override
	public void put(T item) {
		if (size == Integer.MAX_VALUE)
			throw new LimitException("List size is limited by the maximum integer value...");

		Node<T> existing = getNode(item);
		if (existing != null) {
			existing.item = item;
			return;
		}

		Node<T> newNode = new Node<T>();
		newNode.item = item;
		newNode.next = this.root;
		if (this.root != null)
			this.root.previous = newNode;
		this.root = newNode;
		this.size++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mlogic.algos.struct.List#remove(java.lang.Object)
	 */
	@Override
	public void remove(T item) {
		Node<T> node = this.root;
		while (node != null) {
			if (node.item.equals(item)) {
				delete(node);
				this.size--;
				break;
			}
			node = node.next;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mlogic.algos.struct.List#add(mlogic.algos.struct.List)
	 */
	@Override
	public void add(List<T> list) {
		for (T item : list)
			this.put(item);

	}

	/**
	 * Removes a node by connecting its parent to its child.
	 * 
	 * @param node
	 * @param prev
	 */
	private void delete(Node<T> node) {
		Node<T> next = node.next;
		Node<T> previous = node.previous;
		if (previous == null) {
			this.root = next;
		} else
			previous.next = next;
		if (next != null)
			next.previous = previous;
		node = null;
	}

}
