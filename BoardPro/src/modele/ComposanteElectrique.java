package modele;

import exception.ComposanteException;

public abstract class ComposanteElectrique {
	float impedence;
	float ddp;
	float Courant;
	int positionX;
	int positionY;
	static final float IMPEDENCE_DEFAUT = 20;
	static final float DDP_DEFAUT = 40;
	static final float COURANT_DEFAUT = 2;

	public ComposanteElectrique(int positionX, int positionY) throws ComposanteException {
		this.setImpedence(IMPEDENCE_DEFAUT);
		this.setDdp(DDP_DEFAUT);
		this.setCourant(COURANT_DEFAUT);
		this.setPositionX(positionX);
		this.setPositionY(positionY);
	}

	public ComposanteElectrique(float impedence, float ddp, float tensionCourant, int positionX, int positionY) throws ComposanteException {
		this.setImpedence(impedence);
		this.setDdp(ddp);
		this.setCourant(tensionCourant);
		this.setPositionX(positionX);
		this.setPositionY(positionY);

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

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) throws ComposanteException {
		if (positionX >= 0) {
			this.positionX = positionX;
		} else {
			throw new ComposanteException("position X négative");
		}
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) throws ComposanteException {
		if (positionY >= 0) {
			this.positionY = positionY;
		} else {
			throw new ComposanteException("position Y négative");
		}
	}

}
