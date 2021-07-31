package com.epam.jwd.figures.model.point;

public class PointFabric {
    public static Point newInstance(double x, double y){
        return new Point(x, y);
    }

    public static Point newInstance(Point point){
        return new Point(point.getX(), point.getY());
    }
}
