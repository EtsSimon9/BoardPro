package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import utilitaire.Calcul;
import exceptions.MathException;

public class CalculTest {

	@Test
	public void testFrequenceTofrequenceAngulaire() {
		float f = 20;
		assertTrue(Calcul.frequenceTofrequenceAngulaire(f) == (float) (f * 2 * Math.PI));
	}

	@Test
	public void testFrequenceAngulaireTofrequence() {
		float fa = 20;
		assertTrue(Calcul.frequenceAngulaireTofrequence(fa) == (float) (fa / (2 * Math.PI)));
	}

	@Test
	public void testCapaciteCondensateur() {
		float ddp = 20;
		float q = 40;
		try {
			assertTrue(Calcul.capaciteCondensateur(ddp, q) == 2);
		} catch (MathException e) {
			fail();
		}

		try {
			Calcul.capaciteCondensateur(0, q);
			fail();
		} catch (MathException e) {

		}
	}

	@Test
	public void testChargeCondensateur() {
		assertTrue(Calcul.chargeCondensateur(20, 10) == 200);

	}

	@Test
	public void testDdpCondensateur() {
		try {
			assertTrue(Calcul.ddpCondensateur(10, 200) == 20);
		} catch (MathException e) {
			fail();
		}

		try {
			Calcul.ddpCondensateur(0, 20);
			fail();
		} catch (MathException e) {
		}

	}

	@Test
	public void testCapaciteCondensateurPlan() {
		try {
			Calcul.capaciteCondensateurPlan(50, 0);
			fail();
		} catch (MathException e) {
		}

		try {
			assertTrue(Calcul.capaciteCondensateurPlan(20, 20) == (float) Calcul.epsilon);
		} catch (MathException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testCapaciteCondensateurCylindrique() {
		try {
			Calcul.capaciteCondensateurCylindrique(20, 20, -1);
			fail();
		} catch (MathException e) {
		}

		try {
			assertTrue(Calcul.capaciteCondensateurCylindrique(20, 20, 40) == (float) (10 / ((Calcul.k) * Math.log(2))));
		} catch (MathException e) {
			fail();
		}
	}

	@Test
	public void testCapaciteCondensateurSpherique() {
		try {
			Calcul.capaciteCondensateurSpherique(20, -20);
			fail();
		} catch (MathException e) {
		}
		
		try {
			assertTrue(Calcul.capaciteCondensateurSpherique(20, 40) == (float) (4*Math.PI*Calcul.epsilon*20*40/(20)));
		} catch (MathException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testCalculResistance() {
		try {
			Calcul.calculResistance(20, 20, 0);
			fail();
		} catch (MathException e) {
		}
		
		try {
			assertTrue(Calcul.calculResistance(20, 20, 2) == (float) (400/(Math.PI*4)));
		} catch (MathException e) {
			fail();
		}
	}

	@Test
	public void testResistiviteEtTemperature() {
		assertTrue(Calcul.resistiviteEtTemperature(2, 20, 30, 5) == 102);
	}

	@Test
	public void testLoiOhmR() {
		try {
			Calcul.loiOhmR(20, 0);
			fail();
		} catch (MathException e) {
		}
		
		try {
			assertTrue(Calcul.loiOhmR(20, 2) == (float) (10));
		} catch (MathException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testLoiOhmDDP() {
		assertTrue(Calcul.loiOhmDDP(20, 2) == 40);
	}

	@Test
	public void testLoiOhmI() {
		try {
			Calcul.loiOhmI(0, 40);
			fail();
		} catch (MathException e) {
		}
		
		try {
			assertTrue(Calcul.loiOhmI(20, 40) == 2);
		} catch (MathException e) {
			fail();
		}
	}

	@Test
	public void testChargeCondensateurDecharge() {
	}

	@Test
	public void testDdpCondesateurDecharge() {
	}

	@Test
	public void testCourantCondensateurDecharge() {
	}

	@Test
	public void testChargeCondensateurCharge() {
	}

	@Test
	public void testDdpCondesateurCharge() {
	}

	@Test
	public void testCourantCondensateurCharge() {
	}

	@Test
	public void testCourantDeplug() {
	}

	@Test
	public void testCourantPlug() {
	}

	@Test
	public void testImpedanceCircuitRLC() {
	}

	@Test
	public void testImpedanceCondensateur() {
	}

	@Test
	public void testImpedanceBobine() {
	}

}
