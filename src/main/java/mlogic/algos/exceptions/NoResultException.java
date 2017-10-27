package mlogic.algos.exceptions;

/**
 * Throw this when no result was found during an algorithm operation such as
 * search
 * 
 * @author Rajaram G
 *
 */
public class NoResultException extends RuntimeException {

	public NoResultException(String message) {
		super(message);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
