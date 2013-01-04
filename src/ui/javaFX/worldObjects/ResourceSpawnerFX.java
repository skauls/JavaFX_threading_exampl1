package ui.javaFX.worldObjects;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import ui.javaFX.JavaFxApplication;
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

	/** the pane with the context menu */
	private MouseOverPane mouseOverPane;

	public ResourceSpawnerFX(final ResourceSpawner representedResourceSpawner) {
		super();

		this.representedResourceSpawner = representedResourceSpawner;

		this.setLayoutX(representedResourceSpawner.getPosition().getX());
		this.setLayoutY(representedResourceSpawner.getPosition().getY());

		this.setText("+");

		this.addEventFilter(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {

						if (mouseOverPane == null)
							mouseOverPane = new MouseOverPane(
									representedResourceSpawner);

						// possible that the pane is already added because the
						// mouse hovered over this spawner and the pane didn't
						// get removed after the mouse left.
						if (!JavaFxApplication.getInstance().getRootGroup()
								.getChildren().contains(mouseOverPane))
							JavaFxApplication.getInstance().getRootGroup()
									.getChildren().add(mouseOverPane);

					}
				});

	}

}
