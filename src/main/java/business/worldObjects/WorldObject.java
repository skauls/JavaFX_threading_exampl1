package main.java.business.worldObjects;

import java.util.TimerTask;

import main.java.business.logicalObjects.CartesianCoordinate;


/**
 * All objects that are meant to be displayed in the user interface have to
 * implement this interface.
 * 
 * @author Steven Schwenke
 * 
 */
public interface WorldObject {

	public CartesianCoordinate getPosition();

	/**
	 * Destroys this object. All {@link TimerTask}s will be stopped and the
	 * object will be removed from the {@link World}.
	 * 
	 * @return
	 */
	public void destroy();

}
