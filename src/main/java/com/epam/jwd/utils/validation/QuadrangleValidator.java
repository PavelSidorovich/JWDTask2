package com.epam.jwd.utils.validation;

import com.epam.jwd.model.quadrangle.FigureType;
import com.epam.jwd.model.quadrangle.Point;
import com.epam.jwd.utils.action.MathVector;

import java.util.LinkedList;

/**
 * The {@code QuadrangleValidator} class is designed to check the ability to build a quadrangle on coordinates
 *
 * @author Pavel Sidorovich
 * @since 1.0
 */
public class QuadrangleValidator {

    private final LinkedList<Point> points;

    public QuadrangleValidator(LinkedList<Point> points) {
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
            if (Double.valueOf(0).equals(mathVector1.multiply(mathVector2))) {
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

    private boolean intersect(Point point1, Point point2, Point point3, Point point4) {
        return intersect_1(point1.getX(), point2.getX(), point3.getX(), point4.getX())
               || intersect_1(point1.getY(), point2.getY(), point3.getY(), point4.getY())
               || !(area(point1, point2, point3) * area(point1, point2, point4) <= 0)
               || !(area(point3, point4, point1) * area(point3, point4, point2) <= 0);
    }
}
