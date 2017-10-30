package mlogic.algos.graph;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Rajaram G
 *
 */
public class MaxFlowTest {

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

	@Test
	public void testSimpleFlowNetwork() {
		MaxFlow algo = new MaxFlow(getSimpleFlowNetwork(), 0, 8);
		assertEquals(new BigDecimal(20), algo.maxFlow());
		assertEquals(algo.maxFlow(), algo.minCut());

	}

	/**
	 * @return
	 */
	private FlowNetwork getSimpleFlowNetwork() {
		FlowNetwork graph = new FlowNetwork(10);
		graph.addEdge(0, 1, new BigDecimal(15));
		graph.addEdge(0, 2, new BigDecimal(20));
		graph.addEdge(0, 3, new BigDecimal(10));
		graph.addEdge(1, 4, new BigDecimal(10));
		graph.addEdge(1, 5, new BigDecimal(8));
		graph.addEdge(2, 5, new BigDecimal(12));
		graph.addEdge(3, 5, new BigDecimal(9));
		graph.addEdge(3, 6, new BigDecimal(7));
		graph.addEdge(4, 7, new BigDecimal(25));
		graph.addEdge(4, 8, new BigDecimal(20));
		graph.addEdge(5, 8, new BigDecimal(10));
		graph.addEdge(6, 9, new BigDecimal(30));
		return graph;
	}

}
