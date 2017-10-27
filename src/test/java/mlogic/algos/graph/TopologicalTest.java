package mlogic.algos.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;

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
public class TopologicalTest {

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
	public void testComplexDiGraphWithoutCycle() {
		try {
			String actual = Arrays.toString(Topological.sort(getComplexDiGraphWithoutCycle()));
			assertEquals("[8, 6, 9, 5, 7, 1, 2, 4, 3, 0]", actual);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test(expected = CyclicGraphException.class)
	public void testComplexDiGraphWithCycle() {
		try {
			String actual = Arrays.toString(Topological.sort(getComplexDiGraphWithCycle()));
			fail("Cycle not detected");
		} catch (CyclicGraphException e) {
			e.printStackTrace();
			throw e;
		}
	}

	private DirectedGraph getComplexDiGraphWithCycle() {
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

	private DirectedGraph getComplexDiGraphWithoutCycle() {
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
		// 8 6 9 5 7 1 2 4 3 0
	}

}
