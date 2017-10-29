package mlogic.algos.struct;

import java.util.NoSuchElementException;

import mlogic.algos.exceptions.ElementAlreadyExistsException;
import mlogic.algos.struct.BinaryNode.Orientation;

/**
 * Implementation of an unbalanced binary tree. New items are inserted to the
 * left or right of the closest item already in the tree. Tree is not
 * re-balanced after puts and deletes.
 * <p>
 * Best case performance is O(log n) when the incoming data is randomly
 * distributed. Worst case performance is O(n) e.g. when the incoming data is
 * already sorted.
 * 
 * @author Rajaram G
 *
 */
public class BinarySearchTree<T extends Comparable<T>> implements BST<T> {

	/**
	 * The root node of the binary tree
	 */
	protected BinaryNode<T> root;

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.struct.BST#size()
	 */
	@Override
	public Integer size() {
		if (this.root != null)
			return this.root.size;
		else
			return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.struct.BST#height()
	 */
	@Override
	public Integer height() {
		return height(this.root);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.struct.BST#toString()
	 */
	@Override
	public String toString() {
		if (this.root == null)
			return "";
		else
			return this.root.toTreeString("--");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.struct.BST#put(T)
	 */
	@Override
	public void put(T item) {
		if (item == null)
			throw new IllegalArgumentException("Input cannot be null.");
		this.root = put(this.root, item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.struct.BST#get(T)
	 */
	@Override
	public BinaryNode<T> get(T item) {
		if (item == null)
			throw new IllegalArgumentException("Input cannot be null.");
		return get(this.root, item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.struct.BST#remove(T)
	 */
	@Override
	public void remove(T item) {
		if (item == null)
			throw new IllegalArgumentException("Input cannot be null.");

		int initialSize = this.root.size;
		this.root = remove(this.root, item);

		if (this.root != null) {
			resize(this.root);
		}
		if (this.root != null && this.root.size == initialSize)
			throw new NoSuchElementException(item + " not in search tree.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.struct.BST#maximum()
	 */
	@Override
	public BinaryNode<T> maximum() {
		if (this.root != null)
			return maximum(this.root);
		else
			return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.struct.BST#minimum()
	 */
	@Override
	public BinaryNode<T> minimum() {
		if (this.root != null)
			return minimum(this.root);
		else
			return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.struct.BST#predecessor(mathological.algos.struct.
	 * BinaryNode)
	 */
	@Override
	public BinaryNode<T> predecessor(BinaryNode<T> node) {
		if (node == null)
			throw new IllegalArgumentException("Input cannot be null.");
		if (node.hasLeft())
			return maximum(node.left);
		else if (node.isRoot())
			return null;
		else if (node.isRight())
			return node.parent;
		else {
			BinaryNode<T> tmp = node.parent;
			while (tmp != null) {
				if (tmp.isRight())
					return tmp.parent;
				tmp = tmp.parent;
			}
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.struct.BST#successor(mathological.algos.struct.
	 * BinaryNode)
	 */
	@Override
	public BinaryNode<T> successor(BinaryNode<T> node) {
		if (node == null)
			throw new IllegalArgumentException("Input cannot be null.");
		if (node.hasRight())
			return minimum(node.right);
		else if (node.isRoot())
			return null;
		else if (node.isLeft())
			return node.parent;
		else {
			BinaryNode<T> tmp = node.parent;
			while (tmp != null) {
				if (tmp.isLeft())
					return tmp.parent;
				tmp = tmp.parent;
			}
			return null;
		}
	}

	/**
	 * Height of a node, adds 1 before reporting to public.
	 * 
	 * TODO: Eliminate the public-private disconnect and report the stored
	 * value.
	 * 
	 * @param node
	 * @return height
	 */
	protected Integer height(BinaryNode<T> node) {
		if (node == null)
			return 0;
		else
			return 1 + node.height;
	}

	/**
	 * Uses recursion to either create the new node or push creation down the
	 * left or right trees, depending on comparison of input item to the node's
	 * item.
	 * 
	 * @param node
	 * @param item
	 * @return current node
	 */
	private BinaryNode<T> put(BinaryNode<T> node, T item) {
		if (node == null)
			return new BinaryNode<T>(item, Orientation.NEUTRAL);

		if (item.compareTo(node.item) == 0)
			throw new ElementAlreadyExistsException(item + " already exists.");
		else if (item.compareTo(node.item) < 0) {
			node.left = put(node.left, item);
			node.left.parent = node;
			node.left.orientation = Orientation.LEFT;
			resize(node);
		} else {
			node.right = put(node.right, item);
			node.right.parent = node;
			node.right.orientation = Orientation.RIGHT;
			resize(node);
		}

		return node;
	}

	/**
	 * Uses recursion to either return the current node or keep looking down the
	 * left or right trees, depending on comparison of input item to the node's
	 * item.
	 * 
	 * @param node
	 * @param item
	 * @return current node
	 */
	private BinaryNode<T> get(BinaryNode<T> node, T item) {
		if (node == null)
			return null;

		int cmp = item.compareTo(node.item);
		if (cmp == 0)
			return node;
		if (cmp < 1)
			return get(node.left, item);
		else
			return get(node.right, item);

	}

	/**
	 * Uses recursion to either remove the current node or keep looking down the
	 * left or right trees, depending on comparison of input item to the node's
	 * item.
	 * 
	 * @param node
	 * @param item
	 * @return current node after removal of target item and re-balancing
	 */
	private BinaryNode<T> remove(BinaryNode<T> node, T item) {
		if (node == null)
			return null;

		int cmp = item.compareTo(node.item);

		if (cmp == 0)
			return delete(node);
		if (cmp < 1) {

			node.left = remove(node.left, item);
			if (node.left != null) {
				node.left.orientation = Orientation.LEFT;
				node.left.parent = node;
				resize(node.left);
			}

		} else {

			node.right = remove(node.right, item);
			if (node.right != null) {
				node.right.orientation = Orientation.RIGHT;
				node.right.parent = node;
				resize(node.right);
			}

		}
		resize(node);
		return node;
	}

	/**
	 * Once the exact node to be deleted has been found, delete the node and
	 * replace with a node from the tree below.
	 * 
	 * @param node
	 * @return current node
	 */
	private BinaryNode<T> delete(BinaryNode<T> node) {
		if (node.isLeaf()) {
			node = null;
			return null;
		}

		if (node.right != null) {
			BinaryNode<T> minright = minimum(node.right);
			setLeftChild(minright, node.left);
			resizeUpto(minright, node.right);
			return node.right;
		} else { // node.left must be null
			BinaryNode<T> maxleft = maximum(node.left);
			setRightChild(maxleft, node.right);
			resizeUpto(maxleft, node.left);
			return node.left;

		}

	}

	/**
	 * Uses recursion to find the right-most node
	 * 
	 * @param node
	 * @return node
	 */
	protected BinaryNode<T> maximum(BinaryNode<T> node) {
		if (node.right != null)
			return maximum(node.right);
		else
			return node;
	}

	/**
	 * Uses recursion to find the left-most node
	 * 
	 * @param node
	 * @return node
	 */
	protected BinaryNode<T> minimum(BinaryNode<T> node) {
		if (node.left != null)
			return minimum(node.left);
		else
			return node;
	}

	/**
	 * Recomputes the size and height of a node, based on size and height of its
	 * children
	 * 
	 * @param node
	 */
	protected void resize(BinaryNode<T> node) {
		if (node == null)
			return;
		node.height = max(height(node.left), height(node.right));
		node.size = 1 + size(node.left) + size(node.right);

	}

	/**
	 * Returns the size of the node and the tree underneath it.
	 * 
	 * @param node
	 * @return size
	 */
	private Integer size(BinaryNode<T> node) {
		if (node == null)
			return 0;
		else
			return node.size;
	}

	/**
	 * Helper function to return max of 2 integers
	 * 
	 * @param x
	 * @param y
	 * @return max of x and y
	 */
	private Integer max(Integer x, Integer y) {
		if (x >= y)
			return x;
		else
			return y;
	}

	/**
	 * Resizes nodes above a node, starting with the node specified by *bottom*
	 * and ending just under the the node specified by *top*
	 * 
	 * @param bottom
	 * @param top
	 */
	private void resizeUpto(BinaryNode<T> bottom, BinaryNode<T> top) {
		BinaryNode<T> tmp = bottom;
		while (true) {
			resize(tmp);
			if (tmp == top || tmp == null)
				break;
			tmp = tmp.parent;
		}
	}

	/**
	 * Makes *child* the left child of *parent*
	 * 
	 * @param parent
	 * @param child
	 */
	protected void setLeftChild(BinaryNode<T> parent, BinaryNode<T> child) {
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
	protected void setRightChild(BinaryNode<T> parent, BinaryNode<T> child) {
		parent.right = child;
		if (child != null) {
			child.parent = parent;
			child.orientation = Orientation.RIGHT;
		}

	}

}
