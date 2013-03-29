package main.java.business.logicalObjects;

/**
 * Represents a polar coordinate.
 * 
 * @author Steven Schwenke
 * 
 */
public class PolarCoordinate {

	private double angle;

	private double distance;

	public CartesianCoordinate toCartesianCoordinates() {

		if (angle < 0 || angle > 360)
			throw new RuntimeException("Angle has to be > 0 and < 360 degrees.");

		double x = distance * Math.sin(Math.toRadians(angle));
		double y = distance * Math.cos(Math.toRadians(angle));

		CartesianCoordinate cc = new CartesianCoordinate(x, y);
		return cc;
	}

	public PolarCoordinate(double angle, double distance) {
		super();
		this.angle = angle;
		this.distance = distance;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
}
