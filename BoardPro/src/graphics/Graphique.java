package graphics;

import javafx.scene.effect.Light.Point;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import utilitaire.SolveurEquations;

public class Graphique extends Pane {
	private double incrementation = 0.01;
	private Axes axes;
	private Point point = new Point();
	private String fonction;

	public Graphique(String fonction, Axes axes) {
		setFonction(fonction);
		setAxes(axes);
		dessinerResultat();
	}


	public void tirerGraphique(float xAjouter) {
		axes.ajouterX(- deMapX(xAjouter) );
		dessinerResultat();
		point.setX(point.getX() + xAjouter);
	}


	private double mapX(double x) {
		// axes.getPrefWidth() / (axes.getXAxis().getUpperBound() - axes.getXAxis().getLowerBound()) == largeur/(bondsDansLArgeur)
		return  (x - axes.getXAxis().getLowerBound())*
				axes.getPrefWidth() / (axes.getXAxis().getUpperBound() - axes.getXAxis().getLowerBound());
	}

	public  double deMapX(double x) {
		return  x*(axes.getXAxis().getUpperBound() - axes.getXAxis().getLowerBound()) / axes.getPrefWidth();
	}

	private double mapY(double y) {
		// axes.getPrefWidth() / (axes.getXAxis().getUpperBound() - axes.getXAxis().getLowerBound()) == largeur/(bondsDansLArgeur)
		return -y * axes.getPrefWidth() / (axes.getXAxis().getUpperBound() - axes.getXAxis().getLowerBound())
				+ axes.getActualHeight();
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
			while (x < axes.getMaxX() && !fonction.equals("")) {
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
				x += incrementation;
			}
		}
		setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
		setPrefSize(axes.getPrefWidth(), axes.getPrefHeight());
		setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
		this.getChildren().clear();
		this.getChildren().setAll(path, axes);
	}

	public void changerFonction(String f) {
		setFonction(f);
		dessinerResultat();
	}

	private void setAxes(Axes axes) {
		this.axes = axes;
		setIncrementation(this.axes.getBonds() / 50);

	}

	private void setIncrementation(double i) {
		this.incrementation = i;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

}