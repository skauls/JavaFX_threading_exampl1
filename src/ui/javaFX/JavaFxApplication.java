package ui.javaFX;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ui.UserInterface;
import ui.javaFX.worldObjects.ResourceFX;
import ui.javaFX.worldObjects.ResourceSpawnerFX;
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

	private Group rootGroup;

	private void init(Stage primaryStage) {
		rootGroup = new Group();
		world = World.getInstance();
		world.init(this, 1000d, 600d);

		Scene scene = new Scene(rootGroup, world.getWidth(), world.getHeight());
		scene.setFill(Color.DIMGRAY);
		primaryStage.setScene(scene);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		init(primaryStage);

		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void notifyCreation(WorldObject newWorldObject) {
		if (newWorldObject instanceof ResourceSpawner) {
			ResourceSpawnerFX newSpawnerFX = new ResourceSpawnerFX(
					(ResourceSpawner) newWorldObject);
			rootGroup.getChildren().add(newSpawnerFX);
		} else if (newWorldObject instanceof Resource) {
			rootGroup.getChildren().add(
					new ResourceFX((Resource) newWorldObject));
		}
	}
}
