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
	 */
	String algorithmClass();

	/**
	 * X-Axis label
	 */
	String inputSizeLabel();

	/**
	 * The names of the operations to be analyzed for performance e.g. get, put,
	 * remove etc.
	 */
	String[] operationTypes();

	/**
	 * The names of the algorithm implementations to be compared
	 */
	String[] algorithms();

	/**
	 * Input-sizes i.e. x-series
	 */
	int[] inputSizes();

	/**
	 * Elapsed times i.e. y-series
	 */
	long[][][] elapsed();

	/**
	 * Log of Elapsed times i.e. logy-series
	 */
	double[][][] logElapsed();

	/**
	 * Instantiates the algorithm implementations for various input-sizes and
	 * executes each of the operations to generate performance test results.
	 */
	void execute();

}