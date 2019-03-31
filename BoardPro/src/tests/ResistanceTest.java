package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import composantesCircuit.Resistance;
import exceptions.ComposantException;
import utilitaire.Materiaux;

public class ResistanceTest {
	Resistance r1;

	@Before
	public void creerResistor() {
		try {
			r1 = new Resistance((short) 10, (short) 10,null);
		} catch (ComposantException e) {
			// Pas sens� se rendre ici
			fail();
		}
	}

	@Test
	public void testResistor() {
		assertTrue(r1.getImpedence() == 20);
		assertTrue(r1.getCourant() == 0);
		assertTrue(r1.getDdp() == 0);
	}

	@Test
	public void testResistorCustom() {
		// Test 1
		float longueur = 20;
		float rayon = 2;
		float t = 20;
		Materiaux m = Materiaux.ALUMINIUM;
		float resistanceCalc = (float) ((20 * m.getResistivite()) / (Math.PI * rayon * rayon));
		Resistance r = new Resistance((short) 0, (short) 0, longueur, rayon, m, t,null);
		assertTrue(r.getImpedence() == resistanceCalc);

		// Test 2
		t = 50;
		double nouveauP =(m.getResistivite() * (1 + (m.getCoefThermique() * 30)));
		resistanceCalc = (float) ((20 * nouveauP) / (Math.PI * rayon * rayon));
		r = new Resistance((short) 0, (short) 0, longueur, rayon, m, t,null);
		assertTrue(r.getImpedence() == resistanceCalc);
	}

}
