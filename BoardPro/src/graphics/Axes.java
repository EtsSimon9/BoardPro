package graphics;


import javafx.geometry.Side;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.Pane;

public class Axes extends Pane {
	private NumberAxis xAxis, yAxis;
	private double minX,maxX,minY,maxY,bonds,actualHeight;
	private int width;
	private int height;

	public Axes(int width, int height, int bond) {
		this.width = width+20;
		this.height = height;
		this.bonds = bond;
		setMinX(0 * bonds);
		setMaxX(16 * bonds);
		setMinY(-8 * bonds);
		setMaxY(8 * bonds);
		
		creerAxes();
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




	/**
	 * fait bouger l'axe des x selon le parametre recu
	 * @param s
	 */
	public void ajouterX(double s) {
		minX += s;
		maxX += s;
		xAxis.setLowerBound(minX);
		xAxis.setUpperBound(maxX);
		setAxisMap();
	}
/**
 * Cr�e les axes
 */
	private void creerAxes() {
		setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
		setPrefSize(width, height);
		setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
		xAxis = new NumberAxis(minX, maxX, bonds);
		xAxis.setSide(Side.BOTTOM);
		xAxis.setPrefWidth(width);
		xAxis.setLayoutX(-20);

		
		xAxis.setMinorTickVisible(false);
		setAxisMap();
		xAxis.setAnimated(false);
		;
		yAxis = new NumberAxis(-maxY, -minY, bonds);
		yAxis.setSide(Side.LEFT);
		yAxis.setPrefHeight(height);	
		yAxis.setMinorTickVisible(false);
		yAxis.setAnimated(false);
		yAxis.setLayoutX(0);
		
		getChildren().clear();
		getChildren().setAll(xAxis, yAxis);
		
	}

	
	/**
	 *  
	 *  Gere l'affichage de la'axeX 
	 */
	private void setAxisMap() {
		double demiLenght = bonds *8;
			if (minY == -demiLenght && maxY == demiLenght) {
				xAxis.setLayoutY(height / 2);
				actualHeight = (height/2);
			} else {
				actualHeight = (height - height*(maxY/(demiLenght*2)));
				if (actualHeight > height | actualHeight < 20) {
					xAxis.setVisible(false);
				} else {
					xAxis.setLayoutY(actualHeight);
					xAxis.setVisible(true);
				}
			}
	}
	

	public NumberAxis getXAxis() {
		return xAxis;
	}

	public NumberAxis getYAxis() {
		return yAxis;
	}

	
}
