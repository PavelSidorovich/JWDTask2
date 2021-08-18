package com.epam.jwd.quadrangle.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * The {@code Point} class implements the {@code Figure} interface
 *
 * @author Pavel Sidorovich
 * @see Figure
 * @since 1.0
 */
public class Point implements Figure {
    public static final FigureType FIGURE_TYPE = FigureType.POINT;

    private final int id;
    private double x;
    private double y;

    Point(double x, double y) {
        this.id = 0;
        this.x = x;
        this.y = y;
    }

    public Point(Point point) {
        this.id = 0;
        this.x = point.getX();
        this.y = point.getY();
    }

    public Point(int id, Point point) {
        this.id = id;
        this.x = point.getX();
        this.y = point.getY();
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public Integer getNumberOfPoints() {
        return FIGURE_TYPE.getNumberOfPoints();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Figure withId(int id) {
        return new Point(id, this);
    }

    @Override
    public ArrayList<Point> getPoints() {
        return new ArrayList<>(Collections.singletonList(new Point(this)));
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
