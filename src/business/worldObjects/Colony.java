package business.worldObjects;

import java.util.Timer;
import java.util.TimerTask;

import business.logicalObjects.CartesianCoordinate;
import business.logicalObjects.GeographicalLogicProvider;
import business.logicalObjects.Interaction;

/**
 * Some kind of base or settlement or colony. Don't know yet.
 * 
 * @author Steven Schwenke
 */
public class Colony implements WorldObject {

	private CartesianCoordinate position;

	private long availableEnergy;

	public Colony(CartesianCoordinate position) {
		super();
		this.position = position;
		availableEnergy = 0;

		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				acquireNearestResource();
			}
		}, 0, 4000);
	}

	/**
	 * Acquires the nearest resource and fills the energy of this base.
	 */
	private void acquireNearestResource() {
		Resource nearestResource = GeographicalLogicProvider
				.findNearestResource(getPosition());

		if (nearestResource == null) // TODO Don't like this to return null.
			return;

		World.getInstance()
				.interact(Interaction.HARVEST, this, nearestResource);

		World.getInstance().removeWorldObject(nearestResource);
		availableEnergy++;
	}

	/**
	 * Founds a new colony.
	 * 
	 * @param x
	 *            position in the world
	 * @param y
	 *            position in the world
	 */
	public void colonize(double x, double y) {
		World.getInstance().addWorldObject(
				new Colony(new CartesianCoordinate(x, y)));
	}

	public CartesianCoordinate getPosition() {
		return position;
	}

	public void setPosition(CartesianCoordinate position) {
		this.position = position;
	}

	public long getAvailableEnergy() {
		return availableEnergy;
	}

	public void setAvailableEnergy(long availableEnergy) {
		this.availableEnergy = availableEnergy;
	}

}
