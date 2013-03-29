package de.stevenschwenke.java.javafx.simFX.business;

import de.stevenschwenke.java.javafx.simFX.business.logicalObjects.GameState;
import de.stevenschwenke.java.javafx.simFX.business.logicalObjects.Interaction;
import de.stevenschwenke.java.javafx.simFX.business.worldObjects.WorldObject;

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
	 * @param object
	 *            object that should be destroyed
	 */
	void notifyDisappearance(WorldObject object);

	/**
	 * This method notifies the user interface of an interaction between
	 * multiple {@link WorldObject}s. The contract of this method dictates that
	 * the objects are given in the order of acting object(s), object(s) acted
	 * upon. So if one kid beats another one up, this method should be called
	 * something like notifyInteraction(Interaction.BEAT_UP, rowdy, geek);
	 * 
	 * @param interaction
	 *            Kind of interaction
	 * @param objects
	 *            involved in the interaction
	 */
	void notifyInteraction(Interaction interaction, WorldObject[] objects);

	/**
	 * A change in the game state, for example when the player won, has to be
	 * shown to the user via the UserInterface.
	 * 
	 * @param newState
	 */
	void notifyChangeInGameState(GameState newState);
}
