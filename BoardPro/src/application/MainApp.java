package application;

import controleur.ControleurBoardPro;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import vue.ControleurVue;

public class MainApp extends Application {

	private ControleurBoardPro controleur;
	private ControleurVue vue;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		controleur = new ControleurBoardPro();
		vue = controleur.getVue();
		controleur.masterHandler();

		primaryStage.setScene(vue.getScene());
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.show();
		primaryStage.setResizable(false);
	}

	public static Window getStage() {
		// TODO Auto-generated method stub
		return null;
	}

}
