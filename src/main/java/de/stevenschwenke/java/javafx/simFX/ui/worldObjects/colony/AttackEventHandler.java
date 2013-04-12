package de.stevenschwenke.java.javafx.simFX.ui.worldObjects.colony;

import de.stevenschwenke.java.javafx.simFX.business.worldObjects.Colony;
import de.stevenschwenke.java.javafx.simFX.ui.javaFX.JavaFxApplication;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;

/**
 * Event handler for attacking foe colonies.
 * 
 * @author Steven Schwenke
 * 
 */
public class AttackEventHandler implements EventHandler<ActionEvent> {

	private Colony attackingColony;

	/**
	 * @param attackingColony
	 *            initiates the attack
	 */
	public AttackEventHandler(Colony attackingColony) {
		this.attackingColony = attackingColony;
	}

	@Override
	public void handle(ActionEvent event) {
		JavaFxApplication
				.getInstance()
				.postToMessageLabel(
						"Click a foe colony to attack it with all your available energy or press ESCAPE");
		// TODO implement escaping from attack

		final EventHandler<MouseEvent> iconEventHandler = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getTarget().getClass().equals(ColonyFX.class)) {
					JavaFxApplication.getInstance().getScene()
							.setCursor(Cursor.CROSSHAIR);
				} else {
					JavaFxApplication.getInstance().getScene()
							.setCursor(Cursor.DEFAULT);
				}
			}
		};
		JavaFxApplication.getInstance().getScene()
				.addEventFilter(MouseDragEvent.MOUSE_MOVED, iconEventHandler);

		EventHandler<MouseEvent> clickEventHandler = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (event.getTarget().getClass().equals(ColonyFX.class)
						&& event.getClickCount() > 0) {
					Colony attackedColony = ((ColonyFX) event.getTarget())
							.getRepresentedColony();

					JavaFxApplication.getInstance().postToMessageLabel(
							"Attack happened!");

					attackingColony.attack(attackedColony);

					JavaFxApplication.getInstance().getScene()
							.setCursor(Cursor.DEFAULT);

					JavaFxApplication.getInstance().getScene()
							.removeEventFilter(MouseEvent.MOUSE_CLICKED, this);
					JavaFxApplication
							.getInstance()
							.getScene()
							.removeEventFilter(MouseEvent.MOUSE_MOVED,
									iconEventHandler);
				}
			}

		};
		JavaFxApplication
				.getInstance()
				.getScene()
				.addEventFilter(MouseDragEvent.MOUSE_CLICKED, clickEventHandler);
	}
}
