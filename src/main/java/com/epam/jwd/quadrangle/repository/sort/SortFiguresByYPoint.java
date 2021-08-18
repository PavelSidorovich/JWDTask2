package com.epam.jwd.quadrangle.repository.sort;

import com.epam.jwd.quadrangle.model.Figure;

import java.util.Comparator;

public class SortFiguresByYPoint implements Comparator<Figure> {
    @Override
    public int compare(Figure o1, Figure o2) {
        if (o1.getPoints().get(0).getY() - o2.getPoints().get(0).getY() < 0) {
            return -1;
        } else if (o1.getPoints().get(0).getY() - o2.getPoints().get(0).getY() > 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public Comparator<Figure> reversed() {
        return (o1, o2) -> SortFiguresByYPoint.this.compare(o2, o1);
    }
}
