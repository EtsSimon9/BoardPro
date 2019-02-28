package map;

import java.util.ArrayList;

import exception.ComposanteException;
import modele.ComposanteElectrique;

/**
 * Cette classe permet de cr�er une aire de mise en place des composantes
 * �l�ctroniques(map) et de g�rer les comosantes qui y sont pr�sentes via une
 * liste de composantes(composantActuels)
 * 
 * @author HAAS Team
 *
 */
public class Map {
	private ComposanteElectrique[][] Map;
	private short borneX;
	private short borneY;
	private ArrayList<ComposanteElectrique> composantsActuels = new ArrayList<ComposanteElectrique>();

	/**
	 * Construit une map c'est � dire une zone dans laquelle mettre les composantes
	 * lorsqu'on lui donne la taille de celle-ci
	 * 
	 * @param borneX borne horizontale de la zone de contruction
	 * @param borneY borne verticale de la zone de contruction
	 * @throws ComposanteException si les bornes donn�es sont inf�rieures � z�ro
	 */
	public Map(short borneX, short borneY) throws ComposanteException {
		if (validerBorne(borneY) && validerBorne(borneX)) {
			this.borneX = borneX;
			this.borneY = borneY;
			setMap(new ComposanteElectrique[borneX][borneY]);
		} else {
			throw new ComposanteException();
		}
	}

	/**
	 * ajoute une composante �lectrique a la liste de composantes du circuit ci
	 * celle-ci n'y es pas d�ja pr�sente
	 * 
	 * @param comp composante electrique � ajouter
	 * @throws ComposanteException
	 */
	public void addComposant(ComposanteElectrique comp) throws ComposanteException {
		if (respecteBornesMap(comp) && !estDansMap(comp)) {
			Map[comp.getCoordonneX()][comp.getCoordonneY()] = comp;
			composantsActuels.add(comp);
		} else {
			throw new ComposanteException();
		}
	}

	/**
	 * ajoute une composante �lectrique a la liste de composantes du circuit
	 * derriere une autre comoposante donn�e
	 * 
	 * @param actuel   composante actuelle � placer
	 * @param derriere composante derriere laquelle la placer
	 */
	public void addComposantDerriere(ComposanteElectrique actuel, ComposanteElectrique derriere) {
		for (int i = 0; i < composantsActuels.size(); i++) {
			if (composantsActuels.get(i) == derriere) {
				composantsActuels.add(i - 1 < 0 ? 0 : i - 1, actuel);
				break;
			}
		}
	}

	/**
	 * ajoute une composante �lectrique a la liste de composantes du circuit devant
	 * une autre comoposante donn�e
	 * 
	 * @param actuel composante actuelle � placer
	 * @param devant composante devant laquelle la placer
	 */
	public void addComposantDevant(ComposanteElectrique actuel, ComposanteElectrique devant) {
		for (int i = 0; i < composantsActuels.size(); i++) {
			if (composantsActuels.get(i) == devant) {
				composantsActuels.add(i + 1 >= composantsActuels.size() ? i : i + 1, actuel);
				break;
			}
		}
	}

	/**
	 * ajoute une composante �lectrique a la liste de composantes du circuit selon
	 * la position de placement demand�e
	 * 
	 * @param comp     composante � placer
	 * @param position position � laquelle la placer dans l'Arraylist
	 */
	public void addComposantActuels(ComposanteElectrique comp, short position) {
		composantsActuels.add(position, comp);
	}

	/**
	 * v�rifie si une composante est dans la map
	 * 
	 * @param comp composante � chercher dans la map
	 * @return true si elle y est,sinon false
	 */
	public boolean estDansMap(ComposanteElectrique comp) {
		short coordx = comp.getCoordonneX();
		short coordy = comp.getCoordonneY();
		for (ComposanteElectrique autre : composantsActuels) {
			if (autre.getCoordonneX() == coordx && autre.getCoordonneY() == coordy) {
				return true;
			}
		}
		return false;
	}

	/**
	 * regarde si la composante respecte la bordure de la map
	 * 
	 * @param comp composante sur laquelle est faite la v�rification
	 * @return si elle respecte la bordure de la map sinon, non
	 */
	private boolean respecteBornesMap(ComposanteElectrique comp) {
		short coordx = comp.getCoordonneX();
		short coordy = comp.getCoordonneY();
		return coordx >= 0 && coordx <= borneX && coordy >= 0 && coordy <= borneY;
	}

	/**
	 * 
	 * @return la Map donc la zone dans laquelle se trouvent les composantes
	 */
	public ComposanteElectrique[][] getMap() {
		return Map;
	}

	/**
	 * met une aire de construciton si celle-ci n'est pas nulle
	 * 
	 * @param map
	 */
	public void setMap(ComposanteElectrique[][] map) {
		if (map != null) {
			Map = map;
		}
	}

	public short getBorneX() {
		return borneX;
	}

	public short getBorneY() {
		return borneY;
	}

	/**
	 * 
	 * @return retourne la liste des composantes pr�sentes dans la map
	 */
	public ArrayList<ComposanteElectrique> getComposantsActuels() {
		return composantsActuels;
	}

	/**
	 * met en place la liste des compsantes pr�sentes dans la map si celle-ci n'est
	 * pas nulle
	 * 
	 * @param composantsActuels
	 */
	public void setComposantsActuels(ArrayList<ComposanteElectrique> composantsActuels) {
		if (composantsActuels != null)
			this.composantsActuels = composantsActuels;
	}

	/**
	 * v�rifie que la bordure de la map est poisitive
	 * 
	 * @param b bordurex ou y de la map � v�rifier
	 * @return true si celle-ci est positive
	 */
	public boolean validerBorne(short b) {
		return b >= 0;
	}

}
