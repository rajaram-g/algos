package mlogic.algos.exceptions;

/**
 * Throw this when we find edge with negative weights where not expected e.g. in
 * shortest path evaluation
 * 
 * @author Rajaram G
 *
 */
public class NegativeEdgeWeightException extends RuntimeException {

	public NegativeEdgeWeightException(String message) {
		super(message);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
