package mlogic.algos.exceptions;

/**
 * Throw this when we try to stuff data into structures beyond their limits
 * 
 * @author Rajaram G
 *
 */
public class LimitException extends RuntimeException {

	public LimitException(String message) {
		super(message);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
