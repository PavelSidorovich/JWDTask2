package com.epam.jwd.quadrangle.repository.sort;

import com.epam.jwd.quadrangle.model.Figure;

import java.util.Comparator;

public class SortFiguresById implements Comparator<Figure> {
    @Override
    public int compare(Figure o1, Figure o2) {
        return o1.getId() - o2.getId();
    }

    @Override
    public Comparator<Figure> reversed() {
        return (o1, o2) -> SortFiguresById.this.compare(o2, o1);
    }
}
