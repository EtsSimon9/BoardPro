package tests;


import static org.junit.Assert.assertTrue;

// --------

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import composantesCircuit.Fil;
import composantesCircuit.Resistance;
import exceptions.ComposantException;
import map.ComposantMap;
import map.MapParcourable;

public class MapParcourableTest {
	MapParcourable map1, map2, map3,map4;
	Resistance res1, res2, res3, res4, res5;

	@Before
	public void mapParcourableTest() {
		map1 = new MapParcourable();
		map2 = new MapParcourable();
		map3 = new MapParcourable();
		map4 = new MapParcourable();
		try {
			res1 = new Resistance(0,(short) 0, (short) 0,null);
			res2 = new Resistance(0,(short) 0, (short) 1,null);
			res3 = new Resistance(0,(short) 1, (short) 0,null);
			res4 = new Resistance(0,(short) 1, (short) 3,null);
			res5 = new Resistance(0,(short) 3, (short) 1,null);
		} catch (ComposantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	@Test 
	public void testgenrerNoeuds() {
		Fil fil1, fil3, fil5, fil7;
		Resistance res6 = null, res8 = null;
		// voici un circtuit de forme
		//
		// o-o
		// | |
		// o-o
		// les {-,|} sont des resistances et {o} representent des composantes

		try {
			fil1 = new Fil((short) 30, (short) 30,null);

		res2 = new Resistance(0,(short) 30, (short) 31,null);
		fil3 = new Fil((short) 30, (short) 32,null);
		res4 = new Resistance(0,(short) 31, (short) 32,null);
		res4.setSens(false);
		fil5 = new Fil((short) 32, (short) 32,null);
		res6 = new Resistance(0,(short) 32, (short) 31,null);
		fil7 = new Fil((short) 32, (short) 30,null);
		res8 = new Resistance(0,(short) 31, (short) 30,null);
		res8.setSens(false);
		map1.addComposant(fil1);
		map1.addComposant(res2);
		map1.addComposant(fil3);
		map1.addComposant(res4);
		map1.addComposant(fil5);
		map1.addComposant(res6);
		map1.addComposant(fil7);
		map1.addComposant(res8);

		} catch (ComposantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	map1.genrerNoeuds();
	assertTrue(map1.getNoeudsCircuit().size()==0);
	Fil fil10, fil12;
	Resistance res9, res11 = null, res13 = null;
	try {
		// voici un circtuit de forme
		//
		// o-o-o
		// | | |
		// o-o-o
		// les {-,|} sont des resistances et {o} representent des composantes

		fil1 = new Fil((short) 0, (short) 0,null);
		res2 = new Resistance(0,(short) 0, (short) 1,null);
		fil3 = new Fil((short) 0, (short) 2,null);
		res4 = new Resistance(0,(short) 1, (short) 2,null);
		res4.setSens(false);
		fil5 = new Fil((short) 2, (short) 2,null);
		res6 = new Resistance(0,(short) 2, (short) 1,null);
		fil7 = new Fil((short) 2, (short) 0,null);
		res8 = new Resistance(0,(short) 1, (short) 0,null);
		res8.setSens(false);
		res9 = new Resistance(0,(short) 0, (short) 3,null);
		fil10 = new Fil((short) 0, (short) 4,null);
		res11 = new Resistance(0,(short) 1, (short) 4,null);
		res11.setSens(false);
		fil12 = new Fil((short) 2, (short) 4,null);
		res13 = new Resistance(0,(short) 2, (short) 3,null);

		map2.addComposant(fil1);
		map2.addComposant(res2);
		map2.addComposant(fil3);
		map2.addComposant(res4);
		map2.addComposant(fil5);
		map2.addComposant(res6);
		map2.addComposant(fil7);
		map2.addComposant(res8);
		map2.addComposant(res9);
		map2.addComposant(fil10);
		map2.addComposant(res11);
		map2.addComposant(fil12);
		map2.addComposant(res13);

	} catch (ComposantException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	map2.genrerNoeuds();
	assertTrue(map2.getNoeudsCircuit().size()==2);

	}


	
	@Test
	public void testGetMaillsesCircuitsFermes() {
		Fil fil1, fil3, fil5, fil7;
		Resistance res6, res8;
		try {
			// voici un circtuit de forme
			//
			// o-o
			// | |
			// o-o
			// les {-,|} sont des resistances et {o} representent des composantes

			fil1 = new Fil((short) 30, (short) 30,null);
			res2 = new Resistance(0,(short) 30, (short) 31,null);
			fil3 = new Fil((short) 30, (short) 32,null);
			res4 = new Resistance(0,(short) 31, (short) 32,null);
			res4.setSens(false);
			fil5 = new Fil((short) 32, (short) 32,null);
			res6 = new Resistance(0,(short) 32, (short) 31,null);
			fil7 = new Fil((short) 32, (short) 30,null);
			res8 = new Resistance(0,(short) 31, (short) 30,null);
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
		map3.setMaillesCircuitsFermes(map1.getMaillesCircuitsFermes());
		assertTrue(map3.getMaillesCircuitsFermes() == map1.getMaillesCircuitsFermes());
	}

	@Test
	public void testSetMaillsesCircuitsFermes() {

		Fil fil1, fil3, fil5, fil7;
		Resistance res6, res8;
		try {
			// voici un circtuit de forme
			//
			// o-o
			// | |
			// o-o
			// les {-,|} sont des resistances et {o} representent des composantes

			fil1 = new Fil((short) 5, (short) 5,null);
			res2 = new Resistance(0,(short) 5, (short) 6,null);
			fil3 = new Fil((short) 5, (short) 7,null);
			res4 = new Resistance(0,(short) 6, (short) 7,null);
			res4.setSens(false);
			fil5 = new Fil((short) 7, (short) 7,null);
			res6 = new Resistance(0,(short) 7, (short) 6,null);
			fil7 = new Fil((short) 7, (short) 5,null);
			res8 = new Resistance(0,(short) 6, (short) 5,null);
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
		map2.setMaillesCircuitsFermes(map1.getMaillesCircuitsFermes());
		assertTrue(map2.getMaillesCircuitsFermes() == map1.getMaillesCircuitsFermes());
	}

	@Test
	public void testGenrerNoeuds() {
		// Test pas encore disponible puisque cette partie n est pas a faire pour le
		// deuxieme sprint
	}

	@Test
	public void testGenererMailles() {

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
		assertTrue(map3.getMaillesCircuitsFermes().size() == 0);
		Fil fil1, fil3, fil5, fil7;
		Resistance res6, res8;
		try {

			// voici un circtuit de forme
			//
			// o-o
			// | |
			// o-o
			// les {-,|} sont des resistances et {o} representent des composantes

			fil1 = new Fil((short) 0, (short) 0,null);
			res2 = new Resistance(0,(short) 0, (short) 1,null);
			fil3 = new Fil((short) 0, (short) 2,null);
			res4 = new Resistance(0,(short) 1, (short) 2,null);
			res4.setSens(false);
			fil5 = new Fil((short) 2, (short) 2,null);
			res6 = new Resistance(0,(short) 2, (short) 1,null);
			fil7 = new Fil((short) 2, (short) 0,null);
			res8 = new Resistance(0,(short) 1, (short) 0,null);
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
		ArrayList<ArrayList<ComposantMap>> maillsesCircuitsFermes = map1.getMaillesCircuitsFermes();

		for (int i = 0; i < maillsesCircuitsFermes.size(); i++) {
			for (ComposantMap comp : maillsesCircuitsFermes.get(i)) {
				assertTrue(composantsActuels.contains(comp));
			}
			assertTrue(maillsesCircuitsFermes.get(i).size() == 8);
		}

		Fil fil10, fil12;
		Resistance res9, res11, res13;
		try {
			// voici un circtuit de forme
			//
			// o-o-o
			// | | |
			// o-o-o
			// les {-,|} sont des resistances et {o} representent des composantes

			fil1 = new Fil((short) 0, (short) 0,null);
			res2 = new Resistance(0,(short) 0, (short) 1,null);
			fil3 = new Fil((short) 0, (short) 2,null);
			res4 = new Resistance(0,(short) 1, (short) 2,null);
			res4.setSens(false);
			fil5 = new Fil((short) 2, (short) 2,null);
			res6 = new Resistance(0,(short) 2, (short) 1,null);
			fil7 = new Fil((short) 2, (short) 0,null);
			res8 = new Resistance(0,(short) 1, (short) 0,null);
			res8.setSens(false);
			res9 = new Resistance(0,(short) 0, (short) 3,null);
			fil10 = new Fil((short) 0, (short) 4,null);
			res11 = new Resistance(0,(short) 1, (short) 4,null);
			res11.setSens(false);
			fil12 = new Fil((short) 2, (short) 4,null);
			res13 = new Resistance(0,(short) 2, (short) 3,null);

			map2.addComposant(fil1);
			map2.addComposant(res2);
			map2.addComposant(fil3);
			map2.addComposant(res4);
			map2.addComposant(fil5);
			map2.addComposant(res6);
			map2.addComposant(fil7);
			map2.addComposant(res8);
			map2.addComposant(res9);
			map2.addComposant(fil10);
			map2.addComposant(res11);
			map2.addComposant(fil12);
			map2.addComposant(res13);

		} catch (ComposantException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		map2.genererMailles();
		maillsesCircuitsFermes = map2.getMaillesCircuitsFermes();
		// verifie qu il y a 6 mailles produites
		assertTrue(maillsesCircuitsFermes.size() == 6);
		byte nombreMaillesDeHuit = 4;
		byte nombreMaillesDeDouze = 2;

		for (int i = 0; i < maillsesCircuitsFermes.size(); i++) {
			if (maillsesCircuitsFermes.get(i).size() == 8) {
				nombreMaillesDeHuit--;
			}
			if (maillsesCircuitsFermes.get(i).size() == 12) {
				nombreMaillesDeDouze--;
			}
		}
		// veririfie qu il y a 2 maille de 12 composantes et 4 de 8
		assertTrue(nombreMaillesDeHuit == 0);
		assertTrue(nombreMaillesDeDouze == 0);

		Fil fil15, fil17;
		Resistance res14, res16, res18;
		// voici un circtuit de forme
		// o-o
		// | |
		// o-o-o
		// | | | 
		// o-o-o
		// les {-,|} sont des resistances et {o} representent des composantes
		try {
			fil1 = new Fil((short) 0, (short) 0,null);
			res2 = new Resistance(0,(short) 0, (short) 1,null);
			fil3 = new Fil((short) 0, (short) 2,null);
			res4 = new Resistance(0,(short) 1, (short) 2,null);
			res4.setSens(false);
			fil5 = new Fil((short) 2, (short) 2,null);
			res6 = new Resistance(0,(short) 2, (short) 1,null);
			fil7 = new Fil((short) 2, (short) 0,null);
			res8 = new Resistance(0,(short) 1, (short) 0,null);
			res8.setSens(false);
			res9 = new Resistance(0,(short) 0, (short) 3,null);
			fil10 = new Fil((short) 0, (short) 4,null);
			res11 = new Resistance(0,(short) 1, (short) 4,null);
			res11.setSens(false);
			fil12 = new Fil((short) 2, (short) 4,null);
			res13 = new Resistance(0,(short) 2, (short) 3,null);
			
			res14 = new Resistance(0,(short) 3, (short) 0,null);
			res14.setSens(false);
			fil15 = new Fil((short) 4, (short) 0,null);
			res16 = new Resistance(0,(short) 4, (short) 1,null);
			fil17 = new Fil((short) 4, (short) 2,null);
			res18 = new Resistance(0,(short) 3, (short) 2,null);
			res18.setSens(false);

			map4.addComposant(fil1);
			map4.addComposant(res2);
			map4.addComposant(fil3);
			map4.addComposant(res4);
			map4.addComposant(fil5);
			map4.addComposant(res6);
			map4.addComposant(fil7);
			map4.addComposant(res8);
			map4.addComposant(res9);
			map4.addComposant(fil10);
			map4.addComposant(res11);
			map4.addComposant(fil12);
			map4.addComposant(res13);
			map4.addComposant(res14);
			map4.addComposant(fil15);
			map4.addComposant(res16);
			map4.addComposant(fil17);
			map4.addComposant(res18);

		} catch (ComposantException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		map4.genererMailles();
		maillsesCircuitsFermes = map4.getMaillesCircuitsFermes();
		assertTrue(maillsesCircuitsFermes.size()==12);
		
		 nombreMaillesDeHuit = 6;
		 nombreMaillesDeDouze = 4;
		 byte nombreMaillesDeSeize = 2;
		
		for (int i = 0; i < maillsesCircuitsFermes.size(); i++) {
			if (maillsesCircuitsFermes.get(i).size() == 8) {
				nombreMaillesDeHuit--;
			}
			if (maillsesCircuitsFermes.get(i).size() == 12) {
				nombreMaillesDeDouze--;
			}
			if (maillsesCircuitsFermes.get(i).size() == 16) {
				nombreMaillesDeSeize--;
			}
		}
		assertTrue(nombreMaillesDeHuit == 0);
		assertTrue(nombreMaillesDeDouze == 0);
		assertTrue(nombreMaillesDeSeize == 0);	

	}

	@Test
	public void testAddComposant() {

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
	public void testEstDansMap() {
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
	public void testGetComposantsActuels() {
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
	public void testSetComposantsActuels() {
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