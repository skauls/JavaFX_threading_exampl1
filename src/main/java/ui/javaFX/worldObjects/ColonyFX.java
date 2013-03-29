package main.java.ui.javaFX.worldObjects;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.java.business.logicalObjects.GroupMembership;
import main.java.business.worldObjects.Colony;
import main.java.ui.javaFX.JavaFxApplication;
import main.java.ui.javaFX.interfaceObjects.ColonyFXMouseOverPane;

/**
 * Graphical representation of a {@link Colony} for JavaFX.
 * 
 * @author Steven Schwenke
 * 
 */
public class ColonyFX extends ImageView {

	/** the ressource spawner that is represented by this object */
	private Colony representedColony;

	public ColonyFX(final Colony representedColony) {
		super();

		this.representedColony = representedColony;

		final ColonyFX thisColonyFX = this;

		Image image = new Image(representedColony.getGroupMembership().equals(
				GroupMembership.PLAYER) ? "colonyPlayer.png" : "colonyFoe.png");

		setImage(image);
		setFitWidth(30);
		setLayoutX(representedColony.getPosition().getX());
		setLayoutY(representedColony.getPosition().getY());
		setPreserveRatio(true);
		setSmooth(true);
		setCache(true);

		this.addEventFilter(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						if (!representedColony.getGroupMembership().equals(
								GroupMembership.PLAYER)
								&& !JavaFxApplication.getInstance()
										.isRootMode()) {
							return;
						}
						ColonyFXMouseOverPane mouseOverPane = new ColonyFXMouseOverPane(
								thisColonyFX);

						JavaFxApplication.getInstance().getRootGroup()
								.getChildren().add(mouseOverPane);

						mouseOverPane.appear();
					}
				});
	}

	public Colony getRepresentedColony() {
		return representedColony;
	}
}
