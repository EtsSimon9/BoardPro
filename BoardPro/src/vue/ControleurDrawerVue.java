package vue;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import controleur.ControleurBoardPro;
import graphics.Axes;
import graphics.GraphiqueTemps;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ControleurDrawerVue {

	@FXML
	public ToggleButton btnfil;
	@FXML
	public ToggleButton btnresistance;
	@FXML
	public ToggleButton btnsource;
	@FXML
	public ToggleButton btncondensateur;
	@FXML
	public ToggleButton btnbobine;
	@FXML
	public ToggleButton btnampoule;

	public ToggleButton btnfil1;

	@FXML
	void dragDetectedF(MouseEvent event) {
		ControleurBoardPro.composante = "fil";
		Dragboard db = ((ToggleButton) event.getSource()).startDragAndDrop(TransferMode.ANY);
		ClipboardContent content = new ClipboardContent();
		content.putString("THIS HAS BEEN DROPPED");
		db.setContent(content);
		event.consume();
	}
	@FXML
	void dragDetectedR(MouseEvent event) {
		ControleurBoardPro.composante = "resistance";
		Dragboard db = ((ToggleButton) event.getSource()).startDragAndDrop(TransferMode.ANY);
		ClipboardContent content = new ClipboardContent();
		content.putString("THIS HAS BEEN DROPPED");
		db.setContent(content);
		event.consume();
	}
	@FXML
	void dragDetectedC(MouseEvent event) {
		ControleurBoardPro.composante = "condensateur";
		Dragboard db = ((ToggleButton) event.getSource()).startDragAndDrop(TransferMode.ANY);
		ClipboardContent content = new ClipboardContent();
		content.putString("THIS HAS BEEN DROPPED");
		db.setContent(content);
		event.consume();
	}
	@FXML
	void dragDetectedB(MouseEvent event) {
		ControleurBoardPro.composante = "bobine";
		Dragboard db = ((ToggleButton) event.getSource()).startDragAndDrop(TransferMode.ANY);
		ClipboardContent content = new ClipboardContent();
		content.putString("THIS HAS BEEN DROPPED");
		db.setContent(content);
		event.consume();
	}
	@FXML
	void dragDetectedA(MouseEvent event) {
		ControleurBoardPro.composante = "ampoule";
		Dragboard db = ((ToggleButton) event.getSource()).startDragAndDrop(TransferMode.ANY);
		ClipboardContent content = new ClipboardContent();
		content.putString("THIS HAS BEEN DROPPED");
		db.setContent(content);
		event.consume();
	}
	@FXML
	void dragDetectedS(MouseEvent event) {
		ControleurBoardPro.composante = "source";
		Dragboard db = ((ToggleButton) event.getSource()).startDragAndDrop(TransferMode.ANY);
		ClipboardContent content = new ClipboardContent();
		content.putString("THIS HAS BEEN DROPPED");
		db.setContent(content);
		event.consume();
	}
	

	@FXML
	private void selectWire(ActionEvent event) {
		ControleurBoardPro.composante = ("fil");
	}

	@FXML
	private void selectResistor(ActionEvent event) {
		ControleurBoardPro.composante = ("resistance");
	}

	@FXML
	private void selectSource(ActionEvent event) {
		ControleurBoardPro.composante = ("source");
	}

	@FXML
	private void selectCondensator(ActionEvent event) {
		ControleurBoardPro.composante = ("condensateur");
	}

	@FXML
	private void selectBobine(ActionEvent event) {
		ControleurBoardPro.composante = ("bobine");
	}

	@FXML
	private void selectAmpoule(ActionEvent event) {
		ControleurBoardPro.composante = ("ampoule");
	}

	public ToggleGroup cGroup;
	public ControleurBoardPro controleur2;
	public VBox box;

	public void initialize() {
		/*
		 * cGroup = new ToggleGroup();
		 * 
		 * btnfil.setToggleGroup(cGroup); btnresistance.setToggleGroup(cGroup);
		 * btnsource.setToggleGroup(cGroup); btncondensateur.setToggleGroup(cGroup);
		 * btnbobine.setToggleGroup(cGroup); btnampoule.setToggleGroup(cGroup);
		 */
	}

	public VBox getBox() {
		return box;
	}

}
