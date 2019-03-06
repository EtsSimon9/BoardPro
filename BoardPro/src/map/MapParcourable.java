package map;

import java.util.ArrayList;


/**
 *
 * Comme le sang rouge des castors, cette classe est deja morte. Elle permet de créer une map parcourable
 * @author 1740969
 *
 */
public class MapParcourable extends Map {
	private ArrayList<ArrayList<ComposantMap>> maillsesCircuitsFermes = new ArrayList<ArrayList<ComposantMap>>();
	private ArrayList<ArrayList<ComposantMap>> maillesCircuitIncomopletes = new ArrayList<ArrayList<ComposantMap>>();

	public MapParcourable() {
		super();
	}

	public void genrerNoeuds() {

	}


	// pour l'instant le circuit est une seule boucle, en attente des tests des méthodes pour faire le reste
	public void genererMailles() {

	}




	/**
	 * @param composante
	 * @return Retourne le tableau des composantes en contact avec celle donnée
	 */
	private ComposantMap[] trouverComposantesEnContact(ComposantMap composante) {
		ComposantMap[] retour = new ComposantMap[4];

		if (verifierComposante(composante)) {
			short coordx = composante.getCoordonneX();
			short coordy = composante.getCoordonneY();
			for (ComposantMap autre : this.getComposantsActuels()) {
				short coordxa = autre.getCoordonneX();
				short coordya = autre.getCoordonneY();
				if (verifierComposanteContactHaut(coordx, coordy, coordxa, coordya))
					retour[0] = autre;
				if (verifierComposanteContactBas(coordx, coordy, coordxa, coordya))
					retour[1] = autre;
				if (verifierComposanteContactDroite(coordx, coordy, coordxa, coordya))
					retour[2] = autre;
				if (verifierComposanteContactGauche(coordx, coordy, coordxa, coordya))
					retour[3] = autre;
			}
		}
		return retour;

	}

	private boolean verifierComposanteContactHaut(short coordx, short coordy, short coordxa, short coordya) {
		return ((coordx == coordxa) && ((coordy + 1) == coordya));
	}

	private boolean verifierComposanteContactBas(short coordx, short coordy, short coordxa, short coordya) {
		return ((coordx == coordxa) && ((coordy - 1) == coordya));
	}

	private boolean verifierComposanteContactDroite(short coordx, short coordy, short coordxa, short coordya) {
		return (((coordx + 1) == coordxa) && (coordy == coordya));
	}

	private boolean verifierComposanteContactGauche(short coordx, short coordy, short coordxa, short coordya) {
		return (((coordx - 1) == coordxa) && (coordy == coordya));
	}

	private boolean verifierComposante(ComposantMap composante) {
		return composante != null;
	}


public static void main(String[] args) {
	System.out.println("yo");
}
}
