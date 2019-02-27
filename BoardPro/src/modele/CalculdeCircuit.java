package modele;

import map.Map;

public class CalculdeCircuit {
	Map map;
	
	public CalculdeCircuit(Map m) {
		setMap(m);
	}
	
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		if(map != null) {
			this.map = map;
		}
	}
	public void appliquerLoiDesnoeuds() {
		
	}
	public void appliquerLoiDesMailles() {
		
	}
	public void faireFonctionnerCircuit() {
		
	}

}
