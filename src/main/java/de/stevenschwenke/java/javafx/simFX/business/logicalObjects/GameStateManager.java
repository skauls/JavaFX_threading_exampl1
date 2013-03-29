package de.stevenschwenke.java.javafx.simFX.business.logicalObjects;

import de.stevenschwenke.java.javafx.simFX.business.worldObjects.Colony;
import de.stevenschwenke.java.javafx.simFX.business.worldObjects.World;

/**
 * Class that manages the state of the game. Currently, there are two States:
 * Game running and player won.
 * 
 * @author Steven Schwenke
 * 
 */
public class GameStateManager {

	private static GameStateManager INSTANCE;

	private GameState state;

	public static GameStateManager getInstance() {
		if (INSTANCE == null)
			INSTANCE = new GameStateManager();
		return INSTANCE;
	}

	public void checkGameState() {
		for (Colony colony : World.getInstance().getAllExistingColonies()) {
			if (colony.getGroupMembership().equals(GroupMembership.FOE1)) {
				state = GameState.RUNNING;
				return;
			}
		}

		state = GameState.PLAYER_WON;
		World.getInstance().notifyGameStateChange(state);
	}

}
