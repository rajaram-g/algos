package mlogic.algos.graph;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Rajaram G
 *
 */
public class GraphTest {

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
	public void testASimpleUnweightedGraph() {
		Graph graph = new Graph(6);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 3);
		graph.addEdge(2, 4);
		graph.addEdge(3, 5);
		graph.addEdge(4, 5);
		assertEquals(new Integer(6), graph.edgeCount);
		System.out.println(graph.toString());
	}

	@Test
	public void testASimpleUnweightedGraphWithDuplicates() {
		Graph graph = new Graph(6);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(0, 2);
		graph.addEdge(1, 3);
		graph.addEdge(2, 4);
		graph.addEdge(3, 5);
		graph.addEdge(3, 1);
		graph.addEdge(4, 5);
		assertEquals(new Integer(8), graph.edgeCount);
		System.out.println(graph.toString());
	}

	@Test
	public void testASimpleUnweightedGraphWithSelfEdges() {
		Graph graph = new Graph(6);
		graph.addEdge(0, 1);
		graph.addEdge(0, 0);
		graph.addEdge(0, 2);
		graph.addEdge(1, 3);
		graph.addEdge(2, 4);
		graph.addEdge(3, 5);
		graph.addEdge(3, 1);
		graph.addEdge(4, 5);
		assertEquals(new Integer(8), graph.edgeCount);
		System.out.println(graph.toString());
	}

	@Test
	public void testALargeWeightedGraphWithDuplicates() {
		int nodeCount = 100;
		int edgeCount = 300;
		Graph graph = new Graph(nodeCount);
		Random random = new Random();
		for (int e = 0; e < edgeCount; e++) {
			Integer u = random.nextInt(nodeCount);
			Integer v = random.nextInt(nodeCount);
			BigDecimal w = new BigDecimal(random.nextInt(100));
			graph.addEdge(u, v, w);

		}
		assertEquals(new Integer(300), graph.edgeCount);
		System.out.println(graph.toString());
	}
}
