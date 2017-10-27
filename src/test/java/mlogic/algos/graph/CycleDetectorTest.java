package mlogic.algos.graph;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mlogic.algos.exceptions.CyclicGraphException;

/**
 * @author Rajaram G
 *
 */
public class CycleDetectorTest {

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

	@Test(expected = CyclicGraphException.class)
	public void testComplexGraph() {
		try {
			CycleDetector.check(getComplexGraph());
		} catch (CyclicGraphException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Test(expected = CyclicGraphException.class)
	public void testComplexDiGraph() {
		try {
			CycleDetector.check(getComplexDiGraph());
		} catch (CyclicGraphException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Test
	public void testComplexGraphWithoutCycle() {
		try {
			CycleDetector.check(getComplexGraphWithoutCycle());
		} catch (CyclicGraphException e) {
			e.printStackTrace();
			fail("Cycle not expected");
		}
	}

	@Test
	public void testComplexDiGraphWithoutCycle() {
		try {
			CycleDetector.check(getComplexDiGraphWithoutCycle());
		} catch (CyclicGraphException e) {
			e.printStackTrace();
			fail("Cycle not expected");
		}
	}

	private Graph getComplexGraph() {
		Graph graph = new Graph(10);
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
		// 0 0 0 0 0 1 1 1 2 1
	}

	private Graph getComplexDiGraph() {
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
	}

	private Graph getComplexGraphWithoutCycle() {
		Graph graph = new Graph(10);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(1, 4);
		graph.addEdge(2, 0);
		graph.addEdge(6, 5);
		graph.addEdge(5, 7);
		graph.addEdge(6, 9);
		return graph;
	}

	private Graph getComplexDiGraphWithoutCycle() {
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
		graph.addEdge(6, 9);
		graph.addEdge(9, 7);
		return graph;
	}
}
