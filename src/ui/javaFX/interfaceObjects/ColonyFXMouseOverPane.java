package ui.javaFX.interfaceObjects;

import javafx.animation.ScaleTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	private static Label energyLabel;

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

		ObservableList<Node> content = pane.getChildren();

		// Resources and Button to add resources

		Label label = new Label("Energy:");
		GridPane.setConstraints(label, 0, 0);
		GridPane.setHalignment(label, HPos.LEFT);
		content.add(label);

		energyLabel = new Label("" + representedcolony.getAvailableEnergy());
		GridPane.setConstraints(energyLabel, 1, 0); // TODO make the button go
													// over two cells
		GridPane.setHalignment(energyLabel, HPos.LEFT);
		content.add(energyLabel);

		final Button colonizeButton = new Button("Colonize");
		GridPane.setConstraints(colonizeButton, 1, 1);
		GridPane.setHalignment(colonizeButton, HPos.RIGHT);
		content.add(colonizeButton);

		colonizeButton.setOnAction(new ColonizeEventHandler(colonyFX
				.getRepresentedColony()));

		return pane;
	}

	/**
	 * Refreshes the content of the pane and let it appear.
	 */
	public void appear() {
		energyLabel.setText(""
				+ colonyFX.getRepresentedColony().getAvailableEnergy());

		animationAppear.playFromStart();
	}
}
