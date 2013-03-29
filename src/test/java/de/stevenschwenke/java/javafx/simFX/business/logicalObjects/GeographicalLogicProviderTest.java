package de.stevenschwenke.java.javafx.simFX.business.logicalObjects;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test for {@link GeographicalLogicProvider}.
 * 
 * @author Steven Schwenke
 * 
 */
public class GeographicalLogicProviderTest {

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
