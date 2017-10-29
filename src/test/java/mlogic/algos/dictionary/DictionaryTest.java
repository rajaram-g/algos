/**
 * 
 */
package mlogic.algos.dictionary;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import mlogic.algos.exceptions.ElementAlreadyExistsException;
import mlogic.algos.util.RandomizationHelper;

/**
 * @author Rajaram G
 *
 */
public abstract class DictionaryTest {

	private Dictionary<String, String> small;
	private Dictionary<String, String> large;
	private String[] largeKeySet;

	public abstract Dictionary<String, String> createDictionary();

	@Before
	public void setup() {
		small = createDictionary();
		initializeSmall(small);
		large = createDictionary();
		largeKeySet = RandomizationHelper.getShuffledStringArrayOfSizeN(15999);
		initializeLarge(large, largeKeySet);
		Arrays.parallelSort(largeKeySet);

	}

	@Test
	public void testGetsFromLargeDictionary() {
		int n = largeKeySet.length;
		testGet(large, largeKeySet[0], reverse(largeKeySet[0]));
		testGet(large, largeKeySet[n / 2], reverse(largeKeySet[n / 2]));
		testGet(large, largeKeySet[n - 1], reverse(largeKeySet[n - 1]));

	}

	@Test
	public void testPutsIntoLargeDictionary() {
		testPut(large);
		testPutDuplicate(large);
		testPutNull(large);
	}

	@Test
	public void testRemoveFromLargeDictionary() {
		int n = largeKeySet.length;
		testRemove(large, largeKeySet[n / 2], reverse(largeKeySet[n / 2]));
		testRemoveNull(large);
		testRemoveNonExistent(large);
	}

	@Test
	public void testMinMaxFromLargeDictionary() {
		int n = largeKeySet.length;
		testMaximum(large, largeKeySet[n - 1]);
		testMinimum(large, largeKeySet[0]);
	}

	@Test
	public void testPredecessorFromLargeDictionary() {
		int n = largeKeySet.length;
		testPredecessor(large, largeKeySet[n - 1], largeKeySet[n - 2]);
	}

	@Test
	public void testSuccessorFromLargeDictionary() {
		int n = largeKeySet.length;
		testSuccessor(large, largeKeySet[n - 2], largeKeySet[n - 1]);
	}

	@Test
	public void testSmallDictionary() {
		testGet(small, "aardvark", "the only word to be studied for CAT");
		testPut(small);
		testPutDuplicate(small);
		testPutNull(small);
		testRemove(small, "middle", " the one to remove");
		testRemoveNull(small);
		testRemoveNonExistent(small);
		testMaximum(small, "zombie");
		testMinimum(small, "aardvark");
		testPredecessor(small, "zombie", "middle");
		testSuccessor(small, "middle", "zombie");
	}

	private void testGet(Dictionary<String, String> testDict, String key, String value) {
		assertEquals(value, testDict.get(key));
	}

	private void testPut(Dictionary<String, String> testDict) {
		int initSize = testDict.size();
		testDict.put("pepper", "one of the twins");
		assert (testDict.size() == (initSize + 1));
		testDict.remove("pepper");
	}

	private void testPutDuplicate(Dictionary<String, String> testDict) {
		int initSize = testDict.size();
		testDict.put("pepper", "one of the twins");
		try {
			testDict.put("pepper", "the other twin");
		} catch (ElementAlreadyExistsException e) {
			e.printStackTrace();
		}
		assertEquals(new Integer(initSize + 1), testDict.size());
		testDict.remove("pepper");
		// testDict.remove("pepper");

		assert (testDict.size() == (initSize));

	}

	public void testPutNull(Dictionary<String, String> testDict) {
		int initSize = testDict.size();
		testDict.put(null, "the other twin");
		assert (testDict.size() == (initSize));
	}

	public void testRemove(Dictionary<String, String> testDict, String keyToRemove, String valueToRemove) {
		int initSize = testDict.size();
		testDict.remove(keyToRemove);

		try {
			testDict.get(keyToRemove);
			fail(keyToRemove + " has not been removed");
		} catch (NoSuchElementException e) {

		}
		assert (testDict.size() == (initSize - 1));
		testDict.put(keyToRemove, valueToRemove);

	}

	/**
	 * @param keyToRemove
	 */
	private void fail(String keyToRemove) {
		// TODO Auto-generated method stub

	}

	public void testRemoveNull(Dictionary<String, String> testDict) {
		int initSize = testDict.size();
		testDict.remove(null);
		assert (testDict.size() == (initSize));

	}

	public void testRemoveNonExistent(Dictionary<String, String> testDict) {
		int initSize = testDict.size();
		try {
			testDict.remove("no such word");
			fail("Remove invalid item did not throw exception");
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		assert (testDict.size() == (initSize));

	}

	public void testMaximum(Dictionary<String, String> testDict, String max) {
		assertEquals(max, testDict.maximum());
	}

	public void testMinimum(Dictionary<String, String> testDict, String min) {
		assertEquals(min, testDict.minimum());
	}

	public void testPredecessor(Dictionary<String, String> testDict, String key, String predecessor) {
		assertEquals(predecessor, testDict.predecessor(key));
	}

	public void testSuccessor(Dictionary<String, String> testDict, String key, String successor) {
		assertEquals(successor, testDict.successor(key));
	}

	private static void initializeSmall(Dictionary<String, String> dict) {
		dict.put("aardvark", "the only word to be studied for CAT");
		dict.put("zombie", "a popular TV character");
		dict.put("middle", "the one to remove");
	}

	private static void initializeLarge(Dictionary<String, String> dict, String[] keys) {
		for (int i = 0; i < keys.length; i++) {
			dict.put(keys[i], reverse(keys[i]));
		}
	}

	private static String reverse(String stringToReverse) {
		return new StringBuilder(stringToReverse).reverse().toString();
	}
}
