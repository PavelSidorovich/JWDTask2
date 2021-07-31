package com.epam.jwd.figures.model.rectangle;

import com.epam.jwd.figures.model.point.Point;

public class QuadrangleFabric {

    public static Quadrangle newInstance(Point x0y0, Point x0y1, Point x1y0, Point x1y1) {
        return new Quadrangle(x0y0, x0y1, x1y0, x1y1);
    }

    public static Quadrangle newInstance(Quadrangle quadrangle) {
        return new Quadrangle(quadrangle.getPoint1(), quadrangle.getPoint2(),
                              quadrangle.getPoint3(), quadrangle.getPoint4());
    }
}
