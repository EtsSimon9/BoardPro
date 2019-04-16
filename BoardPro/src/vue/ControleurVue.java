package vue;

import java.io.IOException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXToolbar;
import controleur.ControleurBoardPro;
import graphics.Axes;
import graphics.GraphiqueTemps;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
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
	public AnchorPane paneGraph;

	@FXML
	private Label lbOut;
	@FXML
	private Text tVolt;

	@FXML
	private Tab tabBoard;
	
	
	@FXML
	private AnchorPane PaneBoard;
	
	@FXML
	public ScrollPane scrollP;
	@FXML
	public GridPane gridP;
	
	
	
	    @FXML private ColumnConstraints gridCol0; @FXML private ColumnConstraints gridCol1; @FXML private ColumnConstraints gridCol2; @FXML private ColumnConstraints gridCol3;
	    @FXML private ColumnConstraints gridCol4; @FXML private ColumnConstraints gridCol5; @FXML private ColumnConstraints gridCol6; @FXML private ColumnConstraints gridCol7; 
	    @FXML private ColumnConstraints gridCol8; @FXML private ColumnConstraints gridCol9; @FXML private ColumnConstraints gridCol10; @FXML private ColumnConstraints gridCol11; 
	    @FXML private ColumnConstraints gridCol12; @FXML private ColumnConstraints gridCol13; @FXML private ColumnConstraints gridCol14; @FXML private ColumnConstraints gridCol15;
	    @FXML private ColumnConstraints gridCol16; @FXML private ColumnConstraints gridCol17; @FXML private ColumnConstraints gridCol18; @FXML private ColumnConstraints gridCol19;
	    
	    @FXML private RowConstraints gridRow0; @FXML private RowConstraints gridRow1; @FXML private RowConstraints gridRow2; @FXML private RowConstraints gridRow3;
	    @FXML private RowConstraints gridRow4; @FXML private RowConstraints gridRow5; @FXML private RowConstraints gridRow6; @FXML private RowConstraints gridRow7; 
	    @FXML private RowConstraints gridRow8; @FXML private RowConstraints gridRow9; @FXML private RowConstraints gridRow10; @FXML private RowConstraints gridRow11;
	    @FXML private RowConstraints gridRow12; @FXML private RowConstraints gridRow13; @FXML private RowConstraints gridRow14; @FXML private RowConstraints gridRow15;
	    @FXML private RowConstraints gridRow16; @FXML private RowConstraints gridRow17; @FXML private RowConstraints gridRow18; @FXML private RowConstraints gridRow19;

	@FXML
	private MenuItem cFil;
	@FXML
	private MenuItem cResistance;
	@FXML
	private MenuItem cSource;

	@FXML
	public JFXToolbar jfx;
	@FXML
	public JFXButton tbEnregistrer;
	@FXML
	public JFXButton tbOuvrir;
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
	
	public ControleurBoardPro controleur;
	Scene scene;
	private GraphiqueTemps graphiqueTemps;
	
	

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
		// ObservableList<String> composantes = FXCollections.observableArrayList("fil", "resistance", "source");
		
		 tbCompList.getItems().add("Fil");
		 tbCompList.getItems().add("Résistance");
		 tbCompList.getItems().add("Source");
		 tbCompList.getItems().add("Condensateur");
		 tbCompList.getItems().add("Bobine");
		 tbCompList.getItems().add("Ampoule");
		// tbCompList.setExpanded(true);
		 Axes axes = new Axes(1300, 690, 1, 8);
			graphiqueTemps = new GraphiqueTemps("sin(x)", axes,60);
			graphiqueTemps.start();	
			paneGraph.getChildren().add(graphiqueTemps.getGraphique());
	}
	
	public Scene getScene() {
		return scene;
	}

}