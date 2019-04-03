package graphics;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;

public class GraophiqueTemps {
	Graphique graph;
	float temp ;
	
	
	public GraophiqueTemps(String fonction, Axes axes) {
		super();
		this.graph = new Graphique(fonction, axes);
		graph.setStyle("-fx-background-color: rgb(35, 39, 50);");
		temp = 0f;

	}
	public Graphique getGraphique() {
		return this.graph;
	}
	/**
	 * Lencer le graphique qui évolue
	 */
	public void start() {
		temps.setOnSucceeded(e -> {
			  graph.tirerGraphique(-1);
			});
		temps.start();

	}
	public void stop() {
		temps.cancel();
	}

/**
 * Boucle qui gere le temps
 */
	ScheduledService<Void> temps = new ScheduledService<Void>() {
		@Override
		protected Task<Void> createTask() {
			// TODO Auto-generated method stub
			return new Task<Void>() {
				@Override
				protected Void call() throws Exception {
					try {
						Thread.sleep(10);
					} catch (InterruptedException ie) {
					}
					return null;
				}
			};
		}

	};
	
	
}
