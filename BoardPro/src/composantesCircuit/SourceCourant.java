package composantesCircuit;

import composante.CE2Entrees;
import utilitaire.Images;

public class SourceCourant extends CE2Entrees {
	private static final float DDP_DEFAUT = 20;
	private float frequence;
	
	public SourceCourant(float ddp, short coordonnex, short coordonney, Images i) {
		super(coordonnex, coordonney, i);
		if (ddp > 0) {
			this.setDdp(ddp);
		} else {
			this.setDdp(DDP_DEFAUT);
		}
	}

	
	public float getFrequence() {
		return frequence;
	}


	public void setFrequence(float frequence) {
		this.frequence = frequence;
	}


	public SourceCourant(short coordonnex, short coordonney, Images i) {
		super(coordonnex, coordonney, i);
		this.setDdp(DDP_DEFAUT);
	}

	@Override
	public String toString() {
		return " [DV "+ this.getDdp()+"]";
	}

	
}
