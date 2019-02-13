package modele;

import exception.ComposanteException;

public class Resistor extends ComposanteElectrique {
	
	public Resistor(int positionX, int positionY) throws ComposanteException {
		super(positionX, positionY);

	}

	public Resistor(float impedence, float ddp, float tensionCourant, int positionX, int positionY)
			throws ComposanteException {
		super(impedence, ddp, tensionCourant, positionX, positionY);
	}

}
