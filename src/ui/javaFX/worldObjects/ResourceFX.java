package ui.javaFX.worldObjects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
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
		super(new double[] { 0, 0, 10, 10, 20, 0, 20, 10, 30, 20, 20, 20, 20,
				30, 10, 30, 0, 20, 10, 20 });
		this.setFill(Color.CORAL);
		this.representedResource = representedRessource;
		setLayoutX(representedRessource.getPosition().getX());
		setLayoutY(representedRessource.getPosition().getY());
	}
}
