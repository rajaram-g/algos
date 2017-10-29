package mlogic.algos.struct;

import java.util.NoSuchElementException;

import mlogic.algos.exceptions.ElementAlreadyExistsException;
import mlogic.algos.struct.BinaryNode.Orientation;

/**
 * Implementation of a balanced binary tree. Adjusts the tree after every put or
 * remove to ensure the difference between the height of left and right trees is
 * at most 1. Adjustment is done by rotating the tree in the opposite direction
 * of the imbalance. Left rotation is done by pushing the current root into the
 * left tree and replacing it with the min element from the right tree. Right
 * rotation is done by pushing the current root into the right tree and
 * replacing it with the max element from the left tree.
 * <p>
 * Each put (and remove) involves O(log n) steps to walk down the tree to find
 * the right place to insert (or remove), followed by O(n log n)?? height
 * adjustments.
 * <p>
 * The benefit is a guarantee of O(log n) worst case performance for gets.
 * <p>
 * TODO: To accurately estimate order of growth for the height adjustments after
 * every put or remove.
 * 
 * @author Rajaram G
 *
 */
public class BalancedBinarySearchTree<T extends Comparable<T>> extends BinarySearchTree<T> {

	/**
	 * Checks if each node in the tree is height-balanced i.e. absolute
	 * difference between left and right tree heights must be zero or one.
	 * 
	 * @return isBalanced
	 */
	public boolean isBalanced() {
		return isBalanced(this.root);
	}

	/**
	 * Performs binary search to find the right place to update or insert.
	 * Re-balances the tree after inserts.
	 * 
	 * @param key
	 * @param value
	 */
	@Override
	public void put(T item) {
		if (item == null)
			throw new IllegalArgumentException("Input cannot be null.");
		this.root = put(this.root, item);
	}

	/**
	 * Performs binary search to find and delete the node if key exists in tree.
	 * 
	 * @param key
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

	/**
	 * Recursively checks if node and its children are height-balanced
	 * 
	 * @param node
	 * @return isBalanced
	 */
	private boolean isBalanced(BinaryNode<T> node) {
		if (node == null)
			return true;
		if (isBalanced(node.left))
			if (isBalanced(node.right)) {
				int lh = height(node.left);
				int rh = height(node.right);
				if (Math.abs(lh - rh) <= 1)
					return true;
			}
		return false;
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

		if (item.compareTo(node.item) == 0) {
			throw new ElementAlreadyExistsException(item + " already exists.");
		} else if (item.compareTo(node.item) < 0) {
			node.left = put(node.left, item);
			node.left.parent = node;
			node.left.orientation = Orientation.LEFT;
			balance(node);
		} else {
			node.right = put(node.right, item);
			node.right.parent = node;
			node.right.orientation = Orientation.RIGHT;
			balance(node);
		}

		return node;
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
		return balance(node);
	}

	/**
	 * Once the exact node to be deleted has been found, delete the node and
	 * re-balance the section of the tree below deleted node.
	 * 
	 * TODO: Potential bug: Need to keep rotating until balance is restored.
	 * 
	 * @param node
	 * @return current node
	 */
	private BinaryNode<T> delete(BinaryNode<T> node) {
		if (node.isLeaf()) {
			node = null;
			return null;
		}
		if (leansRight(node))
			node = rotateLeft(node, true);
		else if (leansLeft(node))
			node = rotateRight(node, true);
		else
			node = rotateLeft(node, true);

		return node;

	}

	/**
	 * Rotates tree under a node left or right until balance is achieved.
	 * 
	 * @param node
	 * @return current node after the tree below it is re-balanced
	 */
	private BinaryNode<T> balance(BinaryNode<T> node) {
		if (node == null)
			return null;
		while (true) {
			resize(node);
			Integer lh = height(node.left);
			Integer rh = height(node.right);
			if (lh > (rh + 1))
				node = rotateRight(node, false);
			else if (rh > (lh + 1))
				node = rotateLeft(node, false);
			else
				return node;

		}

	}

