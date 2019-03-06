package composantesCircuit;

import utilitaire.Calcul;
import composant.ComposantElectrique_CE_;
import exceptions.ComposantException;
import exceptions.MathException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * Classes des Resistors, un resistor � soit une r�sistance �gale � celle entr�e
 * dans la zone texte (R�sistance) ou bien une r�sistance custom r�sultante de sa longueur
 * rayon et r�sistivit�, l'utilisateur devrait donc pouvoir cocher sont choix puisque c'est l'un
 * ou l'autre.
 * @author Simon Beaulieu
 *
 */
public class Resistance extends ComposantElectrique_CE_ {
	/**
	 * Longueur de la r�sistance utile pour des resistors customs
	 */
	private float longueur;
	/**
	 * R�sistiviter de la r�sistance qui est �crit p normalement, utile pur des resistors customs.
	 */
	private float resistivite;
	/**
	 * Rayon de la r�sistance utile pour des resistors customs
	 */
	private float rayon;

	/**
	 * Constructeur par d�faut des resistors, appele le constructeur d�faut de composante �lectrique
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
	 * Dans la modification des r�sistances, un bouton R�sistance personalis� appelerais cette
	 * m�thode pour modifier la r�sistance du r�sistor selon sa longueur,rayon,r�sistivit�
	 */
	public void changerResistance() {
		try {
			this.setImpedence(Calcul.calculResistance(this.getResistivite(), this.getLongueur(), this.getRayon()));
		} catch (MathException e) {
			Alert alert = new Alert(AlertType.INFORMATION, "Le rayon est �gal � 0\nLa r�sistance de cette\n composante est donc �gale � 0.", ButtonType.OK);
			alert.show();
		}
	}
}
