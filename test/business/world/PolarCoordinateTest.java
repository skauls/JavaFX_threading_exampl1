package business.world;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import business.logicalObjects.CartesianCoordinate;
import business.logicalObjects.PolarCoordinate;

/**
 * Tests for {@link PolarCoordinate}.
 * 
 * @author Steven Schwenke
 * 
 */
public class PolarCoordinateTest {

	/**
	 * 
	 */
	private static final float SMALL_OFFSET = 0.001f;

	@Test
	public void nullVectorToCartesianCoordinatesTest() {

		PolarCoordinate pc = new PolarCoordinate(90, 0);

		CartesianCoordinate cc = pc.toCartesianCoordinates();

		assertEquals(0f, cc.getX(), SMALL_OFFSET);
		assertEquals(0f, cc.getY(), SMALL_OFFSET);
	}

	// Quadrant 1 in unit circle

	@Test
	public void unitCircleVectorNorthToCartesianCoordinatesTest() {

		PolarCoordinate pc = new PolarCoordinate(0, 1);

		CartesianCoordinate cc = pc.toCartesianCoordinates();

		assertEquals(0f, cc.getX(), SMALL_OFFSET);
		assertEquals(1f, cc.getY(), SMALL_OFFSET);
	}

	@Test
	public void firstQuadrantToCartesianCoordinatesTest() {

		PolarCoordinate pc = new PolarCoordinate(45, 1);

		CartesianCoordinate cc = pc.toCartesianCoordinates();

		assertEquals(Math.sqrt(0.5d), cc.getX(), SMALL_OFFSET);
		assertEquals(Math.sqrt(0.5d), cc.getY(), SMALL_OFFSET);
	}

	// Quadrant 2 in unit circle

	@Test
	public void unitCircleVectorEastToCartesianCoordinatesTest() {

		PolarCoordinate pc = new PolarCoordinate(90, 1);

		CartesianCoordinate cc = pc.toCartesianCoordinates();

		assertEquals(1f, cc.getX(), SMALL_OFFSET);
		assertEquals(0f, cc.getY(), SMALL_OFFSET);
	}

	@Test
	public void secondQuadrantToCartesianCoordinatesTest() {

		PolarCoordinate pc = new PolarCoordinate(135, 1);

		CartesianCoordinate cc = pc.toCartesianCoordinates();

		assertEquals(Math.sqrt(0.5d), cc.getX(), SMALL_OFFSET);
		assertEquals(-Math.sqrt(0.5d), cc.getY(), SMALL_OFFSET);
	}

	// Quadrant 3 in unit circle

	@Test
	public void unitCircleVectorSouthToCartesianCoordinatesTest() {

		PolarCoordinate pc = new PolarCoordinate(180, 1);

		CartesianCoordinate cc = pc.toCartesianCoordinates();

		assertEquals(0f, cc.getX(), SMALL_OFFSET);
		assertEquals(-1f, cc.getY(), SMALL_OFFSET);
	}

	@Test
	public void thirdQuadrantToCartesianCoordinatesTest() {

		PolarCoordinate pc = new PolarCoordinate(225, 1);

		CartesianCoordinate cc = pc.toCartesianCoordinates();

		assertEquals(-Math.sqrt(0.5d), cc.getX(), SMALL_OFFSET);
		assertEquals(-Math.sqrt(0.5d), cc.getY(), SMALL_OFFSET);
	}

	// Quadrant 4 in unit circle

	@Test
	public void unitCircleVectorWestToCartesianCoordinatesTest() {

		PolarCoordinate pc = new PolarCoordinate(270, 1);

		CartesianCoordinate cc = pc.toCartesianCoordinates();

		assertEquals(-1f, cc.getX(), SMALL_OFFSET);
		assertEquals(0f, cc.getY(), SMALL_OFFSET);
	}

	@Test
	public void fourthQuadrantToCartesianCoordinatesTest() {

		PolarCoordinate pc = new PolarCoordinate(315, 1);

		CartesianCoordinate cc = pc.toCartesianCoordinates();

		assertEquals(-Math.sqrt(0.5d), cc.getX(), SMALL_OFFSET);
		assertEquals(Math.sqrt(0.5d), cc.getY(), SMALL_OFFSET);
	}
}
