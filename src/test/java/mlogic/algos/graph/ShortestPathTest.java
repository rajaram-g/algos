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
public class ShortestPathTest {

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
	 * {@link mlogic.algos.graph.ShortestPath#topologicalPath(mlogic.algos.graph.Graph, java.lang.Integer, java.lang.Integer)}.
	 */
	@Test
	public void testTopologicalPath() {
		Graph graph = getSimpleGraph();
		ShortestPath algo = new ShortestPath(graph, 0, 5);
		Integer[] shortestPathNodes = algo.topologicalPath();
		assertEquals("[0, 2, 4, 5]", Arrays.toString(shortestPathNodes));

		algo = new ShortestPath(graph, 0, 0);
		shortestPathNodes = algo.topologicalPath();
		assertEquals("[0]", Arrays.toString(shortestPathNodes));
	}

	/**
	 * Test method for
	 * {@link mlogic.algos.graph.ShortestPath#topologicalDistance(mlogic.algos.graph.Graph, java.lang.Integer, java.lang.Integer)}.
	 */
	@Test
	public void testTopologicalDistance() {
		Graph graph = getSimpleGraph();
		ShortestPath algo = new ShortestPath(graph, 0, 5);
		Integer shortestPathDist = algo.topologicalDistance();
		assertEquals(new Integer(3), shortestPathDist);
		algo = new ShortestPath(graph, 0, 0);
		shortestPathDist = algo.topologicalDistance();
		assertEquals(new Integer(0), shortestPathDist);
	}

	/**
	 * Test method for
	 * {@link mlogic.algos.graph.ShortestPath#topologicalPath(mlogic.algos.graph.Graph, java.lang.Integer, java.lang.Integer)}.
	 */
	@Test
	public void testTopologicalPathComplex() {
		Graph graph = getComplexGraph();
		ShortestPath algo = new ShortestPath(graph, 0, 9);
		Integer[] shortestPathNodes = algo.topologicalPath();
		assertEquals("[0, 4, 3, 5, 7, 9]", Arrays.toString(shortestPathNodes));

		algo = new ShortestPath(graph, 4, 2);
		shortestPathNodes = algo.topologicalPath();
		assertEquals("[4, 2]", Arrays.toString(shortestPathNodes));

		algo = new ShortestPath(graph, 9, 6);
		shortestPathNodes = algo.topologicalPath();
		assertEquals("[9, 6]", Arrays.toString(shortestPathNodes));

		algo = new ShortestPath(graph, 4, 7);
		shortestPathNodes = algo.topologicalPath();
		assertEquals("[4, 3, 5, 7]", Arrays.toString(shortestPathNodes));
	}

	/**
	 * Test method for
	 * {@link mlogic.algos.graph.ShortestPath#topologicalDistance(mlogic.algos.graph.Graph, java.lang.Integer, java.lang.Integer)}.
	 */
	@Test
	public void testTopologicalDistanceComplex() {
		Graph graph = getComplexGraph();
		ShortestPath algo = new ShortestPath(graph, 8, 1);
		Integer shortestPathDist = algo.topologicalDistance();
		assertEquals(new Integer(4), shortestPathDist);

		algo = new ShortestPath(graph, 4, 7);
		shortestPathDist = algo.topologicalDistance();
		assertEquals(new Integer(3), shortestPathDist);
	}

	private Graph getSimpleGraph() {
		Graph graph = new Graph(6);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 3);
		graph.addEdge(2, 4);
		graph.addEdge(3, 5);
		graph.addEdge(4, 5);
		return graph;
	}

	private Graph getComplexGraph() {
		Graph graph = new Graph(10);
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(1, 4);
		graph.addEdge(2, 0);
		graph.addEdge(2, 3);
		graph.addEdge(2, 4);
		graph.addEdge(3, 5);
		graph.addEdge(4, 3);
		graph.addEdge(4, 0);
		graph.addEdge(5, 6);
		graph.addEdge(5, 7);
		graph.addEdge(6, 7);
		graph.addEdge(6, 9);
		graph.addEdge(7, 8);
		graph.addEdge(7, 9);
		return graph;
	}

}
