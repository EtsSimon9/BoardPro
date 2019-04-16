package composantesCircuit;

import composante.ComposanteElectrique;
import exceptions.ComposantException;
import utilitaire.Images;

/**
 * Classe des fils
 * @author Simon Beaulieu
 *
 */

  public class Fil extends ComposanteElectrique {
	/**
	 * TRUE si l'on n�glige la r�sistance des fils
	 */
	private boolean neglige = true;
	/**
	 * Quand nous changerons la variable imp�dence d'un fil, nous devrons changer
	 * r�sistance aussi. R�sistance est seulement utile pour garder la valeur de la
	 * r�sistance car si l'on n�glige le fil on met l'imp�dence � 0, si on ne
	 * n�glige plus le fil on a encore la valeur de la r�sistance.
	 */
	private float resistance;

	/**
	 * Constructeur par d�faut des fils, utile lorsque l'on place un fil dans la
	 * zone de dessin on cr�era ce fil automatiquement avec cette classe.
	 * @throws ComposantException
	 */
	private static final float RESISTANCE_DEFAUT = 2f;

	public Fil(short x, short y, Images image) throws ComposantException {
		super(RESISTANCE_DEFAUT,x,y,image);
		this.setResistance(this.getImpedence());
	}

	public boolean isNeglige() {
		return neglige;
	}

	/**
	 * Le set s'occupe aussi de changer l'impedence du fil � 0 si l'on veut n�gliger
	 * les r�sistances Puisque la classe stock la valeur de l'imp�dence avec la
	 * variable Resistance, la classe est capable de reprendre l'imp�dence du fil
	 * sans en entr�e une nouvelle lorsqu'on remet n�gliger � false.
	 *
	 * @param neglige
	 */
	public void setNeglige(boolean neglige) {
		if (neglige == true) {
			this.setImpedence(0);
		}
		if (neglige == false) {
			this.setImpedence(this.resistance);
		}
		this.neglige = neglige;
	}

	public float getResistance() {
		return resistance;
	}

	public void setResistance(float resistance) {
		this.resistance = resistance;
	}

	@Override
	public String toString() {
		return " [" +  " R" + resistance + "]";
	}

}
