package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import exception.MathException;
import modele.Calcul;

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
		fail("Not yet implemented");
	}

	@Test
	public void testCapaciteCondensateurPlan() {
		fail("Not yet implemented");
	}

	@Test
	public void testCapaciteCondensateurCylindrique() {
		fail("Not yet implemented");
	}

	@Test
	public void testCapaciteCondensateurSpherique() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculResistance() {
		fail("Not yet implemented");
	}

	@Test
	public void testResistiviteEtTemperature() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoiOhmR() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoiOhmDDP() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoiOhmI() {
		fail("Not yet implemented");
	}

	@Test
	public void testChargeCondensateurDecharge() {
		fail("Not yet implemented");
	}

	@Test
	public void testDdpCondesateurDecharge() {
		fail("Not yet implemented");
	}

	@Test
	public void testCourantCondensateurDecharge() {
		fail("Not yet implemented");
	}

	@Test
	public void testChargeCondensateurCharge() {
		fail("Not yet implemented");
	}

	@Test
	public void testDdpCondesateurCharge() {
		fail("Not yet implemented");
	}

	@Test
	public void testCourantCondensateurCharge() {
		fail("Not yet implemented");
	}

	@Test
	public void testCourantDeplug() {
		fail("Not yet implemented");
	}

	@Test
	public void testCourantPlug() {
		fail("Not yet implemented");
	}

	@Test
	public void testImpedanceCircuitRLC() {
		fail("Not yet implemented");
	}

	@Test
	public void testImpedanceCondensateur() {
		fail("Not yet implemented");
	}

	@Test
	public void testImpedanceBobine() {
		fail("Not yet implemented");
	}

}
