package main.java.business.worldObjects;

import java.util.Timer;
import java.util.TimerTask;

import main.java.business.logicalObjects.CartesianCoordinate;
import main.java.business.logicalObjects.GameStateManager;
import main.java.business.logicalObjects.GeographicalLogicProvider;
import main.java.business.logicalObjects.GroupMembership;
import main.java.business.logicalObjects.Interaction;


/**
 * Some kind of base or settlement or colony. Don't know yet.
 * 
 * @author Steven Schwenke
 */
public class Colony implements WorldObject {

	private long maxHarvestingDistance = 150;

	/**
	 * amount to which the radius of a possible colonialization increases per
	 * unit of available energy
	 */
	private final double COLONIZE_RADIUS_PER_UNIT_ENERGY = 30;

	private GroupMembership groupMembership;

	private CartesianCoordinate position;

	public long getMaxHarvestingDistance() {
		return maxHarvestingDistance;
	}

	private long availableEnergy;

	/** Task that runs repeatedly to gather resources */
	private TimerTask resourceTimerTask;

	public Colony(GroupMembership groupMembership, CartesianCoordinate position) {
		super();
		this.groupMembership = groupMembership;
		this.position = position;
		availableEnergy = 0;

		resourceTimerTask = new TimerTask() {
			@Override
			public void run() {
				acquireNearestResource();
			}
		};
		new Timer().schedule(resourceTimerTask, 0, 4000);
	}

	/**
	 * Acquires the nearest resource and fills the energy of this base.
	 */
	private synchronized void acquireNearestResource() {
		Resource nearestResource = GeographicalLogicProvider
				.findNearestResource(getPosition(), World.getInstance()
						.getAllExistingResources());

		if (nearestResource == null) // TODO Don't like this to return null.
			return;

		double distanceToRessource = GeographicalLogicProvider
				.calculateDistance(nearestResource.getPosition(), getPosition());
		if (distanceToRessource > maxHarvestingDistance) {
			return;
		}

		World.getInstance()
				.interact(Interaction.HARVEST, this, nearestResource);

		nearestResource.destroy();
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

	/**
	 * Attacks a colony.
	 * 
	 * @param attackedColony
	 *            that should be attacked by this colony
	 */
	public void attack(Colony attackedColony) {

		if (attackedColony.equals(this)) {
			throw new RuntimeException("Colonies don't attack themselves.");
			// TODO There should be a layer or a defined handling of such
			// exceptions. The idea is that every unusual state throws an
			// exception which gets caught by the UI. That way, every anormal
			// state gets processed by the UI and the business layer stays
			// clean.
		}

		long energyAttacked = attackedColony.getAvailableEnergy();

		if (energyAttacked > availableEnergy) {
			attackedColony.setAvailableEnergy(energyAttacked - availableEnergy);
			setAvailableEnergy(0);
		} else if (energyAttacked < availableEnergy) {
			setAvailableEnergy(availableEnergy - energyAttacked);
			attackedColony.destroy();
			GameStateManager.getInstance().checkGameState();
		}
	}

	@Override
	public void destroy() {
		setAvailableEnergy(0);
		resourceTimerTask.cancel();
		World.getInstance().removeWorldObject(this);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((groupMembership == null) ? 0 : groupMembership.hashCode());
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Colony other = (Colony) obj;
		if (groupMembership != other.groupMembership)
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}
}
