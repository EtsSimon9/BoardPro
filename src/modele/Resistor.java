package modele;

import exception.ComposanteException;
import exception.MathException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Resistor extends ComposanteElectrique {
	private float longueur;
	private float resistivite;
	private float rayon;
	
	public Resistor() throws ComposanteException {
		super();

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
	
	public void changerResistance() {
		try {
			this.setResistivite(Calcul.calculResistance(this.getResistivite(), this.getLongueur(), this.getRayon()));
		} catch (MathException e) {
			Alert alert = new Alert(AlertType.INFORMATION, "Le rayon est égal à 0\nLa résistance de cette\n composante est donc égale à 0.", ButtonType.OK);
			alert.show();
		}
	}
}
