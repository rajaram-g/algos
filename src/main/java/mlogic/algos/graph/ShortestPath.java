package mlogic.algos.graph;

import mlogic.algos.exceptions.NoResultException;
import mlogic.algos.struct.Stack;

/**
 * Determines shortest path in un-weighted and undirected graphs using various
 * algorithms
 * 
 * @author Rajaram G
 *
 */
public class ShortestPath {

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
	 * Stack that pops out the shortest path nodes from start to end
	 */
	private Stack<Integer> shortestPathNodes;

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
	public ShortestPath(Graph graph, Integer start, Integer end) {
		checkEmptyGraph(graph);
		checkValidNode(graph, start);
		checkValidNode(graph, end);
		this.graph = graph;
		this.start = start;
		this.end = end;

		compute();

	}

	/**
	 * Identifies topological path i.e. nodes on path from start to end using
	 * BFS
	 * 
	 * @return an array with all the nodes on shortest path
	 */
	public Integer[] topologicalPath() {
		System.out.println(shortestPathNodes);
		Integer[] ret = new Integer[shortestPathNodes.size()];
		shortestPathNodes.toArray(ret);
		return ret;
	}

	/**
	 * Identifies the topological distance i.e number of nodes on shortest path
	 * between start and end
	 * 
	 * @return the number of nodes on shortest path including start and end
	 *         nodes
	 */
	public Integer topologicalDistance() {
		return shortestPathNodes.size() - 1;
	}

	/**
	 * Executes BFS on graph to compute shortest path
	 * 
	 */
	private void compute() {
		nodeTo = new Integer[graph.nodeCount];
		shortestPathNodes = new Stack<Integer>();
		if (start == end) {
			shortestPathNodes.push(start);
			return;
		}

		for (int v = 0; v < graph.nodeCount; v++)
			nodeTo[v] = -1;
		BFS shortestPathFinder = new BFS() {
			@Override
			protected void processTreeEdge(Edge edge, Integer node) {
				Integer v = edge.other(node);
				nodeTo[v] = node;
				if (v == end)
					stopWalking();
			}
		};

		shortestPathFinder.execute(graph, start);

		if (nodeTo[end] == -1)
			throw new NoResultException("No path from " + start + " to " + end + " found in the graph.");

		for (Integer current = end; current != -1; current = nodeTo[current]) {
			shortestPathNodes.push(current);
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
