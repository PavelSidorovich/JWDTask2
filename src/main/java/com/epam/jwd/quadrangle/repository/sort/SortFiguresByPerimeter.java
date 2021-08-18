package com.epam.jwd.quadrangle.repository.sort;

import com.epam.jwd.quadrangle.action.FigureActions;
import com.epam.jwd.quadrangle.action.FigureActions2D;
import com.epam.jwd.quadrangle.model.Figure;

import java.util.Comparator;

public class SortFiguresByPerimeter implements Comparator<Figure> {

    @Override
    public int compare(Figure o1, Figure o2) {
        FigureActions actions1 = new FigureActions2D(o1);
        FigureActions actions2 = new FigureActions2D(o2);
        int result = 0;
        if (actions1.perimeter() - actions2.perimeter() < 0) {
            result = -1;
        } else if (actions1.perimeter() - actions2.perimeter() > 0) {
            result = 1;
        }
        return result;
    }

    @Override
    public Comparator<Figure> reversed() {
        return (o1, o2) -> SortFiguresByPerimeter.this.compare(o2, o1);
    }
}
