package mlogic.algos.graph;

import java.math.BigDecimal;

/**
 * Directed graph data structure.
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
	 * @param weight
	 */
	@Override
	public void addEdge(Integer from, Integer to, BigDecimal weight) {
		Edge edge = new Edge(from, to, weight);
		// Allow duplicate edges
		this.edges[from].put(edge);
		this.edgeCount++;

	}

	/**
	 * Creates a directed graph with all edges reversed
	 * 
	 * @return
	 */
	public DirectedGraph reverse() {
		DirectedGraph reverse = new DirectedGraph(this.nodeCount);

		for (int v = 0; v < this.nodeCount; v++) {
			for (Edge e : this.edges[v]) {
				Edge r = e.reverse();
				reverse.edges[e.other(v)].put(r);
			}
		}
		return reverse;
	}
}
