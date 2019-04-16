package utilitaire;


/**
 * Classe utilitaires pour la gestion des matrices carrées
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

	

	
}
