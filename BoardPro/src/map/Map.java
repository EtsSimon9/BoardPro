package map;

import java.util.ArrayList;

import exception.ComposanteException;
import modele.ComposanteElectrique;

/**
 * Cette classe permet de créer une aire de mise en place des composantes
 * éléctroniques(map) et de gérer les comosantes qui y sont présentes via une
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
	 * Construit une map c'est à dire une zone dans laquelle mettre les composantes
	 * lorsqu'on lui donne la taille de celle-ci
	 * 
	 * @param borneX borne horizontale de la zone de contruction
	 * @param borneY borne verticale de la zone de contruction
	 * @throws ComposanteException si les bornes données sont inférieures à zéro
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
	 * ajoute une composante électrique a la liste de composantes du circuit ci
	 * celle-ci n'y es pas dèja présente
	 * 
	 * @param comp composante electrique à ajouter
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
	 * ajoute une composante électrique a la liste de composantes du circuit
	 * derriere une autre comoposante donnée
	 * 
	 * @param actuel   composante actuelle à placer
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
	 * ajoute une composante électrique a la liste de composantes du circuit devant
	 * une autre comoposante donnée
	 * 
	 * @param actuel composante actuelle à placer
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
	 * ajoute une composante électrique a la liste de composantes du circuit selon
	 * la position de placement demandée
	 * 
	 * @param comp     composante à placer
	 * @param position position à laquelle la placer dans l'Arraylist
	 */
	public void addComposantActuels(ComposanteElectrique comp, short position) {
		composantsActuels.add(position, comp);
	}

	/**
	 * vérifie si une composante est dans la map
	 * 
	 * @param comp composante à chercher dans la map
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
	 * @param comp composante sur laquelle est faite la vérification
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
	 * @return retourne la liste des composantes présentes dans la map
	 */
	public ArrayList<ComposanteElectrique> getComposantsActuels() {
		return composantsActuels;
	}

	/**
	 * met en place la liste des compsantes présentes dans la map si celle-ci n'est
	 * pas nulle
	 * 
	 * @param composantsActuels
	 */
	public void setComposantsActuels(ArrayList<ComposanteElectrique> composantsActuels) {
		if (composantsActuels != null)
			this.composantsActuels = composantsActuels;
	}

	/**
	 * vérifie que la bordure de la map est poisitive
	 * 
	 * @param b bordurex ou y de la map à vérifier
	 * @return true si celle-ci est positive
	 */
	public boolean validerBorne(short b) {
		return b >= 0;
	}

}