	/**
	 * Rotates tree left by a) Extracting the min from right tree and b) making
	 * it the new root and c) pushing the current root into the left tree
	 * 
	 * @param node
	 * @param deleteRoot
	 *            set to true if the current node is to be deleted (used by
	 *            remove operation)
	 * @return current node
	 */
	private BinaryNode<T> rotateLeft(BinaryNode<T> node, boolean deleteRoot) {
		if (node.right == null)
			return node;

		BinaryNode<T> newroot = deleteMin(node.right);
		if (deleteRoot == false) {
			node.left = put(node.left, node.item);
			node.left.parent = node;
			node.left.orientation = Orientation.LEFT;
		}
		node.item = newroot.item;
		resize(node);
		return node;

	}

	/**
	 * Deletes the min from a tree (or section of tree under a node)
	 * 
	 * @param node
	 * @return min node
	 */
	private BinaryNode<T> deleteMin(BinaryNode<T> node) {
		if (node == null)
			return null;
		if (node.left == null) {
			if (node.parent != null) {
				setRightChild(node.parent, node.right);
				resize(node.parent);
			} else {
				this.root = node.right;
				if (this.root != null) {
					this.root.parent = null;
					this.root.orientation = Orientation.NEUTRAL;
				}
			}
			return node;
		} else {
			BinaryNode<T> newroot = minimum(node);
			BinaryNode<T> newrootp = newroot.parent;
			setLeftChild(newrootp, newroot.right);
			balanceUpto(newrootp, node.parent);
			return newroot;

		}
	}

	/**
	 * Rotates tree left by a) Extracting the max from left tree and b) making
	 * it the new root and c) pushing the current root into the right tree
	 * 
	 * @param node
	 * @param deleteRoot
	 *            set to true if the current node is to be deleted (used by
	 *            remove operation)
	 * @return current node
	 */
	private BinaryNode<T> rotateRight(BinaryNode<T> node, boolean deleteRoot) {
		if (node.left == null)
			return node;

		BinaryNode<T> newroot = deleteMax(node.left);
		if (deleteRoot == false) {
			node.right = put(node.right, node.item);
			node.right.parent = node;
			node.right.orientation = Orientation.LEFT;
		}
		node.item = newroot.item;
		resize(node);
		return node;
	}

	/**
	 * Deletes the max from a tree (or section of tree under a node)
	 * 
	 * @param node
	 * @return max node
	 */
	private BinaryNode<T> deleteMax(BinaryNode<T> node) {
		if (node.right == null) {
			if (node.parent != null) {
				setLeftChild(node.parent, node.left);
				resize(node.parent);
			} else {
				this.root = node.left;
				if (this.root != null) {
					this.root.parent = null;
					this.root.orientation = Orientation.NEUTRAL;
				}
			}
			return node;
		} else {
			BinaryNode<T> newroot = maximum(node);
			BinaryNode<T> newrootp = newroot.parent;
			setRightChild(newrootp, newroot.left);
			balanceUpto(newrootp, node.parent);
			return newroot;

		}
	}

	/**
	 * Balances a tree bottom up, starting with the node specified by *bottom*
	 * and ending just under the the node specified by *top*
	 * 
	 * @param bottom
	 * @param top
	 * @return highest balanced node
	 */
	private BinaryNode<T> balanceUpto(BinaryNode<T> bottom, BinaryNode<T> top) {
		BinaryNode<T> tmp = bottom;
		while (true) {
			tmp = balance(tmp);
			if (tmp.parent == null || tmp.parent == top)
				return tmp;
			tmp = tmp.parent;

		}

	}

	/**
	 * Checks if the given node leans left i.e. left tree is longer than right
	 * tree
	 * 
	 * @param node
	 * @return leans left?
	 */
	private boolean leansLeft(BinaryNode<T> node) {
		if (node == null)
			return false;
		Integer lh = height(node.left);
		Integer rh = height(node.right);
		if (lh > rh)
			return true;
		else
			return false;
	}

	/**
	 * Checks if the given node leans right i.e. left tree is shorter than right
	 * tree
	 * 
	 * @param node
	 * @return leans right?
	 */
	private boolean leansRight(BinaryNode<T> node) {
		if (node == null)
			return false;
		Integer lh = height(node.left);
		Integer rh = height(node.right);
		if (lh < rh)
			return true;
		else
			return false;
	}

}
