package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modele.CalculdeCircuit;
import exception.ComposanteException;
import map.Map;
import modele.Resistor;

public class CalculdeCircuitTest {
	Map map1;
	Resistor res1;
	CalculdeCircuit cr;

	@Before
	public void calculComposant() throws ComposanteException {
		try {
			res1 = new Resistor((short) 30, (short) 50);
			map1 = new Map((short) 50, (short) 60);
			map1.addComposant(res1);
			cr = new CalculdeCircuit(map1);

		} catch (Exception e) {
		}
	}

	@Test
	public void testCalculdeCircuit() {
		assertTrue(!cr.isCircuitFerme());
	}

}
