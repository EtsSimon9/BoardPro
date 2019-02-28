package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import exception.ComposanteException;
import map.Map;
import modele.ComposanteElectrique;
import modele.Resistor;

public class MapTest {
	Map map1, map2, map3;
	Resistor res1, res2, res3, res4;

	@Before
	public void map() throws ComposanteException {
		try {
			map1 = new Map((short) 50, (short) 30);
			map2 = new Map((short) 30, (short) 50);
			map3 = new Map((short) 30, (short) 50);
			res1 = new Resistor((short) 30, (short) 50);
			res2 = new Resistor((short) 30, (short) 50);
			res3 = new Resistor((short) 5, (short) 50);
			res4 = new Resistor((short) -10, (short) 50);
		} catch (Exception e) {
		}
	}

	@Test
	public void testMap() {
		try {
			map1 = new Map((short) -50, (short) 30);
			fail("Erreur, zone avec une mesure négative");
		} catch (ComposanteException e) {
		}
		try {
			map1 = new Map((short) 50, (short) -30);
			fail("Erreur, zone avec une mesure négative");
		} catch (ComposanteException e) {
		}
		try {
			map1 = new Map((short) -50, (short) -30);
			fail("Erreur, zone avec une mesure négative");
		} catch (ComposanteException e) {
		}
	}

	@Test
	public void testAddComposant() {
		try {
			assertTrue(map1.getComposantsActuels().size() == 0);
			map1.addComposant(res1);
			assertTrue(map1.getComposantsActuels().size() == 1);
			map1.addComposant(res2);
			assertTrue(map1.getComposantsActuels().size() == 1);
			map1.addComposant(res3);
			assertTrue(map1.getComposantsActuels().size() == 2);
		} catch (ComposanteException e) {
		}
		try {
			map1.addComposant(res4);
			fail("La composante est mauvaise pourtant");
		} catch (ComposanteException e) {
		}
	}

	@Test
	public void testAddComposantDerriere() {
		try {
			assertTrue(map1.getComposantsActuels().size() == 0);
			map1.addComposant(res1);
			assertTrue(map1.getComposantsActuels().size() == 1);
			map1.addComposantDerriere(res2, res1);
			assertTrue(map1.getComposantsActuels().get(0) == res2);
		} catch (ComposanteException e) {
		}
	}

	@Test
	public void testAddComposantDevant() {
		try {
			assertTrue(map1.getComposantsActuels().size() == 0);
			map1.addComposant(res1);
			assertTrue(map1.getComposantsActuels().size() == 1);
			map1.addComposantDevant(res2, res1);
			assertTrue(map1.getComposantsActuels().get(1) == res1);
		} catch (ComposanteException e) {
		}
	}

	@Test
	public void testAddComposantActuels() {
		try {
			assertTrue(map1.getComposantsActuels().size() == 0);
			map1.addComposant(res1);
			map1.addComposant(res3);
			assertTrue(map1.getComposantsActuels().size() == 2);
			map1.addComposantActuels(res2, (short) 1);
			assertTrue(map1.getComposantsActuels().get(1) == res2);
		} catch (ComposanteException e) {
		}
	}

	@Test
	public void testSetMap() {
		try {
			ComposanteElectrique[][] mape = new ComposanteElectrique[15][50];
			map1.setMap(mape);
			assertTrue(map1.getMap() == mape);
			map1.setMap(null);
			assertTrue(map1.getMap() == mape);
		} catch (Exception e) {
		}
	}
}