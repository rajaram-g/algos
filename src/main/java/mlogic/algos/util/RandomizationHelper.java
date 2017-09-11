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

	public static Integer[] getRandomArrayOfRandomSize() {
		Random random = new Random();
		int n = random.nextInt(MILLION) + 1;
		return getRandomArrayOfSizeN(n);

	}

	public static Integer[] getRandomArrayOfSizeN(int n) {
		Random random = new Random();
		Integer[] array = new Integer[n];
		for (int i = 0; i < n; i++)
			array[i] = random.nextInt(n);

		return array;
	}

	public static String[] getRandomStringArrayOfSizeN(int n) {
		Random random = new Random();
		String[] array = new String[n];
		for (int i = 0; i < n; i++) {
			int m = random.nextInt(MILLION);
			array[i] = Integer.toHexString(m);

		}
		return array;
	}

	// Ensures unique values
	public static String[] getShuffledStringArrayOfSizeN(int n) {
		String[] array = new String[n];
		for (int i = 0; i < n; i++) {
			array[i] = Integer.toHexString(i);
		}
		List<String> list = Arrays.asList(array);
		Collections.shuffle(list);
		return (String[]) list.toArray();
	}

	// Ensures unique values
	public static Integer[] getShuffledIntArrayOfSizeN(int n) {
		Integer[] array = new Integer[n];
		for (Integer i = 0; i < n; i++) {
			array[i] = i;
		}
		List<Integer> list = Arrays.asList(array);
		Collections.shuffle(list);
		return (Integer[]) list.toArray();
	}
}
