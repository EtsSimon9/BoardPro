package map;

import java.util.ArrayList;

import map.ComposantMap;
import exceptions.ComposantException;

/**
 * Cette classe permet de créer une aire de mise en place des composantes
 * éléctroniques(map) et de gérer les comosantes qui y sont présentes via une
 * liste de composantes(composantActuels). C'est une classe abstaite
 *
 * @author HAAS Team
 *
 */
public abstract class Map {
	protected ArrayList<ComposantMap> composantsActuels = new ArrayList<ComposantMap>();

	/**
	 * Construit une map c'est à dire une zone dans laquelle mettre les composantes
	 * lorsqu'on lui donne la taille de celle-ci
	 *
	 * @param borneX borne horizontale de la zone de contruction
	 * @param borneY borne verticale de la zone de contruction
	 * @throws ComposanteException si les bornes données sont inférieures à zéro
	 */
	public Map() {
	}

	/**
	 * ajoute une composante électrique a la liste de composantes du circuit ci
	 * celle-ci n'y es pas dèja présente. Attention, si la liste de composantes
	 * composantsActuels est triée, alors il faut utiliser addCompsantAvecTri
	 *
	 * @param comp composante electrique à ajouter
	 * @throws ComposanteException
	 */
	public void addComposant(ComposantMap comp) throws ComposantException {
		if (!estDansMap(comp)) {
			composantsActuels.add(comp);
		} else {
			throw new ComposantException();
		}
	}

	/**
	 * ajoute une composante électrique a la liste ordonnée de composantes du
	 * circuit de maniere ordonnée si celle-ci n'y es pas dèja présente
	 *
	 * @param comp composante electrique à ajouter
	 * @throws ComposanteException
	 */
	public void addComposantAvecTri(ComposantMap comp) {

		for (int i = 0; i < composantsActuels.size()
				&& !(comp.getCoordonneX() == composantsActuels.get(i).getCoordonneX()
						&& comp.getCoordonneY() == composantsActuels.get(i).getCoordonneY()); i++) {
			if ((i > 0) && (composantsActuels.get(i).compareTo(comp) > 0)) {
				composantsActuels.set(i, comp);
			} else {
				composantsActuels.add(comp);
			}
		}

	}

	/**
	 * trie la liste de composantsActuels en ordre croissant de leur x^2+y^2
	 */
	public void trier() {
		int compteur = composantsActuels.size();
		int indicateurDeSortie = 0;
		while (compteur > 0 && indicateurDeSortie != (composantsActuels.size() - 1)) {
			indicateurDeSortie = 0;
			for (int i = 0, j = i + 1; j < composantsActuels.size() && i < composantsActuels.size() - 1; i++, j++) {
				if (composantsActuels.get(j).compareTo(composantsActuels.get(i)) > 0) {
					permuter(i, j);
					indicateurDeSortie = 0;
				} else {
					indicateurDeSortie++;
				}
			}
			compteur--;
		}
	}

	/**
	 * permute 2 objects qui sont dans la liste de composantes; ceux donnés en paramètre
	 *
	 * @param premier
	 * @param dernier
	 */
	public void permuter(int premier, int dernier) {
		ComposantMap c1 = composantsActuels.get(premier);
		ComposantMap c2 = composantsActuels.get(dernier);
		composantsActuels.set(premier, c2);
		composantsActuels.set(dernier, c1);
	}

	/**
	 * vérifie si une composante est dans la map
	 *
	 * @param comp composante à chercher dans la map
	 * @return true si elle y est,sinon false
	 */
	public boolean estDansMap(ComposantMap comp) {
		boolean retour = false;
		short coordx = comp.getCoordonneX();
		short coordy = comp.getCoordonneY();
		for (ComposantMap autre : composantsActuels) {
			if (autre.getCoordonneX() == coordx && autre.getCoordonneY() == coordy) {
				retour = true;
			}
		}
		return retour;
	}

	/**
	 *
	 * @return retourne la liste des composantes présentes dans la map
	 */
	public ArrayList<ComposantMap> getComposantsActuels() {
		return composantsActuels;
	}

	/**
	 * met en place la liste des compsantes présentes dans la map si celle-ci n'est
	 * pas nulle
	 *
	 * @param composantsActuels
	 */
	public void setComposantsActuels(ArrayList<ComposantMap> composantsActuels) {
		if (composantsActuels != null)
			this.composantsActuels = composantsActuels;
	}

}