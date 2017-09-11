package mlogic.algos.struct;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;
import mlogic.algos.struct.LinkedList;
import mlogic.algos.util.RandomizationHelper;

/**
 * @author Rajaram G
 *
 */
public abstract class LinkedListTest extends TestCase {

	/**
	 * Implement this method to deliver the type of linked list to test
	 * 
	 * @return
	 */
	public abstract LinkedList<Integer, Integer> createLinkedList();

	/**
	 * Constructor
	 */
	public LinkedListTest() {
		super();
	}

	/**
	 * @param name
	 */
	public LinkedListTest(String name) {
		super(name);
	}

	public void testAddToEmptyList() {
		LinkedList<Integer, Integer> list = createLinkedList();
		list.put(10, 10);
		assertEquals("[10]", list.toString());
	}

	public void testAddABunchOfItems() {
		LinkedList<Integer, Integer> list = createLinkedListFromArray(new Integer[] { 10, 5, 6, 3, 4, 2, 1, 10, 10 });
		assertEquals("[1, 2, 4, 3, 6, 5, 10]", list.toString());
	}

	public void testDeleteFirst() {
		LinkedList<Integer, Integer> list = createLinkedListFromArray(new Integer[] { 10, 5, 6, 3, 4, 2, 1, 10, 10 });
		list.remove(10);
		assertEquals("[1, 2, 4, 3, 6, 5]", list.toString());
	}

	public void testAddToLargeList() {
		Integer[] array = RandomizationHelper.getShuffledIntArrayOfSizeN(10000);
		LinkedList<Integer, Integer> list = createLinkedListFromArray(array);
		list.put(10001, 10001);
		list.put(10002, 10002);
		assertEquals(new Integer(10002), list.size());
	}

	public void testDeleteMiddle() {
		LinkedList<Integer, Integer> list = createLinkedListFromArray(new Integer[] { 10, 5, 6, 3, 4, 2, 1, 10, 10 });
		list.remove(4);
		assertEquals("[1, 2, 3, 6, 5, 10]", list.toString());
	}

	public void testDeleteLast() {
		LinkedList<Integer, Integer> list = createLinkedListFromArray(
				new Integer[] { 12, 10, 5, 6, 3, 4, 2, 1, 10, 10 });
		list.remove(12);
		assertEquals("[1, 2, 4, 3, 6, 5, 10]", list.toString());
	}

	public void testDeleteFromLargeList() {
		Integer[] array = RandomizationHelper.getShuffledIntArrayOfSizeN(10000);
		LinkedList<Integer, Integer> list = createLinkedListFromArray(array);
		list.put(10001, 10001);
		list.put(10002, 10002);
		list.remove(10002);
		list.remove(10001);
		assertEquals(new Integer(10000), list.size());
	}

	private LinkedList<Integer, Integer> createLinkedListFromArray(Integer[] array) {
		LinkedList<Integer, Integer> list = createLinkedList();
		for (int i = 0; i < array.length; i++)
			list.put(array[i], array[i]);
		return list;
	}

	public void testPutInOrder() {
		LinkedList<Integer, Integer> list = createSortedLinkedListFromArray(
				new Integer[] { 12, 10, 5, 6, 3, 4, 2, 1, 10, 10 });
		assertEquals("[1, 2, 3, 4, 5, 6, 10, 12]", list.toString());
	}

	public void testPutInShuffledOrder() {
		Integer[] array = new Integer[] { 12, 10, 5, 6, 3, 4, 2, 1, 10, 10 };
		List<Integer> alist = Arrays.asList(array);
		Collections.shuffle(alist);
		LinkedList<Integer, Integer> list = createSortedLinkedListFromArray((Integer[]) alist.toArray());

		assertEquals("[1, 2, 3, 4, 5, 6, 10, 12]", list.toString());
	}

	public void testPutInReverseOrder() {
		LinkedList<Integer, Integer> list = createSortedLinkedListFromArray(new Integer[] { 5, 4, 3, 2, 1 });
		assertEquals("[1, 2, 3, 4, 5]", list.toString());
	}

	public void testPutInForwardOrder() {
		LinkedList<Integer, Integer> list = createSortedLinkedListFromArray(new Integer[] { 1, 2, 3, 4, 5 });
		assertEquals("[1, 2, 3, 4, 5]", list.toString());
	}

	private LinkedList<Integer, Integer> createSortedLinkedListFromArray(Integer[] array) {
		LinkedList<Integer, Integer> list = createLinkedList();
		for (int i = 0; i < array.length; i++)
			list.orderedPut(array[i], array[i]);
		return list;
	}

}