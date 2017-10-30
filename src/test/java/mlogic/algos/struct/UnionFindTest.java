package mlogic.algos.struct;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Rajaram G
 *
 */
public class UnionFindTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link mlogic.algos.struct.UnionFind#find(java.lang.Integer)}.
	 */
	@Test
	public void testFindAndMergeAndFind() {
		UnionFind uf = new UnionFind(10);
		assert (uf.find(9) == 9);
		uf.merge(0, 9);
		uf.merge(1, 5);
		uf.merge(5, 9);
		assert (uf.find(5) == 1);
		uf.merge(2, 8);
		uf.merge(8, 3);
		assert (uf.find(3) == 2);
		uf.merge(3, 9);
		assert (uf.find(3) == 1);

	}

}
