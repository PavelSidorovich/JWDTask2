package com.epam.jwd.quadrangle.repository.sort;

import com.epam.jwd.quadrangle.action.FigureActions;
import com.epam.jwd.quadrangle.action.FigureActions2D;
import com.epam.jwd.quadrangle.model.Figure;

import java.util.Comparator;

public class SortFiguresBySquare implements Comparator<Figure> {
    @Override
    public int compare(Figure o1, Figure o2) {
        FigureActions actions1 = new FigureActions2D(o1);
        FigureActions actions2 = new FigureActions2D(o2);
        int result = 0;
        if (actions1.square() - actions2.square() < 0) {
            result = -1;
        } else if (actions1.square() - actions2.square() > 0) {
            result = 1;
        }
        return result;
    }

    @Override
    public Comparator<Figure> reversed() {
        return (o1, o2) -> SortFiguresBySquare.this.compare(o2, o1);
    }
}
