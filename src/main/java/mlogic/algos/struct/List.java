package mlogic.algos.struct;

/**
 * Specifies the operations by List data structures
 * 
 * @author Rajaram G
 *
 */
public interface List<T> extends Iterable<T> {

	/**
	 * 
	 * @param item
	 * @return returns true if item exists in the list
	 */
	boolean contains(T item);

	/**
	 * 
	 * @param item
	 * @return item from the list that matches the search item
	 */
	T get(T item);

	/**
	 * 
	 * @param index
	 * @return returns the item at the specified index
	 */
	T get(int index);

	/**
	 * Inserts an item into the list
	 * 
	 * @param item
	 *            the item to insert
	 */
	void put(T item);

	/**
	 * Removes item from the list
	 * 
	 * @param item
	 *            item to remove
	 */
	void remove(T item);

	/**
	 * 
	 * @return size of the list
	 */
	Integer size();

	/**
	 * 
	 * @return String representation of the list
	 */
	String toString();

	/**
	 * @return Array representation of the list
	 */
	T[] toArray();

	/**
	 * Adds items into this list from another list
	 * 
	 * @param list
	 */
	void add(List<T> list);

}
