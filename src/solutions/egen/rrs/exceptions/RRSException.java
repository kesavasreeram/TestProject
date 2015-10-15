/**
 * 
 */
package solutions.egen.rrs.exceptions;

/**
 * @author Kesava
 *
 */
public class RRSException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2304139343226696312L;

	/**
	 * 
	 */
	public RRSException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public RRSException(String message, Throwable cause, 
			boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public RRSException(String message, Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public RRSException(String message)
	{
		super(message);
	}

	/**
	 * @param cause
	 */
	public RRSException(Throwable cause)
	{
		super(cause);
	}

	
	
}
