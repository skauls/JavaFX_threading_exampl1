package ui.javaFX.worldObjects;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import ui.javaFX.JavaFxApplication;
import ui.javaFX.interfaceObjects.ResourceSpawnerFXMouseOverPane;
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
	private ResourceSpawnerFXMouseOverPane mouseOverPane;

	/**
	 * red circle that marks the radius in which new resources are spawned
	 */
	private Circle radiusCircle;

	public ResourceSpawnerFX(final ResourceSpawner representedResourceSpawner) {
		super();

		this.representedResourceSpawner = representedResourceSpawner;

		final ResourceSpawnerFX thisResourceSpawnerFX = this;

		this.setLayoutX(representedResourceSpawner.getPosition().getX());
		this.setLayoutY(representedResourceSpawner.getPosition().getY());
		this.setText("R");

		this.setVisible(JavaFxApplication.getInstance().isRootMode());

		radiusCircle = new Circle(representedResourceSpawner.getPosition()
				.getX(), representedResourceSpawner.getPosition().getY(),
				representedResourceSpawner.getRadius(), Color.TRANSPARENT);
		radiusCircle.setStroke(Color.RED);

		this.addEventFilter(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {

						JavaFxApplication.getInstance().getRootGroup()
								.getChildren().add(radiusCircle);

						if (mouseOverPane == null)
							mouseOverPane = new ResourceSpawnerFXMouseOverPane(
									thisResourceSpawnerFX);

						// Possible that the pane is already added because the
						// mouse hovered over this spawner and the pane didn't
						// get removed after the mouse left.
						if (!JavaFxApplication.getInstance().getRootGroup()
								.getChildren().contains(mouseOverPane)) {
							JavaFxApplication.getInstance().getRootGroup()
									.getChildren().add(mouseOverPane);

						}
						mouseOverPane.appear();
					}
				});
	}

	public ResourceSpawner getRepresentedResourceSpawner() {
		return representedResourceSpawner;
	}

	public Circle getRadiusCircle() {
		return radiusCircle;
	}
}
