package business.logicalObjects;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import business.worldObjects.Resource;
import business.worldObjects.World;

/**
 * Test for {@link GeographicalLogicProvider}.
 * 
 * @author Steven Schwenke
 * 
 */
public class GeographicalLogicProviderTest {

	// TODO Think about what to return when there is absolutely no resource on
	// the map.

	@Ignore("Final use of this method not clear yet.")
	@Test
	public void nearestResourceIsNotNullWhenThereIsAResource() {

	}

	@Ignore("Final use of this method not clear yet.")
	@Test
	public void findNearestResourceWhenResourceIsOnPosition() {
		World.getInstance().init(null, 100, 100);
		Resource resourceOnPlace = new Resource(new CartesianCoordinate(10, 10));
		World.getInstance().addWorldObject(resourceOnPlace);

		Resource nearestResource = GeographicalLogicProvider
				.findNearestResource(new CartesianCoordinate(10, 10));

		assertEquals(resourceOnPlace, nearestResource);
	}

}
