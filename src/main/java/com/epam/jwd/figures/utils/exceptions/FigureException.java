package com.epam.jwd.figures.utils.exceptions;

import com.epam.jwd.figures.model.Figure;
import com.epam.jwd.figures.model.point.Point;

import java.util.List;

public class FigureException extends IllegalArgumentException {
    private final Class<? extends Figure> figure;
    private final List<Point> points;

    public FigureException(String s, Class<? extends Figure> figure, List<Point> points) {
        super(s);
        this.figure = figure;
        this.points = points;
    }

    @Override
    public String getMessage() {
        return String.format(super.getMessage(), figure.getSimpleName(), points.size(), points);
    }
}
