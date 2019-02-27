package map;

import java.util.ArrayList;

import modele.ComposanteElectrique;

public class Map {
	private ComposanteElectrique[][] Map;
	private short borneX;
	private short borneY;
	private ArrayList<ComposanteElectrique> composantsActuels = new ArrayList<ComposanteElectrique>();

	public Map(short borneX, short borneY) {
		setBorneX(borneX);
		setBorneY(borneY);
		setMap(new ComposanteElectrique[borneX][borneY]);
	}

	public void addComposant(ComposanteElectrique comp) {
		if (respecteBornesMap(comp) && !estDansMap(comp)) {
			Map[comp.getCoordonneX()][comp.getCoordonneY()] = comp;
			composantsActuels.add(comp);
		}
	}

	public void addComposantDerriere(ComposanteElectrique actuel, ComposanteElectrique derriere) {
		for (int i = 0; i < composantsActuels.size(); i++) {
			if (composantsActuels.get(i) == derriere) {
				composantsActuels.add(i - 1 < 0 ? composantsActuels.size() : i - 1, actuel);
				break;
			}
		}
	}

	public void addComposantDevant(ComposanteElectrique actuel, ComposanteElectrique devant) {
		for (int i = 0; i < composantsActuels.size(); i++) {
			if (composantsActuels.get(i) == devant) {
				composantsActuels.add(i + 1 < composantsActuels.size() ? i + 1 : i, actuel);
				break;
			}
		}
	}

	public void addComposantActuels(ComposanteElectrique comp, short position) {
		composantsActuels.add(position, comp);
	}

	private boolean estDansMap(ComposanteElectrique comp) {
		short coordx = comp.getCoordonneX();
		short coordy = comp.getCoordonneY();
		for (ComposanteElectrique autre : composantsActuels) {
			if (autre.getCoordonneX() == coordx && autre.getCoordonneY() == coordy) {
				return true;
			}
		}
		return false;
	}

	private boolean respecteBornesMap(ComposanteElectrique comp) {
		short coordx = comp.getCoordonneX();
		short coordy = comp.getCoordonneY();
		return coordx >= 0 && coordx <= borneX && coordy >= 0 && coordy <= borneY;
	}

	public ComposanteElectrique[][] getMap() {
		return Map;
	}

	public void setMap(ComposanteElectrique[][] map) {
		Map = map;
	}

	public short getBorneX() {
		return borneX;
	}

	public void setBorneX(short borneX) {
		this.borneX = borneX;
	}

	public short getBorneY() {
		return borneY;
	}

	public void setBorneY(short borneY) {
		this.borneY = borneY;
	}

	public ArrayList<ComposanteElectrique> getComposantsActuels() {
		return composantsActuels;
	}

	public void setComposantsActuels(ArrayList<ComposanteElectrique> composantsActuels) {
		this.composantsActuels = composantsActuels;
	}

}
