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

	private int positionX;
	private int positionY;
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
	private ArrayList<Integer> dghb;

	public Images(Composante nom, int px, int py) {
		if (nom != null) {
			this.setNom(nom);
		}
		dghb = new ArrayList<Integer>();
		dghb.add(0);
		dghb.add(0);
		dghb.add(0);
		dghb.add(0);
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

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
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
		} else if (nom.equals(Composante.FilH)) {
			i = new Image("/img/Composante_fil.png");
		} else if (nom.equals(Composante.FilV)) {
			i = new Image("/img/Composante_fil.png");
			this.setRotation(90);
		} else if (nom.equals(Composante.FilBD)) {
			i = new Image("/img/Composante_fil.png");
		} else if (nom.equals(Composante.FilBG)) {
			i = new Image("/img/Composante_fil.png");
		} else if (nom.equals(Composante.FilHD)) {
			i = new Image("/img/Composante_fil.png");
		} else if (nom.equals(Composante.FilHG)) {
			i = new Image("/img/Composante_fil.png");
		} else if (nom.equals(Composante.FilBGH)) {
			i = new Image("/img/Composante_fil.png");
		} else if (nom.equals(Composante.FilBDH)) {
			i = new Image("/img/Composante_fil.png");
		} else if (nom.equals(Composante.FilGBD)) {
			i = new Image("/img/Composante_fil.png");
		} else if (nom.equals(Composante.FilGHD)) {
			i = new Image("/img/Composante_fil.png");
		} else if (nom.equals(Composante.FilAll)) {
			i = new Image("/img/Composante_fil.png");
		} else if (nom.equals(Composante.Source)) {
			i = new Image("/img/sourceAC.png");
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

	public ArrayList<Integer> getDghb() {
		return dghb;
	}

	public void setDghb(ArrayList<Integer> dghb) {
		this.dghb = dghb;
	}

	/**
	 * 
	 * @param listeImage
	 * @return Retourne un tableau des index des composantes qui doivent être
	 *         changer (on a ajouter qq chose près)
	 */
	public HashSet<Integer> composanteProche(ArrayList<Images> listeImage) {
		HashSet<Integer> indexaModif = new HashSet<Integer>();
		// Vérification à Droite
		for (int i = 0; i < listeImage.size(); i++) {
			if (listeImage.get(i).getPositionX() == positionX + 1 && listeImage.get(i).getPositionY() == positionY) {
				dghb.set(0, 1);
				// Puisque l'image à droite doit aussi considérer l'image actuelle à sa gauche
				listeImage.get(i).getDghb().set(1, 1);
				indexaModif.add(i);
			}
		}

		// Vérification à Gauche
		for (int i = 0; i < listeImage.size(); i++) {
			if (listeImage.get(i).getPositionX() == positionX - 1 && listeImage.get(i).getPositionY() == positionY) {
				dghb.set(1, 1);
				// Puisque l'image à gauche doit aussi considérer l'image actuelle à sa droite
				listeImage.get(i).getDghb().set(0, 1);
				indexaModif.add(i);
			}
		}

		// Vérification Haut
		for (int i = 0; i < listeImage.size(); i++) {
			if (listeImage.get(i).getPositionX() == positionX && listeImage.get(i).getPositionY() == positionY - 1) {
				dghb.set(2, 1);
				// Puisque l'image à gauche doit aussi considérer l'image actuelle à sa gauche
				listeImage.get(i).getDghb().set(3, 1);
				indexaModif.add(i);
			}
		}

		// Vérification Bas
		for (int i = 0; i < listeImage.size(); i++) {
			if (listeImage.get(i).getPositionX() == positionX && listeImage.get(i).getPositionY() == positionY + 1) {
				dghb.set(3, 1);
				// Puisque l'image à gauche doit aussi considérer l'image actuelle à sa gauche
				listeImage.get(i).getDghb().set(2, 1);
				indexaModif.add(i);
			}
		}
		return indexaModif;
	}

	public Composante choisirImage(Images i) {
		Composante retour = null;

		if (i.getNom().toString().substring(0, 3).equals("Fil")) {
			retour = choisirImageFil(i);
		}

		else if (i.getNom().equals(Composante.Condensateur)) {
			retour = Composante.Condensateur;
			if (i.dghb.get(2) == 1 && i.dghb.get(3) == 1) {
				i.setRotation(90);
			}
		} else if (i.getNom().equals(Composante.Ampoule)) {
			retour = Composante.Ampoule;
			if (i.dghb.get(2) == 1 && i.dghb.get(3) == 1) {
				i.setRotation(90);
			}
		} else if (i.getNom().equals(Composante.Bobine)) {
			retour = Composante.Bobine;
			if (i.dghb.get(2) == 1 && i.dghb.get(3) == 1) {
				i.setRotation(90);
			}
		} else if (i.getNom().equals(Composante.Résistance)) {
			retour = Composante.Résistance;
			if (i.dghb.get(2) == 1 && i.dghb.get(3) == 1) {
				i.setRotation(90);
			}
		} else if (i.getNom().equals(Composante.Source)) {
			retour = Composante.Source;
			if (i.dghb.get(0) == 1 && i.dghb.get(1) == 1) {
				i.setRotation(90);
			}
		}
		return retour;
	}

	public Composante choisirImageFil(Images i) {
		Composante retour = Composante.FilH;

		if (i.getDghb().get(0) == 1 && i.getDghb().get(1) == 1 && i.getDghb().get(2) == 0 && i.getDghb().get(3) == 0) {
			retour = Composante.FilH;

		} else if (i.getDghb().get(0) == 0 && i.getDghb().get(1) == 0 && i.getDghb().get(2) == 1 && i.getDghb().get(3) == 1) {
			retour = Composante.FilV;

		} else if (i.getDghb().get(1) == 0 && i.getDghb().get(1) == 0 && i.getDghb().get(2) == 1 && i.getDghb().get(3) == 1) {
			retour = Composante.FilAll;

		} else if (i.getDghb().get(0) == 1 && i.getDghb().get(1) == 1 && i.getDghb().get(2) == 1 && i.getDghb().get(3) == 0) {
			retour = Composante.FilGHD;

		} else if (i.getDghb().get(0) == 1 && i.getDghb().get(1) == 1 && i.getDghb().get(2) == 0 && i.getDghb().get(3) == 1) {
			retour = Composante.FilGBD;

		} else if (i.getDghb().get(0) == 1 && i.getDghb().get(1) == 0 && i.getDghb().get(2) == 1 && i.getDghb().get(3) == 1) {
			retour = Composante.FilBDH;

		} else if (i.getDghb().get(0) == 0 && i.getDghb().get(1) == 1 && i.getDghb().get(2) == 1 && i.getDghb().get(3) == 1) {
			retour = Composante.FilBGH;

		} else if (i.getDghb().get(0) == 1 && i.getDghb().get(1) == 0 && i.getDghb().get(2) == 0 && i.getDghb().get(3) == 1) {
			retour = Composante.FilBD;

		} else if (i.getDghb().get(0) == 0 && i.getDghb().get(1) == 1 && i.getDghb().get(2) == 0 && i.getDghb().get(3) == 1) {
			retour = Composante.FilBG;

		} else if (i.getDghb().get(0) == 0 && i.getDghb().get(1) == 1 && i.getDghb().get(2) == 1 && i.getDghb().get(3) == 0) {
			retour = Composante.FilHG;

		} else if (i.getDghb().get(0) == 1 && i.getDghb().get(1) == 0 && i.getDghb().get(2) == 1 && i.getDghb().get(3) == 0) {
			retour = Composante.FilHD;

		} else if (i.getDghb().get(0) == 0 && i.getDghb().get(1) == 0 && i.getDghb().get(2) == 1 && i.getDghb().get(3) == 0) {
			retour = Composante.FilV;
		} else if (i.getDghb().get(0) == 0 && i.getDghb().get(1) == 0 && i.getDghb().get(2) == 0 && i.getDghb().get(3) == 1) {
			retour = Composante.FilV;
		}
		return retour;
	}
}
