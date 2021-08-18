package com.epam.jwd.quadrangle.repository.search;

import com.epam.jwd.quadrangle.model.Figure;

public class FindByFigureId implements SearchSpecification<Figure> {
    private final int id;

    public FindByFigureId(int id) {
        this.id = id;
    }

    @Override
    public boolean exists(Figure figure) {
        return id == figure.getId();
    }

    @Override
    public String toString() {
        return "FindByFigureId{" +
               "id=" + id +
               '}';
    }
}
