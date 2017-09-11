package mlogic.algos.struct;

/**
 * Represents a node on a binary tree. Contains the key, value and pointers to
 * the left, right and parent nodes in the tree. Orientation specifies whether
 * the node is to the left or right of its parent.
 * 
 * @author Rajaram G
 *
 */
public class BinaryNode<Key extends Comparable<Key>, Value> {

	public Key key;

	public Value value;

	/**
	 * Left child
	 */
	BinaryNode<Key, Value> left;

	/**
	 * Right child
	 */
	BinaryNode<Key, Value> right;

	/**
	 * Parent
	 */
	BinaryNode<Key, Value> parent;

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
	public BinaryNode(Key key, Value value, Orientation orientation) {
		this.key = key;
		this.value = value;
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
	 * @return string with node attributes
	 */
	public String toString(String indent) {
		StringBuffer buf = new StringBuffer("");
		buf.append(indent + this.key + " ");
		if (!this.isRoot())
			buf.append("p:" + this.parent.key + " ");
		if (this.hasLeft())
			buf.append("l:" + this.left.key + " ");
		if (this.hasRight())
			buf.append("r:" + this.right.key + " ");
		buf.append("h:" + this.height + " ");
		buf.append("s:" + this.size + " ");
		return buf.toString();
	}

	/**
	 * String representation of the node and the tree under it
	 * 
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
