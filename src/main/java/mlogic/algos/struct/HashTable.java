package mlogic.algos.struct;

/**
 * Specifies the methods to be implemented by any HashTable implementation
 * 
 * @author Rajaram G
 *
 * @param <Key>
 *            Key by which to index and retrieve objects in the tree
 * @param <Value>
 *            The object of interest
 */
public interface HashTable<Key extends Comparable<Key>, Value> {

	/**
	 * @return size of the hash table
	 */
	Integer size();

	/**
	 * @param key
	 *            key to be hashed
	 * @return an integer hash value given a key
	 */
	Integer hash(Key key);

	/**
	 * Compute a hash value for the key and use it to locate the region within
	 * the hash table. Then use the key to search within the region. If found,
	 * then update the value, else, create a new entry.
	 * 
	 * @param key
	 * @param value
	 */
	void put(Key key, Value value);

	/**
	 * Compute a hash value for the key and use it to locate the region within
	 * the hash table. Then use the key to search within the region. If found,
	 * then return the corresponding value, else, return null.
	 * 
	 * @param key
	 * @return value
	 */
	Value get(Key key);

	/**
	 * Compute a hash value for the key and use it to locate the region within
	 * the hash table. Then use the key to search within the region. If found,
	 * then delete the entry, else, do nothing.
	 * 
	 * @param key
	 */
	void remove(Key key);

	/**
	 * Returns the entry with the largest key in the table
	 * 
	 * @return tuple containing the maximum key and corresponding value
	 */
	Tuple<Key, Value> maximum();

	/**
	 * Returns the entry with smallest key in the table
	 * 
	 * @return tuple containing the minimum key and corresponding value
	 */
	Tuple<Key, Value> minimum();

	/**
	 * Finds the entry with the largest key that is smaller than the input key
	 * 
	 * @param key
	 * @return tuple containing predecessor key and corresponding value
	 */
	Tuple<Key, Value> predecessor(Key key);

	/**
	 * Finds the entry with the smallest key that is larger than the input key
	 * 
	 * @param key
	 * @return tuple containing successor key and corresponding value
	 */
	Tuple<Key, Value> successor(Key key);

}
