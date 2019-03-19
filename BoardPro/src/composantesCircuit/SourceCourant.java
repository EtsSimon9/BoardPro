package composantesCircuit;

import composante.CE2Entrees;

public class SourceCourant extends CE2Entrees {
	private static final float DDP_DEFAUT = 20;

	public SourceCourant(float ddp, short coordonnex, short coordonney) {
		super(coordonnex, coordonney);
		if (ddp > 0) {
			this.setDdp(ddp);
		}else {
			this.setDdp(DDP_DEFAUT);
		}
	}

}
