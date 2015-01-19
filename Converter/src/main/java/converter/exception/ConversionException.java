package converter.exception;

/**
 * This is Generic Runtime Error that can be throw on exceptional runtime
 * conditions.
 * 
 * @author : amitkumarsingapore@gmail.com Date: Jan 18, 2015
 */
public class ConversionException extends Exception {

	/**
	 * Serial Version ID for serialisation
	 */
	private static final long serialVersionUID = -4179371736024548014L;

	/**
	 * Default Constructor with no arguments
	 */
	public ConversionException() {

	}

	/**
	 * Constructor with message argument
	 * 
	 * @param message
	 *            : Error message to associate with exception
	 */
	public ConversionException(String message) {
		super(message);
	}

	/**
	 * Constructor with throwable argument to accept all type of exception
	 * 
	 * @param cause
	 *            : Throwable object to accept all type of exception
	 */
	public ConversionException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor with throwable argument to accept all type of exception
	 * 
	 * @param message
	 *            : Error message to associate with exception
	 * @param cause
	 *            : Throwable object to accept all type of exception
	 */
	public ConversionException(String message, Throwable cause) {
		super(message, cause);
	}

}
