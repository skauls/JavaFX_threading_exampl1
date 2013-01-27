package ui;

import business.logicalObjects.Interaction;
import business.worldObjects.WorldObject;

/**
 * This interface has to be implemented by all base classes for other user
 * interfaces.
 * 
 * @author Steven Schwenke
 * 
 */
public interface UserInterface {

	/**
	 * When a new {@link WorldObject} that is meant to be displayed at the user
	 * interface, the user interface has to be notified.
	 * 
	 * @param newWorldObject
	 *            object that should be displayed
	 */
	void notifyCreation(WorldObject newWorldObject);

	/**
	 * When a {@link WorldObject} is meant to be destroyed at the user
	 * interface, the user interface has to be notified.
	 * 
	 * @param newWorldObject
	 *            object that should be destroyed
	 */
	void notifyDisappearance(WorldObject newWorldObject);

	/**
	 * This method notifies the user interface of an interaction between
	 * multiple {@link WorldObject}s.
	 * 
	 * @param interaction
	 *            Kind of interaction
	 * @param objects
	 *            involved in the interaction
	 */
	void notifyInteraction(Interaction interaction, WorldObject[] objects);
}
