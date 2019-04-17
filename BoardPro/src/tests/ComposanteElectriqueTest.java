package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import composante.CE2Entrees;
import composantesCircuit.Resistance;
import exceptions.ComposantException;

public class ComposanteElectriqueTest {
	CE2Entrees c1;
	CE2Entrees c2;
	
	@Before
	public void creerComposante() {
		try {
			c1 = new Resistance(0,(short) 10,(short) 10,null);
			c2 = new Resistance(0,(short) 10,(short) 10,null);
		} catch (ComposantException e) {
		//Pas senser se rendre ici avec val defaut
			fail();
		}
	}
	@Test
	public void testComposanteElectrique() {
		assertTrue(c1.getImpedence() == 20);
		assertTrue(c2.getImpedence() != 0);
	}

}