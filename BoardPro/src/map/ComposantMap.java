package map;

/**
 * classe abstraite de composantes contenant les proprietes propres a une map de
 * celles-ci
 *
 * @author HAAS Team
 *
 */
public abstract class ComposantMap implements Comparable<ComposantMap>, Point {

	private short coordonneX;
	private short coordonneY;

	public ComposantMap(short x, short y) {
		setCoordonneX(x);
		setCoordonneY(y);
	}



	@Override
	public short getCoordonneY() {
		return coordonneY;
	}


	@Override
	public short getCoordonneX() {
		return coordonneX;
	}


	@Override
	public void setCoordonneX(short coordonnex) {
		this.coordonneX = coordonnex;
	}


	@Override
	public void setCoordonneY(short coordonney) {
		this.coordonneY = coordonney;
	}


	@Override
	public int compareTo(ComposantMap obj) {
		return (Math.sqrt(coordonneX) + Math.sqrt(coordonneY)) >= (Math.sqrt(obj.getCoordonneX())
				+ Math.sqrt(obj.getCoordonneY())) ? 1 : 0;
	}

}
