package com.epam.jwd.quadrangle.model;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonFigureProperties implements Figure {

    private int id;
    private final List<Point> points;

    private final FigureType figureType;

    public CommonFigureProperties(List<Point> points, FigureType figureType) {
        id = 0;
        this.points = points;
        this.figureType = figureType;
    }

    public CommonFigureProperties(int id, List<Point> points, FigureType figureType) {
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
    public ArrayList<Point> getPoints() {
        return new ArrayList<>(points);
    }

    @Override
    public int getNumberOfPoints() {
        return figureType.getNumberOfPoints();
    }

}
