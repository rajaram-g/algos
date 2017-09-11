/**
 * 
 */
package mlogic.algos.analysis;

import org.junit.Test;

import mlogic.algos.analysis.OrderOfGrowthEstimator;

/**
 * @author Rajaram G
 *
 */
public class OrderOfGrowthEstimatorTest {

	/**
	 * Test method for
	 * {@link mlogic.algos.analysis.OrderOfGrowthEstimator#estimate(int[], int[])}.
	 */
	@Test
	public void testConstantGrowth() {
		int[] xseries = { 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		long[] yseries = constructConstantGrowthArray(xseries);
		OrderOfGrowthEstimator.GrowthClass fn = OrderOfGrowthEstimator.estimate(xseries, yseries);
		assert (fn == OrderOfGrowthEstimator.GrowthClass.O_1);
	}

	@Test
	public void testLinearGrowth() {
		int[] xseries = { 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		long[] yseries = constructLinearGrowthArray(xseries);
		OrderOfGrowthEstimator.GrowthClass fn = OrderOfGrowthEstimator.estimate(xseries, yseries);
		assert (fn == OrderOfGrowthEstimator.GrowthClass.O_N);
	}

	@Test
	public void testLinearithmicGrowth() {
		int[] xseries = { 4, 8, 16, 32, 64, 128, 512, 1024, 2048, 4096 };
		long[] yseries = { 4 * 2, 8 * 3, 16 * 4, 32 * 5, 64 * 6, 128 * 7, 512 * 8, 1024 * 9, 2048 * 10, 4096 * 11 };
		OrderOfGrowthEstimator.GrowthClass fn = OrderOfGrowthEstimator.estimate(xseries, yseries);
		System.out.println(fn);
		assert (fn == OrderOfGrowthEstimator.GrowthClass.O_NLOGN);
	}

	@Test
	public void testLogGrowth() {
		int[] xseries = { 4, 8, 16, 32, 64, 128, 512, 1024, 2048, 4096 };
		long[] yseries = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
		OrderOfGrowthEstimator.GrowthClass fn = OrderOfGrowthEstimator.estimate(xseries, yseries);
		assert (fn == OrderOfGrowthEstimator.GrowthClass.O_LOGN);
	}

	@Test
	public void testPolyGrowth() {
		int[] xseries = { 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		long[] yseries = constructPolyGrowthArray(xseries);
		OrderOfGrowthEstimator.GrowthClass fn = OrderOfGrowthEstimator.estimate(xseries, yseries);
		assert (fn == OrderOfGrowthEstimator.GrowthClass.O_POLYN);
	}

	@Test
	public void testExpGrowth() {
		int[] xseries = { 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		long[] yseries = constructExpGrowthArray(xseries);
		OrderOfGrowthEstimator.GrowthClass fn = OrderOfGrowthEstimator.estimate(xseries, yseries);
		assert (fn == OrderOfGrowthEstimator.GrowthClass.O_EXPN);
	}

	private long[] constructConstantGrowthArray(int[] xseries) {
		long[] yseries = new long[xseries.length];
		for (int i = 0; i < xseries.length; i++)
			yseries[i] = 10;
		return yseries;
	}

	private long[] constructLinearGrowthArray(int[] xseries) {
		long[] yseries = new long[xseries.length];
		int K = 10;
		int C = 5;
		for (int i = 0; i < xseries.length; i++)
			yseries[i] = K * xseries[i] + C;
		return yseries;
	}

	private long[] constructPolyGrowthArray(int[] xseries) {
		long[] yseries = new long[xseries.length];
		int K = 10;
		int C = 5;
		int p = 3;
		for (int i = 0; i < xseries.length; i++) {
			Double power = Math.pow(xseries[i], p);
			yseries[i] = K * (power.intValue()) + C;
		}
		return yseries;
	}

	private long[] constructExpGrowthArray(int[] xseries) {
		long[] yseries = new long[xseries.length];
		int K = 10;
		int C = 5;
		int b = 2;
		for (int i = 0; i < xseries.length; i++) {
			Double exp = Math.pow(b, xseries[i]);
			yseries[i] = K * (exp.intValue()) + C;
		}
		return yseries;
	}
}
