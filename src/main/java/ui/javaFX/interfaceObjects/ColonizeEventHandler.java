package main.java.ui.javaFX.interfaceObjects;

import main.java.business.logicalObjects.CartesianCoordinate;
import main.java.business.worldObjects.Colony;
import main.java.ui.javaFX.JavaFxApplication;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.scene.input.MouseEvent;

/**
 * Event handler for creating new colonies.
 * 
 * @author Steven Schwenke
 * 
 */
public class ColonizeEventHandler implements EventHandler<ActionEvent> {

	/** founding colony */
	private Colony foundingColony;

	/**
	 * @param foundingColony
	 *            colony that creates the new colony
	 */
	public ColonizeEventHandler(Colony foundingColony) {
		this.foundingColony = foundingColony;
	}

	@Override
	public void handle(ActionEvent event) {
		JavaFxApplication.getInstance().postToMessageLabel(
				"Place your new colony or press ESCAPE"); // TODO
															// implement
															// escaping
															// from
															// colony-placement

		// Switch into position selection mode
		EventHandler<MouseEvent> clickEventHandler = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				EventTarget target = event.getTarget();
				if (!target.equals(JavaFxApplication.getInstance().getScene()))
					return;

				boolean colonializationOK = foundingColony
						.colonize(new CartesianCoordinate(event.getSceneX(),
								event.getSceneY()));

				JavaFxApplication.getInstance().getScene()
						.removeEventFilter(MouseEvent.MOUSE_CLICKED, this);

				if (colonializationOK)
					JavaFxApplication.getInstance().postToMessageLabel(
							"Yeah, you founded a new colony!");
				else
					JavaFxApplication
							.getInstance()
							.postToMessageLabel(
									"This is too far for this colony. Let it gather more energy!");
			}
		};
		JavaFxApplication.getInstance().getScene()
				.addEventFilter(MouseEvent.MOUSE_CLICKED, clickEventHandler);
	}
}
