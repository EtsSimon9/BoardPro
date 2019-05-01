package utilitaire;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.ejml.data.SingularMatrixException;
import org.ejml.simple.SimpleMatrix;

import composantesCircuit.Fil;
import composantesCircuit.Resistance;
import exceptions.ComposantException;
import map.ComposantMap;
import map.MapParcourable;

/**
 * Classe utilitaires pour la gestion des matrices carrées
 *  + Framework static permettant de resoudre des systemes d'equations avec n
 * inconnues et m equations a l'aide de matrices. Utilise la librairie EJML :
 * https://ejml.org/wiki/index.php?title=Main_Page
 *
 * @author  Hany Derriche et Felix-Antoine Lamoureux
 */
public class MatriceUtilitaires {

	/**
	 * Permet de produire une chaîne de caractères pour l'affichage d'une matrice N
	 * X M dans la console. Très utile pour faire des tests.
	 *
	 * <pre>
	 *  Exemple d'affichage voulu:
	 *
	 *  [6, 1, -5]
	 *  [-2, -5, 4]
	 *  [-3, 3, -1]
	 * </pre> 
	 *
	 * @param mat
	 *            la matrice N X M à afficher
	 *
	 * @return la chaîne de caractères
	 */
	public static String toStringMat(int[][] mat) {
		String retour = "";
		if (mat != null && (mat.length != 0 && mat[0].length != 0)) {

			for (int i = 0; i < mat.length; i++) {
				retour += "[";
				for (int j = 0; j < mat[i].length; j++) {
					retour += mat[i][j];
					if (j != mat[i].length - 1) {
						retour += ", ";
					}
				}
				retour += "]" + "\n";

			}
		}
		return retour;

	}

	/**
	 * Retourne la matrice carrée M X N transposée à partir d'une matrice carrée N X
	 * M reçue.
	 *
	 * La matrice transposée d'une matrice est obtenue en échangeant les lignes et
	 * les colonnes.
	 *
	 * @param mat
	 *            la matrice d'origine
	 *
	 * @return la matrice carrée M X N transposée
	 */
	public static int[][] getMatTranspose(int[][] mat) {
		int[][] retour = null;
		if (mat != null) {
			retour = new int[mat[1].length][mat.length];

			for (int i = 0; i < retour.length; i++) {
				for (int j = 0; j < retour[i].length; j++) {
					retour[i][j] = mat[j][i];
				}
			}
		}
		return retour;
	}

	/**
	 * Retourne la matrice carrée (N-1) X (N-1) mineure d'une matrice carrée N X N
	 * reçue.
	 *
	 * Est la matrice carrée résultante, lorsque l'on supprime toutes les valeurs de
	 * la ligne et de la colonne reçues, à partir la matrice d'origine reçue.
	 *
	 * @param mat
	 *            la matrice d'origine
	 * @param ligne
	 *            la ligne de valeurs à supprimer
	 * @param col
	 *            la colonne de valeurs à supprimer
	 *
	 * @return la matrice carrée (N-1) X (N-1) mineure résultante
	 *
	 */
	private static int[][] getMatMineur(int[][] mat, int ligne, int col) {
		int[][] retour = null;
		if (mat.length >= 2 && mat[0].length >= 2) {
			retour = new int[mat[1].length - 1][mat.length - 1];
			int valref1;
			int valref2;
			for (int i = 0; i < retour.length; i++) {
				valref1 = i;
				if (i >= ligne) {
					valref1 = i + 1;
				}
				for (int j = 0; j < retour[i].length; j++) {
					valref2 = j;
					if (j >= col) {
						valref2 = j + 1;
					}
					retour[i][j] = mat[valref1][valref2];
				}
			}
		}
		return retour;
	}

	/**
	 * Retourne une matrice N X M résultante de la multiplication d'un scalaire reçu
	 * avec chaque membre d'une matrice N X M reçue
	 *
	 * @param mat
	 *            la matrice d'origine
	 * @param scalaire
	 *            le scalaire
	 *
	 * @return la matrice résultante de la multiplication avec un scalaire
	 */
	public static int[][] getMatMultScalaire(int[][] mat, double scalaire) {
		int[][] retour = null;
		if (mat != null) {
			retour = new int[mat[1].length][mat.length];

			for (int i = 0; i < retour.length; i++) {
				for (int j = 0; j < retour[i].length; j++) {
					retour[i][j] = (int) (mat[i][j] * scalaire);
				}
			}
		}
		return retour;
	}

