package mlogic.algos.graph;

/**
 * Uses Kosaraju-Sharir algorithm to identify the strongly connected components
 * in a directed graph. Two nodes A and B are strongly connected if there is a
 * path from A to B as well as from B to A.
 * 
 * @author Rajaram G
 *
 */
public class StronglyConnectedComponents {
	/**
	 * Graph to process
	 */
	private DirectedGraph graph;

	/**
	 * array to store the connected component index
	 */
	private Integer[] connected;

	/**
	 * Constructor
	 */
	public StronglyConnectedComponents(DirectedGraph graph) {
		Graph.checkEmpty(graph);
		this.graph = graph;
		this.connected = new Integer[graph.nodeCount];
		for (int v = 0; v < graph.nodeCount; v++)
			connected[v] = -1;
		compute();

	}

	/**
	 * Perform DFS on nodes in reverse post order of reverse of input graph
	 */
	private void compute() {
		final int[] currentIndex = { -1 };
		DirectedGraph reverse = graph.reverse();
		final boolean[] marked = new boolean[graph.nodeCount];

		DFS dfs = new DFS() {
			@Override
			protected void after(Integer node) {
				// System.out.println("Visiting edge " + from + "->" +
				// e.other(from));
				if (!marked[node]) {
					marked[node] = true;
					connected[node] = currentIndex[0];
				}
			}

		};
		Integer[] reversePostOrderOfReverseGraph = ReversePostOrder.order(reverse);
		for (Integer v : reversePostOrderOfReverseGraph) {
			if (connected[v] > -1)
				continue;
			currentIndex[0]++;
			dfs.execute(graph, v);

		}

	}

	/**
	 * @return array that stores the connected component index
	 */
	public Integer[] connectedComponentArray() {
		return connected;
	}

}
