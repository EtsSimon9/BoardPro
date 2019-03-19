package composante;

public abstract class CE2Entrees extends ComposanteElectrique {
	/**
	 *  sens de la composante, true vertivale,  et false horizontale
	 */
	private static final boolean SENS_DEFAUT = true;

	boolean sens;

	public CE2Entrees(short coordonnex, short coordonney) {
		super(coordonnex, coordonney);

		this.setSens(SENS_DEFAUT);
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
