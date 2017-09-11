/**
 * 
 */
package mlogic.algos.dictionary;

import java.util.Arrays;

import mlogic.algos.analysis.OrderOfGrowthEstimator;
import mlogic.algos.dictionary.Dictionary;
import mlogic.algos.dictionary.SortedArrayDictionary;
import mlogic.algos.dictionary.UnsortedArrayDictionary;
import mlogic.algos.util.RandomizationHelper;

/**
 * @author Rajaram G
 *
 */
public class DictionaryOrderOfGrowthTest {

	private int[] inputSize;
	private long[][] opTime;
	private String[] dictionaryTypes = { "Unsorted Array", "Sorted Array" };
	private String[] operations = { "put", "get", "remove", "maximum", "minimum", "predecessor", "successor" };
	private String[][] output;

	private DictionaryOrderOfGrowthTest() {

		inputSize = new int[50];
		System.out.println("Input Sizes ");
		for (int i = 0; i < 50; i++) {
			inputSize[i] = (i + 1) * 100;
		}
		System.out.println(Arrays.toString(inputSize));

		output = new String[dictionaryTypes.length][operations.length];
	}

	private void run() {
		opTime = new long[operations.length][inputSize.length];
		for (int k = 0; k < dictionaryTypes.length; k++) {
			System.out.println("Testing " + dictionaryTypes[k]);
			for (int i = 0; i < inputSize.length; i++) {
				System.out.print(".");
				Dictionary<String, String> testDict = getDictionary(k);
				String[] data = RandomizationHelper.getShuffledStringArrayOfSizeN(inputSize[i]);
				initialize(testDict, data);
				for (int j = 0; j < operations.length; j++)
					doOp(testDict, data, i, j);
			}
			System.out.print("\n");
			classify(k);
		}
		printOutput();

	}

	private Dictionary<String, String> getDictionary(int dictionaryType) {
		switch (dictionaryType) {
		case 0:
			return new UnsortedArrayDictionary();
		case 1:
			return new SortedArrayDictionary();
		default:
			return new UnsortedArrayDictionary();

		}
	}

	private void classify(int dictionaryType) {
		OrderOfGrowthEstimator.GrowthClass clazz;
		for (int i = 0; i < operations.length; i++) {
			clazz = OrderOfGrowthEstimator.estimate(inputSize, opTime[i]);
			output[dictionaryType][i] = clazz.toString();
		}

	}

	private void printOutput() {
		System.out.println("Growth Classification of Dictionary Implementations");
		System.out.println("===================================================");
		// System.out.println(Arrays.toString(opTime[1]));
		System.out.printf("%-20s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s \n", "ALGORITHM", "PUT", "GET",
				"REMOVE", "MIN", "MAX", "PREDECESSOR", "SUCCESSOR");
		System.out.printf("%-20s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s \n", "---------", "---", "---",
				"------", "---", "---", "-----------", "---------");
		for (int k = 0; k < dictionaryTypes.length; k++)
			System.out.printf("%-20s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s \n", dictionaryTypes[k],
					output[k][0], output[k][1], output[k][2], output[k][3], output[k][4], output[k][5], output[k][6]);

	}

	private void doOp(Dictionary<String, String> testDict, String[] data, int i, int opType) {
		long elapsed = System.nanoTime();
		switch (opType) {
		case 0:
			testDict.put("zzzzzz", "at the very end");
			break;
		case 1:
			testDict.get("nosuchelement");
			break;
		case 2:
			testDict.remove(data[inputSize[i] - 1]);
			break;
		case 3:
			testDict.minimum();
			break;
		case 4:
			testDict.maximum();
			break;
		case 5:
			testDict.predecessor(data[inputSize[i] / 2]);
			break;
		case 6:
			testDict.successor(data[inputSize[i] / 2]);
			break;
		default:
			break;

		}
		// timer.stop();
		elapsed = System.nanoTime() - elapsed;
		opTime[opType][i] = elapsed;
		testDict = null;
		Arrays.fill(data, null);
	}

	public static void main(String[] args) {
		DictionaryOrderOfGrowthTest test = new DictionaryOrderOfGrowthTest();
		test.run();

	}

	private static void initialize(Dictionary<String, String> dict, String[] keys) {
		for (int i = 0; i < keys.length; i++) {
			dict.put(keys[i], reverse(keys[i]));
		}
	}

	private static String reverse(String stringToReverse) {
		return new StringBuilder(stringToReverse).reverse().toString();
	}

}
