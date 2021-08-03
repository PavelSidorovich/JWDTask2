package com.epam.jwd.figures.utils.action;

import com.epam.jwd.figures.model.point.Point;

public class MathVector {
    private final double x;
    private final double y;

    public MathVector(Point point1, Point point2) {
        x = point2.getX() - point1.getX();
        y = point2.getY() - point1.getY();
    }

    public double multiply(MathVector secondVector) {
        return this.x * secondVector.y - secondVector.x * this.y;
    }
}
