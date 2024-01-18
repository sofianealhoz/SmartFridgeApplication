package Back;

public class ApiLimitExceededException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApiLimitExceededException() {
        super();
    }

    public ApiLimitExceededException(String message) {
        super(message);
    }

    // You can also add constructors for Throwable, etc., if needed.
}
