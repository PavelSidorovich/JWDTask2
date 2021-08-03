package com.epam.jwd.figures.model.rectangle;

import com.epam.jwd.figures.model.Figure;
import com.epam.jwd.figures.model.FigureTypes;
import com.epam.jwd.figures.model.point.Point;
import com.epam.jwd.figures.model.point.PointFabric;

import java.util.Objects;

/**
 * This class is immutable
 */
public class Quadrangle implements Figure {
    private static final FigureTypes FIGURE_TYPE = FigureTypes.QUADRANGLE;
    private static final PointFabric POINT_FABRIC = new PointFabric();

    private final Point point1;
    private final Point point2;
    private final Point point3;
    private final Point point4;

    Quadrangle(Point point1, Point point2, Point point3, Point point4) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.point4 = point4;
    }

    public Point getPoint1() {
        return POINT_FABRIC.newInstance(point1);
    }

    public Point getPoint2() {
        return POINT_FABRIC.newInstance(point2);
    }

    public Point getPoint3() {
        return POINT_FABRIC.newInstance(point3);
    }

    public Point getPoint4() {
        return POINT_FABRIC.newInstance(point4);
    }

    @Override
    public int getNumberOfPoints() {
        return FIGURE_TYPE.getNumberOfPoints();
    }

    @Override
    public String toString() {
        return "Quadrangle{" +
               "point1=" + point1 +
               ", point2=" + point2 +
               ", point3=" + point3 +
               ", point4=" + point4 +
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
        Quadrangle quadrangle = (Quadrangle) o;
        return Objects.equals(point1, quadrangle.point1) && Objects.equals(point2, quadrangle.point2) &&
               Objects.equals(point3, quadrangle.point3) && Objects.equals(point4, quadrangle.point4);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point1, point2, point3, point4);
    }
}
