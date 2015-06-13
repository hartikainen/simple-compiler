package fi.tkk.cs.tkkcc.slx;

/**
 * Illegal instruction exception.
 * 
 * Exception which indicates unknown or malformed instruction or parameters.
 * 
 * @author Timo Montonen
 */
public class IllegalInstructionException extends RuntimeException {

	/** Generated <code>serialVersionUID</code>. */
	private static final long serialVersionUID = 2669715413592758915L;

	public IllegalInstructionException() {
		super();
	}

	public IllegalInstructionException(String message) {
		super(message);
	}

	public IllegalInstructionException(Throwable cause) {
		super(cause);
	}

	public IllegalInstructionException(String message, Throwable cause) {
		super(message, cause);
	}

}
