package com.epam.jwd.figures.utils.exceptions;

import com.epam.jwd.figures.model.point.Point;

import java.util.List;

public class PointException extends IllegalArgumentException {
    private static final String ILLEGAL_ARGUMENT_MSG = "%s cannot be build from coordinates: %s";

    private final List<String> coordinates;

    public PointException(String s, List<String> coordinates) {
        super(s);
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        String message = getClass().getCanonicalName() + ": ";
        message += String.format(getMessage() + ILLEGAL_ARGUMENT_MSG,
                                 Point.class.getSimpleName(), coordinates);
        return message;
    }
}
