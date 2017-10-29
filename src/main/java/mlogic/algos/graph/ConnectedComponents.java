package mlogic.algos.graph;

/**
 * Uses BFS to determine connected components in an undirected graph
 * 
 * @author Rajaram G
 *
 */
public class ConnectedComponents {
	/**
	 * Graph to process
	 */
	private Graph graph;

	/**
	 * array to store the connected component index
	 */
	private Integer[] connected;

	/**
	 * Constructor
	 */
	public ConnectedComponents(Graph graph) {
		Graph.checkEmpty(graph);
		this.graph = graph;
		this.connected = new Integer[graph.nodeCount];
		for (int v = 0; v < graph.nodeCount; v++)
			connected[v] = -1;
		compute();
	}

	/**
	 * Identify connected components by iterating through the nodes and
	 * performing BFS on any unmarked node.
	 */
	private void compute() {
		final Integer[] currentIndex = { -1 };

		BFS bfs = new BFS() {

			@Override
			protected void processTreeEdge(Edge e, Integer from) {
				// System.out.println("Visiting edge " + from + "->" +
				// e.other(from));
				connected[e.other(from)] = currentIndex[0];
			}

		};
		for (int v = 0; v < graph.nodeCount; v++) {
			System.out.println("Visiting node " + v);
			if (connected[v] > -1)
				continue;
			connected[v] = ++currentIndex[0];
			bfs.execute(graph, v);
		}

	}

	/**
	 * @return array that stores the connected component index
	 */
	public Integer[] connectedComponentArray() {
		return connected;
	}
}
