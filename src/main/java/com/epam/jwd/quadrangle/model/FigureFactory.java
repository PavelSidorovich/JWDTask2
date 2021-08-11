package com.epam.jwd.quadrangle.model;

import java.util.LinkedList;

public interface FigureFactory {
    Figure newInstance(LinkedList<Point> pointList);
}
