package mlogic.algos.graph;

import mlogic.algos.struct.Queue;

/**
 * Executes breadth first search
 * 
 * @author Rajaram G
 *
 */
public class BFS {

	/**
	 * Graph to search
	 */
	private Graph graph;

	/**
	 * Stores the state of each node. States can be unvisited, in queue or
	 * processed
	 */
	private int[] state;

	private static int UNVISITED = 0;

	private static int ENQUEUED = 1;

	private static int PROCESSED = 2;

	/**
	 * Level of each node in the BFS tree
	 */
	private int[] level;

	/**
	 * Kill switch
	 */
	private boolean stopWalking;

	/**
	 * Executes breadth first search on a graph starting with specified start
	 * node
	 * 
	 * @param graph
	 *            graph to search
	 * @param start
	 *            start node
	 */
	public void execute(Graph graph, Integer start) {
		if (graph == null || graph.nodeCount == 0)
			throw new IllegalArgumentException("No point searching an empty graph");

		this.graph = graph;
		this.state = new int[graph.nodeCount];
		this.level = new int[graph.nodeCount];
		this.level[start] = 0;

		Queue<Integer> queue = new Queue<Integer>();
		queue.enqueue(start);
		this.state[start] = ENQUEUED;
		while (!queue.isEmpty() && !stopWalking) {
			Integer u = queue.dequeue();
			before(u);
			for (Edge edge : graph.edges[u]) {
				Integer v = edge.other(u);
				processEdge(edge, u);
				if (this.state[v] == UNVISITED) {
					this.state[v] = ENQUEUED;
					// If the next node has not been visited, then this edge
					// adds to the search tree, hence "Tree Edge"
					processTreeEdge(edge, u);
					this.level[v] = this.level[u] + 1;
					queue.enqueue(v);
				} else if (this.state[v] != UNVISITED) {
					// if the next node has already been processed or in queue,
					// then the
					// edge points back to the current search tree, hence
					// "backward edge"
					processBackwardEdge(edge, u);
				} else if (this.state[v] == PROCESSED) {
					// if the next node is completely processed and at an equal
					// or
					// higher level than current node, then edge must
					// point to a different part of the search tree i.e. "cross
					// edge".
					// if the next node is completely processed and at a lower
					// level
					// than current node, then the next node is part of current
					// tree
					// as well as
					// another previously processed part of the search tree and
					// hence the
					// edge is a
					// "forward edge".
					if (level[u] >= level[v])
						processCrossEdge(edge, u);
					else
						processForwardEdge(edge, u);
				}
			}
			after(u);
			this.state[u] = PROCESSED;

		}

	}

	/**
	 * Process a node before processing its edges
	 * 
	 * @param node
	 */
	protected void before(Integer node) {

	}

	/**
	 * Process a node after processing all its edges
	 * 
	 * @param node
	 */
	protected void after(Integer node) {

	}

	/**
	 * Process an edge in the direction pointing away from the source node
	 * 
	 * @param edge
	 * @param source
	 */
	protected void processEdge(Edge edge, Integer source) {

	}

	/**
	 * Process an edge in the direction pointing from the source node to the a
	 * node already in the BFS tree
	 * 
	 * @param edge
	 * @param source
	 */
	protected void processBackwardEdge(Edge edge, Integer source) {

	}

	/**
	 * Process an edge in the direction pointing from the source node to an
	 * unvisited node
	 * 
	 * @param edge
	 * @param source
	 */
	protected void processTreeEdge(Edge edge, Integer source) {

	}

	/**
	 * Process an edge in the direction pointing from the source node to an
	 * already processed, different part of the graph
	 * 
	 * @param edge
	 * @param source
	 */
	protected void processCrossEdge(Edge edge, Integer source) {

	}

	/**
	 * Process an edge in the direction pointing from the source node to a node
	 * below, but one that was an already processed by a different part of the
	 * graph
	 * 
	 * @param edge
	 * @param source
	 */
	protected void processForwardEdge(Edge edge, Integer source) {

	}

	/**
	 * Activates kill switch
	 */
	protected void stopWalking() {
		stopWalking = true;
	}

}
