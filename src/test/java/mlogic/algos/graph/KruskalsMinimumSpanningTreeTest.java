package mlogic.algos.graph;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mlogic.algos.struct.List;

/**
 * @author Rajaram G
 *
 */
public class KruskalsMinimumSpanningTreeTest {

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
	public void testGetMSTWeightComplexGraph() {
		KruskalsMinimumSpanningTree algo = new KruskalsMinimumSpanningTree(getComplexGraph());
		List<Edge> msf = algo.minimumSpanningForest();
		BigDecimal total = sumMSTEdges(msf);
		assertEquals(new BigDecimal(48), total);
	}

	/**
	 * @param list
	 *            of edges
	 * @return
	 */
	private BigDecimal sumMSTEdges(List<Edge> edges) {
		BigDecimal total = new BigDecimal(0);
		for (Edge e : edges)
			total = total.add(e.weight());
		return total;
	}

	private UndirectedGraph getComplexGraph() {
		UndirectedGraph graph = new UndirectedGraph(10);
		graph.addEdge(0, 1, 10.0);
		graph.addEdge(0, 2, 5.0);
		graph.addEdge(1, 3, 10.0);
		graph.addEdge(1, 2, 4.0);
		graph.addEdge(2, 3, 15.0);
		graph.addEdge(3, 4, 6.0);
		graph.addEdge(3, 5, 9.0);
		graph.addEdge(4, 5, 2.0);
		graph.addEdge(6, 7, 8.0);
		graph.addEdge(6, 8, 12.0);
		graph.addEdge(6, 9, 10.0);
		graph.addEdge(7, 9, 15.0);
		graph.addEdge(8, 9, 3.0);

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
