package composante;

import map.ComposantMap;
import utilitaire.Images;

public abstract class ComposanteElectrique extends ComposantMap {
	/**
	 * R�sistance/imp�dance de la composante
	 */
	private float impedence;
	/**
	 * Diff�rence de potentiel �lectrique de la composante
	 */
	private float ddp;
	/**
	 * Courant �lectrique circulant dans la composante
	 */

	private float Courant;
	static final float IMPEDENCE_DEFAUT = 20;
	static final float DDP_DEFAUT = 0;
	static final float COURANT_DEFAUT = 0;
	/**
	 * Image dans la grille
	 */
	private Images image;

	/**
	 * Constructeur par d�faut. Lors de l'ajout d'une composante elle sera cr�e d�s
	 * qu'elle est d�pos� dans la zone de dessin avec les valeurs d�faut. On ne peu
	 * pas vraiment choisir la DDP et le courant puisqu'ils d�pendent de la totalit�
	 * du circuit.
	 */
	public ComposanteElectrique(short x, short y, Images image) {
		super(x, y);
		this.setImpedence(IMPEDENCE_DEFAUT);
		this.setDdp(DDP_DEFAUT);
		this.setCourant(COURANT_DEFAUT);
		if (image != null) {
			this.setImage(image);
		}
	}

	public ComposanteElectrique(float impedance, short x, short y, Images image) {
		super(x, y);
		this.setImpedence(impedance);
		this.setDdp(DDP_DEFAUT);
		this.setCourant(COURANT_DEFAUT);
		if (image != null) {
			this.setImage(image);
		}
	}

	public Images getImage() {
		return image;
	}

	public void setImage(Images image) {
		this.image = image;
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
