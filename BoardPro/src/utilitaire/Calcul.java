package utilitaire;

import exceptions.MathException;

/**
 * Classe remplit de calcul utile en �lectricit�, toutes les formules et quelque
 * constante utile vue lors de notre cours d'�lectricit� y sont. Des
 * commentaires s�pares les sections pour mieux s'y retrouver. Ajouter des
 * m�thode de calculs au besoins.
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
	 * Section calcul de fr�quence
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
	 * Prend une fr�quence (en Hz) et retourne la fr�quence angulaire (en rad/s)
	 */
	public static float frequenceTofrequenceAngulaire(float frequence) {
		return (float) (2 * Math.PI * frequence);
	}

	/**
	 * Prend une fr�quence angulaire (en rad/s) et retourne la fr�quence (en Hz)
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
	 * Donne la capacit� du condensateur, ce calcul � besoin de la diff�rence de
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
	 * Donne la charge sur le condensateur, ce calcul � besoin de la diff�rence de
	 * potentielle et la capacit� du condensateur
	 */
	public static float chargeCondensateur(float ddp, float c) {
		return ddp * c;
	}

	/**
	 * Donne la diff�rence de potentielle au bornes du condensateur, ce cacul �
	 * besoin de la capacit� du condensateur et de la charge sur celui-ci.
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
	 * plan. Ce calcul � besoin de l'aire des branches qui peuvent �tre
	 * carr�,rectangle,cercle donc on se contente de recevoir l'aire en param, et
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
	 * cylindrique Ce calcul � besoin de la longueur des cylindres, le rayonA du
	 * plus petit et le rayonB du plus grand.
	 */
	public static float capaciteCondensateurCylindrique(float longueur, float rayonA, float rayonB)
			throws MathException {
		float capacite = 0;
		if (rayonA > 0 && rayonB > 0) {
			capacite = (float) (longueur / (2 * Calcul.k * Math.log(rayonB / rayonA)));
		} else {
			throw new MathException("Rayon du condensateur cylindrique invalide");
		}
		return capacite;
	}

	/**
	 * Utile pour des condensateurs customs, calcul de la capacit� d'un condensateur
	 * spherique. Ce calcul � besoin du rayon de la petite sph�re rayonA et du rayon
	 * de la grande sph�re rayonB
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
	 * Section r�sistor
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
	 * Calcul de la r�sistance d'une r�sistance custom. Ce calcul � besoin de la
	 * r�sistivit� du mat�riau p, de la longueur de la r�sistance et du rayon du
	 * fil/r�sistor
	 * @throws MathException 
	 */
	public static float calculResistance(double p, float l, float rayon) throws MathException {
		float r = 0;
		if (rayon != 0) {
			r = (float) ((p * l) / (Math.PI * rayon * rayon));
		} else {
			throw new MathException("Rayon de la résistance null!");
		}
		return r;
	}

	/**
	 * Calcul de la r�sistivit� (variant avec la temp�rature) custom d'une
	 * r�sistance custom. Ce calcul � besoin de la r�sistvit� du mat�riaux � 20� pi,
	 * de la temp�rature initiale ti (20� souvent) , de la temp�rature finale et du
	 * coefficient thermique (le symbole est alpha) Voir p.199 du Lafrance pour +
	 * d'info.
	 */
	public static double resistiviteEtTemperature(double resisti, float ti, float tf, double coef) {
		float deltaT = tf - ti;
		return (resisti * (1 + (coef * deltaT)));
	}

	/**
	 * Calcul loi d'ohm trouvant la r�sistance
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
	 * Calcul loi d'ohm trouvant la diff�rence de potentielle
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
	 * Section circuit RC : r�sistance condensateur
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
	 * Circuit RC Calcul trouvant la charge d'un condensateur qui est en d�charge,
	 * cette charge varie dans le temps selon une exponentielle inverse. Ce calcul �
	 * besoin de la charge maximale du condensateur, du temps �coul� depuis le d�but
	 * de la d�charge, de la r�sistance du r�sistor dans le circuit et de la
	 * capacit� du condensateur
	 */
	public static float ChargeCondensateurDecharge(float ChargeMax, float t, float R, float C) {
		return (float) (ChargeMax * Math.exp((-t) / (R * C)));
	}

	/**
	 * Circuit RC Calcul trouvant la diff�rence de potentielle d'un condensateur qui
	 * est en d�charge, cette DDP varie dans le temps selon une exponentielle
	 * inverse. Ce calcul � besoin de la diff�rence de potentielle maximale du
	 * condensateur, du temps �coul� depuis le d�but de la d�charge, de la
	 * r�sistance du r�sistor dans le circuit et de la capacit� du condensateur
	 */
	public static float ddpCondesateurDecharge(float ddpMax, float t, float R, float C) {
		return (float) (ddpMax * Math.exp((-t) / (R * C)));
	}

	/**
	 * Circuit RC Calcul trouvant le courant d'un condensateur qui est en d�charge,
	 * ce courant varie dans le temps selon une exponentielle inverse. Ce calcul �
	 * besoin du courant maximal du condensateur, du temps �coul� depuis le d�but de
	 * la d�charge, de la r�sistance du r�sistor dans le circuit et de la capacit�
	 * du condensateur
	 */
	public static float courantCondensateurDecharge(float courantMax, float t, float R, float C) {
		return (float) (courantMax * Math.exp((-t) / (R * C)));
	}

	/**
	 * Circuit RC Calcul trouvant la charge d'un condensateur qui est en charge,
	 * cette charge varie dans le temps selon un exponentielle. Ce calcul � besoin
	 * de la charge maximal du condensateur, du temps �coul� depuis le d�but de la
	 * charge, de la r�sistance du r�sistor dans le circuit et de la capacit� du
	 * condensateur
	 */
	public static float ChargeCondensateurCharge(float ChargeMax, float t, float R, float C) {
		return (float) (ChargeMax * (1 - Math.exp((-t) / (R * C))));
	}

	/**
	 * Circuit RC Calcul trouvant la diff�rence de potentielle d'un condensateur qui
	 * est en charge, cette DDP varie dans le temps selon un exponentielle. Ce
	 * calcul � besoin de la DDP maximal du condensateur, du temps �coul� depuis le
	 * d�but de la charge, de la r�sistance du r�sistor dans le circuit et de la
	 * capacit� du condensateur.
	 */
	public static float ddpCondesateurCharge(float ddpMax, float t, float R, float C) {
		return (float) (ddpMax * (1 - Math.exp((-t) / (R * C))));
	}

	/**
	 * Circuit RC Calcul trouvant le courant dans un d'un condensateur qui est en
	 * charge, ce courant varie dans le temps selon un exponentielle. Ce calcul �
	 * besoin du courant maximal du condensateur, du temps �coul� depuis le d�but de
	 * la charge, de la r�sistance du r�sistor dans le circuit et de la capacit� du
	 * condensateur.
	 */
	public static float courantCondensateurCharge(float courantMax, float t, float R, float C) {
		return (float) (courantMax * (1 - Math.exp((-t) / (R * C))));
	}

	/*
	 * Section circuit RL: r�sistance bobine d'inductance
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
	 * Circuit RL Calcul trouvant le courant dans le circuit RL suite � l'ouverture
	 * du circuit, ce courant varie dans le temps selon une exponentiel inverser
	 * (chute de courant amortie, max a min avec courbe)
	 */
	public static float CourantDeplug(float CourantMax, float t, float R, float L) {
		return (float) (CourantMax * Math.exp((-t * R) / L));
	}

	/**
	 * Circuit RL Calcul trouvant le courant dans le circuit RL suite � la fermeture
	 * du circuit Le cournat varie dans le temps selon une exponentiel (augmentation
	 * rapide de courant amortie)
	 */
	public static float CourantPlug(float CourantMax, float t, float R, float L) {
		return (float) (1 - (CourantMax * Math.exp((-t * R) / L)));
	}

	/*
	 * Section circuit RLC: R�sistance, bobine d'inductance et condensateur
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
	 * Circuit RLC Trouve l'impedance/r�sistance d'un circuit RLC
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
			throw new MathException("Resistance, inductance ou capacite n�gative dans le calcul d'imp�dence RLC");
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
