package com.epam.jwd.quadrangle.repository;

import com.epam.jwd.quadrangle.action.FigureActions;
import com.epam.jwd.quadrangle.action.FigureActions2D;
import com.epam.jwd.quadrangle.model.Figure;

public class FindByFigureSquareRange implements Specification<Figure> {

    private double fromSquareValue;
    private double toSquareValue;

    public FindByFigureSquareRange(double fromSquareValue, double toSquareValue) {
        this.fromSquareValue = fromSquareValue;
        this.toSquareValue = toSquareValue;
    }

    @Override
    public boolean exists(Figure figure) {
        FigureActions figureActions = new FigureActions2D(figure);
        double square = figureActions.square();
        return square >= fromSquareValue
               && square <= toSquareValue;
    }
}
