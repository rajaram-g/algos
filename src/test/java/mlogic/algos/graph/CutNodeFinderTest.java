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
public class CutNodeFinderTest {

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
	public void testComplexGraph() {
		Integer[] cutNodes = CutNodeFinder.cutNodes(getComplexGraph());
		assertEquals("[1, 2, 3, 2, 0, 0, 2, 0, 0, 2, 0]", Arrays.toString(cutNodes));
	}

	@Test
	public void testComplexDisconnectedGraph() {
		Integer[] cutNodes = CutNodeFinder.cutNodes(getComplexDisconnectedGraph());
		assertEquals("[1, 2, 3, 0, 0, 0, 1, 0, 0, 2, 0]", Arrays.toString(cutNodes));
	}

	private UndirectedGraph getComplexGraph() {
		UndirectedGraph graph = new UndirectedGraph(11);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 3);
		graph.addEdge(1, 4);
		graph.addEdge(2, 4);
		graph.addEdge(2, 5);
		graph.addEdge(3, 6);
		graph.addEdge(5, 9);
		graph.addEdge(6, 7);
		graph.addEdge(6, 8);
		graph.addEdge(9, 10);
		graph.addEdge(9, 2);

		return graph;
	}

	private UndirectedGraph getComplexDisconnectedGraph() {
		UndirectedGraph graph = new UndirectedGraph(11);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 3);
		graph.addEdge(1, 4);
		graph.addEdge(2, 4);
		graph.addEdge(2, 5);
		graph.addEdge(5, 9);
		graph.addEdge(6, 7);
		graph.addEdge(6, 8);
		graph.addEdge(9, 10);
		graph.addEdge(9, 2);

		return graph;
	}
}
