package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import utilitaire.MatriceUtilitaires;

public class MatriceUtilitairesTest {
	int[][] mat4;
	int[][] mat1 = { { 1, 3, 7 }, { 2, 4, 8 }, { 5, 0, 6 } };
	int[][] mat2 = { { 1, 2, 1, 0 }, { 0, 3, 1, 1 }, { -1, 0, 3, 1 }, { 3, 1, 2, 0 } };
	int[][] mat3 = { { 1, 2 }, { 2, -2 } };
	int[][] mat7 = { { 7 } };

	@Test
	public void testToStringMat() {

		int[][] mat6 = {};
		assertTrue(MatriceUtilitaires.toStringMat(mat4).isEmpty());
		assertTrue(MatriceUtilitaires.toStringMat(mat6).isEmpty());
		assertTrue(MatriceUtilitaires.toStringMat(null).isEmpty());
	}

	@Test
	public void testGetMatTranspose() {
		int[][] mat13 = { { 1, 3 }, { 2, -2 } };
		int[][] mat14 = { { 1, 2 }, { 3, -2 } };
		int[][] mat5 = { { 1, 2, 5 }, { 3, 4, 0 }, { 7, 8, 6 } };
		assertTrue((MatriceUtilitaires.toStringMat(MatriceUtilitaires.getMatTranspose(mat13)))
				.equalsIgnoreCase(MatriceUtilitaires.toStringMat(mat14)));
		assertTrue((MatriceUtilitaires.toStringMat(MatriceUtilitaires.getMatTranspose(mat1)))
				.equalsIgnoreCase(MatriceUtilitaires.toStringMat(mat5)));
		assertTrue(MatriceUtilitaires.toStringMat(MatriceUtilitaires.getMatTranspose(null)) == "");
	}

	@Test
	public void testGetMatMultScalaire() {
		int[][] mat13 = { { 2, 4 }, { 4, -4 } };
		int[][] mat5 = { { 2, 6, 14 }, { 4, 8, 16 }, { 10, 0, 12 } };
		assertTrue(MatriceUtilitaires.toStringMat(MatriceUtilitaires.getMatMultScalaire(mat1, 2))
				.equalsIgnoreCase(MatriceUtilitaires.toStringMat(mat5)));
		assertTrue(MatriceUtilitaires.toStringMat(MatriceUtilitaires.getMatMultScalaire(mat3, 2))
				.equalsIgnoreCase(MatriceUtilitaires.toStringMat(mat13)));
		assertTrue(MatriceUtilitaires.toStringMat(MatriceUtilitaires.getMatMultScalaire(null, 2)) == "");
	}

	@Test
	public void testGetMatModuloX() {
		int[][] mat13 = { { 1, 0 }, { 0, 0 } };
		int[][] mat5 = { { 1, 1, 1 }, { 0, 0, 0 }, { 1, 0, 0 } };
		assertTrue(MatriceUtilitaires.toStringMat(MatriceUtilitaires.getMatModuloX(mat1, 2))
				.equalsIgnoreCase(MatriceUtilitaires.toStringMat(mat5)));
		assertTrue(MatriceUtilitaires.toStringMat(MatriceUtilitaires.getMatModuloX(mat3, 2))
				.equalsIgnoreCase(MatriceUtilitaires.toStringMat(mat13)));
		assertTrue(MatriceUtilitaires.toStringMat(MatriceUtilitaires.getMatModuloX(null, 2)) == "");

	}

	@Test
	public void testGetDeterminant() {
		assertTrue(MatriceUtilitaires.getDeterminant(mat2) == 16);
		assertTrue(MatriceUtilitaires.getDeterminant(mat3) == -6);
		assertTrue(MatriceUtilitaires.getDeterminant(mat7) == 7);

	}

	@Test
	public void testGetMatCofacteurs() {
		int[][] mat5 = { { 1, 2, 5 }, { 3, 4, 0 }, { 7, 8, 6 } };
		int[][] mat5Cofacteur = { { 24, -18, -4 }, { 28, -29, 6 }, { -20, 15, -2 } };
		assertTrue((MatriceUtilitaires.toStringMat(MatriceUtilitaires.getMatCofacteurs(mat5)))
				.equalsIgnoreCase(MatriceUtilitaires.toStringMat(mat5Cofacteur)));
		assertTrue(MatriceUtilitaires.toStringMat(MatriceUtilitaires.getMatCofacteurs(null)) == "");

	}

	@Test
	public void testGetMatAdjointe() {
		int[][] mat5 = { { 24, -18, -4 }, { 28, -29, 6 }, { -20, 15, -2 } };
		assertTrue(MatriceUtilitaires.toStringMat(MatriceUtilitaires.getMatAdjointe(mat1))
				.equalsIgnoreCase(MatriceUtilitaires.toStringMat(mat5)));
		assertTrue(MatriceUtilitaires.toStringMat(MatriceUtilitaires.getMatAdjointe(null)) == "");


	}

}
