package com.epam.jwd.figures.model.rectangle;

import com.epam.jwd.figures.model.FigureFabric;
import com.epam.jwd.figures.model.FigureTypes;
import com.epam.jwd.figures.model.point.Point;
import com.epam.jwd.figures.utils.exceptions.FigureException;

import java.util.LinkedList;

public class QuadrangleFabric implements FigureFabric {
    @Override
    public Quadrangle newInstance(LinkedList<Point> pointList) {
        if (pointList.size() == FigureTypes.QUADRANGLE.getNumberOfPoints()) {
            return new Quadrangle(pointList);
        } else {
            throw new FigureException("Wrong number of coordinates! "
                                      + FigureException.ARGUMENTS_ERROR_MSG, Quadrangle.class, pointList);
        }
    }
}
