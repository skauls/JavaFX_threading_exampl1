package ui.javaFX.interfaceObjects;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import ui.javaFX.JavaFxApplication;
import ui.javaFX.worldObjects.ColonyFX;
import business.worldObjects.Colony;

/**
 * Pane that opens when hovering over a {@link ColonyFX}.
 * 
 * @author Steven Schwenke
 * 
 */
public class ColonyFXMouseOverPane extends TitledPane {

	private ScaleTransition animationAppear;
	private ScaleTransition animationDisappear;

	private ColonyFX colonyFX;

	public ColonyFXMouseOverPane(final ColonyFX colonyFX) {
		super("Colony", createGridPane(colonyFX));

		this.colonyFX = colonyFX;

		final ColonyFXMouseOverPane createdPane = this;

		setCollapsible(false);
		setPrefSize(200, 200);
		setLayoutX(colonyFX.getRepresentedColony().getPosition().getX());
		setLayoutY(colonyFX.getRepresentedColony().getPosition().getY());

		addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				animationDisappear
						.setOnFinished(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								JavaFxApplication.getInstance().getRootGroup()
										.getChildren().remove(createdPane);

							}
						});

				animationDisappear.play();

			}
		});

		animationAppear = new ScaleTransition(Duration.millis(300), this);
		animationAppear.setFromX(0);
		animationAppear.setToX(1);

		animationDisappear = new ScaleTransition(Duration.millis(300), this);
		animationDisappear.setFromX(1);
		animationDisappear.setToX(0);
	}

	private static GridPane createGridPane(final ColonyFX colonyFX) {

		final Colony representedcolony = colonyFX.getRepresentedColony();

		GridPane pane = new GridPane();

		pane.setHgap(4);
		pane.setVgap(6);

		pane.setPadding(new Insets(5, 3, 3, 3));

		ColumnConstraints colinfo = new ColumnConstraints(20, 70, 90);
		for (int j = 0; j <= 2; j++) {
			pane.getColumnConstraints().add(colinfo);
		}

		return pane;
	}

	public ScaleTransition getAnimationAppear() {
		return animationAppear;
	}
}
