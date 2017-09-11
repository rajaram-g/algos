package mlogic.algos.dictionary;

/**
 * Specifies operations to be implemented by dictionary implementations
 *
 * @author Rajaram G
 *
 */
public interface Dictionary<Key extends Comparable<Key>, Value> {

	/**
	 * If the key exists in the dictionary, returns the corresponding value. If
	 * the key is not present, then returns null.
	 * 
	 * @param key
	 * @return value
	 */
	public Value get(Key key);

	/**
	 * If the key exists in the dictionary, updates the corresponding value. If
	 * the key is not present, then creates a new entry.
	 * 
	 * @param key
	 * @return value
	 */
	public void put(Key key, Value value);

	/**
	 * If the key exists in the dictionary, removes the corresponding node. If
	 * the key is not present, then does nothing.
	 * 
	 * @param key
	 * @return value
	 */
	public void remove(Key key);

	/**
	 * Returns the largest key from the dictionary
	 * 
	 * @return key
	 */
	public Key maximum();

	/**
	 * Returns the smallest key from the dictionary
	 * 
	 * @return key
	 */
	public Key minimum();

	/**
	 * Returns the largest key from the dictionary that is smaller than the
	 * input key
	 * 
	 * @return key
	 */
	public Key predecessor(Key key);

	/**
	 * Returns the smallest key from the dictionary that is larger than the
	 * input key
	 * 
	 * @return key
	 */
	public Key successor(Key key);

	/**
	 * Returns the size of the dictionary
	 * 
	 * @return size
	 */
	public Integer size();

}
