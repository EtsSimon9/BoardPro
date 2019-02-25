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
	 * Quand nous changerons la variable imp�dence d'un fil on devra changer r�sistance aussi
	 * R�sistance est seulement utile pour garder la valeur de la r�sistance car si l'on n�glige 
	 * le fil on met l'imp�dence � 0, si on ne n�glige plus le fil on a encore la r�sistance.
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
