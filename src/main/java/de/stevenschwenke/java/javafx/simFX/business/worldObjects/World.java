package de.stevenschwenke.java.javafx.simFX.business.worldObjects;

import java.util.HashSet;
import java.util.Set;

import de.stevenschwenke.java.javafx.simFX.business.GameStateManager;
import de.stevenschwenke.java.javafx.simFX.business.UserInterface;
import de.stevenschwenke.java.javafx.simFX.business.logicalObjects.CartesianCoordinate;
import de.stevenschwenke.java.javafx.simFX.business.logicalObjects.GameState;
import de.stevenschwenke.java.javafx.simFX.business.logicalObjects.GroupMembership;
import de.stevenschwenke.java.javafx.simFX.business.logicalObjects.Interaction;


/**
 * The world in which the simulation takes place. Singelton.
 * 
 * @author Steven Schwenke
 * 
 */
public class World {

	/** the user interface that displays the world graphicaly */
	private UserInterface userInterface;

	private Set<WorldObject> objects;

	/** dimensions of the world */
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

	public void init(UserInterface userInterface, double width, double height,
			boolean addSomeObjects) {
		this.userInterface = userInterface;
		this.width = width;
		this.height = height;

		objects = new HashSet<WorldObject>();

		if (addSomeObjects) {
			addSomeObjects();
		}

		GameStateManager.getInstance().checkGameState();
	}

	/**
	 * Adds some objects to the world so it doesn't look empty.
	 */
	private void addSomeObjects() {

		addWorldObject(new Colony(GroupMembership.PLAYER,
				new CartesianCoordinate(200, 100)));
		addWorldObject(new ResourceSpawner(new CartesianCoordinate(100, 200),
				100));
		addWorldObject(new ResourceSpawner(new CartesianCoordinate(200, 200),
				100));

		addWorldObject(new Colony(GroupMembership.FOE1,
				new CartesianCoordinate(900, 600)));
		addWorldObject(new ResourceSpawner(new CartesianCoordinate(900, 500),
				100));
		addWorldObject(new ResourceSpawner(new CartesianCoordinate(800, 500),
				100));
	}

	/**
	 * Adds a {@link WorldObject} to the world and triggers the user interface
	 * to display the new object.
	 * 
	 * @param newObject
	 *            object to add to the world and display
	 */
	public void addWorldObject(WorldObject newObject) {
		synchronized (objects) {
			objects.add(newObject);
		}
		userInterface.notifyCreation(newObject);

	}

	/**
	 * Notifies the user interface about a change in the game state.
	 * 
	 * @param newGameState
	 */
	public void notifyGameStateChange(GameState newGameState) {
		// TODO this is just a delegation. Better: GameStateManager can notify
		// the user interface directly.
		userInterface.notifyChangeInGameState(newGameState);
	}

	/**
	 * Removes a {@link WorldObject} from the world and triggers the user
	 * interface to display this.
	 * 
	 * @param object
	 *            object to remove from the world
	 */
	public void removeWorldObject(WorldObject object) {
		synchronized (objects) {
			objects.remove(object);
			userInterface.notifyDisappearance(object);
		}
	}

	/**
	 * Triggers an interaction of two {@link WorldObject}s at the user
	 * interface. This method is only for displaying some effect on the
	 * interface, not for logic.
	 * 
	 * @param interaction
	 *            kind of interaction
	 * @param objects
	 *            that are involved in the interaction
	 * 
	 */
	public void interact(Interaction interaction, WorldObject... objects) {
		userInterface.notifyInteraction(interaction, objects);
	}

	/**
	 * @return all {@link Resource}s on the map
	 */
	public Set<Resource> getAllExistingResources() {
		synchronized (objects) {
			Set<Resource> resources = new HashSet<Resource>();
			for (WorldObject worldObject : objects) {
				if (worldObject instanceof Resource)
					resources.add((Resource) worldObject);
			}

			return resources;
		}
	}

	/**
	 * @return all {@link Colony}-objects on the map
	 */
	public Set<Colony> getAllExistingColonies() {
		Set<Colony> colonies = new HashSet<Colony>();
		for (WorldObject worldObject : objects) {
			if (worldObject instanceof Colony)
				colonies.add((Colony) worldObject);
		}
		return colonies;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}
}
