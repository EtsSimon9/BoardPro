package modele;

import map.Map;

/**
 * construit un r�solveur de circuit �l�ctriques avec une Map, donc une zone
 * contenant des elements �l�ctroniques
 *
 * @author HAAS Team
 *
 */
public class CalculdeCircuit {
	Map map;
	boolean circuitFerme = false;

	/**
	 * construit un r�solveur de circuit �lectrique
	 *
	 * @param prends une map contenant des element �lectroniques
	 */
	public CalculdeCircuit(Map m) {
		setMap(m);
	}

	/**
	 * @return la map utilis�e par cette classe de calcul
	 */
	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		if (map != null) {
			this.map = map;
		}
	}

	/**
	 * m�thode appliquant la loi des mailles au circuit de l'objet map
	 */
	private void appliquerLoiDesMailles() {

	}

	/**
	 * fait foncitonner le circuit et en appliquant les divers calculs, pour
	 * l'instant, fait juste appliquer la loi des mailles pour v�rifier que le
	 * circuit en s�rie est ferm�
	 */
	public void faireFonctionnerCircuit() {
		appliquerLoiDesMailles();
	}

}
