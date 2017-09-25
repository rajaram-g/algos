package mlogic.algos.sort;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import mlogic.algos.util.RandomizationHelper;

/**
 * @author Rajaram G
 *
 */
public abstract class SortTest {

	protected abstract Sort<Integer> createIntegerSorter(Integer[] array);

	protected abstract Sort<String> createStringSorter(String[] array);

	/**
	 * Test method for {@link mlogic.algos.sort.Sort#sort(T[])}.
	 */
	@Test
	public void testSortAnIntegerArray() {
		Integer[] test = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		System.out.println("Before: " + Arrays.toString(test));
		Sort<Integer> algo = createIntegerSorter(test);
		algo.sort();
		System.out.println("After: " + Arrays.toString(test));
		String expected = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]";
		String actual = Arrays.toString(test);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link mlogic.algos.sort.Sort#sort(T[])}.
	 */
	@Test
	public void testAFewRandomIntegerArrays() {

		for (int n = 50; n < 1000; n += 50) {
			Integer[] orig = RandomizationHelper.getRandomArrayOfSizeN(n);
			Integer[] test = orig.clone();
			Sort<Integer> algo = createIntegerSorter(test);
			algo.sort();
			Arrays.sort(orig);
			String expected = Arrays.toString(orig);
			String actual = Arrays.toString(test);
			assertEquals(expected, actual);
		}
	}

	/**
	 * Test method for {@link mlogic.algos.sort.Sort#sort(T[])}.
	 */
	@Test
	public void testSortAStringArray() {
		String[] test = { "do", "re", "mi", "fa", "so", "la", "ti" };
		System.out.println("Before: " + Arrays.toString(test));
		Sort<String> algo = createStringSorter(test);
		algo.sort();
		System.out.println("After: " + Arrays.toString(test));
		String expected = "[do, fa, la, mi, re, so, ti]";
		String actual = Arrays.toString(test);
		assertEquals(expected, actual);
	}
}
