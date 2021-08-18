package com.epam.jwd.quadrangle.model;

import java.util.List;
import java.util.concurrent.Flow.Publisher;

public interface FigureFactory {
    Figure of(List<Point> pointList);

    FigurePublisher publisherOf(List<Point> pointList);
}
