package com.epam.jwd.quadrangle.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;

/**
 * The {@code Point} class is immutable. It implements the {@code Figure} interface
 *
 * @author Pavel Sidorovich
 * @since 1.0
 * @see Figure
 */
public class Point implements Figure {
    public static final FigureType FIGURE_TYPE = FigureType.POINT;

    private final double x;
    private final double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point point) {
        this.x = point.getX();
        this.y = point.getY();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public int getNumberOfPoints() {
        return FIGURE_TYPE.getNumberOfPoints();
    }

    @Override
    public LinkedList<Point> getPoints() {
        return new LinkedList<>(Collections.singletonList(new Point(this)));
    }

    @Override
    public String toString() {
        return "Point{" +
               "x=" + x +
               ", y=" + y +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
