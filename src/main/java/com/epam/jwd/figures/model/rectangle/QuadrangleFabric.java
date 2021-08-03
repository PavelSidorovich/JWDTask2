package com.epam.jwd.figures.model.rectangle;

import com.epam.jwd.figures.model.FigureFabric;
import com.epam.jwd.figures.model.FigureTypes;
import com.epam.jwd.figures.model.point.Point;
import com.epam.jwd.figures.utils.exceptions.FigureException;

import java.util.LinkedList;

public class QuadrangleFabric implements FigureFabric {

    public Quadrangle newInstance(Point x0y0, Point x0y1, Point x1y0, Point x1y1) {
        return new Quadrangle(x0y0, x0y1, x1y0, x1y1);
    }

    public Quadrangle newInstance(Quadrangle quadrangle) {
        return new Quadrangle(quadrangle.getPoint1(), quadrangle.getPoint2(),
                              quadrangle.getPoint3(), quadrangle.getPoint4());
    }

    @Override
    public Quadrangle newInstance(LinkedList<Point> pointList) {
        if (pointList.size() == FigureTypes.QUADRANGLE.getNumberOfPoints()) {
            return new Quadrangle(pointList.get(0), pointList.get(1),
                                  pointList.get(2), pointList.get(3));
        } else {
            throw new FigureException("Wrong number of coordinates! "
                                      + FigureException.ARGUMENTS_ERROR_MSG, Quadrangle.class, pointList);
        }
    }
}
