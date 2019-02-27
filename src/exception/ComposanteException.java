package exception;

public class ComposanteException extends Exception {

	public ComposanteException() {
		super("Erreur de type: ComposanteException");
	}
	
	public ComposanteException(String message) {
		super(message);
	}
}
