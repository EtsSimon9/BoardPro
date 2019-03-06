package exceptions;

public class ComposantException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = -4052275719729119199L;

	public ComposantException() {
		super("Erreur de type: ComposanteException");
	}

	public ComposantException(String message) {
		super(message);
	}
}
