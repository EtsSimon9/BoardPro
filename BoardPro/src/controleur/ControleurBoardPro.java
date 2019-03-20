package controleur;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.transform.Scale;
import vue.ControleurVue;

public class ControleurBoardPro {

	private ControleurVue vue;
	double OgScale = 1;
	private String composante = "fil";

	public ControleurBoardPro() {
		vue = new ControleurVue(this);
	}

	public ControleurVue getVue() {
		return vue;
	}

	public void masterHandler() {

		vue.exitBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// vue.boardToolb.setVisible(false);
				// vue.tbCompList.setExpanded(true);
				// vue.tbCompList.depthProperty().set(1);
				System.exit(1);
			}
		});

		// ---------------------------ZOOMING----------------------------
		vue.scrollP.setHvalue(0.5);
		vue.scrollP.setVvalue(0.5);
		vue.gridP.setOnScroll(new EventHandler<ScrollEvent>() {
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
			}});

		
		vue.tbCompList.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				int p = event.getTarget().toString().indexOf("'");
				composante = event.getTarget().toString().substring(p);
			}});
		
		// -------------------------SELECT & PUT------------------------
		
		vue.gridP.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				int positionX = (int) (Math.floor(event.getX() / 75));
				int positionY = (int) (Math.floor(event.getY() / 75));
				if (composante.equals("'Fil'")) {
				} else if (composante.equals("'resistance'")) {
					Image r = new Image("/img/Composante_resistance.png");
					ImageView viewR = new ImageView(r);
					viewR.setFitWidth(75);
					viewR.setFitHeight(75);
					vue.gridP.add(viewR, positionX, positionY);

				} else if (composante.equals("'Condensateur'")) {
					Image r = new Image("/img/condensateur.png");
					ImageView viewR = new ImageView(r);
					viewR.setFitWidth(75);
					viewR.setFitHeight(75);
					vue.gridP.add(viewR, positionX, positionY);

				} else if (composante.equals("'Bobine'")) {
					Image r = new Image("/img/bobine.png");
					ImageView viewR = new ImageView(r);
					viewR.setFitWidth(75);
					viewR.setFitHeight(75);
					vue.gridP.add(viewR, positionX, positionY);

				} else if (composante.equals("'source'")) {
					Image r = new Image("/img/sourceAC.png");
					ImageView viewR = new ImageView(r);
					viewR.setFitWidth(75);
					viewR.setFitHeight(75);
					vue.gridP.add(viewR, positionX, positionY);

				} else if (composante.equals("'Ampoule'")) {
					Image r = new Image("/img/ampoule.png");
					ImageView viewR = new ImageView(r);
					viewR.setFitWidth(75);
					viewR.setFitHeight(75);
					vue.gridP.add(viewR, positionX, positionY);

				} else if (composante.equals("'fil'")) {
					Image r = new Image("/img/Composante_fil.png");
					ImageView viewR = new ImageView(r);
					viewR.setFitWidth(75);
					viewR.setFitHeight(75);
					vue.gridP.add(viewR, positionX, positionY);
				}
			}

		});

		// -------------------------DRAG & DROP------------------------

		vue.tbCompList.setOnMouseDragEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (vue.tbCompList.getSelectionModel().getSelectedItem() == "resistance") {
					System.out.println("allo");
				}
			}
		});

		// -------------------------SELECT & PUT------------------------

	}

}