package com.epam.jwd.quadrangle.repository;

import com.epam.jwd.quadrangle.action.FigureActions;
import com.epam.jwd.quadrangle.action.FigureActions2D;
import com.epam.jwd.quadrangle.model.Figure;

public class FindByFigurePerimeterRange implements Specification<Figure> {

    private double fromPerimeterValue;
    private double toPerimeterValue;

    public FindByFigurePerimeterRange(double fromPerimeterValue, double toPerimeterValue) {
        this.fromPerimeterValue = fromPerimeterValue;
        this.toPerimeterValue = toPerimeterValue;
    }

    @Override
    public boolean exists(Figure figure) {
        FigureActions figureActions = new FigureActions2D(figure);
        double perimeter = figureActions.square();
        return perimeter >= fromPerimeterValue
               && perimeter <= toPerimeterValue;
    }
}
