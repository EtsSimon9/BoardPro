package modele;

import exception.ComposanteException;
/**
 * 
 * @author Simon Beaulieu
 *
 */
public abstract class ComposanteElectrique {
	float impedence;
	float ddp;
	float Courant;
	static final float IMPEDENCE_DEFAUT = 20;
	static final float DDP_DEFAUT = 40;
	static final float COURANT_DEFAUT = 2;
	
	public ComposanteElectrique() throws ComposanteException {
		this.setImpedence(IMPEDENCE_DEFAUT);
		this.setDdp(DDP_DEFAUT);
		this.setCourant(COURANT_DEFAUT);
	}
	
	public ComposanteElectrique(float impedence, float ddp, float tensionCourant) throws ComposanteException {
		this.setImpedence(impedence);
		this.setDdp(ddp);
		this.setCourant(tensionCourant);

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
