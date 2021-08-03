package com.epam.jwd.figures.model.point;

import com.epam.jwd.figures.model.FigureFabric;
import com.epam.jwd.figures.model.FigureTypes;

import java.util.LinkedList;

public class PointFabric implements FigureFabric {

    public Point newInstance(double x, double y){
        return new Point(x, y);
    }

    public Point newInstance(Point point){
        return new Point(point.getX(), point.getY());
    }

    @Override
    public Point newInstance(LinkedList<Point> pointList) {
        if (pointList.size() == FigureTypes.POINT.getNumberOfPoints()) {
            return new Point(pointList.get(0));
        }
        return null;
    }
}

