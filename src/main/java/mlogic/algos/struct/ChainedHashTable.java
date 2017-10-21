package mlogic.algos.struct;

import java.util.NoSuchElementException;

/**
 * Hash table organized as an array of buckets. Each bucket is a linked list
 * containing all the items that hash to the same value.
 * 
 * @author Rajaram G
 *
 */
public class ChainedHashTable<Key extends Comparable<Key>, Value> implements HashTable<Key, Value> {

	/**
	 * Initial size of the bucket array (also the size of increments)
	 */
	private static final int INITIAL_SIZE = 997;

	/**
	 * Number of entries in the hash table
	 */
	private Integer size = 0;

	/**
	 * Array of hash buckets
	 */
	private List<Tuple<Key, Value>>[] buckets;

	/**
	 * Maximum entry
	 */
	private Tuple<Key, Value> maximum;

	/**
	 * Minimum entry
	 */
	private Tuple<Key, Value> minimum;

	/**
	 * Constructor
	 */
	public ChainedHashTable() {
		this.buckets = new List[INITIAL_SIZE];
		for (int i = 0; i < INITIAL_SIZE; i++)
			this.buckets[i] = new SinglyLinkedList<Tuple<Key, Value>>();
	}

	/**
	 * Maps the key's hashCode value to an index of the bucket array
	 * 
	 * @link http://algs4.cs.princeton.edu/34hash/
	 * 
	 */
	@Override
	public Integer hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % buckets.length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.struct.HashTable#size()
	 */
	@Override
	public Integer size() {
		return this.size;
	}

	/**
	 * Compute a hash value for the key and use it to locate the bucket within
	 * the hash table. Then use the key to search within the bucket. If found,
	 * then update the value, else, create a new entry.
	 * 
	 * @param key
	 * @param value
	 */
	@Override
	public void put(Key key, Value value) {
		if (key == null)
			return;
		Integer h = hash(key);
		Tuple<Key, Value> tuple = new Tuple<Key, Value>(key, value);
		if (!this.buckets[h].contains(tuple)) {
			this.buckets[h].put(tuple);
			if (this.maximum == null || key.compareTo(this.maximum.key) > 0)
				this.maximum = tuple;
			if (this.minimum == null || key.compareTo(this.minimum.key) < 0)
				this.minimum = tuple;
			this.size++;
		}

	}

	/**
	 * Compute a hash value for the key and use it to locate the bucket within
	 * the hash table. Then use the key to search within the bucket. If found,
	 * then return the corresponding value, else, return null.
	 * 
	 * @param key
	 * @return value
	 */
	@Override
	public Value get(Key key) {
		if (key == null)
			return null;
		Integer h = hash(key);
		Tuple<Key, Value> find = new Tuple<Key, Value>(key, null);
		Tuple<Key, Value> result = this.buckets[h].get(find);
		if (result == null)
			throw new NoSuchElementException();
		return result.value;
	}

	/**
	 * Compute a hash value for the key and use it to locate the bucket within
	 * the hash table. Then use the key to search within the bucket. If found,
	 * then delete the entry, else, do nothing.
	 * 
	 * @param key
	 * @return value
	 */
	@Override
	public void remove(Key key) {
		if (key == null)
			return;
		Integer h = hash(key);
		Tuple<Key, Value> toRemove = new Tuple<Key, Value>(key, null);
		if (this.buckets[h].contains(toRemove)) {
			this.buckets[h].remove(toRemove);
			if (this.maximum.key.equals(key))
				resetMaximum();
			if (this.minimum.key.equals(key))
				resetMinimum();
			this.size--;
		}

	}

	/**
	 * Resets the maximum
	 */
	private void resetMaximum() {
		this.maximum = null;
		boolean isNull = true;
		for (int i = 0; i < this.buckets.length; i++) {
			for (Tuple<Key, Value> item : this.buckets[i]) {
				if (isNull) {
					this.maximum = new Tuple<Key, Value>(item.key, item.value);
					isNull = false;
				} else {
					if (item.compareTo(this.maximum) > 0) {
						this.maximum.key = item.key;
						this.maximum.value = item.value;
					}
				}

			}
		}

	}

	/**
	 * Resets the minimum
	 */
	private void resetMinimum() {
		this.minimum = null;
		boolean isNull = true;
		for (int i = 0; i < this.buckets.length; i++) {
			for (Tuple<Key, Value> item : this.buckets[i]) {
				if (isNull) {
					this.minimum = new Tuple<Key, Value>(item.key, item.value);
					isNull = false;
				} else {
					if (item.compareTo(this.minimum) < 0) {
						this.minimum.key = item.key;
						this.minimum.value = item.value;
					}
				}

			}

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.struct.HashTable#maximum()
	 */
	@Override
	public Tuple<Key, Value> maximum() {
		return this.maximum;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.struct.HashTable#minimum()
	 */
	@Override
	public Tuple<Key, Value> minimum() {
		return this.minimum;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mathological.algos.struct.HashTable#predecessor(java.lang.Comparable)
	 */
	@Override
	public Tuple<Key, Value> predecessor(Key key) {
		if (key == null)
			return null;
		Tuple<Key, Value> predecessor = null;
		boolean isNull = true;
		for (int i = 0; i < this.buckets.length; i++) {
			for (Tuple<Key, Value> item : this.buckets[i]) {
				if (item.key.compareTo(key) < 0) {
					if (isNull) {
						predecessor = new Tuple<Key, Value>(item.key, item.value);
						isNull = false;
					} else {
						if (item.compareTo(predecessor) > 0) {
							predecessor.key = item.key;
							predecessor.value = item.value;
						}
					}
				}

			}
		}
		return predecessor;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.struct.HashTable#successor(java.lang.Comparable)
	 */
	@Override
	public Tuple<Key, Value> successor(Key key) {
		if (key == null)
			return null;
		Tuple<Key, Value> successor = null;
		boolean isNull = true;
		for (int i = 0; i < this.buckets.length; i++) {
			for (Tuple<Key, Value> item : this.buckets[i]) {
				if (item.key.compareTo(key) > 0) {
					if (isNull) {
						successor = new Tuple<Key, Value>(item.key, item.value);
						isNull = false;
					} else {
						if (item.compareTo(successor) < 0) {
							successor.key = item.key;
							successor.value = item.value;
						}
					}
				}
			}
		}
		return successor;
	}

}
