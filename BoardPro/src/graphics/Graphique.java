package graphics;

import javafx.scene.effect.Light.Point;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import utilitaire.SolveurEquations;


/**
 * Cette classe prends une tring d'une foncion et des axes et dessine un graphique pour cela
 * @author HAAS Team
 *
 */
public class Graphique extends Pane {
	private float precisionDessin = 0.01f;
	private Axes axes;
	private Point point = new Point();
	private String fonction;

	/**
	 * Initialise les donn�es de la Pane repr�sentant un garaphique statique en lui
	 * donnant les axes et la foncion et dessine ensuite le r�sultat
	 *
	 * @param fonction
	 * @param axes
	 */
	public Graphique(String fonction, Axes axes) {
		this.fonction = fonction;
		setAxes(axes);
		dessinerResultat();
	}

	/**
	 * nombre de pixels tir�s vers la droite
	 *
	 * @param xAjouter
	 */
	public void tirerGraphique(float xAjouter) {
		axes.ajouterX(-deMapX(xAjouter));
		point.setX(point.getX() + xAjouter);
		dessinerResultat();
	}

	private double mapX(double x) {
		return (x - axes.getMinX()) * axes.getMesurePixelUnite();
	}

	public double deMapX(double x) {
		return x / axes.getMesurePixelUnite();
	}

	private double mapY(double y) {
		return -y * axes.getMesurePixelUnite() + axes.getActualHeight();
	}

	private void dessinerResultat() {
		Path path = new Path();
		path.setStroke(Color.ORANGE.deriveColor(0, 1, 1, 0.6));
		path.setStrokeWidth(2);
		path.setClip(new Rectangle(0, 0, axes.getPrefWidth(), axes.getPrefHeight()));

		if (fonction != null) {
			double x = axes.getMinX();
			double y = 0;
			boolean depart = true;
			while (x < axes.getMaxX()) {
				try {
					SolveurEquations.initPrecedence();
					y = SolveurEquations.evaluer(fonction.replace("x", x + ""));

					if (!Double.isNaN(y)) {
						if (depart) {
							path.getElements().add(new MoveTo(mapX(x), mapY(y)));
							depart = false;
						} else {
							path.getElements().add(new LineTo(mapX(x), mapY(y)));
						}
					}
				} catch (Exception e) {
				}
				x += precisionDessin;
			}
		}
		setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
		setPrefSize(axes.getPrefWidth(), axes.getPrefHeight());
		setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
		this.getChildren().clear();
		this.getChildren().setAll(path, axes);
	}

	public void changerFonction(String f) {
		this.fonction = f;
		dessinerResultat();
	}

	private void setAxes(Axes axes) {
		this.axes = axes;
		precisionDessin = (float) (this.axes.getBonds() / 50);

	}

}