package com.epam.jwd.quadrangle.repository.search;

import com.epam.jwd.quadrangle.model.Figure;
import com.epam.jwd.quadrangle.model.Point;

public class FindByFigureDistanceFromOrigin
        extends Point
        implements SearchSpecification<Figure> {

    /**
     * @param point limits the area (where to find figures) from origin
     */
    public FindByFigureDistanceFromOrigin(Point point) {
        super(point);
    }

    /**
     * @param figure figure to be checked
     * @return true if figure or its part is in the area which is limited by point
     */
    @Override
    public boolean exists(Figure figure) {
        for (Point point : figure.getPoints()) {
            if (point.getX() < this.getX()
                && point.getY() < this.getY()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "FindByFigureDistanceFromOrigin{" +
               "oxDistance=" + getX() +
               ", oyDistance=" + getY() +
               '}';
    }
}
