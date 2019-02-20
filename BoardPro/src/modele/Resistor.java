package modele;

import exception.ComposanteException;

public class Resistor extends ComposanteElectrique {
	
	public Resistor() throws ComposanteException {
		super();

	}

	public Resistor(float impedence, float ddp, float tensionCourant)
			throws ComposanteException {
		super(impedence, ddp, tensionCourant);
	}

}
