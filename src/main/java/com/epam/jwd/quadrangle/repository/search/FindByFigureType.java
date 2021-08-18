package com.epam.jwd.quadrangle.repository.search;

import com.epam.jwd.quadrangle.model.Figure;
import com.epam.jwd.quadrangle.model.FigureType;

public class FindByFigureType implements SearchSpecification<Figure> {

    private final FigureType figureType;

    public FindByFigureType(FigureType figureType) {
        this.figureType = figureType;
    }

    @Override
    public boolean exists(Figure figure) {
        return figure.getNumberOfPoints().equals(figureType.getNumberOfPoints());
    }

    @Override
    public String toString() {
        return "FindByFigureType{" +
               "figureType=" + figureType +
               '}';
    }
}
