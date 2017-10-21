package mlogic.algos.struct;

import mlogic.algos.exceptions.LimitException;

/**
 * @author Rajaram G
 *
 * @param <Key>
 * @param <Value>
 */
public interface LinkedList<Key extends Comparable<Key>, Value> {

	KeyedNode<Key, Value> root();

	/**
	 * Size of the list
	 */
	Integer size();

	/**
	 * Inserts the new item at the head of the list.
	 * 
	 * @param key
	 * @param value
	 * @throws LimitException
	 */
	void put(Key key, Value value);

	/**
	 * Inserts the new item in proper order of key.
	 * 
	 * @param key
	 * @param value
	 */
	void orderedPut(Key key, Value value);

	/**
	 * Traverse list using key and returns the value given key. Returns null if
	 * the key is not found.
	 * 
	 * @param key
	 * @return value
	 */
	Value get(Key key);

	/**
	 * Traverse list using key and returns the node given key. Returns null if
	 * the key is not found.
	 * 
	 * @param key
	 * @return node
	 */
	KeyedNode<Key, Value> getNode(Key key);

	/**
	 * Traverse list using key and removes the node if key is found.
	 * 
	 * @param key
	 */
	void remove(Key key);

	/**
	 * Array string representation
	 * 
	 * @return Array string representation
	 */
	String toString();

}