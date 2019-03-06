package composant;

public abstract class CEDeuxEntres_CEDE_ extends ComposantElectrique_CE_ {
	// sens de la composante, true, horizontale et false vertivale
	private static final boolean SENS_DEFAUT = true;

	boolean sens;

	public CEDeuxEntres_CEDE_(short coordonnex, short coordonney) {
		super(coordonnex, coordonney);

		this.setSens(SENS_DEFAUT);
	}

	public boolean isSens() {
		return sens;
	}

	public void setSens(boolean sens) {
		this.sens = sens;
	}

}
