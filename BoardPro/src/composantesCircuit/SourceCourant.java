package composantesCircuit;

import composant.CEDeuxEntres_CEDE_;

public class SourceCourant extends CEDeuxEntres_CEDE_ {
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
