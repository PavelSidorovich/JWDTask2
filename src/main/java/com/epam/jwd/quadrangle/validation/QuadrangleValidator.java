package com.epam.jwd.quadrangle.validation;

import com.epam.jwd.quadrangle.model.FigureType;
import com.epam.jwd.quadrangle.model.MathVector;
import com.epam.jwd.quadrangle.model.Point;

import java.util.List;

/**
 * The {@code QuadrangleValidator} class is designed to check the ability to build a quadrangle on coordinates
 *
 * @author Pavel Sidorovich
 * @since 1.0
 */
// TODO: 8/11/2021 make it implement Validator
public class QuadrangleValidator {

    private final List<Point> points;

    public QuadrangleValidator(List<Point> points) {
        this.points = points;
    }

    public boolean canBeBuilt() {
        if (points.size() != FigureType.QUADRANGLE.getNumberOfPoints()) {
            return false;
        }

        for (int i = 0; i < FigureType.QUADRANGLE.getNumberOfPoints() - 1; i++) {
            MathVector mathVector1;
            MathVector mathVector2;
            if (i != FigureType.QUADRANGLE.getNumberOfPoints() - 2) {
                mathVector1 = new MathVector(points.get(i), points.get(i + 1));
                mathVector2 = new MathVector(points.get(i + 1), points.get(i + 2));
            } else {
                mathVector1 = new MathVector(points.get(FigureType.QUADRANGLE.getNumberOfPoints() - 1),
                                             points.get(0));
                mathVector2 = new MathVector(points.get(0), points.get(1));
            }
            if (Double.valueOf(0).equals(mathVector1.scalarProduct(mathVector2))) {
                return false;
            }
        }

        //checking crossing of the lines
        return intersect(points.get(0), points.get(1), points.get(2), points.get(3))
               && intersect(points.get(0), points.get(3), points.get(1), points.get(2));
    }

    private Double area(Point a, Point b, Point c) {
        return (b.getX() - a.getX()) * (c.getY() - a.getY())
               - (b.getY() - a.getY()) * (c.getX() - a.getX());
    }

    // TODO: 8/11/2021 edit name
    private boolean intersect_1(Double a, Double b, Double c, Double d) {
        if (a > b) {
            double temp = a;
            a = b;
            b = temp;
        }
        if (c > d) {
            double temp = c;
            c = d;
            d = temp;
        }
        return !(Math.max(a, c) <= Math.min(b, d));
    }

    private boolean intersect(Point a, Point b, Point c, Point d) {
        return intersect_1(a.getX(), b.getX(), c.getX(), d.getX())
               || intersect_1(a.getY(), b.getY(), c.getY(), d.getY())
               || !(area(a, b, c) * area(a, b, d) <= 0)
               || !(area(c, d, a) * area(c, d, b) <= 0);
    }
}
