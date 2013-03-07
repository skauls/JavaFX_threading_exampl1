package ui.javaFX.worldObjects;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import business.worldObjects.Resource;

/**
 * Graphical representation of a {@link Resource} for JavaFX.
 * 
 * @author Steven Schwenke
 * 
 */
public class ResourceFX extends ImageView {

	private Resource representedResource;

	public ResourceFX(final Resource representedRessource) {
		super();

		this.representedResource = representedRessource;

		int randomNumber = (int) Math.round((Math.random() * 3) + 1);
		Image image = new Image("resource" + randomNumber + ".png");

		setImage(image);
		setFitWidth(30);
		setLayoutX(representedRessource.getPosition().getX());
		setLayoutY(representedRessource.getPosition().getY());
		setPreserveRatio(true);
		setSmooth(true);
		setCache(true);

		FadeTransition ft = new FadeTransition(Duration.millis(3000), this);
		ft.setFromValue(0.1);
		ft.setToValue(1.0);
		ft.play();

		ScaleTransition st = new ScaleTransition(Duration.millis(3000), this);
		st.setFromX(0);
		st.setToX(0.4);
		st.setFromY(0);
		st.setToY(0.4);
		st.play();
	}

	public Resource getRepresentedResource() {
		return representedResource;
	}

}
