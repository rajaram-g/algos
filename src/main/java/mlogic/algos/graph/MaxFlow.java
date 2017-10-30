package mlogic.algos.graph;

import java.math.BigDecimal;

import mlogic.algos.struct.List;
import mlogic.algos.struct.Queue;
import mlogic.algos.struct.SinglyLinkedList;

/**
 * Computes maxflow / mincut in a flow network using Karp implementation of the
 * Ford-Fulkerson method. Ford-Fulkerson method builds total flow by iteratively
 * finding an augmenting path in the residual graph and adding flow equal to the
 * augmenting path bottleneck capacity each time. The Karp implementation uses a
 * breadth-first search algorithm with an assumption that the quickest way to
 * find an augmenting path is through the shortest path. However shortest among
 * all augmenting paths may not provide the maximum bottleneck capacity. So the
 * implementation allows for reversing / reducing flow through a particular edge
 * if it results in increasing overall flow. This allows the algorithm to
 * recover from sub-optimal paths chosen by shortest path heuristic.
 * 
 * @author Rajaram G
 *
 */
public class MaxFlow {

	/**
	 * Graph to be assessed for maxflow/mincut
	 */
	private FlowNetwork graph;

	/**
	 * Stores current augmenting path
	 */
	private FlowEdge[] edgeTo;

	/**
	 * Source node for maxflow computation
	 */
	private Integer source;

	/**
	 * Target node for maxflow computation
	 */
	private Integer sink;

	/**
	 * Keeps track of nodes that are found during BFS starting from source node
	 */
	private boolean[] connectedToSource;

	/**
	 * Total flow through the network
	 */
	private BigDecimal totalFlow;

	/**
	 * Tracks maxflow computation state
	 */
	private boolean isMaxFlowComplete;

	/**
	 * Constructor
	 * 
	 * @param graph
	 *            flow network
	 * @param source
	 * @param sink
	 */
	public MaxFlow(FlowNetwork graph, Integer source, Integer sink) {
		Graph.checkEmpty(graph);
		this.graph = graph;

		if (source < 0 || source >= graph.nodeCount)
			throw new IllegalArgumentException(source + " out of range.");
		if (sink < 0 || sink >= graph.nodeCount)
			throw new IllegalArgumentException(sink + " out of range.");

		this.source = source;
		this.sink = sink;
		this.totalFlow = new BigDecimal(0);

		while (findAugmentingPath()) {
			BigDecimal bottleneck = computeBottleneckCapacity();
			addFlowToNetwork(bottleneck);
		}

		isMaxFlowComplete = true;

	}

	/**
	 * @return max flow through network
	 */
	public BigDecimal maxFlow() {
		if (!isMaxFlowComplete)
			throw new UnsupportedOperationException("Max flow yet to be computed");
		else
			return totalFlow;
	}

	/**
	 * Computes min cut by adding up capacity of all edges that need to be
	 * removed from the graph to disconnect source from sink in the final
	 * residual graph.
	 * 
	 * @return Sum of capacities of the edges in the min cut.
	 */
	public BigDecimal minCut() {
		List<FlowEdge> minCutEdges = new SinglyLinkedList<FlowEdge>();
		if (!isMaxFlowComplete)
			throw new UnsupportedOperationException("Max flow yet to be computed");
		else {
			for (Integer v = 0; v < this.graph.nodeCount; v++) {
				if (connectedToSource[v]) {
					for (Edge e : this.graph.edges[v]) {
						Integer w = e.other(v);
						if (!connectedToSource[w])
							minCutEdges.put((FlowEdge) e);
					}
				}
			}
		}
		BigDecimal minCut = new BigDecimal(0);
		for (FlowEdge fe : minCutEdges) {
			minCut = minCut.add(fe.capacity());
		}

		return minCut;

	}

	/**
	 * Does a BFS walk from source to all the nodes connected to in the residual
	 * graph. Keeps track of the edge through which each node is reached.
	 * Backtracking along these edges from the sink node yields the shortest
	 * augmenting path (not necessarily the path with maximum remaining
	 * capacity).
	 * 
	 * @return true if an augmenting path from source to sink was found in
	 *         residual graph
	 */
	private boolean findAugmentingPath() {

		edgeTo = new FlowEdge[graph.nodeCount];
		connectedToSource = new boolean[graph.nodeCount];
		connectedToSource[this.source] = true;
		Queue<Integer> queue = new Queue<Integer>();
		Integer u = this.source;
		queue.enqueue(u);
		while (!queue.isEmpty()) {
			Integer v = queue.dequeue();
			for (Edge e : graph.edges[v]) {
				FlowEdge fe = (FlowEdge) e;
				Integer w = e.other(v);
				if (!connectedToSource[w]) {
					System.out.println("Visiting edge" + v + "->" + w + " " + fe.residualCapacityTo(w));
					if (fe.residualCapacityTo(w).compareTo(new BigDecimal(0)) > 0) {
						edgeTo[w] = fe;
						connectedToSource[w] = true;
						queue.enqueue(w);
					}
				}

			}
		}

		if (connectedToSource[this.sink])
			return true;
		else
			return false;
	}

	/**
	 * Walks back from sink node along the current augmenting path to identify
	 * the edge with lowest residual capacity
	 * 
	 * @return bottleneck capacity along current augmenting path
	 */
	private BigDecimal computeBottleneckCapacity() {
		BigDecimal bottleneck = new BigDecimal(Double.MAX_VALUE);
		System.out.print("\nAugmenting path " + this.sink);
		for (Integer v = this.sink; v != this.source; v = edgeTo[v].other(v)) {
			System.out.print("<-" + edgeTo[v].other(v));
			BigDecimal residual = edgeTo[v].residualCapacityTo(v);
			if (residual.compareTo(bottleneck) < 0)
				bottleneck = residual;
		}
		System.out.print(" bottleneck" + bottleneck + "\n");
		return bottleneck;
	}

	/**
	 * @param bottleneck
	 */
	private void addFlowToNetwork(BigDecimal bottleneck) {
		this.totalFlow = this.totalFlow.add(bottleneck);
		for (Integer v = this.sink; v != this.source; v = edgeTo[v].other(v)) {
			edgeTo[v].addFlowTo(bottleneck, v);
			System.out.println("Added flow to " + edgeTo[v].other(v) + "->" + v + "new residual flow "
					+ edgeTo[v].residualCapacityTo(v));
		}

	}

}
