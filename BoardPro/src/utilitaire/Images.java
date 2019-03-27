package utilitaire;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utilitaire.Images.Composante;

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
		Image i = creerImage(nom);
		this.setImage(i);
		ImageView view = creerView(i);
		view.setRotate(rotation);
		this.setView(view);
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
		this.setView(creerView(image));
	}

	private Image creerImage(Composante nom) {
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
		return i;
	}

	private ImageView creerView(Image image) {
		ImageView v = null;
		if (image != null) {
			v = new ImageView(this.getImage());
			v.setFitWidth(DIMMENSIONS);
			v.setFitHeight(DIMMENSIONS);
		}
		return v;
	}

	public ArrayList<Integer> getDghb() {
		return dghb;
	}

	public void setDghb(ArrayList<Integer> dghb) {
		if (dghb.get(0) == 1 && dghb.get(1) == 1 && dghb.get(2) == 0 && dghb.get(3) == 0) {
			this.setImage(creerImage(Composante.FilH));
			this.dghb = dghb;

		} else if (dghb.get(0) == 0 && dghb.get(1) == 0 && dghb.get(2) == 1 && dghb.get(3) == 1) {
			this.setImage(creerImage(Composante.FilV));
			this.dghb = dghb;

		} else if (dghb.get(1) == 0 && dghb.get(1) == 0 && dghb.get(2) == 1 && dghb.get(3) == 1) {
			this.setImage(creerImage(Composante.FilAll));
			this.dghb = dghb;

		} else if (dghb.get(0) == 1 && dghb.get(1) == 1 && dghb.get(2) == 1 && dghb.get(3) == 0) {
			this.setImage(creerImage(Composante.FilGHD));
			this.dghb = dghb;

		} else if (dghb.get(0) == 1 && dghb.get(1) == 1 && dghb.get(2) == 0 && dghb.get(3) == 1) {
			this.setImage(creerImage(Composante.FilGBD));
			this.dghb = dghb;

		} else if (dghb.get(0) == 1 && dghb.get(1) == 0 && dghb.get(2) == 1 && dghb.get(3) == 1) {
			this.setImage(creerImage(Composante.FilBDH));
			this.dghb = dghb;

		} else if (dghb.get(0) == 0 && dghb.get(1) == 1 && dghb.get(2) == 1 && dghb.get(3) == 1) {
			this.setImage(creerImage(Composante.FilBGH));
			this.dghb = dghb;

		} else if (dghb.get(0) == 1 && dghb.get(1) == 0 && dghb.get(2) == 0 && dghb.get(3) == 1) {
			this.setImage(creerImage(Composante.FilBD));
			this.dghb = dghb;

		} else if (dghb.get(0) == 0 && dghb.get(1) == 1 && dghb.get(2) == 0 && dghb.get(3) == 1) {
			this.setImage(creerImage(Composante.FilBG));
			this.dghb = dghb;

		} else if (dghb.get(0) == 0 && dghb.get(1) == 1 && dghb.get(2) == 1 && dghb.get(3) == 0) {
			this.setImage(creerImage(Composante.FilHG));
			this.dghb = dghb;

		} else if (dghb.get(0) == 1 && dghb.get(1) == 0 && dghb.get(2) == 1 && dghb.get(3) == 0) {
			this.setImage(creerImage(Composante.FilHD));
			this.dghb = dghb;

		} else if (dghb.get(0) == 0 && dghb.get(1) == 0 && dghb.get(2) == 1 && dghb.get(3) == 0) {
			this.setImage(creerImage(Composante.FilV));
			this.dghb = dghb;
		} else if (dghb.get(0) == 0 && dghb.get(1) == 0 && dghb.get(2) == 0 && dghb.get(3) == 1) {
			this.setImage(creerImage(Composante.FilV));
			this.dghb = dghb;
		} else {
			this.setImage(creerImage(Composante.FilH));
			this.dghb = dghb;
		}
	}

}
