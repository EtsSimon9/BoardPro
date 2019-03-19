package composantesCircuit;

import utilitaire.Calcul;
import utilitaire.Materiaux;
import composante.CE2Entrees;
import exceptions.ComposantException;
import exceptions.MathException;

/**
 * Classes des Resistors, un resistor � soit une r�sistance �gale � celle entr�e
 * dans la zone texte (R�sistance) ou bien une r�sistance custom r�sultante de
 * sa longueur rayon et r�sistivit�, l'utilisateur devrait donc pouvoir cocher
 * sont choix puisque c'est l'un ou l'autre.
 * 
 * @author Simon Beaulieu
 *
 */
public class Resistance extends CE2Entrees {
	/**
	 * Longueur de la r�sistance utile pour des resistors customs
	 */
	private float longueur;

	/**
	 * Rayon de la r�sistance utile pour des resistors customs
	 */
	private float rayon;

	/**
	 * Mat�riau de la r�sistance
	 */
	private Materiaux materiau;

	/**
	 * Noter bien que cette temp�rature est en degr� celcius lorsque l'utilisateur
	 * nous la donne (cot� pratique). Par contre, dans nos calcul nous devons la
	 * transferer.
	 * 
	 */
	private float temperature;

	public static final int TEMPMIN = -273;
	public static final int TEMPMAX = 1000;
	public static final int DIMMENSIONMIN = 0;
	public static final int DIMMENSIONMAX = 1000;

	/**
	 * Constructeur par d�faut des resistors, appele le constructeur d�faut de
	 * composante �lectrique
	 * 
	 * @throws ComposantException
	 */
	public Resistance(short x, short y) throws ComposantException {
		super(x, y);

	}

	/**
	 * Constructeur pour resistance modifier, c'est-�-dire que l'utilisateur nous
	 * indique la longueur, le rayon et le mat�riaux de la r�sistance pour qu'on
	 * trouve nous m�me la valeur de la r�sistance.
	 */
	public Resistance(short x, short y, float longueur, float rayon, Materiaux mat, float t) {
		super(x, y);
		if (this.validerParam(longueur, rayon, mat, t)) {
			this.setLongueur(longueur);
			this.setRayon(rayon);
			this.setMateriau(mat);
			this.setTemperature(t);
			mat.getCoefThermique();
			double p = Calcul.resistiviteEtTemperature(mat.getResistivite(), 20, t, mat.getCoefThermique());
			if (rayon != 0) {
				try {
					this.setImpedence(Calcul.calculResistance(p, longueur, rayon));
				} catch (MathException e) {
				}
			}
		}
	}

	public float getLongueur() {
		return longueur;
	}

	public void setLongueur(float longueur) {
		this.longueur = longueur;
	}

	public float getRayon() {
		return rayon;
	}

	public void setRayon(float rayon) {
		this.rayon = rayon;
	}

	public Materiaux getMateriau() {
		return materiau;
	}

	public void setMateriau(Materiaux materiau) {
		this.materiau = materiau;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	private boolean validerParam(float l, float r, Materiaux m, float t) {
		boolean retour = false;
		if (l > DIMMENSIONMIN && l <= DIMMENSIONMAX && r > DIMMENSIONMIN && r <= DIMMENSIONMAX && t >= TEMPMIN
				&& t <= TEMPMAX && m != null) {
			retour = true;
		}
		return retour;
	}
}
