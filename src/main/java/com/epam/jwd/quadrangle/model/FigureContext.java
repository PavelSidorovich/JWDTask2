package com.epam.jwd.quadrangle.model;

import com.epam.jwd.quadrangle.action.FigureActions2D;

public class FigureContext {
    private final Figure figure;
    private final double square;
    private final double perimeter;
    private final boolean isConvex;

    public FigureContext(Figure figure) {
        FigureActions2D actions = new FigureActions2D(figure);
        this.figure = figure;
        perimeter = actions.perimeter();
        square = actions.square();
        isConvex = actions.isConvex();
    }

    public Figure getFigure() {
        return figure;
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
