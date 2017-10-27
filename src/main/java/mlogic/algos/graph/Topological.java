package mlogic.algos.graph;

import mlogic.algos.exceptions.CyclicGraphException;
import mlogic.algos.struct.Stack;

/**
 * Identifies the topological sort order of nodes in a directed graph.
 * Topological order is an arrangement such that for any edge, the *from* node
 * is to the left of the *to* node in the sorted order. Topological sort is
 * infeasible on cyclic graphs.
 * 
 * @author Rajaram G
 *
 */
public class Topological extends DFS {

	/**
	 * Performs topological sort using DFS
	 * 
	 * @return array of nodes in topological order
	 */
	public static Integer[] sort(DirectedGraph graph) {
		final boolean[] marked = new boolean[graph.nodeCount];
		final Stack<Integer> postOrder = new Stack<Integer>();
		DFS dfs = new DFS() {

			@Override
			protected void processBackwardEdge(Edge edge, Integer node) {
				throw new CyclicGraphException("Edge " + node + "->" + edge.other(node)
						+ " forms a cycle. Topological sort cannot be performed.");
			}

			@Override
			protected void after(Integer from) {
				if (!marked[from]) {
					marked[from] = true;
					postOrder.push(from);
				}
			}

		};
		for (int v = 0; v < graph.nodeCount; v++) {
			if (marked[v])
				continue;
			dfs.execute(graph, v);
		}

		Integer[] reversePostOrder = new Integer[postOrder.size()];
		int current = 0;
		while (true) {
			Integer next = postOrder.pop();
			if (next == null)
				break;
			reversePostOrder[current++] = next;
		}

		return reversePostOrder;

	}

}
