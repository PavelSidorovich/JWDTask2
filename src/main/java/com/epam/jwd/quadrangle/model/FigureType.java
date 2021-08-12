package com.epam.jwd.quadrangle.model;

import com.epam.jwd.quadrangle.validation.PointBuildValidator;
import com.epam.jwd.quadrangle.validation.QuadrangleBuildValidator;
import com.epam.jwd.quadrangle.validation.Validator;

public enum FigureType {
    POINT(1, new PointBuildValidator()),
    LINE(2, null),
    TRIANGLE(3, null),
    QUADRANGLE(4, new QuadrangleBuildValidator()),
    ;

    private final int numberOfPoints;
    private final Validator validator;

    FigureType(int numberOfPoints, Validator validator) {
        this.numberOfPoints = numberOfPoints;
        this.validator = validator;
    }

    public int getNumberOfPoints() {
        return numberOfPoints;
    }

    public Validator getValidator() {
        return validator;
    }
}
