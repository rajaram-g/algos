package mlogic.algos.graph;

import java.util.Arrays;

/**
 * Uses DFS to identify cut nodes in a graph. Cut nodes partition the graph when
 * removed.
 * 
 * @author Rajaram G
 *
 */
public class CutNodeFinder {

	public static Integer[] cutNodes(UndirectedGraph graph) {
		Graph.checkEmpty(graph);
		final Integer[] cutNodes = new Integer[graph.nodeCount];
		final int ROOT_CUT = 1;
		final int BRIDGE_CUT = 2;
		final int PARENT_CUT = 3;

		final int[] level = new int[graph.nodeCount];
		final int[] parent = new int[graph.nodeCount];
		final int[] degree = new int[graph.nodeCount];
		final Integer[] reachableAncestor = new Integer[graph.nodeCount];
		for (int v = 0; v < graph.nodeCount; v++) {
			reachableAncestor[v] = v;
			parent[v] = -1;
			cutNodes[v] = 0;
		}

		DFS dfs = new DFS() {
			/**
			 * Increments the degree of each node for every edge
			 */
			@Override
			protected void processEdge(Edge edge, Integer from) {
				Integer to = edge.other(from);
				// Skip reverse edges
				if (parent[from] == to)
					return;
				degree[from]++;

			}

			/**
			 * Sets level and marks the path to a node in depth first order
			 */
			@Override
			protected void processTreeEdge(Edge edge, Integer from) {
				Integer to = edge.other(from);
				level[to] = level[from] + 1;
				parent[to] = from;

			}

			/**
			 * If a node can reach an earlier ancestor than its parent, then the
			 * parent can also reach the same ancestor.
			 */
			@Override
			protected void processBackwardEdge(Edge edge, Integer from) {
				Integer to = edge.other(from);
				// Skip reverse edges
				if (parent[from] == to)
					return;
				if (level[to] < level[reachableAncestor[from]])
					reachableAncestor[from] = reachableAncestor[to];
			}

			@Override
			protected void after(Integer node) {

				// root cut node
				if (parent[node] == -1) {
					if (degree[node] > 1) {
						cutNodes[node] = ROOT_CUT;
					}
				} else {

					// bridge cut node
					if (parent[parent[node]] > -1 && reachableAncestor[node] == node) {
						cutNodes[parent[node]] = BRIDGE_CUT;

						if (degree[node] > 0) {
							cutNodes[node] = BRIDGE_CUT;
						}
					}

					// parent cut node
					else if (parent[parent[node]] > -1 && reachableAncestor[node] == parent[node]) {
						cutNodes[parent[node]] = PARENT_CUT;

					}

					Integer levelOReachablefAncestor = level[reachableAncestor[node]];
					Integer levelOfParentsReachableAncestor = level[reachableAncestor[parent[node]]];
					if (levelOReachablefAncestor < levelOfParentsReachableAncestor)
						reachableAncestor[parent[node]] = reachableAncestor[node];
				}

			}

		};

		for (int v = 0; v < graph.nodeCount; v++) {
			if (parent[v] > -1)
				continue;
			dfs.execute(graph, v);
		}

		System.out.println(Arrays.toString(cutNodes));

		return cutNodes;
	}

}
