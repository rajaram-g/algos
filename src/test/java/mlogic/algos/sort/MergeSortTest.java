package mlogic.algos.sort;

/**
 * @author Rajaram G
 *
 */
public class MergeSortTest extends SortTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see mlogic.algos.sort.SortTest#createIntegerSorter(java.lang.Integer[])
	 */
	@Override
	protected Sort<Integer> createIntegerSorter(Integer[] array) {
		return new MergeSort<Integer>(array);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mlogic.algos.sort.SortTest#createStringSorter(java.lang.String[])
	 */
	@Override
	protected Sort<String> createStringSorter(String[] array) {
		return new MergeSort<String>(array);

	}

}
