package com.epam.jwd.quadrangle.model;

import com.epam.jwd.quadrangle.exception.ArgumentNullException;
import com.epam.jwd.quadrangle.exception.PointNumberException;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonFigureProperties implements Figure {
    private final int id;
    protected final List<Point> points;

    public CommonFigureProperties(List<Point> points) {
        if (points == null) {
            throw new ArgumentNullException();
        }
        id = 0;
        this.points = points;
    }

    public CommonFigureProperties(int id, List<Point> points) {
        if (points == null) {
            throw new ArgumentNullException();
        }
        this.id = id;
        this.points = points;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public ArrayList<Point> getPoints() {
        return new ArrayList<>(points);
    }

    public abstract void setPoint(int numberOfPoint, Point point) throws PointNumberException;
}
