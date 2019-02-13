package modele;

import exception.ComposanteException;

public class Fil extends ComposanteElectrique {

	public Fil(int positionX, int positionY) throws ComposanteException {
		super(positionX, positionY);
	}

	public Fil(float impedence, float ddp, float tensionCourant, int positionX, int positionY)
			throws ComposanteException {
		super(impedence, ddp, tensionCourant, positionX, positionY);
	}

	
}
