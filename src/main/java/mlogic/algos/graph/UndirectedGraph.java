package mlogic.algos.graph;

import java.math.BigDecimal;

/**
 * Undirected graph data structure
 * 
 * @author Rajaram G
 *
 */
public class UndirectedGraph extends Graph {

	/**
	 * @param nodeCount
	 */
	public UndirectedGraph(Integer nodeCount) {
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
		if (from != to)
			this.edges[to].put(edge);
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
		if (from != to)
			this.edges[to].put(edge);
		this.edgeCount++;

	}

}
