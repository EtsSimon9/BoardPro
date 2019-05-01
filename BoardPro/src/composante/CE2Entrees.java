package composante;

import utilitaire.Images;

public abstract class CE2Entrees extends ComposanteElectrique {
	/**
	 *  sens de la composante, true vertivale,  et false horizontale
	 */
	private static final boolean SENS_HORIZONTAL_DEFAUT = false;

	boolean sens;

	public CE2Entrees(short coordonnex, short coordonney,Images i) {
		super(coordonnex, coordonney, i);
		this.setSens(SENS_HORIZONTAL_DEFAUT);
	}
	
	public CE2Entrees(float r,short coordonnex, short coordonney,Images i) {
		super(r,coordonnex, coordonney, i);
		this.setSens(SENS_HORIZONTAL_DEFAUT);
	}

	public boolean isVertical() {
		return sens;
	}
/**
 * 
 * @param sens : true, vertical et false horizontal
 */
	public void setSens(boolean sens) {
		this.sens = sens;
	}

}
