package mlogic.algos.exceptions;

/**
 * Throw this when we find a cycle in a graph when not expected e.g. during
 * topological sort
 * 
 * @author Rajaram G
 *
 */
public class CyclicGraphException extends RuntimeException {

	public CyclicGraphException(String message) {
		super(message);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
