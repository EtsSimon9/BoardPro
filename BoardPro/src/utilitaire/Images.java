package utilitaire;

import java.util.ArrayList;
import java.util.HashSet;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Images {
	public enum Composante {
		FilH, FilV, FilBD, FilBG, FilHG, FilHD, FilGBD, FilGHD, FilBGH, FilBDH, FilAll, Résistance, Condensateur,
		Bobine, Ampoule, Source
	};

	private byte positionX;
	private byte positionY;
	private ImageView view;
	private Image image;
	private Composante nom;
	private static final short DIMMENSIONS = 75;
	private int rotation = 0;
	/**
	 * dghb :Droite Gauche Haut Bas, si une image est directement à gauche de cette
	 * image, la 2e valeur de l'arraylist est 1, sinon 0. Droite:1er position,
	 * Gauche 2e position, Haut 3e et Bas 4e dans l'array
	 */
	private byte[] dghb;

	public Images(Composante nom, byte px, byte py) {
		if (nom != null) {
			this.setNom(nom);
		}
		dghb = new byte[4];
		this.setPositionX(px);
		this.setPositionY(py);
		creerImage(nom);
		creerView();
	}

	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	public Composante getNom() {
		return nom;
	}

	public void setNom(Composante nom) {
		this.nom = nom;
	}

	public byte getPositionX() {
		return positionX;
	}

	public void setPositionX(byte positionX) {
		this.positionX = positionX;
	}

	public byte getPositionY() {
		return positionY;
	}

	public void setPositionY(byte positionY) {
		this.positionY = positionY;
	}

	public ImageView getView() {
		return view;
	}

	public void setView(ImageView view) {
		this.view = view;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void creerImage(Composante nom) {
		Image i = null;

		if (nom.equals(Composante.Résistance)) {
			i = new Image("/img/Composante_resistance.png");
		} else if (nom.equals(Composante.Condensateur)) {
			i = new Image("/img/condensateur.png");
		} else if (nom.equals(Composante.Bobine)) {
			i = new Image("/img/bobine.png");
		} else if (nom.equals(Composante.FilAll)) {
			i = new Image("/img/4way.png");
		} else if (nom.equals(Composante.FilH)) {
			i = new Image("/img/Composante_fil.png");
			this.setRotation(0);
		} else if (nom.equals(Composante.FilV)) {
			i = new Image("/img/Composante_fil.png");
			this.setRotation(90);
		} else if (nom.equals(Composante.FilBD)) {
			i = new Image("/img/coin.png");
			this.setRotation(180);
		} else if (nom.equals(Composante.FilBG)) {
			i = new Image("/img/coin.png");
			this.setRotation(270);
		} else if (nom.equals(Composante.FilHD)) {
			i = new Image("/img/coin.png");
			this.setRotation(90);
		} else if (nom.equals(Composante.FilHG)) {
			i = new Image("/img/coin.png");
			this.setRotation(0);
		} else if (nom.equals(Composante.FilBGH)) {
			i = new Image("/img/3 way.png");
			this.setRotation(270);
		} else if (nom.equals(Composante.FilBDH)) {
			i = new Image("/img/3 way.png");
			this.setRotation(90);
		} else if (nom.equals(Composante.FilGBD)) {
			i = new Image("/img/3 way.png");
			this.setRotation(180);
		} else if (nom.equals(Composante.FilGHD)) {
			i = new Image("/img/3 way.png");
			this.setRotation(0);
		} else if (nom.equals(Composante.Source)) {
			i = new Image("/img/source.png");
		} else if (nom.equals(Composante.Ampoule)) {
			i = new Image("/img/ampoule.png");
		}
		this.setImage(i);
	}

	public void creerView() {
		ImageView v = null;
		if (image != null) {
			v = new ImageView(this.getImage());
			v.setFitWidth(DIMMENSIONS);
			v.setFitHeight(DIMMENSIONS);
			v.setRotate(this.getRotation());
		}
		this.setView(v);
	}

	public byte[] getDghb() {
		return dghb;
	}

	public void setDghb(byte[] dghb) {
		this.dghb = dghb;
	}

	/**
	 * 
	 * @param listeImage
	 * @return Retourne un tableau des index des composantes qui doivent être
	 *         changer (on a ajouter qq chose près)
	 */
	public HashSet<Byte> composanteProche(ArrayList<Images> listeImage) {
		HashSet<Byte> indexaModif = new HashSet<Byte>();
		// Vérification à Droite
		for (int i = 0; i < listeImage.size(); i++) {
			if (listeImage.get(i).getPositionX() == positionX + 1 && listeImage.get(i).getPositionY() == positionY) {
				this.dghb[0] = 1;
				// Puisque l'image à gauche doit aussi considérer l'image actuelle à sa droite
				listeImage.get(i).getDghb()[1] = 1;
				indexaModif.add((byte) i);
			}
		}

		// Vérification à Gauche
		for (int i = 0; i < listeImage.size(); i++) {
			if (listeImage.get(i).getPositionX() == positionX - 1 && listeImage.get(i).getPositionY() == positionY) {
				this.dghb[1] = 1;
				// Puisque l'image à gauche doit aussi considérer l'image actuelle à sa droite
				listeImage.get(i).getDghb()[0] = 1;
				indexaModif.add((byte) i);
			}
		}

		// Vérification Haut
		for (int i = 0; i < listeImage.size(); i++) {
			if (listeImage.get(i).getPositionX() == positionX && listeImage.get(i).getPositionY() == positionY - 1) {
				this.dghb[2] = 1;
				// Puisque l'image à gauche doit aussi considérer l'image actuelle à sa gauche
				listeImage.get(i).getDghb()[3] = 1;
				indexaModif.add((byte) i);
			}
		}

		// Vérification Bas
		for (int i = 0; i < listeImage.size(); i++) {
			if (listeImage.get(i).getPositionX() == positionX && listeImage.get(i).getPositionY() == positionY + 1) {
				this.dghb[3] = 1;
				// Puisque l'image à gauche doit aussi considérer l'image actuelle à sa gauche
				listeImage.get(i).getDghb()[2] = 1;
				indexaModif.add((byte) i);
			}
		}
		return indexaModif;
	}

	public Composante choisirImage(Images i) {
		Composante retour = null;

		if (i.dghb[2] == 1 || i.dghb[3] == 1) {
			i.setRotation(90);
		}
		if (i.dghb[0] == 1 || i.dghb[1] == 1) {
			i.setRotation(0);
		}
		if (i.dghb[2] == 1 && i.dghb[3] == 1) {
			i.setRotation(90);
		}
		if (i.dghb[0] == 1 && i.dghb[1] == 1) {
			i.setRotation(0);
		}
		if (i.getNom().toString().substring(0, 3).equals("Fil")) {
			retour = choisirImageFil(i);
		}

		else if (i.getNom().equals(Composante.Condensateur)) {
			retour = Composante.Condensateur;

		} else if (i.getNom().equals(Composante.Ampoule)) {
			retour = Composante.Ampoule;
		} else if (i.getNom().equals(Composante.Bobine)) {
			retour = Composante.Bobine;
		} else if (i.getNom().equals(Composante.Résistance)) {
			retour = Composante.Résistance;
		} else if (i.getNom().equals(Composante.Source)) {
			retour = Composante.Source;
		}
		return retour;
	}

	public Composante choisirImageFil(Images i) {
		Composante retour = Composante.FilH;

		if (i.getDghb()[0] == 1 && i.getDghb()[1] == 1 && i.getDghb()[2] == 1 && i.getDghb()[3] == 1) {
			retour = Composante.FilAll;

		} else if (i.getDghb()[0] == 1 && i.getDghb()[1] == 1 && i.getDghb()[2] == 1
				&& i.getDghb()[3] == 0) {
			retour = Composante.FilGHD;

		} else if (i.getDghb()[0] == 1 && i.getDghb()[1] == 1 && i.getDghb()[2] == 0
				&& i.getDghb()[3] == 1) {
			retour = Composante.FilGBD;

		} else if (i.getDghb()[0] == 1 && i.getDghb()[1] == 0 && i.getDghb()[2] == 1
				&& i.getDghb()[3] == 1) {
			retour = Composante.FilBDH;

		} else if (i.getDghb()[0] == 0 && i.getDghb()[1] == 1 && i.getDghb()[2] == 1
				&& i.getDghb()[3] == 1) {
			retour = Composante.FilBGH;

		} else if (i.getDghb()[0] == 1 && i.getDghb()[1] == 0 && i.getDghb()[2] == 0
				&& i.getDghb()[3] == 1) {
			retour = Composante.FilBD;

		} else if (i.getDghb()[0] == 0 && i.getDghb()[1] == 1 && i.getDghb()[2] == 0
				&& i.getDghb()[3] == 1) {
			retour = Composante.FilBG;

		} else if (i.getDghb()[0] == 0 && i.getDghb()[1] == 1 && i.getDghb()[2] == 1
				&& i.getDghb()[3] == 0) {
			retour = Composante.FilHG;

		} else if (i.getDghb()[0] == 1 && i.getDghb()[1] == 0 && i.getDghb()[2] == 1
				&& i.getDghb()[3] == 0) {
			retour = Composante.FilHD;

		}
		if (i.getDghb()[0] == 1 && i.getDghb()[1] == 1 && i.getDghb()[2] == 0 && i.getDghb()[3] == 0) {
			retour = Composante.FilH;

		} else if (i.getDghb()[0] == 0 && i.getDghb()[1] == 0 && i.getDghb()[2] == 1
				&& i.getDghb()[3] == 1) {
			retour = Composante.FilV;

		} else if (i.getDghb()[0] == 0 && i.getDghb()[1] == 0 && i.getDghb()[2] == 1
				&& i.getDghb()[3] == 0) {
			retour = Composante.FilV;
		} else if (i.getDghb()[0] == 0 && i.getDghb()[1] == 0 && i.getDghb()[2] == 0
				&& i.getDghb()[3] == 1) {
			retour = Composante.FilV;
		}
		return retour;
	}
}
