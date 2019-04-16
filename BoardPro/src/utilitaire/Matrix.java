package utilitaire;

import org.ejml.data.SingularMatrixException;
import org.ejml.simple.SimpleMatrix;

/**
 * Framework static permettant de resoudre des systemes d'equations avec n
 * inconnues et m equations a l'aide de matrices. Utilise la librairie EJML :
 * https://ejml.org/wiki/index.php?title=Main_Page
 * 
 * @author Alexandre
 */
public class Matrix {

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
}
