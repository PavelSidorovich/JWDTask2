package com.epam.jwd.quadrangle.exception;

import com.epam.jwd.quadrangle.model.Point;

import java.util.List;

public class PointArgumentException extends IllegalArgumentException {
    private static final String ILLEGAL_ARGUMENT_MSG = "%s cannot be build from coordinates: %s";

    private final List<String> coordinates;

    public PointArgumentException(String s, List<String> coordinates) {
        super(s);
        this.coordinates = coordinates;
    }

    @Override
    public String getMessage() {
        String message = String.format(ILLEGAL_ARGUMENT_MSG, Point.class.getSimpleName(), coordinates);
        return super.getMessage() + message;
    }
}
