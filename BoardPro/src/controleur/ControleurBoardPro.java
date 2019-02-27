package controleur;

import vue.ControleurVue;

public class ControleurBoardPro {

	private ControleurVue vue;
	
	
	public ControleurBoardPro() {
		vue = new ControleurVue(this);
	}

	public ControleurVue getVue() {
		return vue;
	}
}