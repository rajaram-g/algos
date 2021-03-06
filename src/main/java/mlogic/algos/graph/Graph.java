package mlogic.algos.graph;

import java.math.BigDecimal;

import mlogic.algos.struct.List;
import mlogic.algos.struct.SinglyLinkedList;

/**
 * Graph data structure, defaults to undirected graph
 * 
 * @author Rajaram G
 *
 */
public abstract class Graph {

	/**
	 * Array to store the edges for each node
	 */
	List<Edge>[] edges;

	/**
	 * Number of nodes in the graph
	 */
	Integer nodeCount;

	/**
	 * Number of edges in the graph
	 */
	Integer edgeCount;

	/**
	 * Constructor
	 * 
	 * @param nodeCount
	 */
	public Graph(Integer nodeCount) {
		if (nodeCount <= 0)
			throw new IllegalArgumentException("No point creating an empty graph.");

		this.nodeCount = nodeCount;
		this.edges = new SinglyLinkedList[nodeCount];
		for (int i = 0; i < nodeCount; i++)
			this.edges[i] = new SinglyLinkedList<Edge>();
		this.edgeCount = 0;
	}

	/**
	 * Add an un-weighted edge
	 * 
	 * @param from
	 * @param to
	 */
	public abstract void addEdge(Integer from, Integer to);

	/**
	 * Add a weighted edge
	 * 
	 * @param from
	 * @param to
	 * @param weight
	 */
	public abstract void addEdge(Integer from, Integer to, BigDecimal weight);

	/**
	 * Add a weighted edge
	 * 
	 * @param from
	 * @param to
	 * @param weight
	 */
	public void addEdge(Integer from, Integer to, Double weight) {
		addEdge(from, to, new BigDecimal(weight));
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		for (int u = 0; u < this.nodeCount; u++) {
			buf.append(u + " :: ");
			for (Edge edge : this.edges[u]) {
				buf.append(u + "->" + edge.other(u) + "(");
				buf.append(String.format("%.2f", edge.weight()));
				buf.append(") ");
			}
			buf.append("\n");
		}
		return buf.toString();
	}

	/**
	 * Checks if the graph is empty
	 * 
	 * @param graph
	 */
	public static void checkEmpty(Graph graph) {
		if (graph == null || graph.nodeCount <= 0)
			throw new IllegalArgumentException("No point searching in an empty graph.");
	}

}
