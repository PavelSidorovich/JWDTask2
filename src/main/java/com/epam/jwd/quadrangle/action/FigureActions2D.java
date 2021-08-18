package com.epam.jwd.quadrangle.action;

import com.epam.jwd.quadrangle.exception.ArgumentNullException;
import com.epam.jwd.quadrangle.model.Figure;
import com.epam.jwd.quadrangle.model.MathVector;
import com.epam.jwd.quadrangle.model.Point;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The {@code FigureActions2D} class contains methods which realises different actions applied to figures
 *
 * @author Pavel Sidorovich
 * @since 1.0
 */
public class FigureActions2D implements FigureActions {
    private static final Logger LOG = LogManager.getLogger(FigureActions2D.class);

    private final Figure figure;

    public FigureActions2D(Figure figure) {
        if (figure == null) {
            throw new ArgumentNullException();
        }
        this.figure = figure;
    }

    public Double distance(Point point1, Point point2) {
        return Math.sqrt(Math.pow((point2.getX() - point1.getX()), 2) + Math.pow((point2.getY() - point1.getY()), 2));
    }

    public Boolean isConvex() {
        boolean convex = true;

        if (figure instanceof Point) {
            return true;
        }

        List<MathVector> vectors = figure.getVectors();
        boolean negative = vectors.get(0).scalarProduct(vectors.get(1)) < 0;

        for (int i = 0; i < vectors.size(); i++) {
            boolean tmp;
            if (i != vectors.size() - 1) {
                tmp = (vectors.get(i).scalarProduct(vectors.get(i + 1)) < 0) != negative;
            } else {
                tmp = (vectors.get(i).scalarProduct(vectors.get(0)) < 0) != negative;
            }
            if (tmp) {
                convex = false;
                break;
            }
        }
        LOG.trace(figure + ": " + convex);
        return convex;
    }

    @Override
    public double square() {
        double square = 0;

        if (figure instanceof Point) {
            return 0;
        }
        for (int i = 0; i < figure.getNumberOfPoints() - 1; i++) {
            square += figure.getPoints().get(i).getX() * figure.getPoints().get(i + 1).getY()
                      - figure.getPoints().get(i).getY() * figure.getPoints().get(i + 1).getX();
        }
        square += figure.getPoints().get(figure.getNumberOfPoints() - 1).getX() * figure.getPoints().get(0).getY()
                  - figure.getPoints().get(figure.getNumberOfPoints() - 1).getY() * figure.getPoints().get(0).getX();
        LOG.trace(figure + ": " + Math.abs(square / 2));
        return Math.abs(square / 2);
    }

    @Override
    public double perimeter() {
        double perimeter = 0;

        if (figure instanceof Point) {
            return 0;
        }
        for (int i = 0; i < figure.getNumberOfPoints() - 1; i++) {
            perimeter += distance(figure.getPoints().get(i),
                                  figure.getPoints().get(i + 1));
        }
        perimeter += distance(figure.getPoints().get(figure.getNumberOfPoints() - 1),
                              figure.getPoints().get(0));
        LOG.trace(figure + ": " + perimeter);
        return perimeter;
    }

    @Override
    public Figure getFigure() {
        return figure;
    }
}
