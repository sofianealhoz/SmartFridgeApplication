package Back;

public class NoInternetConnectionException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoInternetConnectionException() {
        super();
    }

    public NoInternetConnectionException(String message) {
        super(message);
    }

    // Additional constructors, similar to above, can be added as necessary.
}
