package mlogic.algos.struct;

import mlogic.algos.exceptions.LimitException;

/**
 * Singly linked list with put, get and remove operations. Each node includes a
 * reference to the next item in the list.
 * 
 * @author Rajaram G
 *
 */
public class SortedSinglyLinkedList<T extends Comparable<T>> extends SinglyLinkedList<T> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see mlogic.algos.struct.List#put(java.lang.Object)
	 */
	@Override
	public void put(T item) {
		if (size == Integer.MAX_VALUE)
			throw new LimitException("List size is limited by the maximum integer value...");

		Node<T> newNode = new Node<T>();
		newNode.item = item;
		if (this.root == null) {
			this.root = newNode;
			this.size++;
		} else {
			Node<T> existing = getNode(item);
			if (existing != null) {
				existing.item = item;
				return;
			}

			Node<T> node = this.root;
			Node<T> prev = null;
			if (item == null) {
				insertBetween(prev, node, newNode);
				this.size++;
			} else {
				while (true) {
					if (node.item != null && item.compareTo(node.item) < 0) {
						insertBetween(prev, node, newNode);
						this.size++;
						break;
					}
					Node<T> next = node.next;
					if (next == null) {
						insertAfter(node, newNode);
						this.size++;
						break;
					}
					prev = node;
					node = next;

				}

			}

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mlogic.algos.struct.List#size()
	 */
	@Override
	public Integer size() {
		return size;
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
	 * Inserts new node after a specified node.
	 * 
	 * @param node
	 * @param newNode
	 */
	private void insertAfter(Node<T> node, Node<T> newNode) {
		Node<T> tmp = node.next;
		node.next = newNode;
		newNode.next = tmp;
	}

	/**
	 * Inserts a node between specified nodes
	 * 
	 * @param node
	 * @param newNode
	 */
	private void insertBetween(Node<T> before, Node<T> after, Node<T> newNode) {
		if (before == null)
			this.root = newNode;
		else
			before.next = newNode;
		newNode.next = after;

	}

}
