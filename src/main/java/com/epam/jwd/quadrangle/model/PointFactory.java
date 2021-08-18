package com.epam.jwd.quadrangle.model;

import com.epam.jwd.quadrangle.exception.PointBuildException;

import java.util.List;

/**
 * The {@code PointFactory} class is a fabric designed to create {@link Point} objects
 *
 * @author Pavel Sidorovich
 * @see FigureFactory
 * @since 1.0
 */
public class PointFactory implements FigureFactory {

    private static PointFactory factory;

    private PointFactory() {
    }

    public static PointFactory getInstance() {
        if (factory == null) {
            factory = new PointFactory();
        }
        return factory;
    }

    public Point of(double x, double y) {
        return new Point(x, y);
    }

    /**
     * Creates new instance of {@code Point} class
     *
     * @param pointList list of points
     * @return created object of {@code Point} class and {@code null} if the number of coordinates is invalid
     */
    @Override
    public Point of(List<Point> pointList) {
        if (pointList.size() == FigureType.POINT.getNumberOfPoints()) {
            return new Point(pointList.get(0));
        } else {
            throw new PointBuildException();
        }
    }

    @Override
    public FigurePublisher publisherOf(List<Point> pointList) {
        if (pointList.size() == FigureType.POINT.getNumberOfPoints()) {
            return new FigurePublisher(new Point(pointList.get(0)));
        } else {
            throw new PointBuildException();
        }
    }

    public FigurePublisher publisherOf(double x, double y) {
        return new FigurePublisher(new Point(x, y));
    }
}

