package business.worldObjects;

import business.logicalObjects.CartesianCoordinate;

/**
 * Some kind of base or settlement or colony. Don't know yet.
 * 
 * @author Steven Schwenke
 */
public class Colony implements WorldObject {

	private CartesianCoordinate position;

	public Colony(CartesianCoordinate position) {
		super();
		this.position = position;
	}

	public CartesianCoordinate getPosition() {
		return position;
	}

	public void setPosition(CartesianCoordinate position) {
		this.position = position;
	}
}
