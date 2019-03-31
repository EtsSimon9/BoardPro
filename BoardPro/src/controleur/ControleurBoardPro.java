package controleur;

import java.util.ArrayList;
import java.util.HashSet;

import composante.ComposanteElectrique;
import composantesCircuit.Bobine;
import composantesCircuit.Condensateur;
import composantesCircuit.Fil;
import composantesCircuit.Resistance;
import composantesCircuit.SourceCourant;
import exceptions.ComposantException;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.transform.Scale;
import map.ComposantMap;
import map.MapParcourable;
import utilitaire.Images;
import utilitaire.Images.Composante;
import vue.ControleurVue;

public class ControleurBoardPro {

	private ControleurVue vue;
	double OgScale = 1;
	private String composante = "Fil";
	private ArrayList<Images> listeImage = new ArrayList<Images>();
	public int numero = 1;
	MapParcourable map = new MapParcourable();

	public ControleurBoardPro() {
		vue = new ControleurVue(this);
	}

	public ControleurVue getVue() {
		return vue;
	}

	public void masterHandler() {

		vue.exitBtn.setOnMouseClicked(genererExitButton());

		vue.scrollP.setHvalue(0.5);
		vue.scrollP.setVvalue(0.5);
		vue.gridP.setOnScroll(genererZoomHandler());

		vue.tbCompList.setOnMouseClicked(genererListClicked());
		vue.gridP.setOnMouseClicked(genererOnMouseClicked());
		vue.tbCompList.setOnDragDetected(dragDetected());
		vue.gridP.setOnDragDropped(dragDropped());
		vue.gridP.setOnDragOver(dragOver());
		vue.gridP.setOnDragDropped(dragDropped());
	}

