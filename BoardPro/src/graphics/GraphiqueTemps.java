package graphics;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import temps.Temps;

public class GraphiqueTemps {
	Graphique graph;
	Axes axes;
	Temps temp;
	int fps;

	public GraphiqueTemps(String fonction, Axes axes, int fps) {
		super();
		this.graph = new Graphique(fonction, axes);
		graph.setStyle("-fx-background-color: rgb(35, 39, 50);");
		this.axes = axes;
		fps = 1000 / fps;
	}

	public Graphique getGraphique() {
		return this.graph;
	}

	public void start() {
		temp = new Temps();
		temps.setOnSucceeded(e -> {
			graph.tirerGraphique(-(float) (temp.getDeltaT() * axes.getMesurePixelUnite() * axes.getBonds()));
			temp.setTZero();
		});
		temps.start();

	}

	public void stop() {
		temps.cancel();
	}
	
	public void reset() {
		temps.reset();
	}

	ScheduledService<Void> temps = new ScheduledService<Void>() {
		@Override
		protected Task<Void> createTask() {
			// TODO Auto-generated method stub
			return new Task<Void>() {
				@Override
				protected Void call() throws Exception {
					try {
						Thread.sleep(fps);
					} catch (InterruptedException ie) {
					}
					return null;
				}
			};
		}

	};

}
