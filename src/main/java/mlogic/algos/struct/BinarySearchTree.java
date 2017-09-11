package mlogic.algos.struct;

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
public class BinarySearchTree<Key extends Comparable<Key>, Value> implements BST<Key, Value> {

	/**
	 * The root node of the binary tree
	 */
	protected BinaryNode<Key, Value> root;

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
	 * @see mathological.algos.struct.BST#put(Key, Value)
	 */
	@Override
	public void put(Key key, Value value) {
		if (key == null)
			return;
		this.root = put(this.root, key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.struct.BST#get(Key)
	 */
	@Override
	public BinaryNode<Key, Value> get(Key key) {
		if (key == null)
			return null;
		return get(this.root, key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.struct.BST#remove(Key)
	 */
	@Override
	public void remove(Key key) {
		if (key == null)
			return;
		this.root = remove(this.root, key);

		if (this.root != null) {
			resize(this.root);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.struct.BST#maximum()
	 */
	@Override
	public BinaryNode<Key, Value> maximum() {
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
	public BinaryNode<Key, Value> minimum() {
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
	public BinaryNode<Key, Value> predecessor(BinaryNode<Key, Value> node) {
		if (node.hasLeft())
			return maximum(node.left);
		else if (node.isRoot())
			return null;
		else if (node.isRight())
			return node.parent;
		else {
			BinaryNode<Key, Value> tmp = node.parent;
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
	public BinaryNode<Key, Value> successor(BinaryNode<Key, Value> node) {
		if (node.hasRight())
			return minimum(node.right);
		else if (node.isRoot())
			return null;
		else if (node.isLeft())
			return node.parent;
		else {
			BinaryNode<Key, Value> tmp = node.parent;
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
	protected Integer height(BinaryNode<Key, Value> node) {
		if (node == null)
			return 0;
		else
			return 1 + node.height;
	}

	/**
	 * Uses recursion to either create the new node or push creation down the
	 * left or right trees, depending on comparison of input key to the node's
	 * key.
	 * 
	 * @param node
	 * @param key
	 * @param value
	 * @return current node
	 */
	private BinaryNode<Key, Value> put(BinaryNode<Key, Value> node, Key key, Value value) {
		if (node == null)
			return new BinaryNode<Key, Value>(key, value, Orientation.NEUTRAL);

		if (key.compareTo(node.key) == 0) {
			node.value = value;
		} else if (key.compareTo(node.key) < 0) {
			node.left = put(node.left, key, value);
			node.left.parent = node;
			node.left.orientation = Orientation.LEFT;
			resize(node);
		} else {
			node.right = put(node.right, key, value);
			node.right.parent = node;
			node.right.orientation = Orientation.RIGHT;
			resize(node);
		}

		return node;
	}

	/**
	 * Uses recursion to either return the current node or keep looking down the
	 * left or right trees, depending on comparison of input key to the node's
	 * key.
	 * 
	 * @param node
	 * @param key
	 * @param value
	 * @return current node
	 */
	private BinaryNode<Key, Value> get(BinaryNode<Key, Value> node, Key key) {
		if (node == null)
			return null;

		int cmp = key.compareTo(node.key);
		if (cmp == 0)
			return node;
		if (cmp < 1)
			return get(node.left, key);
		else
			return get(node.right, key);

	}

	/**
	 * Uses recursion to either remove the current node or keep looking down the
	 * left or right trees, depending on comparison of input key to the node's
	 * key.
	 * 
	 * @param node
	 * @param key
	 * @return current node after removal of target key and re-balancing
	 */
	private BinaryNode<Key, Value> remove(BinaryNode<Key, Value> node, Key key) {
		if (node == null)
			return null;

		int cmp = key.compareTo(node.key);

		if (cmp == 0)
			return delete(node);
		if (cmp < 1) {

			node.left = remove(node.left, key);
			if (node.left != null) {
				node.left.orientation = Orientation.LEFT;
				node.left.parent = node;
				resize(node.left);
			}

		} else {

			node.right = remove(node.right, key);
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
	 * TODO: Potential bug: Need to keep rotating until balance is restored.
	 * 
	 * @param node
	 * @return current node
	 */
	private BinaryNode<Key, Value> delete(BinaryNode<Key, Value> node) {
		if (node.isLeaf()) {
			node = null;
			return null;
		}

		if (node.right != null) {
			BinaryNode<Key, Value> minright = minimum(node.right);
			setLeftChild(minright, node.left);
			resizeUpto(minright, node.right);
			return node.right;
		} else { // node.left must be null
			BinaryNode<Key, Value> maxleft = maximum(node.left);
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
	protected BinaryNode<Key, Value> maximum(BinaryNode<Key, Value> node) {
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
	protected BinaryNode<Key, Value> minimum(BinaryNode<Key, Value> node) {
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
	protected void resize(BinaryNode<Key, Value> node) {
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
	private Integer size(BinaryNode<Key, Value> node) {
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
	private void resizeUpto(BinaryNode<Key, Value> bottom, BinaryNode<Key, Value> top) {
		BinaryNode<Key, Value> tmp = bottom;
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
	protected void setLeftChild(BinaryNode<Key, Value> parent, BinaryNode<Key, Value> child) {
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
	protected void setRightChild(BinaryNode<Key, Value> parent, BinaryNode<Key, Value> child) {
		parent.right = child;
		if (child != null) {
			child.parent = parent;
			child.orientation = Orientation.RIGHT;
		}

	}

}
