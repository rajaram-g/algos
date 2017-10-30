package mlogic.algos.graph;

import java.math.BigDecimal;

/**
 * Edge in a flow network
 * 
 * @author Rajaram G
 *
 */
public class FlowEdge extends Edge {

	/**
	 * Stores the flow assigned to the edge
	 */
	private BigDecimal flow;

	/**
	 * @param from
	 * @param to
	 * @param capacity
	 */
	public FlowEdge(Integer from, Integer to, BigDecimal capacity) {
		super(from, to, capacity);
		this.flow = new BigDecimal(0);
	}

	/**
	 * @return capacity of the edge
	 */
	public BigDecimal capacity() {
		return this.weight();
	}

	/**
	 * @return flow assigned to the edge
	 */
	public BigDecimal flow() {
		return this.flow;
	}

	/**
	 * In the direction of the edge, this equals difference between capacity and
	 * assigned flow and represents how much flow can be added to potentially
	 * increase the flow within the network. In the opposite direction of the
	 * edge, this equals the assigned flow and represents how much flow can be
	 * potentially removed from this edge to increase the overall flow within
	 * the network.
	 * 
	 * @param node
	 * @return residual capacity in the direction of the specified node
	 */
	public BigDecimal residualCapacityTo(Integer node) {
		if (node == this.to())
			return this.capacity().subtract(this.flow);
		else
			return this.flow;

	}

	/**
	 * Adds flow to the flow edge. In the original direction of the edge, this
	 * adds forward flow. In the reverse direction, this reduces forward flow
	 * and increases forward capacity.
	 * 
	 * @param bottleneck
	 * @param node
	 */
	public void addFlowTo(BigDecimal bottleneck, Integer node) {
		if (node == this.to())
			this.flow = this.flow.add(bottleneck);
		else
			this.flow = this.flow.subtract(bottleneck);

	}

}
