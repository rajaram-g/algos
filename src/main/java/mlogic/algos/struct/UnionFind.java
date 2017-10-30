package mlogic.algos.struct;

/**
 * Data Structure that helps keep track of connected sections of a graph
 * 
 * @author Rajaram G
 *
 */
public class UnionFind {

	/**
	 * Stores the parent of each node
	 */
	Integer[] parent;

	/**
	 * Stores the height of each set from node to its lowest child
	 */
	Integer[] height;

	/**
	 * @param nodeCount
	 *            number of nodes in graph
	 */
	public UnionFind(Integer nodeCount) {
		parent = new Integer[nodeCount];
		height = new Integer[nodeCount];
		for (int v = 0; v < nodeCount; v++) {
			parent[v] = v;
			height[v] = 1;
		}

	}

	/**
	 * Walks backward through parent array until it finds a node which is its
	 * own parent. This is the root node of a set within the UF data structure.
	 * 
	 * @param from
	 * @return root of the set connected to *from* node
	 */
	public Integer find(Integer from) {
		if (from < 0 || from > parent.length)
			throw new IllegalArgumentException(from + " is out of range.");
		Integer node = from;
		while (parent[node] != node)
			node = parent[node];
		return node;
	}

	/**
	 * Merges 2 sections of the UF, by making the smaller set a child of the
	 * root of the bigger set.
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public Integer merge(Integer from, Integer to) {
		if (from < 0 || from > parent.length)
			throw new IllegalArgumentException(from + " is out of range.");
		if (to < 0 || to > parent.length)
			throw new IllegalArgumentException(to + " is out of range.");
		if (from == to)
			throw new IllegalArgumentException("Nodes to be merged are the same.");
		Integer fromRoot = find(from);
		Integer toRoot = find(to);
		if (fromRoot == toRoot) // already merged
			return fromRoot;

		if (height[fromRoot] >= height[toRoot]) {
			parent[toRoot] = fromRoot;
			if (height[toRoot] + 1 > height[fromRoot])
				height[fromRoot] = height[toRoot] + 1;
			return fromRoot;
		} else {
			parent[fromRoot] = toRoot;
			if (height[fromRoot] + 1 > height[toRoot])
				height[toRoot] = height[fromRoot] + 1;
			return toRoot;
		}

	}

}
