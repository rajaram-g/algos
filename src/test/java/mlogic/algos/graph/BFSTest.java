package mlogic.algos.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
public class BFSTest {

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
	public void testBFSOnASimpleGraph() {
		final int[] level = new int[6];
		BFS bfs = new BFS() {
			@Override
			protected void processTreeEdge(Edge edge, Integer node) {
				level[edge.other(node)] = level[node] + 1;
			}
		};
		Graph graph = new UndirectedGraph(6);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 3);
		graph.addEdge(2, 4);
		graph.addEdge(3, 5);
		graph.addEdge(4, 5);
		bfs.execute(graph, 0);
		assertTrue(3 == level[5]);
	}

	@Test
	public void testBFSComputePreOrder() {
		final int[] preorder = new int[6];
		BFS bfs = new BFS() {
			private int current = 0;

			@Override
			protected void before(Integer node) {
				preorder[node] = current++;
			}
		};
		Graph graph = new UndirectedGraph(6);
		graph.addEdge(0, 2);
		graph.addEdge(0, 1);
		graph.addEdge(1, 3);
		graph.addEdge(1, 3);
		graph.addEdge(2, 4);
		graph.addEdge(2, 2);
		graph.addEdge(3, 5);
		graph.addEdge(4, 5);
		bfs.execute(graph, 0);
		assertEquals("[0, 1, 2, 3, 4, 5]", Arrays.toString(preorder));
	}

	@Test
	public void testBFSComputePostOrder() {
		final int[] postorder = new int[6];
		BFS bfs = new BFS() {
			private int current = 0;

			@Override
			protected void after(Integer node) {
				postorder[node] = current++;
			}
		};
		Graph graph = new UndirectedGraph(6);
		graph.addEdge(0, 2);
		graph.addEdge(0, 1);
		graph.addEdge(1, 3);
		graph.addEdge(1, 3);
		graph.addEdge(2, 4);
		graph.addEdge(2, 2);
		graph.addEdge(3, 5);
		graph.addEdge(4, 5);
		bfs.execute(graph, 0);
		assertEquals("[0, 1, 2, 3, 4, 5]", Arrays.toString(postorder));
	}

	@Test
	public void testBFSCountBackEdges() {
		final Integer[] backEdgeCount = new Integer[1];
		backEdgeCount[0] = 0;
		BFS bfs = new BFS() {
			@Override
			protected void processBackwardEdge(Edge e, Integer node) {
				System.out.println("Back from " + node + " to " + e.other(node));
				backEdgeCount[0]++;
			}
		};
		Graph graph = new UndirectedGraph(6);
		graph.addEdge(0, 2); // back edge from 2 to 0
		graph.addEdge(0, 1); // back edge from 1 to 0
		graph.addEdge(1, 3); // back edge from 3 to 1
		graph.addEdge(1, 3); // back edge from 3 to 1
		graph.addEdge(2, 4); // back edge from 4 to 2
		graph.addEdge(2, 2); // back edge from 2 to 2
		graph.addEdge(3, 5); // back edge from 5 to 3
		graph.addEdge(4, 5); // back edge from 5 to 4
		bfs.execute(graph, 0);
		assertEquals(new Integer(8), backEdgeCount[0]);
	}

}
