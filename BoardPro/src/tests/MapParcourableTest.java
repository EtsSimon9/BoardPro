package tests;

import static org.junit.jupiter.api.Assertions.*;
// --------

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import composantesCircuit.Fil;
import composantesCircuit.Resistance;
import exceptions.ComposantException;
import map.ComposantMap;
import map.MapParcourable;

class MapParcourableTest {
	MapParcourable map1, map2, map3;
	Resistance res1, res2, res3, res4, res5;

	@BeforeEach
	public void mapParcourableTest() {
		map1 = new MapParcourable();
		map2 = new MapParcourable();
		map3 = new MapParcourable();
	
		try {
			res1 = new Resistance((short) 0, (short) 0);
			res2 = new Resistance((short) 0, (short) 1);
			res3 = new Resistance((short) 1, (short) 0);
			res4 = new Resistance((short) 1, (short) 3);
			res5 = new Resistance((short) 3, (short) 1);
		} catch (ComposantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	void testGetMaillsesCircuitsFermes() {
		Fil fil1, fil3,fil5,fil7;
		Resistance res6,res8;
		try {
			fil1 = new Fil((short) 30, (short) 30);
			res2 = new Resistance((short) 30, (short) 31);
			fil3 = new Fil((short) 30, (short) 32);
			res4 = new Resistance((short) 31, (short) 32);
			res4.setSens(false);
			fil5 = new Fil((short) 32, (short) 32);
			res6 = new Resistance((short) 32, (short) 31);
			fil7 = new Fil((short) 32, (short) 30);
			res8 = new Resistance((short) 31, (short) 30);
			res8.setSens(false);
			map1.addComposant(fil1);
			map1.addComposant(res2);
			map1.addComposant(fil3);
			map1.addComposant(res4);
			map1.addComposant(fil5);
			map1.addComposant(res6);
			map1.addComposant(fil7);
			map1.addComposant(res8);
			
		} catch (ComposantException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		map1.genererMailles();
		map3.setMaillsesCircuitsFermes(map1.getMaillsesCircuitsFermes());
		assertTrue(map3.getMaillsesCircuitsFermes()==map1.getMaillsesCircuitsFermes());
	}

	@Test
	void testSetMaillsesCircuitsFermes() {
		
		Fil fil1, fil3,fil5,fil7;
		Resistance res6,res8;
		try {
		fil1 = new Fil((short) 5, (short) 5);
		res2 = new Resistance((short) 5, (short) 6);
		fil3 = new Fil((short) 5, (short) 7);
		res4 = new Resistance((short) 6, (short) 7);
		res4.setSens(false);
		 fil5 = new Fil((short) 7, (short) 7);
		 res6 = new Resistance((short) 7, (short) 6);
		fil7 = new Fil((short) 7, (short) 5);
		 res8 = new Resistance((short) 6, (short) 5);
		res8.setSens(false);
		map1.addComposant(fil1);
		map1.addComposant(res2);
		map1.addComposant(fil3);
		map1.addComposant(res4);
		map1.addComposant(fil5);
		map1.addComposant(res6);
		map1.addComposant(fil7);
		map1.addComposant(res8);
		
		} catch (ComposantException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	
		map1.genererMailles();
		map2.setMaillsesCircuitsFermes(map1.getMaillsesCircuitsFermes());
		assertTrue(map2.getMaillsesCircuitsFermes()==map1.getMaillsesCircuitsFermes());
	}

	@Test
	void testGenrerNoeuds() {
		// Test pas encore disponible puisque cette partie n est pas a faire pour le
		// deuxieme sprint
	}

	@Test
	void testGenererMailles() {

		try {
			map3.addComposant(res1);
			map3.addComposant(res2);
			map3.addComposant(res3);
			map3.addComposant(res4);
			map3.addComposant(res5);
		} catch (ComposantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map3.genererMailles();
		assertTrue(map3.getMaillsesCircuitsFermes().size()==0);
		Fil fil1, fil3,fil5,fil7;
		Resistance res6,res8;
		try {
		fil1 = new Fil((short) 0, (short) 0);
		res2 = new Resistance((short) 0, (short) 1);
		fil3 = new Fil((short) 0, (short) 2);
		res4 = new Resistance((short) 1, (short) 2);
		res4.setSens(false);
		 fil5 = new Fil((short) 2, (short) 2);
		 res6 = new Resistance((short) 2, (short) 1);
		 fil7 = new Fil((short) 2, (short) 0);
		 res8 = new Resistance((short) 1, (short) 0);
		res8.setSens(false);
		
		map1.addComposant(fil1);
		map1.addComposant(res2);
		map1.addComposant(fil3);
		map1.addComposant(res4);
		map1.addComposant(fil5);
		map1.addComposant(res6);
		map1.addComposant(fil7);
		map1.addComposant(res8);
		
		} catch (ComposantException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	
		
	
		map1.genererMailles();
		ArrayList<ComposantMap> composantsActuels = map1.getComposantsActuels();
		
		for(int i = 0;i<map1.getMaillsesCircuitsFermes().size();i++) {
			for(ComposantMap comp : map1.getMaillsesCircuitsFermes().get(i)) {
				assertTrue(composantsActuels.contains(comp));
			}
			assertTrue( map1.getMaillsesCircuitsFermes().get(i).size()==8);
		}
	
	}

	@Test
	void testAddComposant() {

		try {
			map2.addComposant(res1);
			map2.addComposant(res2);
			map2.addComposant(res3);
			map2.addComposant(res4);
			map2.addComposant(res5);
		} catch (ComposantException e) {
			e.printStackTrace();
		}
		boolean estDedant1 = false;
		boolean estDedant2 = false;
		boolean estDedant3 = false;
		boolean estDedant4 = false;
		boolean estDedant5 = false;
		for (ComposantMap comp : map2.getComposantsActuels()) {
			if (comp == res1) {
				estDedant1 = true;
			}
			if (comp == res2) {
				estDedant2 = true;
			}
			if (comp == res3) {
				estDedant3 = true;
			}
			if (comp == res4) {
				estDedant4 = true;
			}
			if (comp == res5) {
				estDedant5 = true;
			}

		}
		assertTrue(estDedant1);
		assertTrue(estDedant2);
		assertTrue(estDedant3);
		assertTrue(estDedant4);
		assertTrue(estDedant5);
	}



	@Test
	void testEstDansMap() {
		map1 = new MapParcourable();
		try {
			map1.addComposant(res1);
		} catch (ComposantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(map1.estDansMap(res1));
		try {
			map1.addComposant(res2);
		} catch (ComposantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(map1.estDansMap(res2));
		try {
			
			map1.addComposant(res3);
		} catch (ComposantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(map1.estDansMap(res3));

		try {
			map1.addComposant(res4);
		} catch (ComposantException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		assertTrue(map1.estDansMap(res4));

		try {
			map1.addComposant(res5);
		} catch (ComposantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(map1.estDansMap(res4));

	}

	@Test
	void testGetComposantsActuels() {
		ArrayList<ComposantMap> truc = new ArrayList<ComposantMap>();
		truc.add(res1);
		truc.add(res2);
		truc.add(res3);
		truc.add(res4);
		truc.add(res5);
		map1.setComposantsActuels(truc);
		assertTrue(map1.getComposantsActuels() == truc);
	}

	@Test
	void testSetComposantsActuels() {
		ArrayList<ComposantMap> truc = new ArrayList<ComposantMap>();
		truc.add(res1);
		truc.add(res2);
		truc.add(res3);
		truc.add(res4);
		truc.add(res5);
		map1.setComposantsActuels(truc);
		assertTrue(map1.getComposantsActuels() == truc);

	}

}
//--------