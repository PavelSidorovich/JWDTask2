package com.epam.jwd.quadrangle.model;

public enum FigureType {
    POINT (1),
    LINE(2),
    TRIANGLE(3),
    QUADRANGLE(4),
    ;

    private final int NUMBER_OF_POINTS;

    public int getNumberOfPoints() {
        return NUMBER_OF_POINTS;
    }

    FigureType(int number_of_points) {
        NUMBER_OF_POINTS = number_of_points;
    }
}
