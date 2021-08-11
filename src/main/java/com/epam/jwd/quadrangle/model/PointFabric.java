package com.epam.jwd.quadrangle.model;

import java.util.LinkedList;

/**
 * The {@code PointFabric} class is a fabric designed to
 * create {@link Point} objects
 *
 * @author Pavel Sidorovich
 * @since 1.0
 * @see FigureFabric
 */
public class PointFabric implements FigureFabric {

    public Point newInstance(double x, double y){
        return new Point(x, y);
    }

    /**
     * Creates new instance of {@code Point} class
     * @param pointList list of points
     * @return created object of {@code Point} class and {@code null} if the number of coordinates is invalid
     */
    @Override
    public Point newInstance(LinkedList<Point> pointList) {
        if (pointList.size() == FigureType.POINT.getNumberOfPoints()) {
            return new Point(pointList.get(0));
        }
        return null;
    }
}

