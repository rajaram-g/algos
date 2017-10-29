package mlogic.algos.graph;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Rajaram G
 *
 */
public class StronglyConnectedComponentsTest {

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
	 * {@link mlogic.algos.graph.ConnectedComponents#ConnectedComponents(mlogic.algos.graph.Graph)}.
	 */
	@Test
	public void testComplexDiGraph() {
		StronglyConnectedComponents algo = new StronglyConnectedComponents(getComplexDiGraph());
		String actual = Arrays.toString(algo.connectedComponentArray());
		assertEquals("[3, 6, 5, 2, 4, 1, 1, 1, 0, 1]", actual);
	}

	private DirectedGraph getComplexDiGraph() {
		DirectedGraph graph = new DirectedGraph(10);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(1, 4);
		graph.addEdge(2, 0);
		graph.addEdge(2, 3);
		graph.addEdge(2, 4);
		graph.addEdge(4, 3);
		graph.addEdge(4, 0);
		graph.addEdge(6, 5);
		graph.addEdge(5, 7);
		graph.addEdge(7, 6);
		graph.addEdge(6, 9);
		graph.addEdge(9, 7);
		return graph;
		// 3 6 5 2 4 1 1 1 0 1
	}
}
