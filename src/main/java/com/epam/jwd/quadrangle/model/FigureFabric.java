package com.epam.jwd.quadrangle.model;

import java.util.LinkedList;

public interface FigureFabric {
    Figure newInstance(LinkedList<Point> pointList);
}
