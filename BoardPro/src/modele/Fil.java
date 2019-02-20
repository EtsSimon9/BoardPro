package modele;

import exception.ComposanteException;

public class Fil extends ComposanteElectrique {

	public Fil() throws ComposanteException {
		super();
	}

	public Fil(float impedence, float ddp, float tensionCourant)
			throws ComposanteException {
		super(impedence, ddp, tensionCourant);
	}

	
}
