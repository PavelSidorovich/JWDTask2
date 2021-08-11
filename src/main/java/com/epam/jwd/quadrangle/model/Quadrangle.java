package com.epam.jwd.quadrangle.model;

import java.util.LinkedList;
import java.util.Objects;

/**
 * The {@code Quadrangle} class is immutable. It extends the {@code CommonFigureProperties} abstract class
 *
 * @author Pavel Sidorovich
 * @see CommonFigureProperties
 * @since 1.0
 */
public class Quadrangle extends CommonFigureProperties {

    public Quadrangle(LinkedList<Point> points) {
        super(points, FigureType.QUADRANGLE);
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
