package modele;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import exception.ComposanteException;
import map.Map;

/**
 * construit un résolveur de circuit éléctriques avec une Map, donc une zone
 * contenant des elements éléctroniques
 *
 * @author HAAS Team
 *
 */
public class CalculdeCircuit {
	Map map = new Map((short)30,(short)30);
	boolean circuitFerme = false;
	ArrayList<ComposanteElectrique> composantsActuels;

	public boolean isCircuitFerme() {
		return circuitFerme;
	}


	/**
	 * construit un résolveur de circuit électrique
	 *
	 * @param prends une map contenant des element électroniques
	 * @throws ComposanteException 
	 */
	public CalculdeCircuit(Map m) throws ComposanteException {
		if(map!= null) {
		setMap(m);
		composantsActuels = m.getComposantsActuels();
		appliquerLoiDesMailles();
		}else {
			throw new ComposanteException();
		}
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
		int i = 0;
		if(composantsActuels!=null&& composantsActuels.size()>1) {
			for (ComposanteElectrique composant : composantsActuels) {
				if(map.estDansMap(composant)) {
					i++;
				}
			}
		}
			circuitFerme=(i==composantsActuels.size());

	}

	/**
	 * fait foncitonner le circuit et en appliquant les divers calculs, pour
	 * l'instant, fait juste appliquer la loi des mailles pour vérifier que le
	 * circuit en série est fermé
	 */
	private void faireFonctionnerCircuit() {
		appliquerLoiDesMailles();
	}
	
	

}
