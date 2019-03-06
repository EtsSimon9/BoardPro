package composantesCircuit;

import composant.CEDeuxEntres_CEDE_;

public class Condensateur extends CEDeuxEntres_CEDE_ {
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
