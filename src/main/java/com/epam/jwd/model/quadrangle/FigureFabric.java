package com.epam.jwd.model.quadrangle;

import java.util.LinkedList;

public interface FigureFabric {
    Figure newInstance(LinkedList<Point> pointList);
}
