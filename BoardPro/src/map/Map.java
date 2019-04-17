package map;

import java.util.ArrayList;

import map.ComposantMap;
import exceptions.ComposantException;

/**
 * Cette classe permet de cr�er une aire de mise en place des composantes
 * �l�ctroniques(map) et de g�rer les comosantes qui y sont pr�sentes via une
 * liste de composantes(composantActuels). C'est une classe abstaite
 * 
 * @author HAAS Team
 *
 */
public abstract class Map {
	protected ArrayList<ComposantMap> composantsActuels = new ArrayList<ComposantMap>();

	/**
	 * Construit une map c'est � dire une zone dans laquelle mettre les composantes
	 * lorsqu'on lui donne la taille de celle-ci
	 * 
	 * @param borneX borne horizontale de la zone de contruction
	 * @param borneY borne verticale de la zone de contruction
	 * @throws ComposanteException si les bornes donn�es sont inf�rieures � z�ro
	 */
	public Map() {
	}

	/**
	 * ajoute une composante �lectrique a la liste de composantes du circuit ci
	 * celle-ci n'y es pas d�ja pr�sente. Attention, si la liste de composantes
	 * composantsActuels est tri�e, alors il faut utiliser addCompsantAvecTri
	 * 
	 * @param comp composante electrique � ajouter
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
	 * Enleve une composante de la liste des compsantes dans la map
	 * @param compo compostante à enlever de la liste de compsatnes
	 */
	public void removeComposante(byte index) {
			composantsActuels.remove(index);
	}
	

	/**
	 *  A faire pour le 3eme sprint
	 *  
	 * ajoute une composante �lectrique a la liste ordonn�e de composantes du
	 * circuit de maniere ordonn�e si celle-ci n'y es pas d�ja pr�sente
	 * 
	 * @param comp composante electrique � ajouter
	 * @throws ComposanteException
	 */
	private void addComposantAvecTri(ComposantMap comp) {

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
	 * A faire pour le 3eme sprint
	 * trie au besoins (vraiment au besoin) la liste de composantsActuels en ordre croissant de leur x^2+y^2 A
	 * 
	 */
	private void trier() {
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
	 *  A faire pour le 3eme sprint
	 *  
	 * permute 2 objects qui sont dans la liste de composantes; ceux donn�s en param�tre
	 * 
	 * @param premier
	 * @param dernier
	 */
	private void permuter(int premier, int dernier) {
		ComposantMap c1 = composantsActuels.get(premier);
		ComposantMap c2 = composantsActuels.get(dernier);
		composantsActuels.set(premier, c2);
		composantsActuels.set(dernier, c1);
	}

	/**
	 * v�rifie si une composante est dans la map
	 * 
	 * @param comp composante � chercher dans la map
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
	 * @return retourne la liste des composantes pr�sentes dans la map
	 */
	public ArrayList<ComposantMap> getComposantsActuels() {
		return composantsActuels;
	}

	/**
	 * met en place la liste des compsantes pr�sentes dans la map si celle-ci n'est
	 * pas nulle
	 * 
	 * @param composantsActuels
	 */
	public void setComposantsActuels(ArrayList<ComposantMap> composantsActuels) {
		if (composantsActuels != null)
			this.composantsActuels = composantsActuels;
	}

}