	/**
	 * Retourne une matrice N X M résultante de l'application d'un modulo reçu sur
	 * chaque membre d'une matrice N X M reçue
	 *
	 * @param mat
	 *            la matrice d'origine
	 * @param mod
	 *            le modulo à appliquer
	 *
	 * @return la matrice résultante de l'application d'un modulo
	 */
	public static int[][] getMatModuloX(int[][] mat, int mod) {
		int[][] retour = null;
		if (mat != null) {
			retour = new int[mat[1].length][mat.length];

			for (int i = 0; i < retour.length; i++) {
				for (int j = 0; j < retour[i].length; j++) {
					retour[i][j] = (mat[i][j]% mod);
				}
			}
		}
		return retour;
	}

	/**
	 * Calcule le déterminant d'une matrice carrée de N X N.
	 *
	 * ATTENTION ; Il existe plusieurs façons de calculer un déterminant, il faut
	 * faire de la recherche.
	 *
	 * Devrait être une méthode récursive...
	 *
	 * @param mat
	 *            la matrice carrée pour les calculs.
	 *
	 * @return le déterminant de la matrice.
	 */
	public static int getDeterminant(int[][] mat) {
		int retour = 0;
		if (mat != null) {
			if (mat.length == 1 && mat[0].length == 1) {
				retour = mat[0][0];
			} else if (mat.length == 2 && mat[1].length == 2) {
				retour = mat[0][0] * mat[1][1] - mat[1][0] * mat[0][1];
			} else {
				for (int i = 0; i < mat.length; i++) {
					retour += (int) (mat[0][i] * Math.pow((-1), (i + 2)) * getDeterminant(getMatMineur(mat, 0, i)));
				}
			}
		}
		return retour;
	}

	/**
	 * Retourne la matrice carrée des cofacteurs N X N d'une matrice carrée N X N
	 * reçue, utile pour déterminer la matrice adjointe.
	 *
	 * @param mat
	 *            la matrice d'origine
	 *
	 * @return la matrice carrée des cofacteurs de la matrice d'origine
	 */
	public static int[][] getMatCofacteurs(int[][] mat) {
		int[][] retour = null;
		if (mat != null) {
			retour = new int[mat[1].length][mat.length];
			for (int i = 0; i < mat.length; i++) {
				for (int j = 0; j < mat[i].length; j++) {
					retour[i][j] = (int) (Math.pow((-1), (i + j + 2)) * getDeterminant(getMatMineur(mat, i, j)));
				}
			}
		}
		return retour;

	}

	/**
	 * Retourne la matrice N X N adjointe à partir d'une matrice N X N reçue.
	 *
	 * Est la matrice transposée des cofacteurs de la matrice d'origine.
	 *
	 * @param mat
	 *            la matrice d'origine
	 *
	 * @return la matrice carrée N X N adjointe
	 */
	public static int[][] getMatAdjointe(int[][] mat) {
		int[][] retour = getMatTranspose(getMatCofacteurs(mat));

		return retour;

	}
	
	/**
	 * Permet de resoudre un systeme d'equation en utilisant les matrices. Utilise
	 * l'algorithme le plus efficace en fonction des matrices fournies en param
	 * 
	 * @param equations, la matrice contenant chaque equation, chaque ligne est une
	 *        equation, chaque colonnes represente la meme variable. Un tableau
	 *        double de float
	 * @param resultats, la matrice avec une seule colonnes, ou chaque ligne
	 *        represente le resultat de l'equation correspondante. Un tableau double
	 *        de float
	 * @return Retourne un tableau simple de float. Chaque case du tableau de float
	 *         contient le resultat des variables. Ex : [i1] [i2] [i3] etc... Le
	 *         tableau a le meme nombre de case que la matrice equation a de
	 *         colonnes en param
	 */
	public static float[] resolveEquation(float[][] equations, float[][] resultats) {
		SimpleMatrix equation = new SimpleMatrix(equations);
		equation.print();
		SimpleMatrix resultat = new SimpleMatrix(resultats);
		equation.print();
		SimpleMatrix solver;
		int columns = equation.numCols();
		float[] retour = new float[columns];

		if ((resultat.numCols() == 1) && (equation.numRows() == resultat.numRows())) {
			try {
				solver = equation.solve(resultat);
				solver.print();
				for (int i = 0; i < retour.length; i++) {
					retour[i] = (float) solver.get((i), 0);
				}
			} catch (SingularMatrixException e) {
				throw new IllegalArgumentException("Singular matrix");
			}
		} else {
			System.out.println("Les tableaux en param ne sont pas good... Fix it");
		}
		return retour;
	}

