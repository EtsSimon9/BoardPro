
package graphics;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import javafx.geometry.Side;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;

public class Axes extends Pane {
	private NumberAxis xAxis, yAxis;
	private double minX, maxX, minY, maxY, bonds, mesurePixelUnite, actualHeight, positionXAxeX, subdivisionAxeX;
	private int width, height;

	public Axes(int width, int height, double bond, int subdivisionAxeX) {
		this.width = width+100;
		this.height = height;
		this.bonds = bond;
		this.subdivisionAxeX = subdivisionAxeX / 2;
		actualHeight = (height / 2);
		setMinX(0);
		setMaxX(this.subdivisionAxeX *2* bonds);
		setMinY(-this.subdivisionAxeX * bonds);
		setMaxY(this.subdivisionAxeX * bonds);
		mesurePixelUnite = this.width / (maxX - minX);
		creerAxes();
	}

	public double getMesurePixelUnite() {
		return mesurePixelUnite;
	}

	public double getBonds() {
		return bonds;
	}

	public double getMinX() {
		return minX;
	}

	public double getActualHeight() {
		return actualHeight;
	}

	private void setMinX(double min) {
		this.minX = min;
	}

	public double getMinY() {
		return minY;
	}

	private void setMinY(double min) {
		this.minY = min;
	}

	public double getMaxX() {
		return maxX;
	}

	private void setMaxX(double max) {
		this.maxX = max;
	}

	public double getMaxY() {
		return maxY;
	}

	private void setMaxY(double max) {
		this.maxY = max;
	}

	public void changerBondsAxes(double bond) {
		this.bonds = bond;
		creerAxes();
	}

	public void ajouterX(double s) {
		positionXAxeX += s;
		xAxis.setLayoutX(-mesurePixelUnite * (positionXAxeX % bonds));
		minX =  positionXAxeX;
		maxX = (subdivisionAxeX *2* bonds) + positionXAxeX;
		xAxis.setUpperBound(maxX - (positionXAxeX % bonds));
		xAxis.setLowerBound(xAxis.getUpperBound() - subdivisionAxeX * 2 * bonds);

	}

	private void creerAxes() {

		setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
		setPrefSize(width, height);
		setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

		xAxis = new NumberAxis(minX, maxX, bonds);
		xAxis.setSide(Side.BOTTOM);
		xAxis.setPrefWidth(width);
		xAxis.setMinorTickVisible(false);
		xAxis.setAnimated(false);
		xAxis.setLayoutY(actualHeight);
		xAxis.setVisible(true);

		yAxis = new NumberAxis(-maxY, -minY, bonds);
		yAxis.setSide(Side.RIGHT);
		yAxis.setLayoutX(0);
		yAxis.setPrefHeight(height);
		yAxis.setMinorTickVisible(false);
		yAxis.setAnimated(false);


		if (bonds <= 0.625 && bonds > 0.00390625) {
			xAxis.setTickLabelFormatter(getStringConverter("#.#######"));
			yAxis.setTickLabelFormatter(getStringConverter("#.#######"));
		} else if (bonds <= 0.00390625 | bonds > 4194304.0) {
			xAxis.setTickLabelFormatter(getStringConverter("#.#E0"));
			yAxis.setTickLabelFormatter(getStringConverter("#.#E0"));
		}

		getChildren().clear();
		getChildren().setAll(xAxis, yAxis);

	}

	/**
	 *
	 * @param axeY Si c'est true, g�re gere l'affichage de la'axeX et si c'est
	 *             false, g�re l'affichage de l'axeY .X
	 */
	

	public NumberAxis getXAxis() {
		return xAxis;
	}

	public NumberAxis getYAxis() {
		return yAxis;
	}

	private StringConverter<Number> getStringConverter(String convert) {
		NumberFormat format = new DecimalFormat(convert);
		return new StringConverter<Number>() {
			@Override
			public String toString(Number number) {
				return format.format(number.doubleValue());
			}

			@Override
			public Number fromString(String string) {
				try {
					return format.parse(string);
				} catch (ParseException e) {
					e.printStackTrace();
					return 0;
				}
			}
		};
	}
}
