package tests;



import static org.junit.Assert.assertTrue;

import org.junit.Test;

import utilitaire.SolveurEquations;

public class SolveurEquationsTest {


	@Test
	public void testEvaluer() {
		SolveurEquations.initPrecedence();
		assertTrue(SolveurEquations.evaluer("sin(40)")==Math.sin(40));
		assertTrue(SolveurEquations.evaluer("log(20)")==Math.log10(20));
		assertTrue(SolveurEquations.evaluer("1+3")==4);
		assertTrue(SolveurEquations.evaluer("(2*56)+tan(50)")==Math.tan(50)+2*56);
		assertTrue(SolveurEquations.evaluer("(3/5)*8+4")==(((double)3/5)*8+4));
		assertTrue(SolveurEquations.evaluer("sin(40)/8/9/9/3")==Math.sin(40)/8/9/9/3);
		assertTrue(SolveurEquations.evaluer("sin(40)^5")==Math.pow(Math.sin(40), 5));
		assertTrue(SolveurEquations.evaluer("sin(40)^-2")==Math.pow(Math.sin(40), -2));
		assertTrue(SolveurEquations.evaluer("sin(40)^-2+sin(40)/8/9/9/3+(2*56)+tan(50)")==Math.pow(Math.sin(40), -2)+Math.sin(40)/8/9/9/3+2*56+Math.tan(50));

	}

}
