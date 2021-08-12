package com.epam.jwd.quadrangle.validation;

import com.epam.jwd.quadrangle.model.FigureType;
import com.epam.jwd.quadrangle.model.MathVector;
import com.epam.jwd.quadrangle.model.Point;

import java.util.List;
import java.util.regex.Pattern;

/**
 * The {@code QuadrangleBuildValidator} class is designed to check the ability to build a quadrangle on coordinates
 *
 * @author Pavel Sidorovich
 * @since 1.0
 */
public class QuadrangleBuildValidator implements Validator {
    private static final String COORDINATES_LINE_REG_EX = "([-+]?[0-9]*\\.?[0-9]+\\s){8}";

    @Override
    public Pattern getPattern() {
        return Pattern.compile(COORDINATES_LINE_REG_EX);
    }

    public boolean creatable(List<Point> points) {
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
        return twoLinesSegmentsIntersect(points.get(0), points.get(1), points.get(2), points.get(3))
               && twoLinesSegmentsIntersect(points.get(0), points.get(3), points.get(1), points.get(2));
    }

    private Double getOrientedAreaOfATriangle(Point a, Point b, Point c) {
        return (b.getX() - a.getX()) * (c.getY() - a.getY())
               - (b.getY() - a.getY()) * (c.getX() - a.getX());
    }

    private boolean theLineSegmentsLieOnOneStraightLine(Double a, Double b, Double c, Double d) {
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

    private boolean twoLinesSegmentsIntersect(Point a, Point b, Point c, Point d) {
        return theLineSegmentsLieOnOneStraightLine(a.getX(), b.getX(), c.getX(), d.getX())
               || theLineSegmentsLieOnOneStraightLine(a.getY(), b.getY(), c.getY(), d.getY())
               || !(getOrientedAreaOfATriangle(a, b, c) * getOrientedAreaOfATriangle(a, b, d) <= 0)
               || !(getOrientedAreaOfATriangle(c, d, a) * getOrientedAreaOfATriangle(c, d, b) <= 0);
    }
}
