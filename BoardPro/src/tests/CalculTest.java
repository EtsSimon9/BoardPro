package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import utilitaire.Calcul;
import exceptions.MathException;

public class CalculTest {

	@Test
	public void testFrequenceTofrequenceAngulaire() {
		float f = 20;
		assertTrue(Calcul.frequenceTofrequenceAngulaire(f) == (float) (f*2*Math.PI));
	}

	@Test
	public void testFrequenceAngulaireTofrequence() {
		float fa = 20;
		assertTrue(Calcul.frequenceAngulaireTofrequence(fa)== (float) (fa/(2*Math.PI)));
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

	}

	@Test
	public void testDdpCondensateur() {

	}

	@Test
	public void testCapaciteCondensateurPlan() {
	}

	@Test
	public void testCapaciteCondensateurCylindrique() {
	}

	@Test
	public void testCapaciteCondensateurSpherique() {
	}

	@Test
	public void testCalculResistance() {
	}

	@Test
	public void testResistiviteEtTemperature() {
	}

	@Test
	public void testLoiOhmR() {
	}

	@Test
	public void testLoiOhmDDP() {
	}

	@Test
	public void testLoiOhmI() {
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
