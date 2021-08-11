package com.epam.jwd.quadrangle.model;

/**
 * The {@code MathVector} class represents mathematical vector and some actions applied to him
 *
 * @author Pavel Sidorovich
 * @since 1.0
 */
public class MathVector {
    private final double x;
    private final double y;

    public MathVector(Point point1, Point point2) {
        x = point2.getX() - point1.getX();
        y = point2.getY() - point1.getY();
    }

    public Double scalarProduct(MathVector secondVector) {
        return this.x * secondVector.y - secondVector.x * this.y;
    }

    public boolean perpendicular(MathVector secondVector) {
        return Double.valueOf(0).equals(Math.abs(this.x * secondVector.x + this.y * secondVector.y));
    }
}
