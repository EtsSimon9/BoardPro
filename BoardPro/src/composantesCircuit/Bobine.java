package composantesCircuit;

import composante.CE2Entrees;
import utilitaire.Images;

public class Bobine extends CE2Entrees {
	private double inductence;
	private static final float INDUCTANCE_DEFAUT = 20;
	
	public Bobine(short coordonnex, short coordonney,double inductence, Images i) {
		super( coordonnex, coordonney, i);
		if (inductence > 0) {
			setInductence(inductence);
		} else {
			setInductence(INDUCTANCE_DEFAUT);
		}
	}
	
	public Bobine(short coordonnex, short coordonney, Images i) {
		super( coordonnex, coordonney, i);
			setInductence(INDUCTANCE_DEFAUT);
	}
	
	
	public double getInductence() {
		return inductence;
	}
	public void setInductence(double inductence) {
		this.inductence = inductence;
	}



}
