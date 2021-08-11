package com.epam.jwd.quadrangle.model;

public enum FigureType {
    POINT(1),
    LINE(2),
    TRIANGLE(3),
    QUADRANGLE(4),
    ;

    private final int numberOfPoints;

    public int getNumberOfPoints() {
        return numberOfPoints;
    }

    FigureType(int numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }
}
