package mlogic.algos.struct;

import java.util.Iterator;
import java.util.NoSuchElementException;

import mlogic.algos.exceptions.LimitException;

/**
 * Singly linked list with put, get and remove operations. Each node includes a
 * reference to the next item in the list.
 * 
 * @author Rajaram G
 *
 */
public class SinglyLinkedList<T> implements List<T> {

	class LinkedListIterator implements Iterator<T> {

		Node<T> current = SinglyLinkedList.this.root;

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return (current != null);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#next()
		 */
		@Override
		public T next() {
			if (current == null)
				throw new NoSuchElementException();
			T ret = current.item;
			current = current.next;
			return ret;
		}

	}

	/**
	 * First node in the list
	 */
	protected Node<T> root;

	protected int size = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.struct.List#toString()
	 */
	@Override
	public String toString() {
		if (this.root == null)
			return "[]";
		StringBuffer buf = new StringBuffer("[");
		Node<T> node = this.root;
		buf.append(node.item);
		while (node.next != null) {
			node = node.next;
			buf.append(", " + node.item);
		}
		buf.append("]");

		return buf.toString();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mlogic.algos.struct.List#get(int)
	 */
	@Override
	public T get(int index) {
		if (index < 0 || index > this.size())
			throw new IllegalArgumentException("Index " + index + " is out of range.");
		Node<T> node = this.root;
		for (int i = 1; i < index; i++)
			node = node.next;

		return node.item;
	}

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
		Node<T> prev = null;
		while (node != null) {
			if (equals(item, node.item)) {
				delete(node, prev);
				this.size--;
				break;
			}
			prev = node;
			node = node.next;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mlogic.algos.struct.List#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(T item) {
		Node<T> node = this.root;
		while (node != null) {
			if (equals(item, node.item)) {
				return true;
			}
			node = node.next;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mlogic.algos.struct.List#get(java.lang.Object)
	 */
	@Override
	public T get(T item) {
		Node<T> node = this.root;
		while (node != null) {
			if (equals(item, node.item)) {
				return node.item;
			}
			node = node.next;
		}
		return null;
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
	 * 
	 * @param item
	 * @return Node containing the specified item
	 */
	public Node<T> getNode(T item) {
		Node<T> node = this.root;
		while (node != null) {
			if (equals(item, node.item)) {
				return node;
			}
			node = node.next;
		}
		return null;
	}

	/**
	 * Checks if items are equal, handles null conditions
	 * 
	 * @param item
	 * @param node
	 * @return
	 */
	private boolean equals(T first, T second) {
		if (first == null && second == null)
			return true;
		if (first == null && second != null)
			return false;
		if (first != null && second == null)
			return false;
		return second.equals(first);
	}

	/**
	 * Removes a node by connecting its parent to its child.
	 * 
	 * @param node
	 * @param prev
	 */
	private void delete(Node<T> node, Node<T> previous) {
		Node<T> next = node.next;
		if (previous == null) {
			this.root = next;
		} else
			previous.next = next;
		node = null;
	}

}
