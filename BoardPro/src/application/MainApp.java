package application;

import controleur.ControleurBoardPro;
import javafx.application.Application;
import javafx.event.EventHandler;
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
		
		vue.tabView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        vue.tabView.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
        
        vue.exitBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	//vue.boardToolb.setVisible(false);
            	//vue.tbCompList.setExpanded(true);
            	//vue.tbCompList.depthProperty().set(1);
              System.exit(1);
            }
        });
		
        
       // vue.initialize();
        
        
		primaryStage.setScene(vue.getScene());
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.show();
		primaryStage.setResizable(false);
	}

}