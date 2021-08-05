package com.epam.jwd.figures.utils.action;

import com.epam.jwd.figures.model.Figure;
import com.epam.jwd.figures.model.point.Point;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;

/**
 * The {@code FigureActions} class contains methods which realise different actions applied to figures
 *
 * @author Pavel Sidorovich
 * @since 1.0
 */
public class FigureActions {
    private static final Logger LOG = LogManager.getLogger(FigureActions.class);

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
        LOG.info(figure + ": " + perimeter);
        return perimeter;
    }

    public double square() {
        double square = 0;

        if (figure instanceof Point) {
            return 0;
        }
        for (int i = 0; i < figure.getNumberOfPoints(); i++) {
            if (i != figure.getNumberOfPoints() - 1) {
                square += figure.getPoints().get(i).getX() * figure.getPoints().get(i + 1).getY()
                          - figure.getPoints().get(i).getY() * figure.getPoints().get(i + 1).getX();
            } else {
                square += figure.getPoints().get(i).getX() * figure.getPoints().get(0).getY()
                          - figure.getPoints().get(i).getY() * figure.getPoints().get(0).getX();
            }
        }
        LOG.info(figure + ": " + Math.abs(square / 2));
        return Math.abs(square / 2);
    }

    public Double distanceBetweenTwoPoints(Point point1, Point point2) {
        return Math.sqrt(Math.pow((point2.getX() - point1.getX()), 2) + Math.pow((point2.getY() - point1.getY()), 2));
    }

    public Boolean isConvex() {
        boolean convex = true;
        LinkedList<MathVector> vectors = figure.getVectors();
        boolean negative = vectors.get(0).multiply(vectors.get(1)) < 0;

        if (figure instanceof Point) {
            return true;
        }
        for (int i = 0; i < vectors.size(); i++) {
            boolean tmp;
            if (i != vectors.size() - 1) {
                tmp = (vectors.get(i).multiply(vectors.get(i + 1)) < 0) != negative;
            } else {
                tmp = (vectors.get(i).multiply(vectors.get(0)) < 0) != negative;
            }
            if (tmp) {
                convex = false;
                break;
            }
        }
        LOG.info(figure + ": " + convex);
        return convex;
    }

    public Figure getFigure() {
        return figure;
    }
}
