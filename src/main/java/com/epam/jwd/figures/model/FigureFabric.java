package com.epam.jwd.figures.model;

import com.epam.jwd.figures.model.point.Point;

import java.util.LinkedList;

public interface FigureFabric {
    Figure newInstance(LinkedList<Point> pointList);
}
