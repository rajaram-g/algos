package mlogic.algos.struct;

/**
 * Represents a node on a binary tree. Contains the item and pointers to the
 * left, right and parent nodes in the tree. Orientation specifies whether the
 * node is to the left or right of its parent.
 * 
 * @author Rajaram G
 *
 */
public class BinaryNode<T extends Comparable<T>> {

	/**
	 * Item in node
	 */
	public T item;

	/**
	 * Left child
	 */
	BinaryNode<T> left;

	/**
	 * Right child
	 */
	BinaryNode<T> right;

	/**
	 * Parent
	 */
	BinaryNode<T> parent;

	/**
	 * Specifies whether the node is to the left or right of its parent
	 */
	enum Orientation {
		LEFT, RIGHT, NEUTRAL
	}

	/**
	 * Specifies whether the node is to the left or right of its parent
	 */
	Orientation orientation;

	/**
	 * Size of the node including its children
	 */
	Integer size = 1;

	/**
	 * (Max) Height of the tree under the node
	 */
	Integer height = 0;

	/**
	 * Constructor
	 * 
	 * @param key
	 * @param value
	 * @param orientation
	 */
	public BinaryNode(T item, Orientation orientation) {
		this.item = item;
		this.orientation = orientation;
	}

	/**
	 * Is this node a left child of its parent?
	 */
	boolean isLeft() {
		return this.orientation == Orientation.LEFT;
	}

	/**
	 * Does this node have a left child?
	 */
	boolean hasLeft() {
		return this.left != null;
	}

	/**
	 * Is this node a right child of its parent?
	 */
	boolean isRight() {
		return this.orientation == Orientation.RIGHT;
	}

	/**
	 * Does this node have a right child?
	 */
	boolean hasRight() {
		return this.right != null;
	}

	/**
	 * Is this the root node?
	 */
	boolean isRoot() {
		return this.parent == null;
	}

	/**
	 * Is this a leaf node?
	 */
	boolean isLeaf() {
		return !this.hasLeft() && !this.hasRight();
	}

	/**
	 * String representation of the node
	 * 
	 * @param indent
	 *            string to be used for indenting levels in the tree
	 * @return string with node attributes
	 */
	public String toString(String indent) {
		StringBuffer buf = new StringBuffer("");
		buf.append(indent + this.item + " ");
		if (!this.isRoot())
			buf.append("p:" + this.parent.item + " ");
		if (this.hasLeft())
			buf.append("l:" + this.left.item + " ");
		if (this.hasRight())
			buf.append("r:" + this.right.item + " ");
		buf.append("h:" + this.height + " ");
		buf.append("s:" + this.size + " ");
		return buf.toString();
	}

	/**
	 * String representation of the node and the tree under it
	 * 
	 * @param indent
	 *            string to be used for indenting levels in the tree
	 * @return tree string
	 */
	public String toTreeString(String indent) {
		StringBuffer buf = new StringBuffer("");
		buf.append(this.toString(indent));
		if (this.hasLeft())
			buf.append("\n" + this.left.toTreeString(indent + "--"));
		if (this.hasRight())
			buf.append("\n" + this.right.toTreeString(indent + "--"));
		return buf.toString();
	}

}
