package application;

import controleur.ControleurBoardPro;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vue.ControleurVue;

/**
 * 
 * @author Aymeric Lamontagne
 *
 */
public class MainApp extends Application {

	private ControleurBoardPro controleur;
	private ControleurVue vue;
	private double xOffset = 0;
	private double yOffset = 0;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		controleur = new ControleurBoardPro();
		vue = controleur.getVue();
		controleur.masterHandler();
		// vue.initialize();

		primaryStage.setScene(vue.getScene());
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.show();
		primaryStage.setResizable(false);
	}

}
