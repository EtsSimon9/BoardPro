package modele;

import exception.ComposanteException;

/**
 * Classe des fils
 * @author Simon Beaulieu
 *
 */
public class Fil extends ComposanteElectrique {
	/**
	 * TRUE si l'on néglige la résistance des fils
	 */
	private boolean neglige = true;
	/**
	 * Quand nous changerons la variable impédence d'un fil, nous devrons changer résistance aussi.
	 * Résistance est seulement utile pour garder la valeur de la résistance car si l'on néglige 
	 * le fil on met l'impédence à 0, si on ne néglige plus le fil on a encore la valeur de la résistance.
	 */
	private float resistance;
	
	/**
	 * Constructeur par défaut des fils, utile lorsque l'on place un fil dans la zone de dessin
	 * on créera ce fil automatiquement avec cette classe.
	 * @throws ComposanteException
	 */
	public Fil() throws ComposanteException {
		super();
		this.setResistance(this.getImpedence());
	}

	public boolean isNeglige() {
		return neglige;
	}

	/**
	 * Le set s'occupe aussi de changer l'impedence du fil à 0 si l'on veut négliger les résistances
	 * Puisque la classe stock la valeur de l'impédence avec la variable Resistance,
	 * la classe est capable de reprendre l'impédence du fil sans en entrée une nouvelle lorsqu'on
	 * remet négliger à false.
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

	
	
}
