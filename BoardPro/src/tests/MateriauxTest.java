package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import utilitaire.Materiaux;

public class MateriauxTest {

	Materiaux m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11;

	@Before
	public void creerMateriaux() {
		m1 = Materiaux.ALUMINIUM;
		m2 = Materiaux.ARGENT;
		m3 = Materiaux.CUIVRE;
		m4 = Materiaux.FER;
		m5 = Materiaux.LAITON;
		m6 = Materiaux.NICHROME;
		m7 = Materiaux.NICKEL;
		m8 = Materiaux.OR;
		m9 = Materiaux.PLATINE;
		m10 = Materiaux.PLOMB;
		m11 = Materiaux.TUNGSTENE;
	}

	@Test
	public void testGetResistivite() {
		assertTrue(m1.getResistivite() == 2.65 * Math.pow(10, -8));
		assertTrue(m2.getResistivite() == 1.59 * Math.pow(10, -8));
		assertTrue(m3.getResistivite() == 1.68 * Math.pow(10, -8));
		assertTrue(m4.getResistivite() == 8.57 * Math.pow(10, -8));
		assertTrue(m5.getResistivite() == 6.08 * Math.pow(10, -8));
		assertTrue(m6.getResistivite() == 108 * Math.pow(10, -8));
		assertTrue(m7.getResistivite() == 6.93 * Math.pow(10, -8));
		assertTrue(m8.getResistivite() == 2.21 * Math.pow(10, -8));
		assertTrue(m9.getResistivite() == 10.5 * Math.pow(10, -8));
		assertTrue(m10.getResistivite() == 19.2 * Math.pow(10, -8));
		assertTrue(m11.getResistivite() == 5.28 * Math.pow(10, -8));
	}

	@Test
	public void testGetCoefThermique() {
		assertTrue(m1.getCoefThermique() == 3.9 * Math.pow(10, -3));
		assertTrue(m2.getCoefThermique() == 3.8 * Math.pow(10, -3));
		assertTrue(m3.getCoefThermique() == 3.9 * Math.pow(10, -3));
		assertTrue(m4.getCoefThermique() == 5 * Math.pow(10, -3));
		assertTrue(m5.getCoefThermique() == 2 * Math.pow(10, -3));
		assertTrue(m6.getCoefThermique() == 0.4 * Math.pow(10, -3));
		assertTrue(m7.getCoefThermique() == 5.9 * Math.pow(10, -3));
		assertTrue(m8.getCoefThermique() == 3.4 * Math.pow(10, -3));
		assertTrue(m9.getCoefThermique() == 3.92 * Math.pow(10, -3));
		assertTrue(m10.getCoefThermique() == 4.3 * Math.pow(10, -3));
		assertTrue(m11.getCoefThermique() == 4.5 * Math.pow(10, -3));

	}

	@Test
	public void testGetNom() {
		assertTrue(m1.getNom().equals("Aluminium"));
		assertTrue(m2.getNom().equals("Argent"));
		assertTrue(m3.getNom().equals("Cuivre"));
		assertTrue(m4.getNom().equals("Fer"));
		assertTrue(m5.getNom().equals("Laiton"));
		assertTrue(m6.getNom().equals("Nichrome"));
		assertTrue(m7.getNom().equals("Nickel"));
		assertTrue(m8.getNom().equals("Or"));
		assertTrue(m9.getNom().equals("Platine"));
		assertTrue(m10.getNom().equals("Plomb"));
		assertTrue(m11.getNom().equals("Tungstène"));
	}

	@Test
	public void testToString() {
		assertTrue(m1.toString().equals("Le matériaux est Aluminium, sa résistivité est de " + m1.getResistivite() + " et son coefficient thermique est de " + m1.getCoefThermique() + "."));
	}

}
