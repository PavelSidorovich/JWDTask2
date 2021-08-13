package com.epam.jwd.quadrangle.repository.sort;

import com.epam.jwd.quadrangle.model.Figure;

import java.util.Comparator;
import java.util.List;

// TODO: 8/12/2021
public class SortFigures implements SortService<Figure> {
    @Override
    public List<Figure> sort(List<Figure> figureList, Comparator<Figure> comparator) {
        figureList.sort(comparator);
        return figureList;
    }
}
