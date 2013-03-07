package business.logicalObjects;

import java.util.Set;

import business.worldObjects.Resource;

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
	 * @param resources
	 *            Set of resources to search in
	 * @return the nearest resource to a given place on the map
	 */
	public static Resource findNearestResource(CartesianCoordinate coordinate,
			Set<Resource> resources) {

		Resource nearestResource = null;
		double distanceToNearestResource = Double.MAX_VALUE;

		for (Resource r : resources) {
			double distance = calculateDistance(r.getPosition(), coordinate);

			if (distance < distanceToNearestResource) {
				distanceToNearestResource = distance;
				nearestResource = r;
			}
		}

		return nearestResource;
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
