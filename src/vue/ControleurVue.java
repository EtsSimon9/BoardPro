package vue;

import java.io.IOException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXToolbar;
import controleur.ControleurBoardPro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class ControleurVue {

	@FXML
	private AnchorPane paneControl;
	@FXML
	public JFXButton exitBtn;
	
	@FXML
	public JFXTabPane tabView;

	@FXML
	private Tab tabGraph;
	@FXML
	private AnchorPane PaneGraph;

	@FXML
	private Label lbOut;
	@FXML
	private Text tVolt;

	@FXML
	private Tab tabBoard;
	@FXML
	private AnchorPane PaneBoard;

	@FXML
	private MenuItem cFil;
	@FXML
	private MenuItem cResistance;
	@FXML
	private MenuItem cSource;

	@FXML
	public JFXToolbar jfx;
	@FXML
	private JFXButton tbEnregistrer;
	@FXML
	private JFXButton tbOuvrir;
	@FXML
	public JFXButton tbPlay;
	@FXML
	public JFXListView<String> tbCompList;
	@FXML
	private Button tbCompAdd;

	@FXML
	private JFXTabPane tabPaneJFX;
	@FXML
	private Tab tab1;
	@FXML
	private Tab tab2;

	@FXML
	public AnchorPane root;

	private ControleurBoardPro controleur;
	Scene scene;
	
	

	public ControleurVue(ControleurBoardPro controleur) {
		this.controleur = controleur;

		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("BasicOverview.fxml"));
		loader.setController(this);
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

	}

	public void initialize() {
		 ObservableList<String> composantes = FXCollections.observableArrayList("fil", "resistance", "source");
		 tbCompList.getItems().add("fil");
		 tbCompList.getItems().add("resistance");
		 tbCompList.getItems().add("source");
		 
		// tbCompList.setExpanded(true);
	}
	
	public Scene getScene() {
		return scene;
	}

}
