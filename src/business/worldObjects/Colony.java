package business.worldObjects;

import java.util.Timer;
import java.util.TimerTask;

import business.logicalObjects.CartesianCoordinate;
import business.logicalObjects.GeographicalLogicProvider;
import business.logicalObjects.GroupMembership;
import business.logicalObjects.Interaction;

/**
 * Some kind of base or settlement or colony. Don't know yet.
 * 
 * @author Steven Schwenke
 */
public class Colony implements WorldObject {

	private GroupMembership groupMembership;

	/**
	 * amount to which the radius of a possible colonialization increases per
	 * unit of available energy
	 */
	private final double COLONIZE_RADIUS_PER_UNIT_ENERGY = 10;

	private CartesianCoordinate position;

	private long availableEnergy;

	public Colony(GroupMembership groupMembership, CartesianCoordinate position) {
		super();
		this.groupMembership = groupMembership;
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
	 * @param coordinate
	 *            for the new colony
	 * @return true if the colonialization is allowed, else false
	 */
	public boolean colonize(CartesianCoordinate coordinate) {

		double distance = GeographicalLogicProvider.calculateDistance(position,
				coordinate);
		if (distance > calculateColonizeRadius()) {
			return false;
		}

		World.getInstance().addWorldObject(
				new Colony(groupMembership, new CartesianCoordinate(coordinate
						.getX(), coordinate.getY())));
		availableEnergy -= distance / COLONIZE_RADIUS_PER_UNIT_ENERGY;
		return true;
	}

	public GroupMembership getGroupMembership() {
		return groupMembership;
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

	/**
	 * Returns the radius, in which a colony can be foundet. Depends on the
	 * available energy.
	 * 
	 * @return radius for colonialization
	 */
	public double calculateColonizeRadius() {
		return availableEnergy * COLONIZE_RADIUS_PER_UNIT_ENERGY;
	}

}
