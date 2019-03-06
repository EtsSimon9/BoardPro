package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import composantesCircuit.Fil;
import exceptions.ComposantException;

public class FilTest {
	Fil f1;
	@Before
	public void creerFil() {
		try {
			f1 = new Fil((short) 10,(short) 10);
		} catch (ComposantException e) {
			//Pas sensé se rendre ici avec val défaut
			fail();
		}
	}
	@Test
	public void testFil() {
		assertTrue(f1.getImpedence() == 2);
		assertTrue(f1.getDdp() == 0);
		assertTrue(f1.getCourant() == 0);
	}

	@Test
	public void testSetNeglige() {
		assertTrue(f1.getImpedence() == 2);
		assertTrue(f1.getResistance() == 2);
		f1.setNeglige(true);
		assertTrue(f1.getImpedence() == 0);
		assertTrue(f1.getResistance() == 2);
		f1.setNeglige(false);
		assertTrue(f1.getImpedence() == 2);
		assertTrue(f1.getResistance() == 2);
	}

}
