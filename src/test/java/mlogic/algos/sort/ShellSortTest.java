package mlogic.algos.sort;

/**
 * @author Rajaram G
 *
 */
public class ShellSortTest extends SortTest {

	/*
	 * (non-Javadoc)
	 * 
	 * @see mlogic.algos.sort.SortTest#createIntegerSorter(java.lang.Integer[])
	 */
	@Override
	protected Sort<Integer> createIntegerSorter(Integer[] array) {
		return new ShellSort<Integer>(array);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mlogic.algos.sort.SortTest#createStringSorter(java.lang.String[])
	 */
	@Override
	protected Sort<String> createStringSorter(String[] array) {
		return new ShellSort<String>(array);

	}

}
