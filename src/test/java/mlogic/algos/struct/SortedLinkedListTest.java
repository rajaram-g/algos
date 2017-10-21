package mlogic.algos.struct;

import java.util.Arrays;
import java.util.Collections;

import junit.framework.TestCase;
import mlogic.algos.util.RandomizationHelper;

/**
 * @author Rajaram G
 *
 */
public abstract class SortedLinkedListTest extends TestCase {

	/**
	 * Implement this method to deliver the type of linked list to test
	 * 
	 * @return
	 */
	public abstract List<Integer> createLinkedList();

	/**
	 * Constructor
	 */
	public SortedLinkedListTest() {
		super();
	}

	/**
	 * @param name
	 */
	public SortedLinkedListTest(String name) {
		super(name);
	}

	public void testAddToEmptyList() {
		List<Integer> list = createLinkedList();
		list.put(10);
		assertEquals("[10]", list.toString());
	}

	public void testAddABunchOfItems() {
		List<Integer> list = createSortedLinkedListFromArray(new Integer[] { 10, 5, 6, 3, 4, 2, 1, 10, 10 });
		assertEquals("[1, 2, 3, 4, 5, 6, 10]", list.toString());
	}

	public void testDeleteFirst() {
		List<Integer> list = createSortedLinkedListFromArray(new Integer[] { 10, 5, 6, 3, 4, 2, 1, 10, 10 });
		list.remove(10);
		assertEquals("[1, 2, 3, 4, 5, 6]", list.toString());
	}

	public void testAddToLargeList() {
		Integer[] array = RandomizationHelper.getShuffledIntArrayOfSizeN(10000);
		List<Integer> list = createLinkedListFromArray(array);
		list.put(10001);
		list.put(10002);
		assertEquals(new Integer(10002), list.size());
	}

	public void testDeleteMiddle() {
		List<Integer> list = createSortedLinkedListFromArray(new Integer[] { 10, 5, 6, 3, 4, 2, 1, 10, 10 });
		list.remove(4);
		assertEquals("[1, 2, 3, 5, 6, 10]", list.toString());
	}

	public void testDeleteLast() {
		List<Integer> list = createSortedLinkedListFromArray(new Integer[] { 12, 10, 5, 6, 3, 4, 2, 1, 10, 10 });
		list.remove(12);
		assertEquals("[1, 2, 3, 4, 5, 6, 10]", list.toString());
	}

	public void testDeleteFromLargeList() {
		Integer[] array = RandomizationHelper.getShuffledIntArrayOfSizeN(10000);
		List<Integer> list = createSortedLinkedListFromArray(array);
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

	public void testPutInOrder() {
		List<Integer> list = createSortedLinkedListFromArray(new Integer[] { 12, 10, 5, 6, 3, 4, 2, 1, 10, 10 });
		assertEquals("[1, 2, 3, 4, 5, 6, 10, 12]", list.toString());
	}

	public void testPutInShuffledOrder() {
		Integer[] array = new Integer[] { 12, 10, 5, 6, 3, 4, 2, 1, 10, 10 };
		java.util.List<Integer> alist = Arrays.asList(array);
		Collections.shuffle(alist);
		List<Integer> list = createSortedLinkedListFromArray((Integer[]) alist.toArray());

		assertEquals("[1, 2, 3, 4, 5, 6, 10, 12]", list.toString());
	}

	public void testPutInReverseOrder() {
		List<Integer> list = createSortedLinkedListFromArray(new Integer[] { 5, 4, 3, 2, 1 });
		assertEquals("[1, 2, 3, 4, 5]", list.toString());
	}

	public void testPutInForwardOrder() {
		List<Integer> list = createSortedLinkedListFromArray(new Integer[] { 1, 2, 3, 4, 5 });
		assertEquals("[1, 2, 3, 4, 5]", list.toString());
	}

	private List<Integer> createSortedLinkedListFromArray(Integer[] array) {
		List<Integer> list = createLinkedList();
		for (int i = 0; i < array.length; i++)
			list.put(array[i]);
		return list;
	}

}