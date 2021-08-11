package com.epam.jwd.model.quadrangle;

import java.util.LinkedList;

public abstract class CommonFigureProperties implements Figure {

    private final LinkedList<Point> points;

    private final FigureType figureType;

    public CommonFigureProperties(LinkedList<Point> points, FigureType figureType) {
        this.points = points;
        this.figureType = figureType;
    }

    public FigureType getFigureType() {
        return figureType;
    }

    @Override
    public LinkedList<Point> getPoints() {
        return new LinkedList<>(points);
    }

    @Override
    public int getNumberOfPoints() {
        return figureType.getNumberOfPoints();
    }

}
