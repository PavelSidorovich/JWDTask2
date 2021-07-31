package com.epam.jwd.figures.utils.action;

import com.epam.jwd.figures.model.point.Point;

public interface FigureActions {
    double perimeter();

    double square();

    default double distanceBetweenTwoPoints(Point point1, Point point2) {
        return Math.sqrt(Math.pow((point2.getX() - point1.getX()), 2) + Math.pow((point2.getY() - point1.getY()), 2));
    }
}
