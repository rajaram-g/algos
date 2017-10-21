package mlogic.algos.struct;

import mlogic.algos.exceptions.LimitException;

/**
 * Singly linked list with put, get and remove operations. Each node includes a
 * reference to the next item in the list.
 * 
 * @author Rajaram G
 *
 */
public class SortedDoublyLinkedList<T extends Comparable<T>> extends DoublyLinkedList2<T> {

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
				insertBefore(node, newNode);
				this.size++;
			} else {
				while (true) {
					if (node.item != null && item.compareTo(node.item) < 0) {
						insertBefore(node, newNode);
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

	/**
	 * Inserts new node after a specified node.
	 * 
	 * @param node
	 * @param newNode
	 */
	private void insertAfter(Node<T> node, Node<T> newNode) {
		Node<T> tmp = node.next;
		node.next = newNode;
		newNode.previous = node;
		newNode.next = tmp;
		if (tmp != null)
			tmp.previous = newNode;
	}

	/**
	 * Inserts a node before specified node
	 * 
	 * @param node
	 * @param newNode
	 */
	private void insertBefore(Node<T> node, Node<T> newNode) {
		if (node == null)
			this.root = newNode;
		else {

			Node<T> tmp = node.previous;
			newNode.next = node;
			node.previous = newNode;
			newNode.previous = tmp;
			if (tmp == null) {
				this.root = newNode;
			} else {
				tmp.next = newNode;
			}
		}

	}

}
