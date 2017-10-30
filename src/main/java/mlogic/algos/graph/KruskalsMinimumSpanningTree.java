package mlogic.algos.graph;

import mlogic.algos.struct.List;
import mlogic.algos.struct.PriorityQueue;
import mlogic.algos.struct.SinglyLinkedList;
import mlogic.algos.struct.UnionFind;

/**
 * Identifies a Minimum Spanning Tree (MST) in an edge-weighted graph using
 * Kruskal's algorithm. Minimum Spanning tree is minimal-weight collection of
 * edges that connects all the nodes in a connected graph. Minimum spanning
 * forest is a collection of minimum spanning trees of a disconnected graph.
 * 
 * @author Rajaram G
 *
 */
public class KruskalsMinimumSpanningTree {

	/**
	 * Graph to search
	 */
	private UndirectedGraph graph;

	/**
	 * Minimum Spanning Forest - List of edges in the minimum spanning tree (or
	 * trees, if the graph is not fully connected)
	 */
	private List<Edge> minimumSpanningForest;

	/**
	 * Constructor
	 * 
	 * @param graph
	 *            graph to search
	 * @param start
	 *            start node
	 * @param end
	 *            end node
	 */
	public KruskalsMinimumSpanningTree(UndirectedGraph graph) {
		Graph.checkEmpty(graph);
		this.graph = graph;
		minimumSpanningForest = new SinglyLinkedList<Edge>();
		compute();

	}

	/**
	 * @return Minimum Spanning Forest - List of edges in the minimum spanning
	 *         tree (or trees, if the graph is not fully connected)
	 */
	public List<Edge> minimumSpanningForest() {
		return minimumSpanningForest;
	}

	/**
	 * Executes Prim's algorithm on an edge weighted graph to compute the MSTs
	 * 
	 */
	private void compute() {
		UnionFind uf = new UnionFind(graph.nodeCount);
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		for (Integer u = 0; u < graph.nodeCount; u++) {
			for (Edge e : graph.edges[u]) {
				if (e.from() == u)
					pq.enqueue(e);
			}

		}

		while (!pq.isEmpty()) {
			Edge e = pq.dequeue();
			Integer fromRoot = uf.find(e.from());
			Integer toRoot = uf.find(e.to());

			if (fromRoot != toRoot) {
				minimumSpanningForest.put(e);
				uf.merge(e.from(), e.to());
			}

		}

	}

}
