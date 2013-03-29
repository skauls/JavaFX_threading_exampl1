package de.stevenschwenke.java.javafx.simFX.ui.rootButton;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import de.stevenschwenke.java.javafx.simFX.business.worldObjects.World;
import de.stevenschwenke.java.javafx.simFX.ui.javaFX.JavaFxApplication;
import de.stevenschwenke.java.javafx.simFX.ui.resourceSpawner.ResourceSpawnerFX;

/**
 * Button at the upper right edge of the screen which enables or disables the
 * root-mode. This mode allows for manipulation of the world.
 * 
 * @author Steven Schwenke
 * 
 */
public class RootButton extends Button {

	private static RootButton instance;

	/** if enabled, the user can manipulate the world */
	private boolean rootMode = false;

	private TitledPane rootInfoPane;

	public static RootButton getInstance() {
		if (instance == null) {
			instance = new RootButton();
		}
		return instance;
	}

	private RootButton() {
		final Button rootButton = new Button("Root");
		rootButton.setLayoutX(World.getInstance().getWidth() - 50);
		rootButton.setLayoutY(10);

		rootButton.addEventFilter(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						rootMode = !rootMode;

						if (rootMode) {
							rootButton.setStyle("-fx-base: #a00;");
							showRootInfoArea();
						} else {
							rootButton.setStyle("-fx-base: #aaa;");
							JavaFxApplication.getInstance().getRootGroup()
									.getChildren().remove(rootInfoPane);
						}
						for (Node n : JavaFxApplication.getInstance()
								.getRootGroup().getChildren()) {
							if (n instanceof ResourceSpawnerFX)
								((ResourceSpawnerFX) n).setVisible(rootMode);
						}
					}
				});

		JavaFxApplication.getInstance().getRootGroup().getChildren()
				.add(rootButton);
	}

	/**
	 * Shows a text area with information about the world.
	 */
	private void showRootInfoArea() {
		rootInfoPane = new RootInfoPane();
		JavaFxApplication.getInstance().getRootGroup().getChildren()
				.add(rootInfoPane);
	}

	public boolean isRootMode() {
		return rootMode;
	}

}
