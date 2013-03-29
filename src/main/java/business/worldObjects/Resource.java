package main.java.business.worldObjects;

import main.java.business.logicalObjects.CartesianCoordinate;

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
