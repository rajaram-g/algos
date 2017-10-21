package mlogic.algos.analysis;

/**
 * WIP...
 * <p>
 * Helps classifies an X-Y data set into one of the following growth classes:
 * <ul>
 * <li>Constant - O(1)</li>
 * <li>Logarithmic - O(log n)</li>
 * <li>Linear - O(n)</li>
 * <li>Linearithmic - O(n log n)</li>
 * <li>Poly - O(poly n)</li>
 * <li>Exponential - O(exp n)</li>
 * </ul>
 * <p>
 * 
 * @author Rajaram G
 *
 */
public class OrderOfGrowthEstimator {

	/**
	 * Enumerates the Big O growth classes
	 * 
	 */
	public enum GrowthClass {
		/**
		 * Unknown
		 */
		UNKNOWN("Unknown"),

		/**
		 * Constant
		 */
		O_1("O(1)"),

		/**
		 * Logarithmic
		 */
		O_LOGN("O(log n)"),

		/**
		 * Linear
		 */
		O_N("O(n)"),

		/**
		 * Linearithmic
		 */
		O_NLOGN("O(n log n)"),

		/**
		 * Polynomial
		 */
		O_POLYN("O(poly n)]"),

		/**
		 * Exponential
		 */
		O_EXPN("O(exp n)");

		/**
		 * Growth class display label
		 */
		private String label;

		/**
		 * Constructor for Growth Class Enum
		 */
		GrowthClass(String label) {
			this.label = label;
		}

		/**
		 * @return growth class display label
		 */
		public String label() {
			return this.label;
		}

	}

	/**
	 * Given a data set of algorithm time (or space) values vs. input sizes,
	 * estimates the order of growth
	 * 
	 * @param xseries
	 *            non-zero input sizes in increasing order
	 * @param yseries
	 *            run time (or space) values
	 * @return Growth Class enum
	 */
	public static GrowthClass estimate(int[] xseries, long[] yseries) {
		checkArguments(xseries, yseries);

		Double epsilon = computeEpsilon(xseries.length);
		int[] classCounts = new int[7];

		int xprev = 0;
		long yprev = 0;
		Double mprev = 0.0;
		Double logyprev = 0.0;
		Double mlogprev = 0.0;
		Double mmlogprev = 0.0;
		for (int i = 0; i < xseries.length; i++) {

			int x = xseries[i];

			if (x == 0 || x < xprev) // Input error - ignore
				continue;

			long y = yseries[i];
			if (y < yprev) // Anomaly - ignore
				continue;

			Double logy = Math.log(y);
			Double m = (double) (y - yprev) / (double) (x - xprev);

			Double mlog = (double) (logy - logyprev) / (double) (x - xprev);
			Double mlogref = (double) (Math.log(x) + Math.log(Math.log(x)) - Math.log(xprev)
					- Math.log(Math.log(xprev))) / (double) (x - xprev);

			if (i > 0) {

				if (m < epsilon)
					classCounts[GrowthClass.O_1.ordinal()]++;
				else if (Math.abs(m - mprev) <= epsilon)
					classCounts[GrowthClass.O_N.ordinal()]++;
				else if (m < mprev)
					classCounts[GrowthClass.O_LOGN.ordinal()]++;
				else {
					if (mlog < mlogref + epsilon) {
						classCounts[GrowthClass.O_NLOGN.ordinal()]++;
					} else if (mlog < mlogprev - epsilon) {
						classCounts[GrowthClass.O_POLYN.ordinal()]++;
					} else if (mlog >= (mlogprev - epsilon)) {
						classCounts[GrowthClass.O_EXPN.ordinal()]++;
					} else
						classCounts[GrowthClass.UNKNOWN.ordinal()]++;

				}

			}
			yprev = y;
			xprev = x;
			mprev = m;
			logyprev = logy;
			mlogprev = mlog;

		}
		int max = getMax(classCounts);
		return GrowthClass.values()[max];

	}

	private static int getMax(int[] array) {
		int max = 0;
		int maxval = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] > maxval) {
				maxval = array[i];
				max = i;
			}
		}
		return max;
	}

	/**
	 * Computes upper limit for "negligible" values given size of input.
	 * Currently defaulted to 0.005.
	 * 
	 * @param size
	 *            of input values
	 * @return
	 */
	private static Double computeEpsilon(int size) {
		return 0.005; // For now let epsilon be 0.5%
	}

	private static void checkArguments(int[] xseries, long[] yseries) {
		if (xseries == null || yseries == null)
			throw new IllegalArgumentException();
		if (xseries.length != yseries.length)
			throw new IllegalArgumentException();
	}

}
