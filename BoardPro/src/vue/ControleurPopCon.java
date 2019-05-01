package vue;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import application.MainApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControleurPopCon {

	@FXML
	private JFXTextField PopConCap;
	@FXML
	private JFXTextField PopConDi;
	@FXML
	private JFXButton PopExit;
	@FXML
	private JFXButton PopSave;
    
    @FXML
    void exitBtn(ActionEvent event) {
    	stage.close();
    }

    @FXML
    void saveBtn(ActionEvent event) {
    	
    }
    
    public void show() {
		stage.showAndWait();
	}

    private Stage stage;
	private Parent root;
	
    public ControleurPopCon() {
		
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/PopConOverview.fxml"));
    	loader.setController(this);
    	try {
			root = loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	Scene scene = new Scene(root);
    	scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
    	stage = new Stage(StageStyle.UTILITY);
    	stage.initStyle(StageStyle.UNDECORATED);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.setAlwaysOnTop(true);
		stage.initOwner(MainApp.getStage());
		stage.initModality(Modality.WINDOW_MODAL);
	}
    
    public void initialize() {
    	PopConCap.setPromptText("Default number");
    	PopConDi.setPromptText("0");
    	PopConCap.setOnKeyTyped(tfNumOnly());
    	PopConDi.setOnKeyTyped(tfNumOnly());
	}
    
    
    private EventHandler<KeyEvent> tfNumOnly() {
		EventHandler<KeyEvent> retour = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				String c = event.getCharacter();
				System.out.print(c);
				for (int i=0; i<10; i++) {
					if ((c.equals(String.valueOf(0))) || (c.equals(String.valueOf(1))) || (c.equals(String.valueOf(2))) || (c.equals(String.valueOf(3))) 
							|| (c.equals(String.valueOf(4))) || (c.equals(String.valueOf(5))) || (c.equals(String.valueOf(6)))
							|| (c.equals(String.valueOf(7)))|| (c.equals(String.valueOf(8))) || (c.equals(String.valueOf(9)))  ) {
					} else {
						event.consume();
					}
				}}};	
		return retour;
    }
    
    
    
    
    
    
    
}