package ui.javaFX.worldObjects;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import ui.javaFX.interfaceObjects.ResourceFXMouseOverPane;
import business.worldObjects.Colony;

/**
 * Graphical representation of a {@link Colony} for JavaFX.
 * 
 * @author Steven Schwenke
 * 
 */
public class ColonyFX extends Button {

	/** the ressource spawner that is represented by this object */
	private Colony representedColony;

	/** the pane with the context menu */
	private ResourceFXMouseOverPane mouseOverPane;

	public ColonyFX(final Colony representedColony) {
		super();

		this.representedColony = representedColony;

		final ColonyFX thisColonyFX = this;

		this.setLayoutX(representedColony.getPosition().getX());
		this.setLayoutY(representedColony.getPosition().getY());
		this.setText("Colony");

		this.addEventFilter(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						// TODO mouse over pane should be displayed regardless
						// of root mode. Allows the player to set the strategy
						// for this colony.

						// if (mouseOverPane == null)
						// mouseOverPane = new MouseOverPane(
						// thisColonyFX);

						// Possible that the pane is already added because the
						// mouse hovered over this spawner and the pane didn't
						// get removed after the mouse left.
						// if (!JavaFxApplication.getInstance().getRootGroup()
						// .getChildren().contains(mouseOverPane)) {
						// JavaFxApplication.getInstance().getRootGroup()
						// .getChildren().add(mouseOverPane);
						//
						// }
						// mouseOverPane.getAnimationAppear().playFromStart();
					}
				});
	}
}
