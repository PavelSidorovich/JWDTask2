package com.epam.jwd.quadrangle.model;

import com.epam.jwd.quadrangle.action.FigureActions2D;

public class FigureContext implements Cloneable {
    private final Figure figure;
    private final double square;
    private final double perimeter;
    private final boolean isConvex;

    public FigureContext(Figure figure) {
        this.figure = figure;
        FigureActions2D actions = new FigureActions2D(figure);
        perimeter = actions.perimeter();
        square = actions.square();
        isConvex = actions.isConvex();
    }

    public Figure getFigure() {
        return figure;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new FigureContext(figure);
    }

    @Override
    public String toString() {
        return "\n\tFigureContext{" +
               "figure=" + figure.getClass().getSimpleName() +
               ", id=" + figure.getId() +
               ", square=" + square +
               ", perimeter=" + perimeter +
               ", isConvex=" + isConvex +
               '}';
    }
}
