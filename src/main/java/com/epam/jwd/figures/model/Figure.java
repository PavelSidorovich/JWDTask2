package com.epam.jwd.figures.model;

import com.epam.jwd.figures.model.point.Point;

import java.util.LinkedList;

/**
 * This interface was designed in case the hierarchy of the project increase.
 */
public interface Figure {
    int getNumberOfPoints();

    LinkedList<Point> getPoints();
}
