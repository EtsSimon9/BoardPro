package controleur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import composante.CE2Entrees;
import composantesCircuit.Bobine;
import composantesCircuit.Condensateur;
import composantesCircuit.Fil;
import composantesCircuit.Resistance;
import composantesCircuit.SourceCourant;
import exceptions.ComposantException;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import map.ComposantMap;
import map.MapParcourable;
import utilitaire.Images;
import utilitaire.Images.Composante;
import vue.ControleurDrawerVue;
import vue.ControleurVue;

public class ControleurBoardPro {

	private ControleurVue vue;
	private ControleurDrawerVue vue2;
	double OgScale = 1;
	public static String composante = "fil";
	private ArrayList<Images> listeImage = new ArrayList<Images>();
	public int numero = 1;
	MapParcourable map = new MapParcourable();
	Label nom = new Label();
	boolean effacer = false;
	boolean playing = false;
	ArrayList<FadeTransition> listeFade = new ArrayList<FadeTransition>();
	 
	public ControleurBoardPro() {
		vue = new ControleurVue(this);
	}

	public ControleurVue getVue() {
		return vue;
	}

	public ControleurDrawerVue getVue2() {
		return vue2;
	}

	public void masterHandler() {
		vue.exitBtn.setOnMouseClicked(genererExitButton());
		nom.setFont(new Font(50));
		nom.setTranslateX(150);
		vue.paneGraph.getChildren().add(nom);
		vue.scrollP.setHvalue(0.5);
		vue.scrollP.setVvalue(0.5);
		vue.gridP.setOnScroll(genererZoomHandler());
		vue.gridP.setOnMouseClicked(genererOnMouseClicked());
		vue.tbReset.setOnAction(resetHandler());
		vue.tbRemove.setOnAction(enleverHandler());
		vue.tbPlay.setOnAction(play());
		vue.rlc.setOnMouseClicked(rlcHandler());
		vue.rl.setOnMouseClicked(rlHandler());
		vue.rc.setOnMouseClicked(rcHandler());
		vue.r.setOnMouseClicked(rHandler());
		vue.c.setOnMouseClicked(cHandler());
		vue.l.setOnMouseClicked(lHandler());
		vue.gridP.setOnDragDropped(dragDropped());
		vue.gridP.setOnDragOver(dragOver());

		vue.tbEnregistrer.setOnAction(enregistrerHandler());
		vue.tbOuvrir.setOnAction(ouvrirHandler());
		vue.tbReset.setOnAction(resetHandler());
		vue.tbScreenShot.setOnAction(screenshotHandler());
	}