	public static ArrayList<ArrayList<ComposantMap>> generEquations(MapParcourable map){
		map.genererMailles();
		map.genrerNoeuds();
		 ArrayList<ArrayList<ComposantMap>> maillesCircuitsFermes = map.getMaille();
		 ArrayList<ComposantMap> noeudsCircuit= map.getNoeudsCircuit();
		 ArrayList<ArrayList<ComposantMap>> branchesCircuits = new ArrayList<ArrayList<ComposantMap>>();
		 ArrayList<ComposantMap> branche = new ArrayList<ComposantMap>();
		 boolean newBranche = true;
			for (ArrayList<ComposantMap> array : maillesCircuitsFermes) {
				if(newBranche) {
					branche = new ArrayList<ComposantMap>();
					newBranche = false;
				}
				for(int i = 0;i<array.size();i++) {
					if(!noeudsCircuit.contains(array.get(i))) {
						branche.add(array.get(i));
					}else {
						branchesCircuits.add(new ArrayList<>(branche));
						newBranche = true;
					}
				}
				
				
			}		
		return branchesCircuits;
	}

//	public static void main(String[] args) {
//		MapParcourable map1, map2, map3,map4;
//		Resistance res1, res2, res3, res4, res5;
//		map1 = new MapParcourable();
//		map2 = new MapParcourable();
//		map3 = new MapParcourable();
//		map4 = new MapParcourable();
//		try {
//			res1 = new Resistance(0,(short) 0, (short) 0,null);
//			res2 = new Resistance(0,(short) 0, (short) 1,null);
//			res3 = new Resistance(0,(short) 1, (short) 0,null);
//			res4 = new Resistance(0,(short) 1, (short) 3,null);
//			res5 = new Resistance(0,(short) 3, (short) 1,null);
//		} catch (ComposantException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Fil fil1, fil3, fil5, fil7;
//		Resistance res6, res8;
//		
//		map1.genererMailles();
//		ArrayList<ComposantMap> composantsActuels = map1.getComposantsActuels();
//		ArrayList<ArrayList<ComposantMap>> maillsesCircuitsFermes = map1.getMaillesCircuitsFermes();
//
//		for (int i = 0; i < maillsesCircuitsFermes.size(); i++) {
//			for (ComposantMap comp : maillsesCircuitsFermes.get(i)) {
//				assertTrue(composantsActuels.contains(comp));
//			}
//			assertTrue(maillsesCircuitsFermes.get(i).size() == 8);
//		}
//
//		Fil fil10, fil12;
//		Resistance res9, res11, res13;
//		try {
//			// voici un circtuit de forme
//			//
//			// o-o-o
//			// | | |
//			// o-o-o
//			// les {-,|} sont des resistances et {o} representent des composantes
//
//			fil1 = new Fil((short) 0, (short) 0,null);
//			res2 = new Resistance(0,(short) 0, (short) 1,null);
//			fil3 = new Fil((short) 0, (short) 2,null);
//			res4 = new Resistance(0,(short) 1, (short) 2,null);
//			res4.setSens(false);
//			fil5 = new Fil((short) 2, (short) 2,null);
//			res6 = new Resistance(0,(short) 2, (short) 1,null);
//			fil7 = new Fil((short) 2, (short) 0,null);
//			res8 = new Resistance(0,(short) 1, (short) 0,null);
//			res8.setSens(false);
//			res9 = new Resistance(0,(short) 0, (short) 3,null);
//			fil10 = new Fil((short) 0, (short) 4,null);
//			res11 = new Resistance(0,(short) 1, (short) 4,null);
//			res11.setSens(false);
//			fil12 = new Fil((short) 2, (short) 4,null);
//			res13 = new Resistance(0,(short) 2, (short) 3,null);
//
//			map2.addComposant(fil1);
//			map2.addComposant(res2);
//			map2.addComposant(fil3);
//			map2.addComposant(res4);
//			map2.addComposant(fil5);
//			map2.addComposant(res6);
//			map2.addComposant(fil7);
//			map2.addComposant(res8);
//			map2.addComposant(res9);
//			map2.addComposant(fil10);
//			map2.addComposant(res11);
//			map2.addComposant(fil12);
//			map2.addComposant(res13);
//
//		} catch (ComposantException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		map2.genererMailles();
//		System.out.println(map2.getMaillesCircuitsFermes().size());
//		// verifie qu il y a 6 mailles produites
//		ArrayList<ArrayList<ComposantMap>> truc = generEquations(map2);
//		for(ArrayList<ComposantMap> array:truc) {
//			System.out.println(array.size());
//		}
//System.out.println( truc.size());
//	}
}
