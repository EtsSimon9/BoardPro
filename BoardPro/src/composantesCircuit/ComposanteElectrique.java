package modele;

import exception.ComposanteException;
import map.Point;

/**
 * 
 * @author Simon Beaulieu Classe mère de toute les composantes de circuit
 *         électrique.
 */
public abstract class ComposanteElectrique implements Point {
	/**
	 * Résistance/impédance de la composante
	 */
	private float impedence;
	/**
	 * Différence de potentiel électrique de la composante
	 */
	private float ddp;
	/**
	 * Courant électrique circulant dans la composante
	 */
	
	private float Courant;
	private short positionX;
	private short positionY;
	static final float IMPEDENCE_DEFAUT = 20;
	static final float DDP_DEFAUT = 0;
	static final float COURANT_DEFAUT = 0;

	/**
	 * Constructeur par défaut. Lors de l'ajout d'une composante elle sera crée dès
	 * qu'elle est déposé dans la zone de dessin avec les valeurs défaut. On ne peu
	 * pas vraiment choisir la DDP et le courant puisqu'ils dépendent de la totalité
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
