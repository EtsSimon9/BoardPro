package composantesCircuit;

import composante.CE2Entrees;

public class Bobine extends CE2Entrees {
	private double inductence;
	private static final float INDUCTANCE_DEFAUT = 20;
	
	public Bobine(  short coordonnex, short coordonney,double inductence) {
		super( coordonnex, coordonney);
		if (inductence > 0) {
			setInductence(inductence);
		} else {
			setInductence(INDUCTANCE_DEFAUT);
		}
	}
	
	
	public double getInductence() {
		return inductence;
	}
	public void setInductence(double inductence) {
		this.inductence = inductence;
	}



}
