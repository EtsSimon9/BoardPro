package map;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.cycle.JohnsonSimpleCycles;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import composante.CE2Entrees;

/**
 *
 * Comme le sang rouge des castors, cette classe est deja morte. Elle permet de
 * cr�er une map parcourable
 *
 * @author HAAS TEAM
 *
 *
 */
public class MapParcourable extends Map {
	private ArrayList<ArrayList<ComposantMap>> maillesCircuitsFermes;
	private ArrayList<ComposantMap> noeudsCircuit;
	private ArrayList<Set<ComposantMap>> branchesCircuit;

	public ArrayList<ComposantMap> getNoeudsCircuit() {
		return noeudsCircuit;
	}

	public void setNoeudsCircuit(ArrayList<ComposantMap> noeudsCircuit) {
		this.noeudsCircuit = noeudsCircuit;
	}

	public ArrayList<ArrayList<ComposantMap>> getMaillesCircuitsFermes() {
		return maillesCircuitsFermes;
	}

	public ArrayList<ArrayList<ComposantMap>> getMaille() {
		genererMailles();
		return maillesCircuitsFermes;
	}

	public void setMaillesCircuitsFermes(ArrayList<ArrayList<ComposantMap>> maillesCircuitsFermes) {
		this.maillesCircuitsFermes = maillesCircuitsFermes;
	}

	public MapParcourable() {
		super();
	}

	/**
	 * 
	 * @return Les branches du circuit , chaque branche est dans l'arrayList
	 */
	public ArrayList<Set<ComposantMap>> getBranches() {
		genrerNoeuds();
		maillesCircuitsFermes = new ArrayList<ArrayList<ComposantMap>>();
		ComposantMap[] composantEnContatc = new ComposantMap[4];
		SimpleDirectedGraph<ComposantMap, DefaultEdge> graphCirucit = new SimpleDirectedGraph<>(DefaultEdge.class);
		for (ComposantMap composante : this.composantsActuels) {
			composantEnContatc = trouverComposantesEnContact(composante);
			graphCirucit.addVertex(composante);
			for (ComposantMap autre : composantEnContatc) {
				if (autre != null) {
					graphCirucit.addVertex(autre);
					graphCirucit.addEdge(autre, composante);
				}
			}
		}
		for (ComposantMap comp : noeudsCircuit) {
			if (noeudsCircuit.contains(comp))
				graphCirucit.removeVertex(comp);
		}
		branchesCircuit = new ArrayList<Set<ComposantMap>>(new ConnectivityInspector(graphCirucit).connectedSets());
		return this.branchesCircuit;
	}

	public int trouverPositionBranche(ComposantMap comp) {
		int retour = 0;
		for (int i = 0; i < branchesCircuit.size(); i++) {
			if (branchesCircuit.get(i).contains(comp)) {
				retour = i;
			}
		}
		return retour;
	}

	public void genrerNoeuds() {
		noeudsCircuit = new ArrayList<ComposantMap>();
		int nbComposantesEnContact = 0;
		ComposantMap[] composantEnContatc = new ComposantMap[4];
		for (ComposantMap composante : this.composantsActuels) {
			composantEnContatc = trouverComposantesEnContact(composante);
			for (ComposantMap autre : composantEnContatc) {
				if (autre != null) {
					nbComposantesEnContact++;
				}
			}
			if ((nbComposantesEnContact >= 3)) {
				noeudsCircuit.add(composante);
			}
			nbComposantesEnContact = 0;
		}

	}

	/**
	 * permet de g�n�rer les mailles dans maillesCircuitsFermes
	 */
	public void genererMailles() {
		maillesCircuitsFermes = new ArrayList<ArrayList<ComposantMap>>();
		ComposantMap[] composantEnContatc = new ComposantMap[4];
		SimpleDirectedGraph<ComposantMap, DefaultEdge> graphCirucit = new SimpleDirectedGraph<>(DefaultEdge.class);
		for (ComposantMap composante : this.composantsActuels) {
			composantEnContatc = trouverComposantesEnContact(composante);
			graphCirucit.addVertex(composante);
			for (ComposantMap autre : composantEnContatc) {
				if (autre != null) {
					graphCirucit.addVertex(autre);
					graphCirucit.addEdge(autre, composante);
				}
			}
		}
		JohnsonSimpleCycles<ComposantMap, DefaultEdge> cycles = new JohnsonSimpleCycles<>(graphCirucit);
		convertirAArrayList(cycles.findSimpleCycles());
	}

	/**
	 * permet de stoquer la List<List<ComposantMap>> donn�e dans
	 * l'ArrayList<ArrayList<ComposantMap>> maillesCircuitsFermes
	 *
	 * @param list
	 */
	private void convertirAArrayList(List<List<ComposantMap>> list) {
		Iterator<List<ComposantMap>> itlistMailles = list.iterator();
		while (itlistMailles.hasNext()) {
			List<ComposantMap> listMailles = itlistMailles.next();
			if (listMailles.size() > 2)
				maillesCircuitsFermes.add(new ArrayList<ComposantMap>(listMailles));
		}
	}

	/**
	 * @param composante
	 * @return Retourne le tableau des composantes en contact avec celle donn�e
	 *         (tableau a [0]: composante en haut,[1]: composante en bas,[2]:
	 *         composante à droite et à [3]: composante à gauche
	 */
	public ComposantMap[] trouverComposantesEnContact(ComposantMap composante) {
		ComposantMap[] retour = new ComposantMap[4];

		if (verifierComposante(composante)) {
			short coordx = composante.getCoordonneX();
			short coordy = composante.getCoordonneY();
			for (ComposantMap autre : this.getComposantsActuels()) {
				short coordxa = autre.getCoordonneX();
				short coordya = autre.getCoordonneY();
				if (!(composante instanceof CE2Entrees)) {
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
	 * donn�. Prends les coordonnes des deux positison (algorithme � am�liorer)
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

		if (comp instanceof CE2Entrees) {

			CE2Entrees composante = (CE2Entrees) comp;
			if (composante.isVertical()) {
				if (verifierComposanteContactHaut(coordx, coordy, coordxa, coordya))
					retour[0] = autre;
				if (verifierComposanteContactBas(coordx, coordy, coordxa, coordya))
					retour[1] = autre;
			} else {
				if (verifierComposanteContactDroite(coordx, coordy, coordxa, coordya)) {
					retour[2] = autre;
				}
				if (verifierComposanteContactGauche(coordx, coordy, coordxa, coordya)) {
					retour[3] = autre;
				}
			}
		}
	}

	/**
	 * Prends les coordonnes des composes fils et verifie si elle est en contact
	 * avec une autre composante et la met dans la bonne case du tableau donn�.
	 * Prends les coordonnes des deux positison (algorithme � am�liorer)
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

	@Override
	public String toString() {
		String retour = "";
		for (int i = 0; i < maillesCircuitsFermes.size(); i++) {
			for (int j = 0; j < maillesCircuitsFermes.get(i).size(); j++) {
				if(maillesCircuitsFermes.get(i).get(j) instanceof CE2Entrees) {
				retour +="("+trouverPositionBranche(maillesCircuitsFermes.get(i).get(j)) +maillesCircuitsFermes.get(i).get(j).toString() ;
				}
			}
			retour += "\n";
		}
		return retour;
	}

}