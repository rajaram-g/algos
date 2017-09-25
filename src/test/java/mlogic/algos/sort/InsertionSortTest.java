package mlogic.algos.sort;

/**
 * @author Rajaram G
 *
 */
public class InsertionSortTest extends SortTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see mlogic.algos.sort.SortTest#createIntegerSorter(java.lang.Integer[])
	 */
	@Override
	protected Sort<Integer> createIntegerSorter(Integer[] array) {
		return new InsertionSort<Integer>(array);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mlogic.algos.sort.SortTest#createStringSorter(java.lang.String[])
	 */
	@Override
	protected Sort<String> createStringSorter(String[] array) {
		return new InsertionSort<String>(array);

	}

}
