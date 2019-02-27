package exception;

public class MathException extends Exception {
	
	public MathException() {
		super("Erreur de type: MathException");
	}
	
	public MathException(String message) {
		super(message);
	}
}
