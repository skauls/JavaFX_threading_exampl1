package business.worldObjects;

import java.util.HashSet;
import java.util.Set;

import ui.UserInterface;
import business.logicalObjects.CartesianCoordinate;

/**
 * The world in which the simulation takes place.
 * 
 * @author Steven Schwenke
 * 
 */
public class World implements WorldObject {

	private UserInterface userInterface;

	private Set<WorldObject> objects;

	private double width, height;

	private static World INSTANCE;

	public static World getInstance() {
		if (INSTANCE == null)
			INSTANCE = new World();
		return INSTANCE;
	}

	private World() {
		super();
	}

	public void init(UserInterface userInterface, double width, double height) {
		this.userInterface = userInterface;
		this.width = width;
		this.height = height;
		objects = new HashSet<WorldObject>();

		addSomeObjects();
	}

	private void addSomeObjects() {
		addWorldObject(new ResourceSpawner(new CartesianCoordinate(100, 200),
				200));

		addWorldObject(new ResourceSpawner(new CartesianCoordinate(600, 300),
				200));
	}

	public void addWorldObject(WorldObject newObject) {
		objects.add(newObject);

		userInterface.notifyCreation(newObject);
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

}
