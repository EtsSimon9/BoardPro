package modele;

import exception.ComposanteException;

/**
 * 
 * @author Simon Beaulieu Classe mère de toute les composantes de circuit
 *         électrique.
 */
public abstract class ComposanteElectrique {
	/**
	 * Résistance/impédance de la composante
	 */
	float impedence;
	/**
	 * Différence de potentiel électrique de la composante
	 */
	float ddp;
	/**
	 * Courant électrique circulant dans la composante
	 */
	float Courant;
	static final float IMPEDENCE_DEFAUT = 20;
	static final float DDP_DEFAUT = 0;
	static final float COURANT_DEFAUT = 0;

	/**
	 * Constructeur par défaut. Lors de l'ajout d'une composante elle sera crée dès
	 * qu'elle est déposé dans la zone de dessin avec les valeurs défaut. On ne peu
	 * pas vraiment choisir la DDP et le courant puisqu'ils dépendent de la totalité
	 * du circuit.
	 */
	public ComposanteElectrique() {
		this.setImpedence(IMPEDENCE_DEFAUT);
		this.setDdp(DDP_DEFAUT);
		this.setCourant(COURANT_DEFAUT);
	}

	public ComposanteElectrique(float impedance) {
		this.setImpedence(impedance);
		this.setDdp(DDP_DEFAUT);
		this.setCourant(COURANT_DEFAUT);
	}
	public float getImpedence() {
		return impedence;
	}

	public void setImpedence(float impedence) {
		this.impedence = impedence;
	}

	public float getDdp() {
		return ddp;
	}

	public void setDdp(float ddp) {
		this.ddp = ddp;
	}

	public float getCourant() {
		return Courant;
	}

	public void setCourant(float tensionCourant) {
		this.Courant = tensionCourant;
	}
}
