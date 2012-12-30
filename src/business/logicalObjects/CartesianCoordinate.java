package business.logicalObjects;

/**
 * Represents a point in a 2-dimensional space.
 * 
 * @author Steven Schwenke
 */
public class CartesianCoordinate {

	private double x;

	private double y;

	public CartesianCoordinate(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}
