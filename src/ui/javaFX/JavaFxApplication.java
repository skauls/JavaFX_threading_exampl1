package ui.javaFX;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import ui.UserInterface;
import ui.javaFX.worldObjects.ColonyFX;
import ui.javaFX.worldObjects.ResourceFX;
import ui.javaFX.worldObjects.ResourceSpawnerFX;
import business.logicalObjects.Interaction;
import business.worldObjects.Colony;
import business.worldObjects.Resource;
import business.worldObjects.ResourceSpawner;
import business.worldObjects.World;
import business.worldObjects.WorldObject;

/**
 * User Interface with JavaFX. This class starts a JavaFX application and
 * instantiates a {@link World}.
 * 
 * @author Steven Schwenke
 * 
 */
public class JavaFxApplication extends Application implements UserInterface {

	/** World that is represented by this application */
	private static World world;

	/** if enabled, the user can manipulate the world */
	private boolean rootMode = false;

	private Group rootGroup;

	private static JavaFxApplication instance;

	public static JavaFxApplication getInstance() {
		if (instance == null) {
			throw new RuntimeException(
					"Before getting an instance of the JavaFX application, execute the main-method in this class first. It launches the FX application.");
		}
		return instance;
	}

	private void init(Stage primaryStage) {
		rootGroup = new Group();

		world = World.getInstance();
		world.init(this, 1024d, 768d);

		addRootButton();

		Scene scene = new Scene(rootGroup, world.getWidth(), world.getHeight());
		scene.setFill(Color.DIMGRAY);
		primaryStage.setScene(scene);
	}

	/**
	 * Adds a button to the upper right edge of the screen which enables or
	 * disables the root-mode. This mode allows for manipulation of the world.
	 */
	private void addRootButton() {
		final Button rootButton = new Button("Root");
		rootButton.setLayoutX(world.getWidth() - 50);
		rootButton.setLayoutY(10);

		rootButton.addEventFilter(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						rootMode = !rootMode;

						if (rootMode)
							rootButton.setStyle("-fx-base: #a00;");
						else
							rootButton.setStyle("-fx-base: #aaa;");

						// rootButton.set

						for (Node n : rootGroup.getChildren()) {
							if (n instanceof ResourceSpawnerFX)
								((ResourceSpawnerFX) n).setVisible(rootMode);
						}
					}
				});

		rootGroup.getChildren().add(rootButton);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		instance = this;

		init(primaryStage);

		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void notifyCreation(final WorldObject newWorldObject) {

		// All new objects are created on the JavaFX Application Thread, even if
		// the call to this method came from another thread, such as a timing
		// thread started by the business logic.
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if (newWorldObject instanceof ResourceSpawner) {
					ResourceSpawnerFX newSpawnerFX = new ResourceSpawnerFX(
							(ResourceSpawner) newWorldObject);
					rootGroup.getChildren().add(newSpawnerFX);
				} else if (newWorldObject instanceof Resource) {
					rootGroup.getChildren().add(
							new ResourceFX((Resource) newWorldObject));
				} else if (newWorldObject instanceof Colony) {
					rootGroup.getChildren().add(
							new ColonyFX((Colony) newWorldObject));
				}
			}
		});
	}

	public Group getRootGroup() {
		return rootGroup;
	}

	public boolean isRootMode() {
		return rootMode;
	}

	// TODO this should be refactored into a separate AnimationFX-class
	@Override
	public void notifyInteraction(final Interaction interaction,
			final WorldObject[] objects) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if (Interaction.HARVEST.equals(interaction)) {
					if (objects.length != 2)
						throw new RuntimeException(
								"A harvest interaction must have exactly two objects involved: the harvester and the harvest");

					WorldObject harvester = objects[0];
					WorldObject harvest = objects[1];

					if (harvester instanceof Colony
							&& harvest instanceof Resource) {

						Colony c = (Colony) harvester;
						Resource r = (Resource) harvest;

						final Line line = new Line(c.getPosition().getX(), c
								.getPosition().getY(), r.getPosition().getX(),
								r.getPosition().getY());

						line.setFill(null);

						line.setStroke(Color.RED);

						line.setStrokeWidth(2);

						rootGroup.getChildren().add(line);

						FadeTransition ft = new FadeTransition(Duration
								.millis(1300), line);
						ft.setFromValue(1.0);
						ft.setToValue(0);
						ft.play();

						// remove the line after the animation
						ft.setOnFinished(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {
								rootGroup.getChildren().remove(line);
							}
						});
					}
				}
			};
		});
	}

	// TODO Das so weiterprogrammieren, später vlt bei notifyCreation eine Map
	// anlegen, welche von BO auf FX mappt, damit das hier nicht mehr so
	// hässlich ist
	@Override
	public void notifyDisappearance(WorldObject newWorldObject) {
		for (Node n : rootGroup.getChildren()) {
			if (n instanceof ResourceFX) {

				final ResourceFX s = (ResourceFX) n;
				if (s.getRepresentedResource().equals(newWorldObject)) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							rootGroup.getChildren().remove(s);
						}
					});
					return;
				}
			}
		}
	}
}
