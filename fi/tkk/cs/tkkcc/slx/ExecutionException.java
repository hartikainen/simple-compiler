package fi.tkk.cs.tkkcc.slx;

/**
 * Execution exception.
 * 
 * This type of exception is thrown when interpreter fails to execute the
 * instruction. Cause may be invalid state or invalid arguments.
 * 
 * @author Timo Montonen
 */
public class ExecutionException extends RuntimeException {

	private static final long serialVersionUID = -7694298219699313115L;

	public ExecutionException() {
		super();
	}

	public ExecutionException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ExecutionException(String arg0) {
		super(arg0);
	}

	public ExecutionException(Throwable arg0) {
		super(arg0);
	}

	
	
}
