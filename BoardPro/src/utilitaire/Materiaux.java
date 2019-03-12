package utilitaire;

/**
 *
 * @author Simon Beaulieu La résistivité est exposant 10^-8 et le coefficient
 *         thermique exposant 10^-3 Les méthodes get s'occupent de ces
 *         multiplications
 */
public enum Materiaux {

	ALUMINIUM(2.65, 3.9, "Aluminium"), ARGENT(1.59, 3.8, "Argent"), CUIVRE(1.68, 3.9, "Cuivre"), FER(8.57, 5, "Fer"),
	LAITON(6.08, 2, "Laiton"), NICHROME(108, 0.4, "Nichrome"), NICKEL(6.93, 5.9, "Nickel"), OR(2.21, 3.4, "Or"),
	PLATINE(10.5, 3.92, "Platine"), PLOMB(19.2, 4.3, "Plomb"), TUNGSTENE(5.28, 4.5, "Tungstène");

	private double resistivite;
	private double coefThermique;
	private String nom;

	/**
	 *
	 * @param p   résitivité du matériaux
	 * @param a   coefficient thermique du matériaux
	 * @param nom nom du matériaux
	 */
	private Materiaux(double p, double a, String nom) {
		this.setResistivite(p);
		this.setCoefThermique(a);
		this.setNom(nom);
	}

	public double getResistivite() {
		return resistivite * Math.pow(10, -8);
	}

	private void setResistivite(double resistivite) {
		this.resistivite = resistivite;
	}

	public double getCoefThermique() {
		return coefThermique * Math.pow(10, -3);
	}

	private void setCoefThermique(double coefThermique) {
		this.coefThermique = coefThermique;
	}

	public String getNom() {
		return nom;
	}

	private void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Simplement pour les tests jUnit
	 */
	@Override
	public String toString() {
		String s = "";
		s += "Le matériaux est " + this.getNom() + ", sa résistivité est de " + this.getResistivite()
				+ " et son coefficient thermique est de " + this.getCoefThermique() + ".";

		return s;
	}

}