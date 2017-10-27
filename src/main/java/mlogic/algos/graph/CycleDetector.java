package mlogic.algos.graph;

import mlogic.algos.exceptions.CyclicGraphException;

/**
 * Detects if a graph has a cycle using DFS
 * 
 * @author Rajaram G
 *
 */
public class CycleDetector {

	/**
	 * Checks and throws CycleFoundException if graph has a cycle
	 */
	public static void check(final Graph graph) {
		Graph.checkEmpty(graph);
		final boolean[] marked = new boolean[graph.nodeCount];
		final Integer[] nodeTo = new Integer[graph.nodeCount];
		DFS dfs;
		dfs = getDFSGivenGraphType(graph, marked, nodeTo);

		for (Integer v = 0; v < graph.nodeCount; v++) {
			if (marked[v])
				continue;
			dfs.execute(graph, v);
		}
	}

	/**
	 * Creates a DFS object with cycle detection logic adjusted for undirected
	 * vs. directed graphs
	 * 
	 * @param graph
	 * @param marked
	 *            array storing visited nodes
	 * @param nodeTo
	 *            array showing parent of each node in DFS tree
	 * @return
	 */
	private static DFS getDFSGivenGraphType(final Graph graph, final boolean[] marked, final Integer[] nodeTo) {
		DFS dfs;
		if (graph instanceof DirectedGraph) {
			dfs = new DFS() {
				@Override
				protected void before(Integer node) {
					marked[node] = true;
				}

				@Override
				protected void processTreeEdge(Edge edge, Integer from) {
					nodeTo[edge.other(from)] = from;
				}

				@Override
				protected void processBackwardEdge(Edge edge, Integer from) {
					throw new CyclicGraphException("Edge " + from + "->" + edge.other(from) + " forms a cycle.");
				}

			};
		} else {
			dfs = new DFS() {
				@Override
				protected void before(Integer node) {
					marked[node] = true;
				}

				@Override
				protected void processTreeEdge(Edge edge, Integer from) {
					nodeTo[edge.other(from)] = from;
				}

				@Override
				protected void processBackwardEdge(Edge edge, Integer from) {
					// For undirected graphs skip evaluation of the immediate
					// reverse edge
					if (nodeTo[from] != edge.other(from))
						throw new CyclicGraphException("Edge " + from + "->" + edge.other(from) + " forms a cycle.");
				}
			};

		}
		return dfs;
	}

}
