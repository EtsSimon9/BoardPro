package composantesCircuit;

import composante.CE2Entrees;
import composante.ComposanteElectrique;
import utilitaire.Images;

public class Condensateur extends CE2Entrees {
	private double capacite = 0.0;

	private static final float CAPACITE_DEFAUT = 20;

	public Condensateur(short coordonnex, short coordonney, double capacite, Images i) {
		super(coordonnex, coordonney, i);
		if (capacite > 0) {
			setCapacite(capacite);
		} else {
			setCapacite(CAPACITE_DEFAUT);
		}
	}

	public Condensateur(short coordonnex, short coordonney, Images i) {
		super(coordonnex, coordonney, i);
		setCapacite(CAPACITE_DEFAUT);
	}

	public double getCapacite() {
		return capacite;
	}

	public void setCapacite(double capacite) {
		this.capacite = capacite;
	}
}
