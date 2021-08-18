package com.epam.jwd.quadrangle.repository.sort;

import com.epam.jwd.quadrangle.model.Figure;

import java.util.Comparator;

public class SortFiguresByXPoint implements Comparator<Figure> {
    @Override
    public int compare(Figure o1, Figure o2) {
        if (o1.getPoints().get(0).getX() - o2.getPoints().get(0).getX() < 0) {
            return -1;
        } else if (o1.getPoints().get(0).getX() - o2.getPoints().get(0).getX() > 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public Comparator<Figure> reversed() {
        return (o1, o2) -> SortFiguresByXPoint.this.compare(o2, o1);
    }
}
