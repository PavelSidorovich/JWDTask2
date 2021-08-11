package com.epam.jwd.quadrangle.model;

import java.util.LinkedList;

public abstract class CommonFigureProperties implements Figure {

    private int id;
    private final LinkedList<Point> points;

    private final FigureType figureType;

    public CommonFigureProperties(LinkedList<Point> points, FigureType figureType) {
        id = 0;
        this.points = points;
        this.figureType = figureType;
    }

    public CommonFigureProperties(int id, LinkedList<Point> points, FigureType figureType) {
        this.id = id;
        this.points = points;
        this.figureType = figureType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
