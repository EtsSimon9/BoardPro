package composantesCircuit;

import composante.CE2Entrees;
import utilitaire.Images;

public class SourceCourant extends CE2Entrees {
	private static final float DDP_DEFAUT = 20;

	public SourceCourant(float ddp, short coordonnex, short coordonney, Images i) {
		super(coordonnex, coordonney, i);
		if (ddp > 0) {
			this.setDdp(ddp);
		} else {
			this.setDdp(DDP_DEFAUT);
		}
	}

	public SourceCourant(short coordonnex, short coordonney, Images i) {
		super(coordonnex, coordonney, i);
		this.setDdp(DDP_DEFAUT);
	}

	
}
