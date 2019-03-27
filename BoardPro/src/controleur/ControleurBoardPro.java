package controleur;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.transform.Scale;
import utilitaire.Calcul;
import utilitaire.Images;
import utilitaire.Images.Composante;
import vue.ControleurVue;

public class ControleurBoardPro {

	private ControleurVue vue;
	double OgScale = 1;
	private String composante = "fil";
	private ArrayList<Images> listeImage = new ArrayList<Images>();

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
		vue.tbCompList.setOnMouseDragEntered(genererOnMouseDrag());

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
					genererFil(event);
				} else if (composante.equals("'Résistance'")) {
					Images image = new Images(Composante.Résistance, positionX, positionY);
					vue.gridP.add(image.getView(), image.getPositionX(), image.getPositionY());
					listeImage.add(image);
				} else if (composante.equals("'Condensateur'")) {
					Images image = new Images(Composante.Condensateur, positionX, positionY);
					vue.gridP.add(image.getView(), image.getPositionX(), image.getPositionY());
					listeImage.add(image);
				} else if (composante.equals("'Bobine'")) {
					Images image = new Images(Composante.Bobine, positionX, positionY);
					vue.gridP.add(image.getView(), image.getPositionX(), image.getPositionY());
					listeImage.add(image);
				} else if (composante.equals("'Source'")) {
					Images image = new Images(Composante.Source, positionX, positionY);
					vue.gridP.add(image.getView(), image.getPositionX(), image.getPositionY());
					listeImage.add(image);
				} else if (composante.equals("'Ampoule'")) {
					Images image = new Images(Composante.Ampoule, positionX, positionY);
					vue.gridP.add(image.getView(), image.getPositionX(), image.getPositionY());
					listeImage.add(image);
				}
			}
		};
		return retour;
	}

	private EventHandler<MouseEvent> genererOnMouseDrag() {
		EventHandler<MouseEvent> retour = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (vue.tbCompList.getSelectionModel().getSelectedItem() == "resistance") {
					System.out.println("allo");
				}

			}

		};
		return retour;
	}

	private EventHandler<MouseEvent> genererDeleteComp() {
		EventHandler<MouseEvent> retour = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println(event.getSource());

			}

		};
		return retour;
	}

	private void genererFil(MouseEvent event) {
		int positionX = (int) (Math.floor(event.getX() / 75));
		int positionY = (int) (Math.floor(event.getY() / 75));
		ArrayList<Integer> dghb = new ArrayList<Integer>();
		dghb.add(0);
		dghb.add(0);
		dghb.add(0);
		dghb.add(0);
		for (int i = 0; i < listeImage.size(); i++) {
			if (listeImage.get(i).getPositionX() == positionX - 1 && listeImage.get(i).getPositionY() == positionY) {
				dghb.set(0, 1);
				ArrayList<Integer> a = listeImage.get(i).getDghb();
				a.set(1, 1);
				listeImage.get(i).setDghb(a);
			}
		}

		for (int i = 0; i < listeImage.size(); i++) {
			if (listeImage.get(i).getPositionX() == positionX + 1 && listeImage.get(i).getPositionY() == positionY) {
				dghb.set(1, 1);
				ArrayList<Integer> a = listeImage.get(i).getDghb();
				a.set(1, 1);
				listeImage.get(i).setDghb(a);
			}
		}

		for (int i = 0; i < listeImage.size(); i++) {
			if (listeImage.get(i).getPositionX() == positionX && listeImage.get(i).getPositionY() == positionY + 1) {
				dghb.set(2, 1);
				ArrayList<Integer> a = listeImage.get(i).getDghb();
				a.set(1, 1);
				listeImage.get(i).setDghb(a);
			}
		}

		for (int i = 0; i < listeImage.size(); i++) {
			if (listeImage.get(i).getPositionX() == positionX && listeImage.get(i).getPositionY() == positionY - 1) {
				dghb.set(3, 1);
				ArrayList<Integer> a = listeImage.get(i).getDghb();
				a.set(1, 1);
				listeImage.get(i).setDghb(a);
			}

		}
		Images image = null;
		if (dghb.get(0) == 1 && dghb.get(1) == 1 && dghb.get(2) == 0 && dghb.get(3) == 0) {
			image = new Images(Composante.FilH, positionX, positionY);
			image.setDghb(dghb);

		} else if (dghb.get(0) == 0 && dghb.get(1) == 0 && dghb.get(2) == 1 && dghb.get(3) == 1) {
			image = new Images(Composante.FilV, positionX, positionY);
			image.setDghb(dghb);

		} else if (dghb.get(1) == 0 && dghb.get(1) == 0 && dghb.get(2) == 1 && dghb.get(3) == 1) {
			image = new Images(Composante.FilAll, positionX, positionY);
			image.setDghb(dghb);

		} else if (dghb.get(0) == 1 && dghb.get(1) == 1 && dghb.get(2) == 1 && dghb.get(3) == 0) {
			image = new Images(Composante.FilGHD, positionX, positionY);
			image.setDghb(dghb);

		} else if (dghb.get(0) == 1 && dghb.get(1) == 1 && dghb.get(2) == 0 && dghb.get(3) == 1) {
			image = new Images(Composante.FilGBD, positionX, positionY);
			image.setDghb(dghb);

		} else if (dghb.get(0) == 1 && dghb.get(1) == 0 && dghb.get(2) == 1 && dghb.get(3) == 1) {
			image = new Images(Composante.FilBDH, positionX, positionY);
			image.setDghb(dghb);

		} else if (dghb.get(0) == 0 && dghb.get(1) == 1 && dghb.get(2) == 1 && dghb.get(3) == 1) {
			image = new Images(Composante.FilBGH, positionX, positionY);
			image.setDghb(dghb);

		} else if (dghb.get(0) == 1 && dghb.get(1) == 0 && dghb.get(2) == 0 && dghb.get(3) == 1) {
			image = new Images(Composante.FilBD, positionX, positionY);
			image.setDghb(dghb);

		} else if (dghb.get(0) == 0 && dghb.get(1) == 1 && dghb.get(2) == 0 && dghb.get(3) == 1) {
			image = new Images(Composante.FilBG, positionX, positionY);
			image.setDghb(dghb);

		} else if (dghb.get(0) == 0 && dghb.get(1) == 1 && dghb.get(2) == 1 && dghb.get(3) == 0) {
			image = new Images(Composante.FilHG, positionX, positionY);
			image.setDghb(dghb);

		} else if (dghb.get(0) == 1 && dghb.get(1) == 0 && dghb.get(2) == 1 && dghb.get(3) == 0) {
			image = new Images(Composante.FilHD, positionX, positionY);
			image.setDghb(dghb);

		} else if (dghb.get(0) == 0 && dghb.get(1) == 0 && dghb.get(2) == 1 && dghb.get(3) == 0) {
			image = new Images(Composante.FilV, positionX, positionY);
			image.setDghb(dghb);
		} else if (dghb.get(0) == 0 && dghb.get(1) == 0 && dghb.get(2) == 0 && dghb.get(3) == 1) {
			image = new Images(Composante.FilV, positionX, positionY);
			image.setDghb(dghb);
		} else {
			image = new Images(Composante.FilH, positionX, positionY);
			image.setDghb(dghb);
		}

		vue.gridP.add(image.getView(), image.getPositionX(), image.getPositionY());
		listeImage.add(image);

	}
}