package mlogic.algos.struct;

import junit.framework.TestCase;
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
	public abstract List<Integer> createLinkedList();

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
		List<Integer> list = createLinkedList();
		list.put(10);
		assertEquals("[10]", list.toString());
	}

	public void testAddABunchOfItems() {
		List<Integer> list = createLinkedListFromArray(new Integer[] { 10, 5, 6, 3, 4, 2, 1, 10, 10 });
		assertEquals("[1, 2, 4, 3, 6, 5, 10]", list.toString());
	}

	public void testDeleteFirst() {
		List<Integer> list = createLinkedListFromArray(new Integer[] { 10, 5, 6, 3, 4, 2, 1, 10, 10 });
		list.remove(10);
		assertEquals("[1, 2, 4, 3, 6, 5]", list.toString());
	}

	public void testAddToLargeList() {
		Integer[] array = RandomizationHelper.getShuffledIntArrayOfSizeN(10000);
		List<Integer> list = createLinkedListFromArray(array);
		list.put(10001);
		list.put(10002);
		assertEquals(new Integer(10002), list.size());
	}

	public void testDeleteMiddle() {
		List<Integer> list = createLinkedListFromArray(new Integer[] { 10, 5, 6, 3, 4, 2, 1, 10, 10 });
		list.remove(4);
		assertEquals("[1, 2, 3, 6, 5, 10]", list.toString());
	}

	public void testDeleteLast() {
		List<Integer> list = createLinkedListFromArray(new Integer[] { 12, 10, 5, 6, 3, 4, 2, 1, 10, 10 });
		list.remove(12);
		assertEquals("[1, 2, 4, 3, 6, 5, 10]", list.toString());
	}

	public void testDeleteFromLargeList() {
		Integer[] array = RandomizationHelper.getShuffledIntArrayOfSizeN(10000);
		List<Integer> list = createLinkedListFromArray(array);
		list.put(10001);
		list.put(10002);
		list.remove(10002);
		list.remove(10001);
		assertEquals(new Integer(10000), list.size());
	}

	private List<Integer> createLinkedListFromArray(Integer[] array) {
		List<Integer> list = createLinkedList();
		for (int i = 0; i < array.length; i++)
			list.put(array[i]);
		return list;
	}

}