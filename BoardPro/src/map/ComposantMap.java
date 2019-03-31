package map;

import utilitaire.Images;

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
	private Images image;
	
	public ComposantMap(short x, short y,Images image) {
		if (image != null) {
			this.setImage(image);
		}
		setCoordonneX(x);
		setCoordonneY(y);
	}



	
	public Images getImage() {
		return image;
	}




	public void setImage(Images image) {
		this.image = image;
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
