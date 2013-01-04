package ui.javaFX.worldObjects;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
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
	private TitledPane mouseOverPane;

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

						Parent flowPane = createGridPane();

						if (mouseOverPane == null)
							mouseOverPane = createMouseOverPane(
									representedResourceSpawner, flowPane);

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

	private TitledPane createMouseOverPane(
			final ResourceSpawner representedResourceSpawner, Parent flowPane) {
		final TitledPane mouseOverPane = new TitledPane("RessourceSpawner",
				flowPane);
		mouseOverPane.setCollapsible(false);
		mouseOverPane.setPrefSize(200, 200);
		mouseOverPane.setLayoutX(representedResourceSpawner.getPosition()
				.getX());
		mouseOverPane.setLayoutY(representedResourceSpawner.getPosition()
				.getY());

		mouseOverPane.addEventFilter(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {

						JavaFxApplication.getInstance().getRootGroup()
								.getChildren().remove(mouseOverPane);
					}
				});

		return mouseOverPane;
	}

	private GridPane createGridPane() {

		GridPane pane = new GridPane();

		pane.setHgap(4);
		pane.setVgap(6);

		pane.setPadding(new Insets(5, 3, 3, 3));

		ColumnConstraints colinfo = new ColumnConstraints(20, 70, 90);
		for (int j = 0; j <= 2; j++) {
			pane.getColumnConstraints().add(colinfo);
		}

		ObservableList<Node> content = pane.getChildren();

		Label label = new Label("Resources:");
		GridPane.setConstraints(label, 0, 0);
		GridPane.setHalignment(label, HPos.LEFT);
		content.add(label);

		final Label resourceLabel = new Label(""
				+ representedResourceSpawner.getResources());
		GridPane.setConstraints(resourceLabel, 1, 0);
		GridPane.setHalignment(resourceLabel, HPos.LEFT);
		content.add(resourceLabel);

		Button addButton = new Button("Add");
		GridPane.setConstraints(addButton, 2, 0);
		GridPane.setHalignment(addButton, HPos.RIGHT);
		content.add(addButton);

		addButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				representedResourceSpawner.spawn();
				resourceLabel.setText(""
						+ representedResourceSpawner.getResources());
			}
		});

		return pane;
	}

}
