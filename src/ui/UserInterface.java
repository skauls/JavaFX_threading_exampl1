package ui;

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
	 * interface is instantiated by some business class, the user interface has
	 * to be notified.
	 * 
	 * @param newWorldObject
	 *            object that should be displayed
	 */
	void notifyCreation(WorldObject newWorldObject);

}