	private EventHandler<ActionEvent> play() {
		EventHandler<ActionEvent> retour = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if (playing == false) {
					if (map.getMaille().size() != 0) {
						playing = true;
						vue.tbPlay.setStyle("-fx-background-color:c4c297");
						vue.graphiqueTemps.reset();
						vue.graphiqueTemps.start();
						for (int i = 1; i < vue.gridP.getChildren().size(); i++) {
							FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.8),
									vue.gridP.getChildren().get(i));
							fadeTransition.setFromValue(1.0);
							fadeTransition.setToValue(0.3);
							fadeTransition.setAutoReverse(true);
							fadeTransition.setCycleCount(Animation.INDEFINITE);
							listeFade.add(fadeTransition);
							fadeTransition.play();
						}
					}
				} else {
					playing = false;
					vue.tbPlay.setStyle(null);
					vue.graphiqueTemps.stop();
					for (FadeTransition i : listeFade) {
						i.pause();
						i.jumpTo(Duration.ZERO);
					}
				}
			}

		};
		return retour;
	}

	private EventHandler<ActionEvent> enleverHandler() {
		EventHandler<ActionEvent> retour = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				vue.tbRemove.setStyle("-fx-background-color:c4c297");

				if (!effacer) {
					effacer = true;
					// Activer handler enlève pour la grille
					vue.gridP.setOnMouseClicked(new EventHandler<MouseEvent>() {

						@Override
						public void handle(MouseEvent event) {
							byte positionX = (byte) (Math.floor(event.getX() / 75));
							byte positionY = (byte) (Math.floor(event.getY() / 75));

							for (byte i = 0; i < listeImage.size(); i++) {
								if (listeImage.get(i).getPositionX() == positionX
										&& listeImage.get(i).getPositionY() == positionY) {
									enlever(listeImage.get(i));
								}
							}

						}

					});
				} else {
					effacer = false;
					vue.gridP.setOnMouseClicked(genererOnMouseClicked());
					vue.tbRemove.setStyle(null);
				}

			}

		};
		return retour;
	}

	private EventHandler<ActionEvent> resetHandler() {
		EventHandler<ActionEvent> retour = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				reset();
			}

		};
		return retour;
	}

	private EventHandler<ActionEvent> screenshotHandler() {
		EventHandler<ActionEvent> retour = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				FileChooser fichierSelecteur = new FileChooser();

				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("*.png", "*.jpg");
				fichierSelecteur.getExtensionFilters().add(extFilter);

				File fichier = fichierSelecteur.showSaveDialog(vue.getScene().getWindow());

				if (fichier != null) {
					Group g = new Group();
					for (int i = 0; i < listeImage.size(); i++) {
						g.getChildren().add(listeImage.get(i).getView());
					}
					WritableImage image = g.snapshot(new SnapshotParameters(), null);
					try {
						ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", fichier);
						g = null;
						for (int i = 0; i < listeImage.size(); i++) {
							vue.gridP.add(listeImage.get(i).getView(), listeImage.get(i).getPositionX(),
									listeImage.get(i).getPositionY());
						}

					} catch (IOException e) {

					}
				}
			}

		};
		return retour;
	}

	private EventHandler<ActionEvent> ouvrirHandler() {
		EventHandler<ActionEvent> retour = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				FileChooser fichierSelecteur = new FileChooser();

				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
				fichierSelecteur.getExtensionFilters().add(extFilter);

				File fichier = fichierSelecteur.showOpenDialog(vue.getScene().getWindow());

				if (fichier != null) {
					reset();
					ouvrirTexte(fichier);
				}
			}

		};
		return retour;
	}

	private void ouvrirTexte(File fichier) {

		BufferedReader br;

		try {
			br = new BufferedReader(new FileReader(fichier));
			String st;
			while ((st = br.readLine()) != null) {
				String nom = st;
				Composante c = null;
				if (nom.equals("FilH")) {
					c = Composante.FilH;
				} else if (nom.equals("FilV")) {
					c = Composante.FilV;
				} else if (nom.equals("FilHD")) {
					c = Composante.FilHD;

				} else if (nom.equals("FilHG")) {
					c = Composante.FilHG;

				} else if (nom.equals("FilBD")) {
					c = Composante.FilBD;

				} else if (nom.equals("FilBG")) {
					c = Composante.FilBG;

				} else if (nom.equals("FilGBD")) {
					c = Composante.FilGBD;

				} else if (nom.equals("FilGHD")) {
					c = Composante.FilGHD;

				} else if (nom.equals("FilBGH")) {
					c = Composante.FilBGH;

				} else if (nom.equals("FilBDH")) {
					c = Composante.FilBDH;

				} else if (nom.equals("FilAll")) {
					c = Composante.FilAll;

				} else if (nom.equals("Résistance")) {
					c = Composante.Résistance;

				} else if (nom.equals("Condensateur")) {
					c = Composante.Condensateur;

				} else if (nom.equals("Ampoule")) {
					c = Composante.Ampoule;

				} else if (nom.equals("Source")) {
					c = Composante.Source;

				} else if (nom.equals("Bobine")) {
					c = Composante.Bobine;
				}

				st = br.readLine();
				if (st != null) {
					byte posX = (byte) Integer.parseInt(st);
					st = br.readLine();
					if (st != null) {
						byte posY = (byte) Integer.parseInt(st);
						genererAutre(posX, posY, c);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private EventHandler<ActionEvent> enregistrerHandler() {
		EventHandler<ActionEvent> retour = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String texte = "";
				for (int i = 0; i < listeImage.size(); i++) {
					texte += listeImage.get(i).getNom() + "\n";
					texte += listeImage.get(i).getPositionX() + "\n";
					texte += listeImage.get(i).getPositionY() + "\n";
				}

				FileChooser fichierSelecteur = new FileChooser();
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
				fichierSelecteur.getExtensionFilters().add(extFilter);

				File fichier = fichierSelecteur.showSaveDialog(vue.getScene().getWindow());

				if (fichier != null) {
					enregistrerTexte(texte, fichier);
				}
			}
		};
		return retour;
	}

	private void enregistrerTexte(String contenu, File fichier) {
		try {
			PrintWriter writer;
			writer = new PrintWriter(fichier);
			writer.println(contenu);
			writer.close();
		} catch (IOException ex) {
			Logger.getLogger(ControleurBoardPro.class.getName()).log(Level.SEVERE, null, ex);
		}
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

	private EventHandler<DragEvent> dragDropped() {
		EventHandler<DragEvent> retour = new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				if (db.hasString()) {
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
				event.consume();
				byte positionX = (byte) (Math.floor(event.getX() / 75));
				byte positionY = (byte) (Math.floor(event.getY() / 75));

				if (composante.equals("fil")) {
					genererFil(positionX, positionY);
				} else if (composante.equals("resistance")) {
					genererAutre(positionX, positionY, Composante.Résistance);
				} else if (composante.equals("condensateur")) {
					genererAutre(positionX, positionY, Composante.Condensateur);
				} else if (composante.equals("bobine")) {
					genererAutre(positionX, positionY, Composante.Bobine);
				} else if (composante.equals("source")) {
					genererAutre(positionX, positionY, Composante.Source);
				} else if (composante.equals("ampoule")) {
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

	private EventHandler<MouseEvent> genererOnMouseClicked() {
		EventHandler<MouseEvent> retour = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				byte positionX = (byte) (Math.floor(event.getX() / 75));
				byte positionY = (byte) (Math.floor(event.getY() / 75));
				// Clique droit
				if (event.getButton().equals(MouseButton.SECONDARY)) {
					nom.setText("");
					for (int i = 0; i < listeImage.size(); i++) {
						if (listeImage.get(i).getPositionX() == positionX
								&& listeImage.get(i).getPositionY() == positionY) {
							String text = listeImage.get(i).getNom().toString();
							if (text.substring(0, 3).equals("Fil")) {
								text = "Fil";
							}
							text += " à la position (X, Y): (" + listeImage.get(i).getPositionX() + ", "
									+ listeImage.get(i).getPositionY() + ")";
							nom.setText(text);
							vue.tabView.getSelectionModel().select(1);
							FadeTransition fadeTransition = new FadeTransition(Duration.seconds(5), nom);
							fadeTransition.setFromValue(1.0);
							fadeTransition.setToValue(0.0);
							fadeTransition.play();
		
							
							if (!listeImage.get(i).getEquationDDP().equals("")) {
								vue.graphiqueTemps.getGraphique().changerFonction(listeImage.get(i).getEquationDDP());
							} else {
								vue.graphiqueTemps.getGraphique().changerFonction("");
							}
						}
					}

				}
				// Clique gauche
				else {

					if (composante.equals("fil")) {
						genererFil(positionX, positionY);
					} else if (composante.equals("resistance")) {
						genererAutre(positionX, positionY, Composante.Résistance);
					} else if (composante.equals("condensateur")) {
						genererAutre(positionX, positionY, Composante.Condensateur);
					} else if (composante.equals("bobine")) {
						genererAutre(positionX, positionY, Composante.Bobine);
					} else if (composante.equals("source")) {
						genererAutre(positionX, positionY, Composante.Source);
					} else if (composante.equals("ampoule")) {
						genererAutre(positionX, positionY, Composante.Ampoule);
					}
				}
			}
		};
		return retour;
	}

	private void genererAutre(byte x, byte y, Composante nom) {
		Images i = new Images(nom, x, y);
		HashSet<Byte> aChanger = i.composanteProche(listeImage);
		changerImage(aChanger);
		i.choisirImage(i);
		Composante n = i.choisirImage(i);
		i.setNom(n);
		i.creerImage(i.getNom());
		i.creerView();
		ajoutComposante(i, null);
	}

	private void genererFil(byte x, byte y) {
		Images image = new Images(Composante.FilH, x, y);

		/*
		 * Cette ligne fait deux chose, trouve les composantes proches de l'image (1) et
		 * puisque ces images proches doivent peut-être changer, garde la liste des
		 * images a changer
		 */
		HashSet<Byte> aChanger = image.composanteProche(listeImage);
		changerImage(aChanger);

		Composante nom = image.choisirImage(image);
		image.setNom(nom);
		image.creerImage(image.getNom());
		image.creerView();
		ajoutComposante(image, null);

	}

	private void changerImage(HashSet<Byte> lesIndexs) {
		ArrayList<Images> liste = new ArrayList<Images>();

		for (Byte i : lesIndexs) {
			liste.add(listeImage.get(i));
		}
		for (int j = 0; j < liste.size(); j++) {

			Composante avant = liste.get(j).getNom();
			int rotAvant = liste.get(j).getRotation();

			Composante apres = liste.get(j).choisirImage(liste.get(j));
			int rotApres = liste.get(j).getRotation();
			if (!avant.equals(apres) || rotAvant != rotApres) {
				ImageView aEnlever = liste.get(j).getView();
				liste.get(j).setNom(apres);
				liste.get(j).creerImage(apres);
				liste.get(j).creerView();
				ajoutComposante(liste.get(j), aEnlever);
			}
		}
	}

	private void ajoutComposante(Images image, ImageView aEnlever) {
		// Remove si à la meme position
		for (byte i = 0; i < listeImage.size(); i++) {
			if (listeImage.get(i) == image || (listeImage.get(i).getPositionX() == image.getPositionX()
					&& listeImage.get(i).getPositionY() == image.getPositionY())) {

				map.removeComposante(i);
				vue.gridP.getChildren().remove(listeImage.get(i).getView());

				if (aEnlever == null) {
					aEnlever = listeImage.get(i).getView();
				}
				vue.gridP.getChildren().remove(aEnlever);
				listeImage.remove(i);

			}
		}

		listeImage.add(image);
		ImageView v = image.getView();
		v.setRotate(image.getRotation());
		vue.gridP.add(v, image.getPositionX(), image.getPositionY());

		// -----------------------Ajout dans composante Map--------------------
		try {
			ComposantMap compo = null;
			CE2Entrees composante = null;
			if (image.getNom().equals(Composante.Ampoule)) {
				composante = new Resistance((short) image.getPositionX(), (short) image.getPositionY(), image);

				if (composante.getImage().getRotation() == 90) {
					composante.setSens(true);
				}
			} else if (image.getNom().equals(Composante.Bobine)) {
				composante = new Bobine((short) image.getPositionX(), (short) image.getPositionY(), image);
				if (composante.getImage().getRotation() == 90) {
					composante.setSens(true);
				}
			} else if (image.getNom().equals(Composante.Condensateur)) {
				composante = new Condensateur((short) image.getPositionX(), (short) image.getPositionY(), image);
				if (composante.getImage().getRotation() == 90) {
					composante.setSens(true);
				}
			} else if (image.getNom().equals(Composante.Résistance)) {
				composante = new Resistance((short) image.getPositionX(), (short) image.getPositionY(), image);
				if (composante.getImage().getRotation() == 90) {
					composante.setSens(true);
				}
			} else if (image.getNom().equals(Composante.Source)) {
				composante = new SourceCourant((short) image.getPositionX(), (short) image.getPositionY(), image);
				if (composante.getImage().getRotation() == 90) {
					composante.setSens(true);
				}
			} else {
				compo = new Fil((short) image.getPositionX(), (short) image.getPositionY(), image);
			}
			if (compo != null) {
				map.addComposant(compo);
			}
			if (composante != null) {
				map.addComposant(composante);
			}
		} catch (ComposantException e) {
			e.printStackTrace();
		}

	}

	private void reset() {
		listeImage.clear();
		map.getComposantsActuels().clear();
		nom.setText("");
		Node n = vue.gridP.getChildren().get(0);
		vue.gridP.getChildren().clear();
		vue.gridP.add(n, 0, 0);
	}

	private void enlever(Images image) {
		HashSet<Byte> aModif = image.modifierAutourRetrait(listeImage);
		changerImage(aModif);
		vue.gridP.getChildren().remove(image.getView());
		map.getComposantsActuels().remove(listeImage.indexOf(image));
		listeImage.remove(image);
	}

	private EventHandler<MouseEvent> rlcHandler() {
		EventHandler<MouseEvent> retour = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				reset();
				File f = new File("./res/circuitPrefait/RLC.txt");
				ouvrirTexte(f);
				vue.tabView.getSelectionModel().select(0);

				// Résistance 16
				listeImage.get(16).setEquationDDP("");
				// Bobine 17
				listeImage.get(17).setEquationDDP("");
				// Condensateur 19
				listeImage.get(19).setEquationDDP("");
			}

		};
		return retour;
	}

	private EventHandler<MouseEvent> rlHandler() {
		EventHandler<MouseEvent> retour = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				reset();
				File f = new File("./res/circuitPrefait/RL.txt");
				ouvrirTexte(f);
				vue.tabView.getSelectionModel().select(0);

				// Résistance
				listeImage.get(18).setEquationDDP("");
				// Bobine
				listeImage.get(19).setEquationDDP("");
			}

		};
		return retour;
	}

	private EventHandler<MouseEvent> rcHandler() {
		EventHandler<MouseEvent> retour = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				reset();
				File f = new File("./res/circuitPrefait/RC.txt");
				ouvrirTexte(f);
				vue.tabView.getSelectionModel().select(0);

				// Résistance
				listeImage.get(18).setEquationDDP("");
				// Condensateur
				listeImage.get(19).setEquationDDP("");
			}

		};
		return retour;
	}

	private EventHandler<MouseEvent> rHandler() {
		EventHandler<MouseEvent> retour = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				reset();
				File f = new File("./res/circuitPrefait/R.txt");
				ouvrirTexte(f);
				vue.tabView.getSelectionModel().select(0);
				listeImage.get(19).setEquationDDP("");
			}

		};
		return retour;
	}

	private EventHandler<MouseEvent> cHandler() {
		EventHandler<MouseEvent> retour = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				reset();
				File f = new File("./res/circuitPrefait/C.txt");
				ouvrirTexte(f);
				vue.tabView.getSelectionModel().select(0);
				listeImage.get(19).setEquationDDP("");

			}

		};
		return retour;
	}

	private EventHandler<MouseEvent> lHandler() {
		EventHandler<MouseEvent> retour = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				reset();
				File f = new File("./res/circuitPrefait/L.txt");
				ouvrirTexte(f);
				vue.tabView.getSelectionModel().select(0);
				listeImage.get(19).setEquationDDP("");
			}

		};
		return retour;
	}
}
