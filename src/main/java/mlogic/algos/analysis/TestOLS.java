package mlogic.algos.analysis;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

/**
 * @author Rajaram G
 *
 */
public class TestOLS {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		fit(new double[] { 5.0, 2.0, 0, 0, 0, 0, 0, 0 }, 100);
		fit(new double[] { 5.0, 2.0, 0, 3.0, 0, 0, 0, 0 }, 100);
		fit(new double[] { 5.0, 2.0, 0, 3.0, 0, 0.35, 0, 0 }, 100);
		fit(new double[] { 5.0, 2.0, 0, 3.0, 0, 0.35, 5, 0 }, 10);
		fit(new double[] { 5.0, 2.0, 0, 3.0, 0, 0.35, 0, 6 }, 10);
		// logfit(new double[] { 5.0, 2.0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 1000,
		// 20);
		// logfit(new double[] { 5.0, 2.0, 3.0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		// 1000, 20);
		logfit(new double[] { 5.0, 1, 1, 0, 0, 0, 0, 0, 0, 0 }, 100, 10);

	}

	private static void fit(double[] b, int n) {
		if (b == null || b.length != 8)
			throw new IllegalArgumentException();

		OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
		regression.setNoIntercept(false);

		int m = b.length - 1;
		double[] yarr = new double[n];
		double[][] xarr = new double[n][m];

		for (int i = 0; i < n; i++) {
			double x = (double) (i + 1);
			xarr[i][0] = x;
			xarr[i][1] = Math.pow(x, 2);
			xarr[i][2] = Math.pow(x, 3);
			xarr[i][3] = Math.pow(x, 4);
			xarr[i][4] = Math.pow(x, 5);
			xarr[i][5] = Math.pow(2, x);
			xarr[i][6] = Math.pow(Math.E, x);

			yarr[i] = b[0];
			for (int j = 0; j < m; j++)
				yarr[i] += b[j + 1] * xarr[i][j];
		}
		regression.newSampleData(yarr, xarr);
		double[] beta = regression.estimateRegressionParameters();
		for (int j = 0; j < beta.length; j++)
			beta[j] = BigDecimal.valueOf(beta[j]).setScale(3, RoundingMode.HALF_UP).doubleValue();
		System.out.println("Test fit ");
		System.out.println("Expected: " + Arrays.toString(b));
		System.out.println("Actual Coeffs: " + Arrays.toString(beta));

	}

	private static void logfit(double[] b, int n, int m) {
		if (b == null)
			throw new IllegalArgumentException();

		OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
		regression.setNoIntercept(false);

		int nvars = b.length - 1;
		double[] yarr = new double[m * n];
		double[][] xarr = new double[m * n][4];

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < m + 1; j++) {
				int index = (i - 1) * 2 + (j - 1);
				xarr[index][0] = Math.log(i);
				xarr[index][1] = Math.log(j);
				xarr[index][2] = Math.log(i + j);
				xarr[index][3] = Math.log(i * j);

				yarr[index] = b[0];
				for (int k = 1; k < 5; k++)
					yarr[index] += b[k] * xarr[index][k - 1];

			}

		}
		regression.newSampleData(yarr, xarr);
		// System.out.println("Expected: " + Arrays.toString(yarr));
		// System.out.println("Expected: " + Arrays.deepToString(xarr));
		double[] beta = regression.estimateRegressionParameters();
		for (int k = 0; k < beta.length; k++)
			beta[k] = BigDecimal.valueOf(beta[k]).setScale(3, RoundingMode.HALF_UP).doubleValue();
		System.out.println("Test log fit ");
		System.out.println("Expected: " + Arrays.toString(b));
		System.out.println("Actual Coeffs: " + Arrays.toString(beta));

		// double[] residuals = regression.estimateResiduals();
		// System.out.println("residuals: " + Arrays.toString(residuals));

		System.out.println("sigma: " + regression.estimateRegressionStandardError());

	}

	private static void logfitold(double[] b, int n, int m) {
		if (b == null || b.length != 12)
			throw new IllegalArgumentException();

		OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
		regression.setNoIntercept(false);

		int nvars = b.length - 1;
		// int nvars = 2;
		double[] yarr = new double[m * n];
		double[][] xarr = new double[m * n][nvars];

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < m + 1; j++) {
				int index = (i - 1) * 2 + (j - 1);
				// c, m, n,
				// log m, log n,
				// log (m+n), mlogm, mlogn , mlog(m+n), n log m, n log n , n log
				// (m+n)
				xarr[index][0] = j;
				xarr[index][1] = i;
				xarr[index][2] = Math.log(j);
				xarr[index][3] = Math.log(i);
				xarr[index][4] = Math.log(i + j);
				xarr[index][5] = j * Math.log(j);
				xarr[index][6] = j * Math.log(i);
				xarr[index][7] = j * Math.log(i + j);
				xarr[index][8] = i * Math.log(j);
				xarr[index][9] = i * Math.log(i);
				xarr[index][10] = i * Math.log(i + j);
				yarr[index] = b[0];
				// for (int k = 1; k < b.length; k++)
				// yarr[index] += b[k] * xarr[index][k - 1];

			}

		}
		regression.newSampleData(yarr, xarr);
		double[] beta = regression.estimateRegressionParameters();
		for (int k = 0; k < beta.length; k++)
			beta[k] = BigDecimal.valueOf(beta[k]).setScale(3, RoundingMode.HALF_UP).doubleValue();
		System.out.println("Test log fit ");
		System.out.println("Expected: " + Arrays.toString(b));
		System.out.println("Actual Coeffs: " + Arrays.toString(beta));

	}
}
