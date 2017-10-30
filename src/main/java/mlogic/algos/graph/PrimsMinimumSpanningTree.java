package mlogic.algos.graph;

import java.math.BigDecimal;

import mlogic.algos.exceptions.NegativeEdgeWeightException;
import mlogic.algos.struct.List;
import mlogic.algos.struct.PriorityQueue;
import mlogic.algos.struct.SinglyLinkedList;

/**
 * Identifies a Minimum Spanning Tree (MST) in an edge-weighted graph using
 * Prim's algorithm. Minimum Spanning tree is minimal-weight collection of edges
 * that connects all the nodes in a connected graph. Minimum spanning forest is
 * a collection of minimum spanning trees of a disconnected graph.
 * 
 * @author Rajaram G
 *
 */
public class PrimsMinimumSpanningTree {

	private class EdgePlus implements Comparable<EdgePlus> {
		private Integer from;
		private Integer to;
		private BigDecimal distance;

		private EdgePlus(Integer from, Integer to, BigDecimal dist) {
			this.from = from;
			this.to = to;
			this.distance = dist;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		@Override
		public int compareTo(EdgePlus o) {
			return distance.compareTo(o.distance);
		}

	}

	/**
	 * Graph to search
	 */
	private UndirectedGraph graph;

	/**
	 * Array storing the edge that points to each node in its minimum spanning
	 * tree
	 */
	private Edge[] edgeTo;

	/**
	 * Array tracking all the nodes already evaluated for MST
	 */
	private boolean[] marked;

	/**
	 * Array storing the weight of the edges that connect MST nodes to their
	 * non-MST neighbors
	 */
	private BigDecimal[] distanceTo;

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
	public PrimsMinimumSpanningTree(UndirectedGraph graph) {
		Graph.checkEmpty(graph);
		this.graph = graph;
		edgeTo = new Edge[graph.nodeCount];
		distanceTo = new BigDecimal[graph.nodeCount];
		for (int v = 0; v < graph.nodeCount; v++) {
			edgeTo[v] = null;
			distanceTo[v] = new BigDecimal(Double.MAX_VALUE);
		}
		marked = new boolean[graph.nodeCount];
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

		for (Integer u = 0; u < graph.nodeCount; u++) {
			if (edgeTo[u] != null)
				continue;
			marked[u] = true;
			PriorityQueue<EdgePlus> pq = new PriorityQueue<EdgePlus>();
			while (true) {
				for (Edge e : graph.edges[u]) {
					Integer v = e.other(u);
					if (marked[v])
						continue;
					if (e.weight().compareTo(new BigDecimal(0)) < 0)
						throw new NegativeEdgeWeightException(
								"Prim's algorithm cannot be applied to a graph with negative edges.");
					if (e.weight().compareTo(distanceTo[v]) < 0) {
						distanceTo[v] = e.weight();
						edgeTo[v] = e;
						pq.enqueue(new EdgePlus(u, v, distanceTo[v]));
					}
				}
				EdgePlus nearest = null;
				while (true) {
					nearest = pq.dequeue();
					if (nearest == null)
						break;
					if (!marked[nearest.to]) {
						break;
					}
				}
				if (nearest != null) {
					marked[nearest.to] = true;
					u = nearest.to;
					minimumSpanningForest.put(edgeTo[nearest.to]);
				} else
					break;

			}

		}

	}

}
