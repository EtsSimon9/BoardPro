package composantesCircuit;

import utilitaire.Calcul;
import utilitaire.Materiaux;
import composant.ComposantElectrique_CE_;
import exceptions.ComposantException;
import exceptions.MathException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * Classes des Resistors, un resistor à soit une résistance égale à celle entrée
 * dans la zone texte (Résistance) ou bien une résistance custom résultante de
 * sa longueur rayon et résistivité, l'utilisateur devrait donc pouvoir cocher
 * sont choix puisque c'est l'un ou l'autre.
 * 
 * @author Simon Beaulieu
 *
 */
public class Resistance extends ComposantElectrique_CE_ {
	/**
	 * Longueur de la résistance utile pour des resistors customs
	 */
	private float longueur;

	/**
	 * Rayon de la résistance utile pour des resistors customs
	 */
	private float rayon;

	/**
	 * Matériau de la résistance
	 */
	private Materiaux materiau;

	/**
	 * Noter bien que cette température est en degré celcius lorsque l'utilisateur
	 * nous la donne (coté pratique). Par contre, dans nos calcul nous devons la
	 * transferer.
	 * 
	 */
	private float temperature;

	public static final int TEMPMIN = -273;
	public static final int TEMPMAX = 1000;
	public static final int DIMMENSIONMIN = 0;
	public static final int DIMMENSIONMAX = 1000;

	/**
	 * Constructeur par défaut des resistors, appele le constructeur défaut de
	 * composante électrique
	 * 
	 * @throws ComposantException
	 */
	public Resistance(short x, short y) throws ComposantException {
		super(x, y);

	}

	/**
	 * Constructeur pour resistance modifier, c'est-à-dire que l'utilisateur nous
	 * indique la longueur, le rayon et le matériaux de la résistance pour qu'on
	 * trouve nous même la valeur de la résistance.
	 */
	public Resistance(short x, short y, float longueur, float rayon, Materiaux mat, float t) {
		super(x, y);
		if (this.validerParam(longueur, rayon, mat, t)) {
			this.setLongueur(longueur);
			this.setRayon(rayon);
			this.setMateriau(mat);
			this.setTemperature(t);
			double p = Calcul.resistiviteEtTemperature(mat.getResistivite(), 20, t, mat.getCoefThermique());
			this.setImpedence(Calcul.calculResistance(p, longueur, rayon));
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
