package mlogic.algos.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Methods to generate random data sets for testing of algorithms
 * 
 * @author Rajaram G
 *
 */
public class RandomizationHelper {

	private static final int MILLION = 1000000;

	/**
	 * Chooses a random integer and creates an array of that size with random
	 * integers. May contain duplicates.
	 * 
	 * @return array of random integers of random size
	 */
	public static Integer[] getRandomArrayOfRandomSize() {
		Random random = new Random();
		int n = random.nextInt(MILLION) + 1;
		return getRandomArrayOfSizeN(n);

	}

	/**
	 * Creates an array of a given size with random integers. May contain
	 * duplicates.
	 * 
	 * @param n
	 *            size of array
	 * @return array of random integers
	 */
	public static Integer[] getRandomArrayOfSizeN(int n) {
		Random random = new Random();
		Integer[] array = new Integer[n];
		for (int i = 0; i < n; i++)
			array[i] = random.nextInt(n);

		return array;
	}

	/**
	 * Creates an array of a given size with random integers. Does contain
	 * duplicates.
	 * 
	 * @param n
	 *            size of array
	 * @return array of random integers
	 */
	public static Integer[] getShuffledIntArrayOfSizeN(int n) {
		Integer[] array = new Integer[n];
		for (Integer i = 0; i < n; i++) {
			array[i] = i;
		}
		List<Integer> list = Arrays.asList(array);
		Collections.shuffle(list);
		return (Integer[]) list.toArray();
	}

	/**
	 * Creates an array of a given size with random strings. The strings are hex
	 * representation of random integers. May contain duplicates.
	 * 
	 * @param n
	 *            size of array
	 * @return array of strings
	 */
	public static String[] getRandomStringArrayOfSizeN(int n) {
		Random random = new Random();
		String[] array = new String[n];
		for (int i = 0; i < n; i++) {
			int m = random.nextInt(MILLION);
			array[i] = Integer.toHexString(m);

		}
		return array;
	}

	/**
	 * Creates an array of a given size with random strings. The strings are hex
	 * representation of random integers. Do not contain duplicates.
	 * 
	 * @param n
	 *            size of array
	 * @return array of strings
	 */
	public static String[] getShuffledStringArrayOfSizeN(int n) {
		String[] array = new String[n];
		for (int i = 0; i < n; i++) {
			array[i] = Integer.toHexString(i);
		}
		List<String> list = Arrays.asList(array);
		Collections.shuffle(list);
		return (String[]) list.toArray();
	}

	/**
	 */

	/**
	 * Performs shuffle on a generic array See <a href=
	 * "https://stackoverflow.com/questions/2450954/how-to-randomize-shuffle-a-javascript-array">StackExchange
	 * discussion</a>
	 * 
	 * @param <T>
	 *            type of elements in array
	 * @param array
	 *            array to be shuffled
	 */
	public static <T> void shuffle(T[] array) {
		if (array == null)
			throw new IllegalArgumentException("Input must not be null.");

		Random random = new Random();
		int n = array.length - 1;
		while (n != 0) {
			int m = random.nextInt(n);
			swap(array, m, n);
			n--;
		}

	}

	/**
	 * Swaps the elements between two indices in an array
	 * 
	 * @param <T>
	 *            type of elements in array
	 * @param array
	 * @param m
	 *            first index
	 * @param n
	 *            second index
	 */
	public static <T> void swap(T[] array, int m, int n) {
		T tmp = array[m];
		array[m] = array[n];
		array[n] = tmp;

	}

}
