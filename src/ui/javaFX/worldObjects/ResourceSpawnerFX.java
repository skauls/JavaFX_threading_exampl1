package ui.javaFX.worldObjects;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import business.worldObjects.ResourceSpawner;

/**
 * Graphical representation of a {@link RessourceSpawnerRepresentation} for
 * JavaFX.
 * 
 * @author Steven Schwenke
 * 
 */
public class ResourceSpawnerFX extends Button {

	/** the ressource spawner that is represented by this object */
	private ResourceSpawner representedResourceSpawner;

	public ResourceSpawnerFX(final ResourceSpawner representedResourceSpawner) {
		super();

		this.representedResourceSpawner = representedResourceSpawner;

		this.setLayoutX(representedResourceSpawner.getPosition().getX());
		this.setLayoutY(representedResourceSpawner.getPosition().getY());

		this.setText("+");

		this.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				representedResourceSpawner.spawn();
			}
		});
	}
}
