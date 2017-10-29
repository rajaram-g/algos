package mlogic.algos.graph;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
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
public class ShortestWeightedPathTest {

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
	 * {@link mlogic.algos.graph.ShortestWeightedPath#physicalPath()}.
	 */
	@Test
	public void testPhysicalPathSimpleGraph() {
		Graph graph = getSimpleGraph();
		ShortestWeightedPath algo = new ShortestWeightedPath(graph, 0, 5);
		Integer[] shortestPathNodes = algo.physicalPath();
		assertEquals("[0, 1, 3, 5]", Arrays.toString(shortestPathNodes));

		algo = new ShortestWeightedPath(graph, 0, 0);
		shortestPathNodes = algo.physicalPath();
		assertEquals("[0]", Arrays.toString(shortestPathNodes));
	}

	/**
	 * Test method for
	 * {@link mlogic.algos.graph.ShortestWeightedPath#physicalDistance()}.
	 */
	@Test
	public void testPhysicalDistanceSimpleGraph() {
		Graph graph = getSimpleGraph();
		ShortestWeightedPath algo = new ShortestWeightedPath(graph, 0, 5);
		BigDecimal shortestPathDistance = algo.physicalDistance();
		assertEquals(new BigDecimal(17), shortestPathDistance);

		algo = new ShortestWeightedPath(graph, 0, 0);
		shortestPathDistance = algo.physicalDistance();
		assertEquals(new BigDecimal(0), shortestPathDistance);
	}

	/**
	 * Test method for
	 * {@link mlogic.algos.graph.ShortestWeightedPath#physicalPath()}.
	 */
	@Test
	public void testPhysicalPathComplexGraph() {
		Graph graph = getComplexGraph();
		ShortestWeightedPath algo = new ShortestWeightedPath(graph, 0, 9);
		Integer[] shortestPathNodes = algo.physicalPath();
		assertEquals("[0, 2, 4, 3, 5, 6, 7, 9]", Arrays.toString(shortestPathNodes));
	}

	/**
	 * Test method for
	 * {@link mlogic.algos.graph.ShortestWeightedPath#physicalDistance()}.
	 */
	@Test
	public void testPhysicalDistanceComplexGraph() {
		Graph graph = getComplexGraph();
		ShortestWeightedPath algo = new ShortestWeightedPath(graph, 0, 9);
		BigDecimal shortestPathDistance = algo.physicalDistance();
		assertEquals(new BigDecimal(36), shortestPathDistance);

	}

	private Graph getSimpleGraph() {
		Graph graph = new UndirectedGraph(6);
		graph.addEdge(0, 1, new BigDecimal(5));
		graph.addEdge(0, 2, new BigDecimal(10));
		graph.addEdge(1, 3, new BigDecimal(8));
		graph.addEdge(2, 4, new BigDecimal(4));
		graph.addEdge(3, 5, new BigDecimal(4));
		graph.addEdge(4, 5, new BigDecimal(4));
		return graph;
	}

	private Graph getComplexGraph() {
		Graph graph = new UndirectedGraph(10);
		graph.addEdge(0, 1, new BigDecimal(5));
		graph.addEdge(1, 2, new BigDecimal(3));
		graph.addEdge(1, 3, new BigDecimal(10));
		graph.addEdge(1, 4, new BigDecimal(5));
		graph.addEdge(2, 0, new BigDecimal(2));
		graph.addEdge(2, 3, new BigDecimal(4));
		graph.addEdge(2, 4, new BigDecimal(1));
		graph.addEdge(3, 5, new BigDecimal(15));
		graph.addEdge(4, 3, new BigDecimal(2));
		graph.addEdge(4, 0, new BigDecimal(5));
		graph.addEdge(5, 6, new BigDecimal(4));
		graph.addEdge(5, 7, new BigDecimal(9));
		graph.addEdge(6, 7, new BigDecimal(4));
		graph.addEdge(6, 9, new BigDecimal(15));
		graph.addEdge(7, 8, new BigDecimal(5));
		graph.addEdge(7, 9, new BigDecimal(8));
		return graph;
		// 0 2 4 3 5 6 7 9 = 36
	}

}
