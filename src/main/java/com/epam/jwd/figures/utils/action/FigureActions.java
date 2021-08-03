package com.epam.jwd.figures.utils.action;

import com.epam.jwd.figures.model.Figure;
import com.epam.jwd.figures.model.point.Point;

public class FigureActions {
    private final Figure figure;

    public FigureActions(Figure figure) {
        this.figure = figure;
    }

    public double perimeter() {
        double perimeter = 0;

        if (figure instanceof Point) {
            return 0;
        }
        for (int i = 0; i < figure.getNumberOfPoints(); i++) {
            if (i != figure.getNumberOfPoints() - 1) {
                perimeter += distanceBetweenTwoPoints(figure.getPoints().get(i),
                                                      figure.getPoints().get(i + 1));
            } else {
                perimeter += distanceBetweenTwoPoints(figure.getPoints().get(i),
                                                      figure.getPoints().get(0));
            }
        }
        return perimeter;
    }

    // TODO: 8/3/2021 square
    public double square() {
        double square = 1;
        double semiPerimeter = perimeter() / 2;

        if (figure instanceof Point) {
            return 0;
        }
        for (int i = 0; i < figure.getNumberOfPoints(); i++) {
            if (i != figure.getNumberOfPoints() - 1) {
                square *= Math.sqrt(semiPerimeter - distanceBetweenTwoPoints(figure.getPoints().get(i),
                                                                             figure.getPoints().get(i + 1)));
            } else {
                square *= Math.sqrt(semiPerimeter - distanceBetweenTwoPoints(figure.getPoints().get(i),
                                                                             figure.getPoints().get(0)));
            }
        }
        return square;
    }

    public double distanceBetweenTwoPoints(Point point1, Point point2) {
        return Math.sqrt(Math.pow((point2.getX() - point1.getX()), 2) + Math.pow((point2.getY() - point1.getY()), 2));
    }

    // TODO: 8/3/2021
//    public boolean isConvex(Figure figure) {
//        if (figure instanceof Point) {
//            return false;
//        }
//    }
}
