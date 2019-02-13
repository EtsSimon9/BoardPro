package modele;

import exception.MathException;

/**
 * 
 * @author Simon Beaulieu
 *
 */
public class Calcul {

	public float frequenceTofrequenceAngulaire(float frequence) {
		return (float) (2 * Math.PI * frequence);
	}

	public float frequenceAngulaireTofrequence(float frequenceAngulaire) {
		return (float) (frequenceAngulaire / (2 * Math.PI));
	}

	public float loiOhmR(float ddp, float i) throws MathException {
		float retour;

		if (i != 0) {
			retour = ddp / i;
		} else {
			throw new MathException("division par zero!");
		}
		return retour;
	}

	public float loiOhmDDP(float r, float i) {
		return r * i;
	}

	public float loiOhmI(float r, float ddp) throws MathException {
		float retour;

		if (r != 0) {
			retour = ddp / r;
		} else {
			throw new MathException("division par zero!");
		}
		return retour;
	}

	public float impedanceCircuitRLC(float resistance, float inductance, float capacite, float frequence) {

		return 0;
	}

}
