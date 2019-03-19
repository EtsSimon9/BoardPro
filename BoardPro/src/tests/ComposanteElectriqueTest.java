package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import composant.CEDeuxEntres_CEDE_;
import composantesCircuit.Resistance;
import exceptions.ComposantException;

public class ComposanteElectriqueTest {
	CEDeuxEntres_CEDE_ c1;
	CEDeuxEntres_CEDE_ c2;
	
	@Before
	public void creerComposante() {
		try {
			c1 = new Resistance((short) 10,(short) 10);
			c2 = new Resistance((short) 10,(short) 10);
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