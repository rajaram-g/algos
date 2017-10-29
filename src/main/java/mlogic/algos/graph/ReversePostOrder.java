package mlogic.algos.graph;

import mlogic.algos.struct.Stack;

/**
 * Identifies the reverse post order of nodes in a directed graph. This is the
 * reverse of the order in which processing is completed for each node in DFS.
 * Similar to Topological ordering, however the latter does not make sense for
 * cyclic graphs.
 * 
 * @author Rajaram G
 *
 */
public class ReversePostOrder extends DFS {

	/**
	 * Identifies reverse post order using DFS
	 * 
	 * @return array of nodes in reverse post order
	 */
	public static Integer[] order(DirectedGraph graph) {
		final boolean[] marked = new boolean[graph.nodeCount];
		final Stack<Integer> postOrder = new Stack<Integer>();
		DFS dfs = new DFS() {

			@Override
			protected void after(Integer node) {
				if (!marked[node]) {
					marked[node] = true;
					postOrder.push(node);
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
