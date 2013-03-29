package test.java.business.logicalObjects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashSet;
import java.util.Set;

import main.java.business.logicalObjects.CartesianCoordinate;
import main.java.business.logicalObjects.GeographicalLogicProvider;
import main.java.business.worldObjects.Resource;

import org.junit.Test;


/**
 * Test for {@link GeographicalLogicProvider}.
 * 
 * @author Steven Schwenke
 * 
 */
public class GeographicalLogicProviderTest {

	@Test
	public void findNearestResourceReturnsNullWhenThereIsNoResource() {
		// TODO Think about what to return when there is absolutely no resource
		// on the map. Null is bad.

		assertNull(GeographicalLogicProvider.findNearestResource(
				new CartesianCoordinate(10, 10), new HashSet<Resource>()));
	}

	@Test
	public void findNearestResourceWhenResourceIsOnPosition() {
		Set<Resource> resourceSet = new HashSet<Resource>();
		Resource resourceOnPlace = new Resource(new CartesianCoordinate(10, 10));
		resourceSet.add(resourceOnPlace);

		Resource nearestResource = GeographicalLogicProvider
				.findNearestResource(new CartesianCoordinate(10, 10),
						resourceSet);

		assertEquals(resourceOnPlace, nearestResource);
	}

	@Test
	public void findNearestResourceWithTwoResources() {
		Set<Resource> resourceSet = new HashSet<Resource>();
		Resource r1 = new Resource(new CartesianCoordinate(0, 10));
		Resource r2 = new Resource(new CartesianCoordinate(0, 20));
		resourceSet.add(r1);
		resourceSet.add(r2);

		Resource nearestResource = GeographicalLogicProvider
				.findNearestResource(new CartesianCoordinate(0, 0), resourceSet);

		assertEquals(r1, nearestResource);
	}

	@Test
	public void findNearestResourceWithThreeResources() {
		Set<Resource> resourceSet = new HashSet<Resource>();
		Resource r1 = new Resource(new CartesianCoordinate(0, 30));
		Resource r2 = new Resource(new CartesianCoordinate(0, 20));
		Resource r3 = new Resource(new CartesianCoordinate(0, 30));
		resourceSet.add(r1);
		resourceSet.add(r2);
		resourceSet.add(r3);

		Resource nearestResource = GeographicalLogicProvider
				.findNearestResource(new CartesianCoordinate(0, 0), resourceSet);

		assertEquals(r2, nearestResource);
	}

	@Test
	public void calculateDistanceIsZeroGivenTwoEqualCoordinates() {
		assertEquals(0, GeographicalLogicProvider.calculateDistance(
				new CartesianCoordinate(10, 10),
				new CartesianCoordinate(10, 10)), 0.0001);
	}

	@Test
	public void calculateDistanceWorksAtXAxis() {
		assertEquals(20, GeographicalLogicProvider.calculateDistance(
				new CartesianCoordinate(0, 0), new CartesianCoordinate(20, 0)),
				0.0001);
	}

	@Test
	public void calculateDistanceWorksAtYAxis() {
		assertEquals(20, GeographicalLogicProvider.calculateDistance(
				new CartesianCoordinate(0, 0), new CartesianCoordinate(0, 20)),
				0.0001);
	}

	@Test
	public void calculateDistanceWorksAtBothAxis() {
		assertEquals(5, GeographicalLogicProvider.calculateDistance(
				new CartesianCoordinate(0, 0), new CartesianCoordinate(3, 4)),
				0.0001);
	}
}
