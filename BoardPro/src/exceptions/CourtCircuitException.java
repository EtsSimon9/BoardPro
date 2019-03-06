package exceptions;

public class CourtCircuitException extends Exception {

	private static final long serialVersionUID = 7212549168070429010L;

	public CourtCircuitException() {
	}

	public CourtCircuitException(String message) {
		super(message);
	}

}
