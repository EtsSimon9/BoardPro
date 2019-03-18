package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CalculTest.class, ComposanteElectrique_CE_Test.class, MapParcourableTest.class , FilTest.class, ResistanceTest.class,
		ResistanceTest.class, MateriauxTest.class})
public class AllTest {

}
