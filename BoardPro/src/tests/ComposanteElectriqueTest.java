package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exception.ComposanteException;
import modele.ComposanteElectrique;
import modele.Resistor;

public class ComposanteElectriqueTest {
	ComposanteElectrique c1,c2;
	@Before
	public void creerComposante() {
		
		try {
			c1 = new Resistor((short) 10,(short) 10);
			c2 = new Resistor((short) 10,(short) 10);
		} catch (ComposanteException e) {
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
