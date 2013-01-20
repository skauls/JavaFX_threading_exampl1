package ui.javaFX.worldObjects;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;
import business.worldObjects.Resource;

/**
 * Graphical representation of a {@link Resource} for JavaFX.
 * 
 * @author Steven Schwenke
 * 
 */
public class ResourceFX extends Polygon {

	private Resource representedResource;

	public ResourceFX(final Resource representedRessource) {

		// these numnbers represent the polygon that shapes the resource
		super(new double[] { 0, 0, 10, 10, 20, 0, 20, 10, 30, 20, 20, 20, 20,
				30, 10, 30, 0, 20, 10, 20 });

		this.representedResource = representedRessource;

		this.setFill(Color.CORAL);
		setLayoutX(representedRessource.getPosition().getX());
		setLayoutY(representedRessource.getPosition().getY());

		FadeTransition ft = new FadeTransition(Duration.millis(3000), this);
		ft.setFromValue(0.1);
		ft.setToValue(1.0);
		ft.play();

		ScaleTransition st = new ScaleTransition(Duration.millis(3000), this);
		st.setFromX(0);
		st.setToX(0.4);
		st.setFromY(0);
		st.setToY(0.4);
		st.play();
	}
}
