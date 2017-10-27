package mlogic.algos.graph;

import java.math.BigDecimal;

/**
 * Un-weighted graph data structure.
 * 
 * @author Rajaram G
 *
 */
public class DirectedGraph extends Graph {

	/**
	 * @param nodeCount
	 */
	public DirectedGraph(Integer nodeCount) {
		super(nodeCount);
	}

	/**
	 * Add an un-weighted edge
	 * 
	 * @param from
	 * @param to
	 */
	@Override
	public void addEdge(Integer from, Integer to) {
		Edge edge = new Edge(from, to);
		// Allow duplicate edges
		this.edges[from].put(edge);
		this.edgeCount++;

	}

	/**
	 * Add a weighted edge
	 * 
	 * @param from
	 * @param to
	 */
	@Override
	public void addEdge(Integer from, Integer to, BigDecimal weight) {
		Edge edge = new Edge(from, to, weight);
		// Allow duplicate edges
		this.edges[from].put(edge);
		this.edgeCount++;

	}
}
