package modele;

import exception.ComposanteException;
import map.Point;

/**
 * 
 * @author Simon Beaulieu Classe m�re de toute les composantes de circuit
 *         �lectrique.
 */
public abstract class ComposanteElectrique implements Point {
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
	private short positionX;
	private short positionY;
	static final float IMPEDENCE_DEFAUT = 20;
	static final float DDP_DEFAUT = 0;
	static final float COURANT_DEFAUT = 0;

	/**
	 * Constructeur par d�faut. Lors de l'ajout d'une composante elle sera cr�e d�s
	 * qu'elle est d�pos� dans la zone de dessin avec les valeurs d�faut. On ne peu
	 * pas vraiment choisir la DDP et le courant puisqu'ils d�pendent de la totalit�
	 * du circuit.
	 */
	public ComposanteElectrique(short x, short y) {
		this.setImpedence(IMPEDENCE_DEFAUT);
		this.setDdp(DDP_DEFAUT);
		this.setCourant(COURANT_DEFAUT);
	}

	public ComposanteElectrique(float impedance, short x, short y) {
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
	
	@Override
	public short getCoordonneY() {
		return 0;
	}

	@Override
	public short getCoordonneX() {
		return 0;
	}

	@Override
	public void setCoordonneX(short a) {
		
	}

	@Override
	public void setCoordonneY(short a) {
		
	}
}
