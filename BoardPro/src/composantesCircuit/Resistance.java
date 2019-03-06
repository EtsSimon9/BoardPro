package composantesCircuit;

import utilitaire.Calcul;
import composant.ComposantElectrique_CE_;
import exceptions.ComposantException;
import exceptions.MathException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * Classes des Resistors, un resistor à soit une résistance égale à celle entrée
 * dans la zone texte (Résistance) ou bien une résistance custom résultante de sa longueur
 * rayon et résistivité, l'utilisateur devrait donc pouvoir cocher sont choix puisque c'est l'un
 * ou l'autre.
 * @author Simon Beaulieu
 *
 */
public class Resistance extends ComposantElectrique_CE_ {
	/**
	 * Longueur de la résistance utile pour des resistors customs
	 */
	private float longueur;
	/**
	 * Résistiviter de la résistance qui est écrit p normalement, utile pur des resistors customs.
	 */
	private float resistivite;
	/**
	 * Rayon de la résistance utile pour des resistors customs
	 */
	private float rayon;

	/**
	 * Constructeur par défaut des resistors, appele le constructeur défaut de composante électrique
	 * @throws ComposantException
	 */
	public Resistance(short x, short y) throws ComposantException {
		super(x,y);

	}
	public float getLongueur() {
		return longueur;
	}

	public void setLongueur(float longueur) {
		this.longueur = longueur;
	}

	public float getResistivite() {
		return resistivite;
	}

	public void setResistivite(float resistivite) {
		this.resistivite = resistivite;
	}
	public float getRayon() {
		return rayon;
	}
	public void setRayon(float rayon) {
		this.rayon = rayon;
	}

	/**
	 * Dans la modification des résistances, un bouton Résistance personalisé appelerais cette
	 * méthode pour modifier la résistance du résistor selon sa longueur,rayon,résistivité
	 */
	public void changerResistance() {
		try {
			this.setImpedence(Calcul.calculResistance(this.getResistivite(), this.getLongueur(), this.getRayon()));
		} catch (MathException e) {
			Alert alert = new Alert(AlertType.INFORMATION, "Le rayon est égal à 0\nLa résistance de cette\n composante est donc égale à 0.", ButtonType.OK);
			alert.show();
		}
	}
}
