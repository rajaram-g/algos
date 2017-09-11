package mlogic.algos.dictionary;

import mlogic.algos.struct.BalancedBinarySearchTree;

/**
 * Dictionary implementation based on Balanced Binary Search Tree that
 * guarantees O(log n) performance for gets, puts and deletes.
 * 
 * @author Rajaram G
 *
 */
public class BBSTDictionary extends BSTDictionary {

	/**
	 * Constructor
	 */
	public BBSTDictionary() {
		this.dictionary = new BalancedBinarySearchTree<String, String>();
	}

}
