package modele;

import map.Map;

/**
 * construit un résolveur de circuit éléctriques avec une Map, donc une zone
 * contenant des elements éléctroniques
 *
 * @author HAAS Team
 *
 */
public class CalculdeCircuit {
	Map map;
	boolean circuitFerme = false;

	/**
	 * construit un résolveur de circuit électrique
	 *
	 * @param prends une map contenant des element électroniques
	 */
	public CalculdeCircuit(Map m) {
		setMap(m);
	}

	/**
	 * @return la map utilisée par cette classe de calcul
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
	 * méthode appliquant la loi des mailles au circuit de l'objet map
	 */
	private void appliquerLoiDesMailles() {

	}

	/**
	 * fait foncitonner le circuit et en appliquant les divers calculs, pour
	 * l'instant, fait juste appliquer la loi des mailles pour vérifier que le
	 * circuit en série est fermé
	 */
	public void faireFonctionnerCircuit() {
		appliquerLoiDesMailles();
	}

}
