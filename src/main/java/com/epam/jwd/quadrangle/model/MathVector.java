package com.epam.jwd.quadrangle.model;

/**
 * The {@code MathVector} class represents mathematical vector and some actions applied to him
 *
 * @author Pavel Sidorovich
 * @since 1.0
 */
public class MathVector {
    private final Point vector;

    public MathVector(Point point1, Point point2) {
        vector = PointFactory.getInstance().of(point2.getX() - point1.getX(), point2.getY() - point1.getY());
    }

    public Double scalarProduct(MathVector secondVector) {
        return vector.getX() * secondVector.vector.getY() - secondVector.vector.getX() * vector.getY();
    }

    public boolean perpendicular(MathVector secondVector) {
        return Double.valueOf(0).equals(Math.abs(vector.getX() * secondVector.vector.getX()
                                                 + vector.getY() * secondVector.vector.getY()));
    }
}
