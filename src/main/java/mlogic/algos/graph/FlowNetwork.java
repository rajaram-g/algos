package mlogic.algos.graph;

import java.math.BigDecimal;

/**
 * Type of undirected graph used to model and optimize flow in a network
 * 
 * @author Rajaram G
 *
 */
public class FlowNetwork extends Graph {

	/**
	 * Constructor
	 * 
	 * @param nodeCount
	 */
	public FlowNetwork(Integer nodeCount) {
		super(nodeCount);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mlogic.algos.graph.Graph#addEdge(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public void addEdge(Integer from, Integer to) {
		throw new UnsupportedOperationException("Flow network requires positive weighted edges.");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mlogic.algos.graph.Graph#addEdge(java.lang.Integer,
	 * java.lang.Integer, java.math.BigDecimal)
	 */
	@Override
	public void addEdge(Integer from, Integer to, BigDecimal capacity) {
		if (capacity == null || capacity.compareTo(new BigDecimal(0)) < 0)
			throw new IllegalArgumentException("Flow network requires positive weighted edges.");
		if (from < 0 || from >= this.nodeCount)
			throw new IllegalArgumentException(from + " out of range.");
		if (to < 0 || to >= this.nodeCount)
			throw new IllegalArgumentException(to + " out of range.");
		FlowEdge edge = new FlowEdge(from, to, capacity);
		// Allow duplicate edges
		this.edges[from].put(edge);
		if (from != to)
			this.edges[to].put(edge);
		this.edgeCount++;

	}

}
