package com.epam.jwd.figures.utils.action;

import com.epam.jwd.figures.model.rectangle.Quadrangle;

public class QuadrangleActions implements FigureActions {
    Quadrangle quadrangle;

    public QuadrangleActions(Quadrangle quadrangle) {
        this.quadrangle = quadrangle;
    }

    @Override
    public double perimeter() {
        return distanceBetweenTwoPoints(quadrangle.getPoint1(), quadrangle.getPoint2())
               + distanceBetweenTwoPoints(quadrangle.getPoint2(), quadrangle.getPoint3())
               + distanceBetweenTwoPoints(quadrangle.getPoint3(), quadrangle.getPoint4())
               + distanceBetweenTwoPoints(quadrangle.getPoint1(), quadrangle.getPoint4());
    }

    @Override
    public double square() {
        double semiPerimeter = perimeter() / 2;
        return Math.sqrt((semiPerimeter - distanceBetweenTwoPoints(quadrangle.getPoint1(), quadrangle.getPoint2()))
                         * (semiPerimeter - distanceBetweenTwoPoints(quadrangle.getPoint2(), quadrangle.getPoint3()))
                         * (semiPerimeter - distanceBetweenTwoPoints(quadrangle.getPoint3(), quadrangle.getPoint4()))
                         * (semiPerimeter - distanceBetweenTwoPoints(quadrangle.getPoint4(), quadrangle.getPoint1())));
    }

    //todo check for the type and convex
//    public boolean isConvex() {
//        return
//    }
}
