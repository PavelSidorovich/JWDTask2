package com.epam.jwd.figures.utils.exceptions;

import com.epam.jwd.figures.model.rectangle.Quadrangle;

public class QuadrangleException extends IllegalArgumentException {
    public static final String BUILD_ERROR_MSG = "Quadrangle cannot be built on these points: ";
    public static final String NUMBER_OF_ARGUMENTS_ERROR_MSG = "Quadrangle cannot be built on %s points: ";

    private final Quadrangle quadrangle;
    private final String[] coordinates;

    public QuadrangleException(String s, Quadrangle quadrangle, String[] coordinates) {
        super(s);
        this.quadrangle = quadrangle;
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        String message = getClass().getCanonicalName() + ": ";
        message += String.format(getMessage(), coordinates.length);
        for (String coordinate: coordinates) {
            message += coordinate + " ";
        }
        return message;
    }
}
