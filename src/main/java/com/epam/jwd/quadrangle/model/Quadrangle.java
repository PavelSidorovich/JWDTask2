package com.epam.jwd.quadrangle.model;

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

    public void setPoint1(Point point){
        getPoints().remove(0);
        getPoints().add(0, point);
    }

    public void setPoint2(Point point){
        getPoints().remove(1);
        getPoints().add(1, point);
    }

    public void setPoint3(Point point){
        getPoints().remove(2);
        getPoints().add(2, point);
    }

    public void setPoint4(Point point){
        getPoints().remove(3);
        getPoints().add(3, point);
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
