package de.stevenschwenke.java.javafx.simFX.ui.resourceSpawner;

import de.stevenschwenke.java.javafx.simFX.business.worldObjects.ResourceSpawner;
import de.stevenschwenke.java.javafx.simFX.ui.javaFX.JavaFxApplication;
import javafx.animation.ScaleTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 * Pane that opens when hovering over a {@link ResourceSpawnerFX}.
 * 
 * @author Steven Schwenke
 * 
 */
public class ResourceSpawnerFXMouseOverPane extends TitledPane {

	private ScaleTransition animationAppear;
	private ScaleTransition animationDisappear;

	private ResourceSpawnerFX resourceSpawnerFX;
	private static Label resourceLabel;

	public ResourceSpawnerFXMouseOverPane(
			final ResourceSpawnerFX resourceSpawnerFX) {
		super("RessourceSpawner", createGridPane(resourceSpawnerFX));

		this.resourceSpawnerFX = resourceSpawnerFX;

		final ResourceSpawnerFXMouseOverPane createdPane = this;

		setCollapsible(false);
		setPrefSize(200, 200);
		setLayoutX(resourceSpawnerFX.getRepresentedResourceSpawner()
				.getPosition().getX());
		setLayoutY(resourceSpawnerFX.getRepresentedResourceSpawner()
				.getPosition().getY());

		addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				animationDisappear
						.setOnFinished(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								JavaFxApplication.getInstance().getRootGroup()
										.getChildren().remove(createdPane);

							}
						});

				JavaFxApplication.getInstance().getRootGroup().getChildren()
						.remove(resourceSpawnerFX.getRadiusCircle());

				animationDisappear.play();

			}
		});

		animationAppear = new ScaleTransition(Duration.millis(300), this);
		animationAppear.setFromX(0);
		animationAppear.setToX(1);

		animationDisappear = new ScaleTransition(Duration.millis(300), this);
		animationDisappear.setFromX(1);
		animationDisappear.setToX(0);
	}

	private static GridPane createGridPane(
			final ResourceSpawnerFX resourceSpawnerFX) {

		final ResourceSpawner representedResourceSpawner = resourceSpawnerFX
				.getRepresentedResourceSpawner();

		GridPane pane = new GridPane();

		pane.setHgap(4);
		pane.setVgap(6);

		pane.setPadding(new Insets(5, 3, 3, 3));

		ColumnConstraints colinfo = new ColumnConstraints(20, 70, 90);
		for (int j = 0; j <= 2; j++) {
			pane.getColumnConstraints().add(colinfo);
		}

		ObservableList<Node> content = pane.getChildren();

		// Resources and Button to add resources

		Label label = new Label("Resources:");
		GridPane.setConstraints(label, 0, 0);
		GridPane.setHalignment(label, HPos.LEFT);
		content.add(label);

		resourceLabel = new Label(""
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

		// Slider for radius

		final Label radiusLabel = new Label("Radius:");
		GridPane.setConstraints(radiusLabel, 0, 1);
		GridPane.setHalignment(radiusLabel, HPos.LEFT);
		content.add(radiusLabel);

		final Slider slider = new Slider(10, 500,
				representedResourceSpawner.getRadius());
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		slider.setMajorTickUnit(100f);
		GridPane.setConstraints(slider, 1, 1, 2, 1);
		GridPane.setHalignment(slider, HPos.RIGHT);
		content.add(slider);

		slider.addEventFilter(MouseEvent.MOUSE_DRAGGED,
				new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent arg0) {
						representedResourceSpawner.setRadius(slider.getValue());
						resourceSpawnerFX.getRadiusCircle().setRadius(
								slider.getValue());
					}
				});

		return pane;
	}

	/**
	 * Refreshes the content of the pane and let it appear.
	 */
	public void appear() {

		resourceLabel.setText(""
				+ resourceSpawnerFX.getRepresentedResourceSpawner()
						.getResources());

		animationAppear.playFromStart();
	}
}
