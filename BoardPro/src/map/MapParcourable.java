package map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jgrapht.alg.cycle.JohnsonSimpleCycles;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import composantesCircuit.ComposanteElectrique;


/**
 *
 * Comme le sang rouge des castors, cette classe est deja morte. Elle permet de
 * créer une map parcourable
 *
 * @author 1740969
 *
 */
public class MapParcourable extends Map {
	private ArrayList<ArrayList<ComposantMap>> maillsesCircuitsFermes;

	public ArrayList<ArrayList<ComposantMap>> getMaillsesCircuitsFermes() {
		return maillsesCircuitsFermes;
	}

	public void setMaillsesCircuitsFermes(ArrayList<ArrayList<ComposantMap>> maillsesCircuitsFermes) {
		this.maillsesCircuitsFermes = maillsesCircuitsFermes;
	}

	public MapParcourable() {
		super();
	}

	public void genrerNoeuds() {

	}

	/**
	 * permet de générer les mailles dans maillesCircuitsFermes
	 */
	public void genererMailles() {
		maillsesCircuitsFermes = new ArrayList<ArrayList<ComposantMap>>();
		ComposantMap[] composantEnContatc = new ComposantMap[4];
		DefaultDirectedGraph<ComposantMap, DefaultEdge> graphCirucit = new DefaultDirectedGraph<>(DefaultEdge.class);
		for (ComposantMap composante : this.composantsActuels) {
			composantEnContatc = trouverComposantesEnContact(composante);
				graphCirucit.addVertex(composante);
				for (ComposantMap autre : composantEnContatc) {
					if(autre != null) {
					graphCirucit.addVertex(autre);
					graphCirucit.addEdge(autre, composante);
					}
				}
		}
	
		JohnsonSimpleCycles<ComposantMap, DefaultEdge> cycles = new JohnsonSimpleCycles<>(graphCirucit);
		convertirAArrayList(cycles.findSimpleCycles());
	}

	/**
	 * permet de stoquer la List<List<ComposantMap>> donnée dans
	 * l'ArrayList<ArrayList<ComposantMap>> maillesCircuitsFermes
	 *
	 * @param list
	 */
	private void convertirAArrayList(List<List<ComposantMap>> list) {
		ArrayList<ArrayList<ComposantMap>> maillsesCircuitsFermesFinal = new ArrayList<ArrayList<ComposantMap>>();
		Iterator<List<ComposantMap>> itlistMailles = list.iterator();
		while (itlistMailles.hasNext()) {
			List<ComposantMap> listMailles = itlistMailles.next();
			Iterator<ComposantMap> itComposantesMaille = listMailles.iterator();
			ArrayList<ComposantMap> arrayListComposanteDsMaille = new ArrayList<ComposantMap>();
			while (itComposantesMaille.hasNext()) {
				ComposantMap composante = itComposantesMaille.next();
				arrayListComposanteDsMaille.add(composante);
			}
			maillsesCircuitsFermes.add(arrayListComposanteDsMaille);
		}
		for(ArrayList<ComposantMap> array : maillsesCircuitsFermes) {
			if(array.size() > 2) {
				maillsesCircuitsFermesFinal.add(array);
			}
//			else {
//				System.out.println(maillsesCircuitsFermes.size());
//			}
		}
		this.maillsesCircuitsFermes = maillsesCircuitsFermesFinal;

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
				if (!(composante instanceof ComposanteElectrique)) {
					trouverObjetContactAvecFil(retour, autre, composante, coordx, coordy, coordxa, coordya);
				} else {
					trouverObjetContactAvecCEDeuxLiens(retour, autre, composante, coordx, coordy, coordxa, coordya);
				}
			}
		}
		return retour;

	}

	/**
	 * Prends les coordonnes des composntes de CEDeuxLiens et verifie si elle est en
	 * contact avec une autre composante et la met dans la bonne case du tableau
	 * donné. Prends les coordonnes des deux positison (algorithme à améliorer)
	 *
	 * @param retour
	 * @param autre
	 * @param comp
	 * @param coordx
	 * @param coordy
	 * @param coordxa
	 * @param coordya
	 */
	private void trouverObjetContactAvecCEDeuxLiens(ComposantMap[] retour, ComposantMap autre, ComposantMap comp,
			short coordx, short coordy, short coordxa, short coordya) {
		if (comp instanceof ComposanteElectrique) {
			ComposanteElectrique composante = (ComposanteElectrique) comp;
			if (composante.isVertical()) {
				if (verifierComposanteContactHaut(coordx, coordy, coordxa, coordya))
					retour[0] = autre;
				if (verifierComposanteContactBas(coordx, coordy, coordxa, coordya))
					retour[1] = autre;
			} else {
				if (verifierComposanteContactDroite(coordx, coordy, coordxa, coordya))
					retour[2] = autre;
				if (verifierComposanteContactGauche(coordx, coordy, coordxa, coordya))
					retour[3] = autre;
			}
		}
	}

	/**
	 * Prends les coordonnes des composes fils et verifie si elle est en contact
	 * avec une autre composante et la met dans la bonne case du tableau donné.
	 * Prends les coordonnes des deux positison (algorithme à améliorer)
	 *
	 * @param retour
	 * @param autre
	 * @param fil
	 * @param coordx
	 * @param coordy
	 * @param coordxa
	 * @param coordya
	 */
	private void trouverObjetContactAvecFil(ComposantMap[] retour, ComposantMap autre, ComposantMap fil, short coordx,
			short coordy, short coordxa, short coordya) {
		if (verifierComposanteContactHaut(coordx, coordy, coordxa, coordya))
			retour[0] = autre;
		if (verifierComposanteContactBas(coordx, coordy, coordxa, coordya))
			retour[1] = autre;
		if (verifierComposanteContactDroite(coordx, coordy, coordxa, coordya))
			retour[2] = autre;
		if (verifierComposanteContactGauche(coordx, coordy, coordxa, coordya))
			retour[3] = autre;
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

}