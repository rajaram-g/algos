package mlogic.algos.dictionary;

import java.util.Arrays;

import mlogic.algos.analysis.PerformanceTest;
import mlogic.algos.util.RandomizationHelper;

/**
 * Implements the PerformanceTest interface to create and test various
 * dictionary implementations and generate response time data for analysis.
 * 
 * @author Rajaram G
 *
 */
public class DictionaryPerformanceTest implements PerformanceTest {

	/**
	 * Number of input sizes to be tested
	 */
	private int numberOfDataPoints = 30;

	private String algorithmClass = "Dictionary";

	private String inputSizeLabel = "Dictionary Size";

	private String[] operationTypes = { "Put", "Get", "Remove", "Maximum", "Minimum", "Predecessor", "Successor" };

	private String[] algorithms = { "Unsorted Array", "Sorted Array", "Unsorted Linked List", "Sorted Linked List",
			"BST", "Balanced BST", "Hash Table" };

	private long[][][] elapsed = new long[algorithms.length][operationTypes.length][numberOfDataPoints];

	private double[][][] logElapsed = new double[algorithms.length][operationTypes.length][numberOfDataPoints];

	private int[] inputSizes = new int[numberOfDataPoints];

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.dictionaries.PerformanceTest#algorithmClass()
	 */
	public String algorithmClass() {
		return algorithmClass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.dictionaries.PerformanceTest#inputSizeLabel()
	 */
	public String inputSizeLabel() {
		return inputSizeLabel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.dictionaries.PerformanceTest#operationTypes()
	 */
	public String[] operationTypes() {
		return operationTypes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.dictionaries.PerformanceTest#algorithms()
	 */
	public String[] algorithms() {
		return algorithms;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.dictionaries.PerformanceTest#elapsed()
	 */
	public long[][][] elapsed() {
		return elapsed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.dictionaries.PerformanceTest#logElapsed()
	 */
	public double[][][] logElapsed() {
		return logElapsed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.dictionaries.PerformanceTest#execute()
	 */
	public void execute() {
		for (int x = 1; x <= inputSizes.length; x++)
			inputSizes[x - 1] = x * 100;
		System.out.print("Generating performance data");
		for (int x = 1; x <= inputSizes.length; x++) {
			System.out.print(".");
			String[] orig = RandomizationHelper.getShuffledStringArrayOfSizeN(inputSizes[x - 1]);
			String[] data = new String[orig.length];
			System.arraycopy(orig, 0, data, 0, orig.length);
			Arrays.sort(data);
			for (int algorithm = 0; algorithm < algorithms.length; algorithm++) {
				Dictionary<String, String> dict = createDictionary(algorithm, orig);
				for (int operationType = 0; operationType < operationTypes.length; operationType++) {
					long y = doOp(dict, operationType, data);
					elapsed[algorithm][operationType][x - 1] = y;
					logElapsed[algorithm][operationType][x - 1] = Math.log(y);

				}
			}
		}
		System.out.print("\n");

	}

	/**
	 * Execute the dictionary operation, verify correctness and record elapsed
	 * time
	 */
	private long doOp(Dictionary<String, String> dict, int operationType, String[] data) {
		int initSize = dict.size();
		String value;
		long elapsed = System.nanoTime();
		switch (operationType) {
		case 0:
			dict.put("zzzzzz", "at the very end");
			elapsed = System.nanoTime() - elapsed;
			assert ((initSize + 1) == dict.size());
			dict.remove("zzzzzz");
			break;
		case 1:
			value = dict.get(data[data.length / 2]);
			elapsed = System.nanoTime() - elapsed;
			assert (value.equals(reverse(data[data.length / 2])));
			break;
		case 2:
			dict.remove(data[data.length / 2]);
			elapsed = System.nanoTime() - elapsed;
			assert ((initSize - 1) == dict.size());
			dict.put(data[data.length / 2], reverse(data[data.length / 2]));
			break;
		case 3:
			value = dict.minimum();
			elapsed = System.nanoTime() - elapsed;
			assert (value.equals(data[0]));
			break;
		case 4:
			value = dict.maximum();
			elapsed = System.nanoTime() - elapsed;
			assert (value.equals(data[data.length - 1]));
			break;
		case 5:
			value = dict.predecessor(data[data.length - 1]);
			elapsed = System.nanoTime() - elapsed;
			assert (value.equals(data[data.length - 2]));
			break;
		case 6:
			value = dict.successor(data[data.length - 2]);
			elapsed = System.nanoTime() - elapsed;
			assert (value.equals(data[data.length - 1]));
			break;
		default:
			break;

		}
		return elapsed;
	}

	/**
	 * Create the dictionary based on input algorithm and initialize with
	 * provided data
	 * 
	 * @param algorithm
	 * @param data
	 *            - array of dictionary entries for initialization
	 * @return
	 */
	private Dictionary<String, String> createDictionary(int algorithm, String[] data) {

		Dictionary<String, String> dict;
		switch (algorithm) {
		case 0:
			dict = new UnsortedArrayDictionary();
			break;
		case 1:
			dict = new SortedArrayDictionary();
			break;
		case 2:
			dict = new UnsortedLinkedListDictionary();
			break;
		case 3:
			dict = new SortedLinkedListDictionary();
			break;
		case 4:
			dict = new BSTDictionary();
			break;
		case 5:
			dict = new BBSTDictionary();
			break;
		case 6:
			dict = new HashTableDictionary();
			break;
		default:
			dict = new UnsortedArrayDictionary();
			break;

		}
		for (int i = 0; i < data.length; i++) {
			dict.put(data[i], reverse(data[i]));
		}
		return dict;
	}

	/**
	 * Helper function to create a dummy value by reversing the key string
	 */
	private String reverse(String stringToReverse) {
		return new StringBuilder(stringToReverse).reverse().toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mathological.algos.analysis.PerformanceTest#inputSizes()
	 */
	@Override
	public int[] inputSizes() {
		return inputSizes;
	}

}