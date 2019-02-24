package modele;

import exception.MathException;

/**
 * 
 * @author Simon Beaulieu
 * 
 */
public class Calcul {
	public static final double chargeE = 1.602 * Math.pow(10, -19);
	public static final double masseElectron = 9.109 * Math.pow(10, -31);
	public static final double masseProton = 1.672 * Math.pow(10, -27);
	public static final double k = 9 * Math.pow(10, 9);
	public static final double epsilon = 8.854 * Math.pow(10, -12);

	// Calculs de fréquence
	public static float frequenceTofrequenceAngulaire(float frequence) {
		return (float) (2 * Math.PI * frequence);
	}

	public static float frequenceAngulaireTofrequence(float frequenceAngulaire) {
		return (float) (frequenceAngulaire / (2 * Math.PI));
	}

	// Calculs de condensateur
	public static float capaciteCondensateur(float ddp, float q) throws MathException {
		float c = 0;
		if (ddp != 0) {
			c = q / ddp;
		} else {
			throw new MathException("Charge du condensateur null!");
		}
		return c;
	}

	public static float chargeCondensateur(float ddp, float c) {
		return ddp * c;
	}

	public static float ddpCondensateur(float c, float q) throws MathException {
		float ddp = 0;
		if (c != 0) {
			ddp = q / c;
		} else {
			throw new MathException("Charge du condensateur = 0!");
		}
		return ddp;
	}

	public static float capaciteCondensateurPlan(float aire, float distance) throws MathException {
		float capacite = 0;
		if (distance != 0) {
			capacite = (float) (Calcul.epsilon * aire / distance);
		} else {
			throw new MathException("distance entre 2 armatures de condensateur null!");
		}
		return capacite;
	}

	public static float capaciteCondensateurCylindrique(float longueur, float rayonA, float rayonB) throws MathException {
		float capacite = 0;
		if ((rayonA > 0 && rayonB > 0) || (rayonB < 0 && rayonB < 0)) {
			capacite = (float) (longueur / (2 * Calcul.k * Math.log(rayonB / rayonA)));
		} else {
			throw new MathException("Rayon du condensateur cylindrique invalide");
		}
		return capacite;
	}

	public static float capaciteCondensateurSpherique(float rayonA, float rayonB) throws MathException {
		float capacite = 0;
		if (rayonB > rayonA) {
			capacite = (float) (4 * Math.PI * Calcul.epsilon * rayonA * rayonB / (rayonB - rayonA));
		} else {
			throw new MathException("Rayon du condensateur cylindrique invalide");
		}
		return capacite;
	}

	// Calcul de resistance
	public static float calculResistance(float p, float l, float a) throws MathException {
		float r = 0;
		if (a != 0) {
			r = (p * l) / a;
		} else {
			throw new MathException("L'aire du fil (resistance) est égale à 0");
		}
		return r;

	}

	public static float resistiviteEtTemperature(float pi, float ti, float tf, float coefThermique) {
		float deltaT = tf - ti;
		return pi * (1 + coefThermique * deltaT);
	}

	public static float loiOhmR(float ddp, float i) throws MathException {
		float retour;

		if (i != 0) {
			retour = ddp / i;
		} else {
			throw new MathException("division par zero!");
		}
		return retour;
	}

	public static float loiOhmDDP(float r, float i) {
		return r * i;
	}

	public static float loiOhmI(float r, float ddp) throws MathException {
		float retour;

		if (r != 0) {
			retour = ddp / r;
		} else {
			throw new MathException("division par zero!");
		}
		return retour;
	}

	// Calcul de circuit RC
	public static float ChargeCondensateurDecharge(float ChargeMax, float t, float R, float C) {
		return (float) (ChargeMax * Math.exp((-t) / (R * C)));
	}

	public static float ddpCondesateurDecharge(float ddpMax, float t, float R, float C) {
		return (float) (ddpMax * Math.exp((-t) / (R * C)));
	}

	public static float courantCondensateurDecharge(float courantMax, float t, float R, float C) {
		return (float) (courantMax * Math.exp((-t) / (R * C)));
	}

	public static float ChargeCondensateurCharge(float ChargeMax, float t, float R, float C) {
		return (float) (ChargeMax * (1 - Math.exp((-t) / (R * C))));
	}

	public static float ddpCondesateurCharge(float ddpMax, float t, float R, float C) {
		return (float) (ddpMax * (1 - Math.exp((-t) / (R * C))));
	}

	public static float courantCondensateurCharge(float courantMax, float t, float R, float C) {
		return (float) (courantMax * (1 - Math.exp((-t) / (R * C))));
	}

	// Calcul de circuit RL
	public static float CourantDeplug(float CourantMax, float t, float R, float L) {
		return (float) (CourantMax * Math.exp((-t*R) / L));
	}
	
	public static float CourantPlug(float CourantMax, float t, float R, float L) {
		return (float) (1 - (CourantMax * Math.exp((-t*R) / L)));
	}
	
	
	// Calcul de circuit RLC
	public static float impedanceCircuitRLC(float resistance, float inductance, float capacite, float frequence) {
		
		return 0;
	}

	public static float impedanceCondensateur(float frequence,float capacite) throws MathException {
		float impedanceCondensateur = 0;
		if (frequence != 0 && capacite != 0) {
			float freqangulaire = Calcul.frequenceTofrequenceAngulaire(frequence);
			impedanceCondensateur = 1/(freqangulaire*capacite);
		} else {
			throw new MathException("frequence ou capacite nul!");
		}
		return impedanceCondensateur;
		
	}
	
	public static float impedanceBobine(float frequence,float inductance)  {
		float impedanceBobine = 0;
		
			float freqangulaire = Calcul.frequenceTofrequenceAngulaire(frequence);
			impedanceBobine = freqangulaire*inductance;
		return impedanceBobine;
		
	}
}
