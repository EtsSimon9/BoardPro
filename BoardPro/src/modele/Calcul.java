package modele;

import exception.MathException;

/**
 * 
 * @author Simon Beaulieu
 * 
 */
public class Calcul {
	public static final double chargeE = 1.602 *  Math.pow(10, -19);
	public static final double masseElectron = 9.109 *  Math.pow(10, -31);
	public static final double masseProton = 1.672 *  Math.pow(10, -27);
	public static final double k = 9 * Math.pow(10, 9);
	public static final double epsilon = 8.854 * Math.pow(10, -12);
	
	
	public float frequenceTofrequenceAngulaire(float frequence) {
		return (float) (2 * Math.PI * frequence);
	}

	public float frequenceAngulaireTofrequence(float frequenceAngulaire) {
		return (float) (frequenceAngulaire / (2 * Math.PI));
	}
	
	
	public float capaciteCondensateur(float ddp, float q) throws MathException {
		float c = 0;
		if (ddp != 0) {
			c = q/ddp;
		} else {
			throw new MathException("Charge du condensateur null!");
		}
		return c;
	}
	
	public float chargeCondensateur(float ddp, float c) {
		return ddp*c;
	}
	
	public float ddpCondensateur(float c, float q) throws MathException {
		float ddp = 0;
		if (c !=0) {
			ddp = q/c;
		} else {
			throw new MathException("Charge du condensateur = 0!");
		} return ddp;
	}
	
	
	public float calculResistance(float p, float l, float a) throws MathException {
		float r = 0;
		if (a != 0) {
			r = (p*l)/a;
		} else {
			throw new MathException("L'aire du fil (resistance) est égale à 0");
		}
		return r;
		
	}
	
	public float resistiviteEtTemperature(float pi,float ti, float tf, float coefThermique) {
		float deltaT = tf - ti;
		return pi *(1+coefThermique*deltaT);
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
