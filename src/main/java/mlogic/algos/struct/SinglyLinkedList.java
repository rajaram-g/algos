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
	protected KeyedNode<Key, Value> root;

	protected int size = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.struct.LinkedList#root()
	 */
	@Override
	public KeyedNode<Key, Value> root() {
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
		KeyedNode<Key, Value> node = getNode(key);
		if (node != null) {
			node.setValue(value);
			return;
		}

		KeyedNode<Key, Value> newNode = new KeyedNode<Key, Value>(key, value);
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

		KeyedNode<Key, Value> newNode = new KeyedNode<Key, Value>(key, value);
		if (this.root == null) {
			this.root = newNode;
			this.size++;
		} else {
			KeyedNode<Key, Value> node = this.root;
			KeyedNode<Key, Value> prev = null;
			while (true) {
				if (key.compareTo(node.key()) == 0) {
					node.setValue(value);
					break;
				} else if (key.compareTo(node.key()) < 0) {
					insertBetween(prev, node, newNode);
					this.size++;
					break;
				}
				KeyedNode<Key, Value> next = node.next();
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
		KeyedNode<Key, Value> node = getNode(key);
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
	public KeyedNode<Key, Value> getNode(Key key) {
		if (key == null)
			return null;
		KeyedNode<Key, Value> node = this.root;
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
		KeyedNode<Key, Value> node = this.root;
		KeyedNode<Key, Value> prev = null;
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
		KeyedNode<Key, Value> node = this.root;
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
	private void insertAfter(KeyedNode<Key, Value> node, KeyedNode<Key, Value> newNode) {
		KeyedNode<Key, Value> tmp = node.next();
		node.setNext(newNode);
		newNode.setNext(tmp);
	}

	/**
	 * Inserts a node between specified nodes
	 * 
	 * @param node
	 * @param newNode
	 */
	private void insertBetween(KeyedNode<Key, Value> before, KeyedNode<Key, Value> after, KeyedNode<Key, Value> newNode) {
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
	private void delete(KeyedNode<Key, Value> node, KeyedNode<Key, Value> previous) {
		KeyedNode<Key, Value> next = node.next();
		if (previous == null) {
			this.root = next;
		} else
			previous.setNext(next);
		node = null;
	}

}
