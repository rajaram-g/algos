package mlogic.algos.struct;

import mlogic.algos.exceptions.LimitException;

/**
 * Doubly linked list with put, get and remove operations. Each node includes a
 * reference to the next and previous items in the list.
 * 
 * @author Rajaram G
 *
 */
public class DoublyLinkedList<Key extends Comparable<Key>, Value> extends SinglyLinkedList<Key, Value> {

	/**
	 * Inserts the new item at the head of the list - used for unsorted linked
	 * lists.
	 * 
	 * @param key
	 * @param value
	 * @throws LimitException
	 */
	public void put(Key key, Value value) throws LimitException {
		if (size == Integer.MAX_VALUE)
			throw new LimitException("List size is limited by the maximum integer value...");
		if (key == null)
			return;
		Node<Key, Value> node = getNode(key);
		if (node != null) {
			node.setValue(value);
			return;
		}

		Node<Key, Value> newNode = new Node<Key, Value>(key, value);
		newNode.setNext(this.root);
		if (this.root != null)
			this.root.setPrevious(newNode);
		this.root = newNode;
		this.size++;
	}

	/**
	 * Inserts the new item in proper order of key - used for sorted linked
	 * lists.
	 * 
	 * @param key
	 * @param value
	 */
	public void orderedPut(Key key, Value value) {
		if (key == null)
			return;

		Node<Key, Value> newNode = new Node<Key, Value>(key, value);
		if (this.root == null) {
			this.root = newNode;
			this.size++;
		} else {
			Node<Key, Value> node = this.root;
			while (true) {
				if (key.compareTo(node.key()) == 0) {
					node.setValue(value);
					break;
				} else if (key.compareTo(node.key()) < 0) {
					insertBefore(node, newNode);
					this.size++;
					break;
				}
				Node<Key, Value> next = node.next();
				if (next == null) {
					insertAfter(node, newNode);
					this.size++;
					break;
				}
				node = next;
			}
		}
	}

	/**
	 * Traverse list using key and removes the node if key is found.
	 * 
	 * @param key
	 */
	public void remove(Key key) {
		Node<Key, Value> node = this.root;
		while (node != null) {
			if (node.key().equals(key)) {
				delete(node);
				this.size--;
				break;
			}
			node = node.next();
		}
	}

	/**
	 * Inserts new node after a specified node.
	 * 
	 * @param node
	 * @param newNode
	 */
	private void insertAfter(Node<Key, Value> node, Node<Key, Value> newNode) {
		Node<Key, Value> tmp = node.next();
		node.setNext(newNode);
		newNode.setPrevious(node);
		newNode.setNext(tmp);
		if (tmp != null)
			tmp.setPrevious(newNode);

	}

	/**
	 * Inserts a node before a specified node
	 * 
	 * @param node
	 * @param newNode
	 */
	private void insertBefore(Node<Key, Value> node, Node<Key, Value> newNode) {
		Node<Key, Value> tmp = node.previous();
		newNode.setNext(node);
		node.setPrevious(newNode);
		newNode.setPrevious(tmp);
		if (tmp == null) {
			this.root = newNode;
		} else {
			tmp.setNext(newNode);
		}

	}

	/**
	 * Removes a node by connecting its parent to its child.
	 * 
	 * @param node
	 */
	private void delete(Node<Key, Value> node) {
		Node<Key, Value> next = node.next();
		Node<Key, Value> previous = node.previous();
		if (previous == null) {
			this.root = next;
		} else
			previous.setNext(next);
		if (next != null)
			next.setPrevious(previous);
		node = null;
	}

}
