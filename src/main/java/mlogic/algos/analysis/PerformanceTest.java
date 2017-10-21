package mlogic.algos.analysis;

/**
 * Interface specifies how to generate performance test data in a usable form
 * for charting and analysis.
 * 
 * @author Rajaram G
 *
 */
public interface PerformanceTest {

	/**
	 * The class of algorithms being analyzed e.g. Dictionary, Sort, Search etc.
	 * 
	 * @return algorithm growth classification - Linear, Logarithmic etc
	 */
	String algorithmClass();

	/**
	 * X-Axis label
	 * 
	 * @return x-axis label
	 */
	String inputSizeLabel();

	/**
	 * The names of the operations to be analyzed for performance e.g. get, put,
	 * remove etc.
	 * 
	 * @return operation types to analyze
	 */
	String[] operationTypes();

	/**
	 * The names of the algorithm implementations to be compared
	 * 
	 * @return algorithm implementation names
	 */
	String[] algorithms();

	/**
	 * Input-sizes i.e. x-series
	 * 
	 * @return range of input sizes for performance test
	 */
	int[] inputSizes();

	/**
	 * Elapsed times i.e. y-series
	 * 
	 * @return 3-D array that captures elapsed times by operation type and
	 *         input-size
	 */
	long[][][] elapsed();

	/**
	 * Log of Elapsed times i.e. logy-series
	 * 
	 * @return 3-D array that captures log of elapsed times by operation type
	 *         and input-size
	 */
	double[][][] logElapsed();

	/**
	 * Instantiates the algorithm implementations for various input-sizes and
	 * executes each of the operations to generate performance test results.
	 */
	void execute();

}