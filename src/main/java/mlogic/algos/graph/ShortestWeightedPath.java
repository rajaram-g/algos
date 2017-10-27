package mlogic.algos.graph;

import java.math.BigDecimal;

import mlogic.algos.exceptions.NegativeEdgeWeightException;
import mlogic.algos.exceptions.NoResultException;
import mlogic.algos.struct.PriorityQueue;
import mlogic.algos.struct.Stack;

/**
 * Determines shortest path in edge-weighted graphs using Dijkstra's algorithm
 * 
 * @author Rajaram G
 *
 */
public class ShortestWeightedPath {

	private class EdgePlus implements Comparable<EdgePlus> {
		private Integer from;
		private Integer to;
		private BigDecimal distanceFromOrigin;

		private EdgePlus(Integer from, Integer to, BigDecimal dist) {
			this.from = from;
			this.to = to;
			this.distanceFromOrigin = dist;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		@Override
		public int compareTo(EdgePlus o) {
			return distanceFromOrigin.compareTo(o.distanceFromOrigin);
		}

	}

	/**
	 * Graph to search
	 */
	private Graph graph;

	/**
	 * Start node of shortest path
	 */
	private Integer start;

	/**
	 * End node of shortest path
	 */
	private Integer end;

	/**
	 * Array storing the node before each node in shortest path
	 */
	private Integer[] nodeTo;

	/**
	 * Array storing the shortest distance from start node to each node in BFS
	 * tree
	 */
	private BigDecimal[] distanceTo;

	/**
	 * Stack that pops out the shortest path nodes from start to end
	 */
	private Stack<Integer> shortestPathNodes;

	/**
	 * Array tracking all the nodes already evaluated for shortest path
	 */
	private boolean[] marked;

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
	public ShortestWeightedPath(Graph graph, Integer start, Integer end) {
		checkEmptyGraph(graph);
		checkValidNode(graph, start);
		checkValidNode(graph, end);
		this.graph = graph;
		this.start = start;
		this.end = end;
		nodeTo = new Integer[graph.nodeCount];
		distanceTo = new BigDecimal[graph.nodeCount];
		for (int v = 0; v < graph.nodeCount; v++) {
			nodeTo[v] = -1;
			distanceTo[v] = new BigDecimal(Double.MAX_VALUE);
		}
		marked = new boolean[graph.nodeCount];
		shortestPathNodes = new Stack<Integer>();
		if (start == end) {
			shortestPathNodes.push(start);
			distanceTo[end] = new BigDecimal(0);
			return;
		} else
			compute();

	}

	/**
	 * @return physical distance along the shortest path between start and end
	 *         nodes
	 */
	public BigDecimal physicalDistance() {
		return distanceTo[this.end];
	}

	/**
	 * @return an array with all the nodes on shortest path
	 */
	public Integer[] physicalPath() {

		System.out.println(shortestPathNodes);

		Integer[] ret = new Integer[shortestPathNodes.size()];
		shortestPathNodes.toArray(ret);

		return ret;
	}

	/**
	 * Executes Dijkstra's algorithm on an edge weighted graph to compute
	 * shortest path
	 * 
	 */
	private void compute() {

		Integer u = start;
		marked[u] = true;
		distanceTo[u] = new BigDecimal(0);
		PriorityQueue<EdgePlus> pq = new PriorityQueue<EdgePlus>();
		while (u != end) {
			for (Edge e : graph.edges[u]) {
				Integer v = e.other(u);
				if (marked[v])
					continue;
				if (e.weight().compareTo(new BigDecimal(0)) < 0)
					throw new NegativeEdgeWeightException(
							"Dijkstra's algorithm cannot be applied to a graph with negative edges.");
				BigDecimal newDistance = distanceTo[u].add(e.weight());
				if (newDistance.compareTo(distanceTo[v]) < 0) {
					distanceTo[v] = newDistance;
					nodeTo[v] = u;
					pq.enqueue(new EdgePlus(u, v, distanceTo[v]));
				}
			}
			EdgePlus nearest = null;
			while (true) {
				nearest = pq.dequeue();
				if (!marked[nearest.to])
					break;
			}
			if (nearest != null) {
				marked[nearest.to] = true;
				u = nearest.to;
			} else
				break;

		}

		if (nodeTo[end] == -1)
			throw new NoResultException("No path from " + start + " to " + end + " found in the graph.");

		for (Integer node = end; node != -1; node = nodeTo[node]) {
			shortestPathNodes.push(node);
		}

	}

	/**
	 * Checks if node exists on graph
	 * 
	 * @param graph
	 * @param node
	 */
	private void checkValidNode(Graph graph, Integer node) {
		if (node >= graph.nodeCount || node < 0)
			throw new IllegalArgumentException(node + "is not a valid node index in the graph.");
	}

	/**
	 * Checks if graph is empty
	 * 
	 * @param graph
	 */
	private void checkEmptyGraph(Graph graph) {
		if (graph == null || graph.nodeCount <= 0)
			throw new IllegalArgumentException("No point searching in an empty graph.");
	}

}
