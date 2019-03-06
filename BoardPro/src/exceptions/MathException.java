package exceptions;

public class MathException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = -1916688105817411016L;

	public MathException() {
		super("Erreur de type: MathException");
	}

	public MathException(String message) {
		super(message);
	}
}
