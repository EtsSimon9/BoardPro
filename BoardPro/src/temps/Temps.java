package temps;

public class Temps {
	// temps au lencement de la lasse temps
	long tempsZero;
	// temps initil pour calculer le delata T
	long tempsInitial;
	// temps final pour calculer le delta T
	long tempsFinal;

	long deltaTemps;

	// temps final exp�rimental calcul� avec les petites variaitons de temps delata
	// T, il est l�g�rement en retard et il sert � compenser le deltaT
	long tempsFinalDecle;

	public Temps() {
		super();
		tempsZero = System.currentTimeMillis();
		setTZero();
	}

	public float getDeltaT() {
		tempsFinal = System.currentTimeMillis();
		deltaTemps = tempsFinal - tempsInitial;
		tempsFinalDecle += deltaTemps;
		deltaTemps = tempsFinal - tempsZero - tempsFinalDecle+ deltaTemps;
		tempsFinalDecle =tempsFinal - tempsZero ;
		return (float) (deltaTemps / Math.pow(10, 3));
	}

	public void setTZero() {
		tempsInitial = System.currentTimeMillis();
	}

}
