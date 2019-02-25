package modele;

import exception.ComposanteException;

/**
 * Classe des fils
 * @author Simon Beaulieu
 *
 */
public class Fil extends ComposanteElectrique {
	private boolean neglige = true;
	/**
	 * Quand nous changerons la variable impédence d'un fil on devra changer résistance aussi
	 * Résistance est seulement utile pour garder la valeur de la résistance car si l'on néglige 
	 * le fil on met l'impédence à 0, si on ne néglige plus le fil on a encore la résistance.
	 */
	private float resistance;
	
	public Fil() throws ComposanteException {
		super();
		this.setResistance(this.getImpedence());
	}

	public boolean isNeglige() {
		return neglige;
	}

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
