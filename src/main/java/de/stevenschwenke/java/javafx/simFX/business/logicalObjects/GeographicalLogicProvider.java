package de.stevenschwenke.java.javafx.simFX.business.logicalObjects;

/**
 * Provides geographical logic.
 * 
 * @author Steven Schwenke
 * 
 */
public class GeographicalLogicProvider {

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
