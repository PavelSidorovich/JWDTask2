package com.epam.jwd.figures.utils.exceptions;

import com.epam.jwd.figures.model.Figure;
import com.epam.jwd.figures.model.point.Point;

import java.util.List;

public class FigureException extends IllegalArgumentException {
    public static final String ARGUMENTS_ERROR_MSG = "%s cannot be built on %s coordinates: ";

    private final Class<? extends Figure> figure;
    private final List<Point> points;

    public FigureException(String s, Class<? extends Figure> figure, List<Point> points) {
        super(s);
        this.figure = figure;
        this.points = points;
    }

    @Override
    public String toString() {
        String message = getClass().getCanonicalName() + ": ";
        message += String.format(getMessage(), figure.getSimpleName(), points.size());
        message += points;
        return message;
    }
}
