package de.stevenschwenke.java.javafx.simFX.business.worldObjects;

import de.stevenschwenke.java.javafx.simFX.business.logicalObjects.CartesianCoordinate;

/**
 * A resource.
 * 
 * @author Steven Schwenke
 * 
 */
public class Resource implements WorldObject {

	private CartesianCoordinate position;

	public Resource(CartesianCoordinate position) {
		super();
		this.position = position;
	}

	public CartesianCoordinate getPosition() {
		return position;
	}

	@Override
	public void destroy() {
		World.getInstance().removeWorldObject(this);
	}
}
