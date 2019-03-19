package composantesCircuit;

import composante.CE2Entrees;

public class Condensateur extends CE2Entrees {
	private double capacite = 0.0;
	
	private static final float CAPACITE_DEFAUT = 20;

	
	public Condensateur(  short coordonnex, short coordonney,double capacite) {
		super(coordonnex, coordonney);
		if (capacite > 0) {
			setCapacite(capacite);
		} else {
			setCapacite(CAPACITE_DEFAUT);
		}
	}

	public double getCapacite() {
		return capacite;
	}
	public void setCapacite(double capacite) {
		this.capacite = capacite;
	}
}
