package mlogic.algos.struct;

import mlogic.algos.struct.BinaryNode.Orientation;

/**
 * Stores a collection of items such that they can be dequeued in their natural
 * order.
 * 
 * @author Rajaram G
 *
 */
public class PriorityQueue<T extends Comparable<T>> {

	/**
	 * First item in the queue
	 */
	private BinaryNode<T> root;

	/**
	 * Size of queue
	 */
	private int size = 0;

	/**
	 * @return size of the queue
	 */
	public Integer size() {
		return this.size;
	}

	/**
	 * Adds an entry to the queue such that it is placed in its natural order on
	 * the binary heap
	 * 
	 * @param item
	 */
	public void enqueue(T item) {
		if (item == null)
			throw new IllegalArgumentException("Priority queue cannot store a null item.");
		BinaryNode<T> newNode = new BinaryNode<T>(item, Orientation.NEUTRAL);

		this.root = pushInto(newNode, this.root);

		this.size++;
	}

	/**
	 * Removes and returns the top of the queue
	 * 
	 * @return node
	 */
	public T dequeue() {
		if (this.root != null) {
			this.size--;
			T item = this.root.item;
			this.root = pullUp(this.root);
			if (this.root != null) {
				this.root.orientation = Orientation.NEUTRAL;
				this.root.parent = null;
			}
			return item;
		} else
			return null;
	}

	/**
	 * @return true if queue is empty
	 */
	public boolean isEmpty() {
		return this.size == 0;
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		if (this.root != null) {
			buf.append(toTreeString(this.root, "--", "C"));
			buf.append("\n");
		}
		return buf.toString();

	}

	/**
	 * @param node
	 * @return
	 */
	private String toTreeString(BinaryNode<T> node, String indent, String dir) {
		if (node == null)
			return "";
		StringBuffer buf = new StringBuffer();
		buf.append(indent + dir + " " + node.item + "\n");
		if (node.hasLeft())
			buf.append(toTreeString(node.left, "--" + indent, "L"));
		if (node.hasRight())
			buf.append(toTreeString(node.right, "--" + indent, "R"));

		return buf.toString();
	}

	/**
	 * Makes *child* the left child of *parent*
	 * 
	 * @param parent
	 * @param child
	 */
	private void setLeftChild(BinaryNode<T> parent, BinaryNode<T> child) {
		parent.left = child;
		if (child != null) {
			child.parent = parent;
			child.orientation = Orientation.LEFT;
		}

	}

	/**
	 * Makes *child* the right child of *parent*
	 * 
	 * @param parent
	 * @param child
	 */
	private void setRightChild(BinaryNode<T> parent, BinaryNode<T> child) {
		parent.right = child;
		if (child != null) {
			child.parent = parent;
			child.orientation = Orientation.RIGHT;
		}

	}

	/**
	 * Removes *node* and recursively pulls up left or right child from the
	 * sub-tree
	 * 
	 * @param node
	 * @return removed node
	 */
	private BinaryNode<T> pullUp(BinaryNode<T> node) {
		if (node == null)
			return null;
		else {
			// case 1: node has no children
			if (!node.hasLeft() && !node.hasRight()) {
				return null;
			}
			// case 2: node has only left
			else if (node.hasLeft() && !node.hasRight()) {
				return node.left;
			}
			// case 3: node has only right
			else if (!node.hasLeft() && node.hasRight()) {
				return node.right;
			}
			// case 4: node has left and right, promote left, push left's right
			// into
			else {
				BinaryNode<T> tmp = pullUp(node.left);
				setRightChild(node.left, node.right);
				setLeftChild(node.left, tmp);
				return node.left;
			}

		}
	}

	/**
	 * Pushes *newNode* into the sub-tree starting at *node*
	 * 
	 * @param newNode
	 * @param node
	 * @return new node at the top of the sub-tree
	 */
	private BinaryNode<T> pushInto(BinaryNode<T> newNode, BinaryNode<T> node) {
		if (node == null) {
			node = newNode;
			return node;
		} else {
			// Case 1: newNode <= node, put newNode on top and set node as left
			// child
			if (newNode.item.compareTo(node.item) <= 0) {
				setLeftChild(newNode, node);
				return newNode;
			}
			// Case 2: newNode > node
			else {
				// case 2.1, node has no children, set newNode as left child of
				// node
				if (!node.hasLeft() && !node.hasRight()) {
					setLeftChild(node, newNode);
				}
				// case 2.2, node has left child only
				else if (node.hasLeft() && !node.hasRight()) {
					// case 2.2.1, newNode <= node.left, move left to right and
					// set newNode as left child of node
					if (newNode.item.compareTo(node.left.item) <= 0) {
						setRightChild(node, node.left);
						setLeftChild(node, newNode);
					}
					// case 2.2.2 newNode > node.left, set newNode as right
					// child of node
					else {
						setRightChild(node, newNode);
					}
				}
				// case 2.3, node has right child only
				else if (!node.hasLeft() && node.hasRight()) {
					// case 2.3.1, newNode <= node.right, set newNode as left
					// child of node
					if (newNode.item.compareTo(node.right.item) <= 0) {
						setLeftChild(node, newNode);

					}
					// case 2.3.2 newNode > node.right, move right to left and
					// set newNode as right child of node
					else {
						setLeftChild(node, node.right);
						setRightChild(node, newNode);

					}
				}
				// case 2.4 node has right and left
				else {
					// case 2.4.1: newNode < node.right, push newNode into left
					// tree
					if (newNode.item.compareTo(node.right.item) < 0) {
						setLeftChild(node, pushInto(newNode, node.left));
					}
					// case 2.4.2: node.right <= newNode, push newNode into
					// right tree
					else
						setRightChild(node, pushInto(newNode, node.right));
				}
				return node;
			}

		}

	}
}