	private EventHandler<DragEvent> dragOver() {
		EventHandler<DragEvent> retour = new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				if (db.hasString()) {
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
				event.consume();
			}

		};
		return retour;
	}

	private EventHandler<MouseEvent> dragDetected() {
		EventHandler<MouseEvent> retour = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				int p = event.getTarget().toString().indexOf("'");
				composante = event.getTarget().toString().substring(p);
				Dragboard db = vue.tbCompList.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
				content.putString("THIS HAS BEEN DROPPED");
				db.setContent(content);
				event.consume();
			}

		};
		return retour;
	}

	private EventHandler<DragEvent> dragDropped() {
		EventHandler<DragEvent> retour = new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				if (db.hasString()) {
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
				event.consume();
				int positionX = (int) (Math.floor(event.getX() / 75));
				int positionY = (int) (Math.floor(event.getY() / 75));

				if (composante.equals("'Fil'")) {
					genererFil(positionX, positionY);
				} else if (composante.equals("'Résistance'")) {
					genererAutre(positionX, positionY, Composante.Résistance);
				} else if (composante.equals("'Condensateur'")) {
					genererAutre(positionX, positionY, Composante.Condensateur);
				} else if (composante.equals("'Bobine'")) {
					genererAutre(positionX, positionY, Composante.Bobine);
				} else if (composante.equals("'Source'")) {
					genererAutre(positionX, positionY, Composante.Source);
				} else if (composante.equals("'Ampoule'")) {
					genererAutre(positionX, positionY, Composante.Ampoule);
				}
			}

		};
		return retour;
	}

	private EventHandler<ScrollEvent> genererZoomHandler() {
		EventHandler<ScrollEvent> retour = new EventHandler<ScrollEvent>() {
			@Override
			public void handle(ScrollEvent event) {

				double zoomPower = 1.03092783505;
				double delta_y = event.getDeltaY();

				if (delta_y < 0) {
					zoomPower = 0.97;
					if (OgScale > .80) {
						OgScale = OgScale * zoomPower;
					}
				}
				if (delta_y > 0) {
					OgScale = OgScale * zoomPower;
				}

				Scale newScale = new Scale();
				newScale.setPivotX(vue.gridP.getWidth() / 2);
				newScale.setPivotY(vue.gridP.getHeight() / 2);
				newScale.setX(vue.gridP.getScaleX() * zoomPower);
				newScale.setY(vue.gridP.getScaleY() * zoomPower);

				if (OgScale > .80) {
					vue.gridP.getTransforms().add(newScale);
				} else {
					OgScale = .81;
				}

				event.consume();
			}
		};
		return retour;
	}

	private EventHandler<MouseEvent> genererExitButton() {
		EventHandler<MouseEvent> retour = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// vue.boardToolb.setVisible(false);
				// vue.tbCompList.setExpanded(true);
				// vue.tbCompList.depthProperty().set(1);
				System.exit(1);
			}
		};
		return retour;
	}

	private EventHandler<MouseEvent> genererListClicked() {
		EventHandler<MouseEvent> retour = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				int p = event.getTarget().toString().indexOf("'");
				composante = event.getTarget().toString().substring(p);
			}
		};
		return retour;
	}

	private EventHandler<MouseEvent> genererOnMouseClicked() {
		EventHandler<MouseEvent> retour = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				int positionX = (int) (Math.floor(event.getX() / 75));
				int positionY = (int) (Math.floor(event.getY() / 75));

				if (composante.equals("'Fil'")) {
					genererFil(positionX, positionY);
				} else if (composante.equals("'Résistance'")) {
					genererAutre(positionX, positionY, Composante.Résistance);
				} else if (composante.equals("'Condensateur'")) {
					genererAutre(positionX, positionY, Composante.Condensateur);
				} else if (composante.equals("'Bobine'")) {
					genererAutre(positionX, positionY, Composante.Bobine);
				} else if (composante.equals("'Source'")) {
					genererAutre(positionX, positionY, Composante.Source);
				} else if (composante.equals("'Ampoule'")) {
					genererAutre(positionX, positionY, Composante.Ampoule);
				}

			}
		};
		return retour;
	}

	private void genererAutre(int x, int y, Composante nom) {
		Images i = new Images(nom, x, y);
		i.composanteProche(listeImage);
		i.choisirImage(i);
		ajoutComposante(i, null);
	}

	private void genererFil(int x, int y) {
		Images image = new Images(Composante.FilH, x, y);

		/*
		 * Cette ligne fait deux chose, trouve les composantes proches de l'image (1) et
		 * puisque ces images proches doivent peut-être changer, garde la liste des
		 * images a changer
		 */
		HashSet<Integer> aChanger = image.composanteProche(listeImage);
		changerImage(aChanger);

		Composante nom = image.choisirImage(image);
		image.setNom(nom);
		image.creerImage(image.getNom());
		image.creerView();
		ajoutComposante(image, null);

	}

	private void changerImage(HashSet<Integer> lesIndexs) {
		Images image;
		for (Integer i : lesIndexs) {
			image = listeImage.get(i);
			Composante avant = image.getNom();
			int rotAvant = image.getRotation();

			Composante apres = image.choisirImage(image);
			int rotApres = image.getRotation();
			if (!avant.equals(apres) || rotAvant != rotApres) {
				ImageView aEnlever = image.getView();
				image.setNom(apres);
				image.creerImage(apres);
				image.creerView();
				ajoutComposante(image, aEnlever);
			}
		}
	}

	private void ajoutComposante(Images image, ImageView aEnlever) {
		// Remove si à la meme position
		for (int i = 0; i < listeImage.size(); i++) {
			if (listeImage.get(i) == image || (listeImage.get(i).getPositionX() == image.getPositionX()
					&& listeImage.get(i).getPositionY() == image.getPositionY())) {
				// Remove de la map
				for (int j = 0; j < map.getComposantsActuels().size(); j++) {
					if (map.getComposantsActuels().get(j).getImage().getView().equals(aEnlever) || map
							.getComposantsActuels().get(j).getImage().getView().equals(listeImage.get(i).getView())) {
						map.removeComposante(map.getComposantsActuels().get(j));
					}
				}

				// Remove de la grille et de la liste d'image

				// Premier pour clique par dessus
				vue.gridP.getChildren().remove(listeImage.get(i).getView());
				// Lui pour les changements d'image
				vue.gridP.getChildren().remove(aEnlever);
				listeImage.remove(i);

			}
		}
		listeImage.add(image);
		ImageView v = image.getView();
		v.setRotate(image.getRotation());
		vue.gridP.add(v, image.getPositionX(), image.getPositionY());
		numero++;

		// -----------------------Ajout dans composante Map--------------------
		try {
			ComposantMap compo = null;
			if (image.getNom().equals(Composante.Ampoule)) {
				// Ampoule n'existe pas encore..
			} else if (image.getNom().equals(Composante.Bobine)) {
				compo = new Bobine((short) image.getPositionX(), (short) image.getPositionY(), image);
			} else if (image.getNom().equals(Composante.Condensateur)) {
				compo = new Condensateur((short) image.getPositionX(), (short) image.getPositionY(), image);
			} else if (image.getNom().equals(Composante.Résistance)) {
				compo = new Resistance((short) image.getPositionX(), (short) image.getPositionY(), image);
			} else if (image.getNom().equals(Composante.Source)) {
				compo = new SourceCourant((short) image.getPositionX(), (short) image.getPositionY(), image);
			} else {
				compo = new Fil((short) image.getPositionX(), (short) image.getPositionY(), image);
			}
			if (compo != null) {
				map.addComposant(compo);
			}
		} catch (ComposantException e) {
			e.printStackTrace();
		}
	}

}