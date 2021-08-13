package com.epam.jwd.quadrangle.repository;

import com.epam.jwd.quadrangle.action.FigureActions;
import com.epam.jwd.quadrangle.action.FigureActions2D;
import com.epam.jwd.quadrangle.model.Figure;

public class FindByFigurePerimeterRange implements Specification<Figure> {

    private final double fromPerimeterValue;
    private final double toPerimeterValue;

    public FindByFigurePerimeterRange(double fromPerimeterValue, double toPerimeterValue) {
        this.fromPerimeterValue = fromPerimeterValue;
        this.toPerimeterValue = toPerimeterValue;
    }

    @Override
    public boolean exists(Figure figure) {
        FigureActions figureActions = new FigureActions2D(figure);
        double perimeter = figureActions.perimeter();
        return perimeter >= fromPerimeterValue
               && perimeter <= toPerimeterValue;
    }

    @Override
    public String toString() {
        return "FindByFigurePerimeterRange{" +
               "fromPerimeterValue=" + fromPerimeterValue +
               ", toPerimeterValue=" + toPerimeterValue +
               '}';
    }
}
