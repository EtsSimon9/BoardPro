package utilitaire;

import exceptions.MathException;

/**
 * Classe remplit de calcul utile en électricité, toutes les formules et quelque
 * constante utile vue lors de notre cours d'électricité y sont. Des
 * commentaires sépares les sections pour mieux s'y retrouver. Ajouter des
 * méthode de calculs au besoins.
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

	/*
	 * Section calcul de fréquence
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 */
	/**
	 * Prend une fréquence (en Hz) et retourne la fréquence angulaire (en rad/s)
	 */
	public static float frequenceTofrequenceAngulaire(float frequence) {
		return (float) (2 * Math.PI * frequence);
	}

	/**
	 * Prend une fréquence angulaire (en rad/s) et retourne la fréquence (en Hz)
	 */
	public static float frequenceAngulaireTofrequence(float frequenceAngulaire) {
		return (float) (frequenceAngulaire / (2 * Math.PI));
	}

	/*
	 * Section Condensateur
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 */
	/**
	 * Donne la capacité du condensateur, ce calcul à besoin de la différence de
	 * potentielle et la charge sur le condensateur
	 */
	public static float capaciteCondensateur(float ddp, float q) throws MathException {
		float c = 0;
		if (ddp != 0) {
			c = q / ddp;
		} else {
			throw new MathException("Charge du condensateur null!");
		}
		return c;
	}

	/**
	 * Donne la charge sur le condensateur, ce calcul à besoin de la différence de
	 * potentielle et la capacité du condensateur
	 */
	public static float chargeCondensateur(float ddp, float c) {
		return ddp * c;
	}

	/**
	 * Donne la différence de potentielle au bornes du condensateur, ce cacul à
	 * besoin de la capacité du condensateur et de la charge sur celui-ci.
	 */
	public static float ddpCondensateur(float c, float q) throws MathException {
		float ddp = 0;
		if (c != 0) {
			ddp = q / c;
		} else {
			throw new MathException("Charge du condensateur = 0!");
		}
		return ddp;
	}

	/**
	 * Utile pour des condensateurs customs, calcul de la capacite d'un condensateur
	 * plan. Ce calcul à besoin de l'aire des branches qui peuvent être
	 * carré,rectangle,cercle donc on se contente de recevoir l'aire en param, et
	 * aussi la distance entre les deux branches
	 */
	public static float capaciteCondensateurPlan(float aire, float distance) throws MathException {
		float capacite = 0;
		if (distance != 0) {
			capacite = (float) (Calcul.epsilon * aire / distance);
		} else {
			throw new MathException("distance entre 2 armatures de condensateur null!");
		}
		return capacite;
	}

	/**
	 * Utile pour des condensateurs customs, calcul de la capacite d'un condensateur
	 * cylindrique Ce calcul à besoin de la longueur des cylindres, le rayonA du
	 * plus petit et le rayonB du plus grand.
	 */
	public static float capaciteCondensateurCylindrique(float longueur, float rayonA, float rayonB)
			throws MathException {
		float capacite = 0;
		if ((rayonA > 0 && rayonB > 0) || (rayonB < 0 && rayonB < 0)) {
			capacite = (float) (longueur / (2 * Calcul.k * Math.log(rayonB / rayonA)));
		} else {
			throw new MathException("Rayon du condensateur cylindrique invalide");
		}
		return capacite;
	}

	/**
	 * Utile pour des condensateurs customs, calcul de la capacité d'un condensateur
	 * spherique. Ce calcul à besoin du rayon de la petite sphère rayonA et du rayon
	 * de la grande sphère rayonB
	 */
	public static float capaciteCondensateurSpherique(float rayonA, float rayonB) throws MathException {
		float capacite = 0;
		if (rayonB > rayonA) {
			capacite = (float) (4 * Math.PI * Calcul.epsilon * rayonA * rayonB / (rayonB - rayonA));
		} else {
			throw new MathException("Rayon du condensateur cylindrique invalide");
		}
		return capacite;
	}

	/*
	 * Section résistor
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 */

	/**
	 * Calcul de la résistance d'une résistance custom. Ce calcul à besoin de la
	 * résistivité du matériau p, de la longueur de la résistance et du rayon du
	 * fil/résistor
	 */
	public static float calculResistance(double p, float l, float rayon) {
		float r = 0;
		if (rayon != 0) {
			r = (float) ((p * l) / (Math.PI * rayon * rayon));
		} 
		return r;
	}

	/**
	 * Calcul de la résistivité (variant avec la température) custom d'une
	 * résistance custom. Ce calcul à besoin de la résistvité du matériaux à 20° pi,
	 * de la température initiale ti (20° souvent) , de la température finale et du
	 * coefficient thermique (le symbole est alpha) Voir p.199 du Lafrance pour +
	 * d'info.
	 */
	public static double resistiviteEtTemperature(double d, float ti, float tf, double e) {
		float deltaT = tf - ti;
		return (d * (1 + (e * deltaT)));
	}

	/**
	 * Calcul loi d'ohm trouvant la résistance
	 */
	public static float loiOhmR(float ddp, float i) throws MathException {
		float retour;

		if (i != 0) {
			retour = ddp / i;
		} else {
			throw new MathException("division par zero!");
		}
		return retour;
	}

	/**
	 * Calcul loi d'ohm trouvant la différence de potentielle
	 */
	public static float loiOhmDDP(float r, float i) {
		return r * i;
	}

	/**
	 * Calcul loi d'ohm trouvant le courant I
	 */
	public static float loiOhmI(float r, float ddp) throws MathException {
		float retour;

		if (r != 0) {
			retour = ddp / r;
		} else {
			throw new MathException("division par zero!");
		}
		return retour;
	}

	/*
	 * Section circuit RC : résistance condensateur
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 */
	/**
	 * Circuit RC Calcul trouvant la charge d'un condensateur qui est en décharge,
	 * cette charge varie dans le temps selon une exponentielle inverse. Ce calcul à
	 * besoin de la charge maximale du condensateur, du temps écoulé depuis le début
	 * de la décharge, de la résistance du résistor dans le circuit et de la
	 * capacité du condensateur
	 */
	public static float ChargeCondensateurDecharge(float ChargeMax, float t, float R, float C) {
		return (float) (ChargeMax * Math.exp((-t) / (R * C)));
	}

	/**
	 * Circuit RC Calcul trouvant la différence de potentielle d'un condensateur qui
	 * est en décharge, cette DDP varie dans le temps selon une exponentielle
	 * inverse. Ce calcul à besoin de la différence de potentielle maximale du
	 * condensateur, du temps écoulé depuis le début de la décharge, de la
	 * résistance du résistor dans le circuit et de la capacité du condensateur
	 */
	public static float ddpCondesateurDecharge(float ddpMax, float t, float R, float C) {
		return (float) (ddpMax * Math.exp((-t) / (R * C)));
	}

	/**
	 * Circuit RC Calcul trouvant le courant d'un condensateur qui est en décharge,
	 * ce courant varie dans le temps selon une exponentielle inverse. Ce calcul à
	 * besoin du courant maximal du condensateur, du temps écoulé depuis le début de
	 * la décharge, de la résistance du résistor dans le circuit et de la capacité
	 * du condensateur
	 */
	public static float courantCondensateurDecharge(float courantMax, float t, float R, float C) {
		return (float) (courantMax * Math.exp((-t) / (R * C)));
	}

	/**
	 * Circuit RC Calcul trouvant la charge d'un condensateur qui est en charge,
	 * cette charge varie dans le temps selon un exponentielle. Ce calcul à besoin
	 * de la charge maximal du condensateur, du temps écoulé depuis le début de la
	 * charge, de la résistance du résistor dans le circuit et de la capacité du
	 * condensateur
	 */
	public static float ChargeCondensateurCharge(float ChargeMax, float t, float R, float C) {
		return (float) (ChargeMax * (1 - Math.exp((-t) / (R * C))));
	}

	/**
	 * Circuit RC Calcul trouvant la différence de potentielle d'un condensateur qui
	 * est en charge, cette DDP varie dans le temps selon un exponentielle. Ce
	 * calcul à besoin de la DDP maximal du condensateur, du temps écoulé depuis le
	 * début de la charge, de la résistance du résistor dans le circuit et de la
	 * capacité du condensateur.
	 */
	public static float ddpCondesateurCharge(float ddpMax, float t, float R, float C) {
		return (float) (ddpMax * (1 - Math.exp((-t) / (R * C))));
	}

	/**
	 * Circuit RC Calcul trouvant le courant dans un d'un condensateur qui est en
	 * charge, ce courant varie dans le temps selon un exponentielle. Ce calcul à
	 * besoin du courant maximal du condensateur, du temps écoulé depuis le début de
	 * la charge, de la résistance du résistor dans le circuit et de la capacité du
	 * condensateur.
	 */
	public static float courantCondensateurCharge(float courantMax, float t, float R, float C) {
		return (float) (courantMax * (1 - Math.exp((-t) / (R * C))));
	}

	/*
	 * Section circuit RL: résistance bobine d'inductance
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 */
	/**
	 * Circuit RL Calcul trouvant le courant dans le circuit RL suite à l'ouverture
	 * du circuit, ce courant varie dans le temps selon une exponentiel inverser
	 * (chute de courant amortie, max a min avec courbe)
	 */
	public static float CourantDeplug(float CourantMax, float t, float R, float L) {
		return (float) (CourantMax * Math.exp((-t * R) / L));
	}

	/**
	 * Circuit RL Calcul trouvant le courant dans le circuit RL suite à la fermeture
	 * du circuit Le cournat varie dans le temps selon une exponentiel (augmentation
	 * rapide de courant amortie)
	 */
	public static float CourantPlug(float CourantMax, float t, float R, float L) {
		return (float) (1 - (CourantMax * Math.exp((-t * R) / L)));
	}

	/*
	 * Section circuit RLC: Résistance, bobine d'inductance et condensateur
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 */
	/**
	 * Circuit RLC Trouve l'impedance/résistance d'un circuit RLC
	 */
	public static float impedanceCircuitRLC(float resistance, float inductance, float capacite, float frequence)
			throws MathException {
		float z = 0;
		if (resistance >= 0 && capacite >= 0 && inductance >= 0) {
			float freqAngulaire = Calcul.frequenceTofrequenceAngulaire(frequence);
			float xc = Calcul.impedanceCondensateur(freqAngulaire, capacite);
			float xl = Calcul.impedanceBobine(freqAngulaire, inductance);
			z = (float) Math.sqrt(Math.pow(resistance, 2) + Math.pow(xl-xc, 2) );
		} else {
			throw new MathException("Resistance, inductance ou capacite négative dans le calcul d'impédence RLC");
		}
		return z;
	}

	/**
	 * Calcul l'impedance d'un condensateur
	 */
	public static float impedanceCondensateur(float frequence, float capacite) throws MathException {
		float impedanceCondensateur = 0;
		if (frequence != 0 && capacite != 0) {
			float freqangulaire = Calcul.frequenceTofrequenceAngulaire(frequence);
			impedanceCondensateur = 1 / (freqangulaire * capacite);
		} else {
			throw new MathException("frequence ou capacite nul!");
		}
		return impedanceCondensateur;

	}

	/**
	 * Calcul l'impedance d'une bobine
	 */
	public static float impedanceBobine(float frequence, float inductance) {
		float impedanceBobine = 0;

		float freqangulaire = Calcul.frequenceTofrequenceAngulaire(frequence);
		impedanceBobine = freqangulaire * inductance;
		return impedanceBobine;

	}
}
