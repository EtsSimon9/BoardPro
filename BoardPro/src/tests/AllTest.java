package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CalculTest.class, ComposanteElectriqueTest.class, FilTest.class, ResistanceTest.class,
		ResistanceTest.class, MateriauxTest.class,MapParcourableTest.class,SolveurEquationsTest.class,MatriceUtilitairesTest.class})
public class AllTest {

}
