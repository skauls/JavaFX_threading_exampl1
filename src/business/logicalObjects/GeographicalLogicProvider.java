package business.logicalObjects;

import java.util.Set;

import business.worldObjects.Resource;
import business.worldObjects.World;

/**
 * Provides geographical logic.
 * 
 * @author Steven Schwenke
 * 
 */
public class GeographicalLogicProvider {

	/**
	 * @param coordinate
	 *            a position on the world map
	 * @return the nearest resource to a given place on the map
	 */
	public static Resource findNearestResource(CartesianCoordinate coordinate) {

		// TODO This is only a first implementation. Goal is to find the nearest
		// resource to a given place on the map.

		Set<Resource> allExistingResources = World.getInstance()
				.getAllExistingResources();

		if (allExistingResources.isEmpty())
			return null;
		return allExistingResources.iterator().next();
	}

	/**
	 * Calculates the distance between two {@link CartesianCoordinate}s.
	 * 
	 * @param coordinate1
	 * @param coordinate2
	 */
	public static double calculateDistance(CartesianCoordinate coordinate1,
			CartesianCoordinate coordinate2) {
		return Math.sqrt(Math.pow(coordinate2.getX() - coordinate1.getX(), 2)
				+ Math.pow(coordinate2.getY() - coordinate1.getY(), 2));
	}
}
