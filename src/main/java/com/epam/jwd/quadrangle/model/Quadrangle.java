package com.epam.jwd.quadrangle.model;

import com.epam.jwd.quadrangle.exception.PointNumberException;

import java.util.List;
import java.util.Objects;

/**
 * The {@code Quadrangle} class is immutable. It extends the {@code CommonFigureProperties} abstract class
 *
 * @author Pavel Sidorovich
 * @see CommonFigureProperties
 * @since 1.0
 */
public class Quadrangle extends CommonFigureProperties {

    public Quadrangle(List<Point> points) {
        super(points);
    }

    public Quadrangle(int id, List<Point> points) {
        super(id, points);
    }

    @Override
    public void setPoint(int numberOfPoint, Point point) throws PointNumberException {
        if (numberOfPoint >= 0 && numberOfPoint < FigureType.QUADRANGLE.getNumberOfPoints()) {
            points.set(numberOfPoint, point);
        }
    }

    @Override
    public Figure withId(int id) {
        return new Quadrangle(id, getPoints());
    }

    @Override
    public String toString() {
        return "Quadrangle{" +
               "points=" + getPoints() +
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
        return Objects.equals(getPoints(), that.getPoints());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPoints());
    }
}
