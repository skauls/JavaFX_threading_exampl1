package de.stevenschwenke.java.javafx.simFX.ui.javaFX.interfaceObjects;

import de.stevenschwenke.java.javafx.simFX.business.worldObjects.World;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;

/**
 * Pane showing information about the world during root mode.
 * 
 * @author Steven Schwenke
 * 
 */
public class RootInfoPane extends TitledPane {

	public RootInfoPane() {

		// create text area for the content
		final TextArea rootInfoArea = new TextArea();

		rootInfoArea.setLayoutX(World.getInstance().getWidth() - 110);
		rootInfoArea.setLayoutY(50);
		rootInfoArea.setMaxWidth(150);

		int numberResources = World.getInstance().getAllExistingResources()
				.size();
		int numberColonies = World.getInstance().getAllExistingColonies()
				.size();
		rootInfoArea.setText("Colonies: " + numberColonies + "\nResources: "
				+ numberResources);
		// TODO refresh when new objects are created

		// Wrap content text area in pane so it can be dragged around

		setContent(rootInfoArea);
		setText("World info");
		// rootInfoPane = new TitledPane("World info", rootInfoArea);
		this.setCollapsible(false);

		// TODO make it resizable

		setOnMouseDragged(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (event instanceof MouseEvent) {
					MouseEvent mouseEvent = (MouseEvent) event;
					setLayoutX(mouseEvent.getSceneX());
					setLayoutY(mouseEvent.getSceneY());
				}
			}
		});
	}
}
