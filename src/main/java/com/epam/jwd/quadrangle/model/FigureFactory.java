package com.epam.jwd.quadrangle.model;

import java.util.List;

public interface FigureFactory {
    Figure newInstance(List<Point> pointList);
}
