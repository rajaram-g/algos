package mlogic.algos.graph;

import java.math.BigDecimal;

/**
 * Represents a path between two nodes in an adjacency-list based graph data
 * structure
 * 
 * @author Rajaram G
 *
 */
public class Edge implements Comparable<Edge> {

	/**
	 * Source node index
	 */
	private Integer from;

	/**
	 * Target node index
	 */
	private Integer to;

	/**
	 * Weight of edge, 0 for un-weighted graphs
	 */
	private BigDecimal weight;

	/**
	 * Constructor for un-weighted edge
	 * 
	 * @param from
	 *            index of source node
	 * @param to
	 *            index of target node
	 */
	public Edge(Integer from, Integer to) {
		this.from = from;
		this.to = to;
		this.weight = new BigDecimal(0);
	}

	/**
	 * Constructor for weighted edge
	 * 
	 * @param from
	 *            index of source node
	 * @param to
	 *            index of target node
	 */
	public Edge(Integer from, Integer to, BigDecimal weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	/**
	 * @return source node index
	 */
	public Integer from() {
		return this.from;
	}

	/**
	 * @return target node index
	 */
	public Integer to() {
		return this.to;
	}

	/**
	 * Given a node in the edge, returns the other node
	 * 
	 * @param node
	 * @return the other node in the edge
	 */
	public Integer other(Integer node) {
		if (node == this.from)
			return this.to;
		if (node == this.to)
			return this.from;
		throw new IllegalArgumentException(node + " does not exist on this edge.");
	}

	/**
	 * 
	 * @return weight of the edge
	 */
	public BigDecimal weight() {
		return weight;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Edge other) {
		return this.weight.compareTo(other.weight);
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Edge)
			return (this.from == ((Edge) other).from && this.to == ((Edge) other).to);

		return false;

	}

	/**
	 * @return reverse of the current edge
	 */
	public Edge reverse() {
		return new Edge(this.to, this.from, this.weight);
	}

}
