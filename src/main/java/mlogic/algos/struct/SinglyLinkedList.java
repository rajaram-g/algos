package mlogic.algos.struct;

import mlogic.algos.exceptions.LimitException;

/**
 * Singly linked list with put, get and remove operations. Each node includes a
 * reference to the next item in the list.
 * 
 * @author Rajaram G
 *
 */
public class SinglyLinkedList<Key extends Comparable<Key>, Value> implements LinkedList<Key, Value> {

	/**
	 * First node in the list
	 */
	protected Node<Key, Value> root;

	protected int size = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.struct.LinkedList#root()
	 */
	@Override
	public Node<Key, Value> root() {
		return this.root;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.struct.LinkedList#size()
	 */
	@Override
	public Integer size() {
		return this.size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.struct.LinkedList#put(Key, Value)
	 */
	@Override
	public void put(Key key, Value value) {
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
		this.root = newNode;
		this.size++;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.struct.LinkedList#orderedPut(Key, Value)
	 */
	@Override
	public void orderedPut(Key key, Value value) {
		if (key == null)
			return;

		Node<Key, Value> newNode = new Node<Key, Value>(key, value);
		if (this.root == null) {
			this.root = newNode;
			this.size++;
		} else {
			Node<Key, Value> node = this.root;
			Node<Key, Value> prev = null;
			while (true) {
				if (key.compareTo(node.key()) == 0) {
					node.setValue(value);
					break;
				} else if (key.compareTo(node.key()) < 0) {
					insertBetween(prev, node, newNode);
					this.size++;
					break;
				}
				Node<Key, Value> next = node.next();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.struct.LinkedList#get(Key)
	 */
	@Override
	public Value get(Key key) {
		Node<Key, Value> node = getNode(key);
		if (node == null)
			return null;
		else
			return node.value();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.struct.LinkedList#getNode(Key)
	 */
	@Override
	public Node<Key, Value> getNode(Key key) {
		if (key == null)
			return null;
		Node<Key, Value> node = this.root;
		while (node != null) {
			if (node.key().equals(key))
				return node;
			node = node.next();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.struct.LinkedList#remove(Key)
	 */
	@Override
	public void remove(Key key) {
		Node<Key, Value> node = this.root;
		Node<Key, Value> prev = null;
		while (node != null) {
			if (node.key().equals(key)) {
				delete(node, prev);
				this.size--;
				break;
			}
			prev = node;
			node = node.next();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.struct.LinkedList#toString()
	 */
	@Override
	public String toString() {
		if (this.root == null)
			return "[]";
		StringBuffer buf = new StringBuffer("[");
		Node<Key, Value> node = this.root;
		buf.append(node.value());
		while (node.hasNext()) {
			node = node.next();
			buf.append(", " + node.value());
		}
		buf.append("]");

		return buf.toString();

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
		newNode.setNext(tmp);
	}

	/**
	 * Inserts a node between specified nodes
	 * 
	 * @param node
	 * @param newNode
	 */
	private void insertBetween(Node<Key, Value> before, Node<Key, Value> after, Node<Key, Value> newNode) {
		if (before == null)
			this.root = newNode;
		else
			before.setNext(newNode);
		newNode.setNext(after);

	}

	/**
	 * Removes a node by connecting its parent to its child.
	 * 
	 * @param node
	 */
	private void delete(Node<Key, Value> node, Node<Key, Value> previous) {
		Node<Key, Value> next = node.next();
		if (previous == null) {
			this.root = next;
		} else
			previous.setNext(next);
		node = null;
	}

}
