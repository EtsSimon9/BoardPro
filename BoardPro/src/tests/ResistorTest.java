package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exception.ComposanteException;
import modele.Resistor;

public class ResistorTest {
	Resistor r1;

	@Before
	public void creerResistor() {
		try {
			r1 = new Resistor();
		} catch (ComposanteException e) {
			//Pas sensé se rendre ici
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
	public void testChangerResistance() {
		float p = (float) 0.0005;
		float l = 20;
		float r = 5;
		
		float i = (float) ((p*l)/(r*r*Math.PI));
		
		r1.setResistivite(p);
		r1.setLongueur(l);
		r1.setRayon(r);
		
		r1.changerResistance();
		assertTrue(r1.getImpedence() == i);
	}

}
