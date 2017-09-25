package mlogic.algos.struct;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 * Unit tests for Binary Heap
 * 
 * @author Rajaram G
 *
 */
public class BinaryHeapTest {

	/**
	 * Test method for {@link mlogic.algos.struct.BinaryHeap#heapify(T[])}.
	 */
	@Test
	public void testHeapifyAnIntegerArray() {
		Integer[] test = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		BinaryHeap.heapify(test);
		// 10 9 7 8 5 6 3 1 4 2
		String expected = "[10, 9, 7, 8, 5, 6, 3, 1, 4, 2]";
		String actual = Arrays.toString(test);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link mlogic.algos.struct.BinaryHeap#heapify(T[])}.
	 */
	@Test
	public void testHeapifyAStringArray() {
		String[] test = { "do", "re", "mi", "fa", "so", "la", "ti" };
		BinaryHeap.heapify(test);
		String expected = "[ti, so, mi, fa, re, la, do]";
		String actual = Arrays.toString(test);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link mlogic.algos.struct.BinaryHeap#isHeap(T[])}.
	 */
	@Test
	public void testIsHeapOnIntegerArray() {
		Integer[] test = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		assertFalse(BinaryHeap.isHeap(test));
		BinaryHeap.heapify(test);
		assertTrue(BinaryHeap.isHeap(test));
	}

	/**
	 * Test method for {@link mlogic.algos.struct.BinaryHeap#isHeap(T[])}.
	 */
	@Test
	public void testIsHeapOnStringArray() {
		String[] test = { "do", "re", "mi", "fa", "so", "la", "ti" };
		assertFalse(BinaryHeap.isHeap(test));
		BinaryHeap.heapify(test);
		assertTrue(BinaryHeap.isHeap(test));
	}

}
