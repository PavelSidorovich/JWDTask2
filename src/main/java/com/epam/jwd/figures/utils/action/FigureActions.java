package com.epam.jwd.figures.utils.action;

import com.epam.jwd.figures.model.Figure;
import com.epam.jwd.figures.model.point.Point;

import java.util.LinkedList;

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

    public Double distanceBetweenTwoPoints(Point point1, Point point2) {
        return Math.sqrt(Math.pow((point2.getX() - point1.getX()), 2) + Math.pow((point2.getY() - point1.getY()), 2));
    }

    public boolean isConvex() {
        if (figure instanceof Point) {
            return false;
        }

        boolean isConvex = true;
        LinkedList<Point> points = figure.getPoints();
        double T = points.get(figure.getNumberOfPoints() - 1).getX() * points.get(0).getY()
                   - points.get(0).getX() * points.get(figure.getNumberOfPoints() - 1).getY();
        double Z = T / Math.abs(T);
        double P = 1;
        for (int i = 0; i < points.size() - 2; i++) {
            MathVector mathVector1 = new MathVector(points.get(i), points.get(i + 1));
            MathVector mathVector2 = new MathVector(points.get(i + 1), points.get(i + 2));
            P *= Z * mathVector1.multiply(mathVector2) / Math.abs(mathVector1.multiply(mathVector2));
            if (P < 0) {
                isConvex = false;
                break;
            }
        }
        return isConvex;
    }

    public Figure getFigure() {
        return figure;
    }
}
