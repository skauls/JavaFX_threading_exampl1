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
}
