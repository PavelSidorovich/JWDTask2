package com.epam.jwd.figures.utils.exceptions;

public class PointException extends IllegalArgumentException {
    public static final String ERROR_MSG = "Point cannot be build from coordinate: %s";
    private final String point;

    public PointException(String s, String point) {
        super(s);
        this.point = point;
    }

    @Override
    public String toString() {
        return String.format(getClass().getCanonicalName() + ": " + getMessage(), point);
    }
}
