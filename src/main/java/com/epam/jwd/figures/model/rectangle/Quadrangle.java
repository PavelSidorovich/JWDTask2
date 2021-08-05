package com.epam.jwd.figures.model.rectangle;

import com.epam.jwd.figures.model.Figure;
import com.epam.jwd.figures.model.FigureTypes;
import com.epam.jwd.figures.model.point.Point;

import java.util.LinkedList;
import java.util.Objects;

/**
 * The {@code Quadrangle} class is immutable. It implements the {@code Figure} interface
 *
 * @author Pavel Sidorovich
 * @since 1.0
 * @see Figure
 */
public class Quadrangle implements Figure {
    private static final FigureTypes FIGURE_TYPE = FigureTypes.QUADRANGLE;

    private final LinkedList<Point> points;

    public Quadrangle(LinkedList<Point> points) {
            this.points = points;
    }

    @Override
    public int getNumberOfPoints() {
        return FIGURE_TYPE.getNumberOfPoints();
    }

    @Override
    public LinkedList<Point> getPoints() {
        return new LinkedList<>(points);
    }

    @Override
    public String toString() {
        return "Quadrangle{" +
               "points=" + points +
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
        Quadrangle that = (Quadrangle) o;
        return Objects.equals(points, that.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(points);
    }
}
