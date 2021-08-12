package com.epam.jwd.quadrangle.repository;

import com.epam.jwd.quadrangle.model.Figure;

public class FindByFigureId implements Specification<Figure> {
    private int id;

    public FindByFigureId(int id) {
        this.id = id;
    }

    @Override
    public boolean exists(Figure figure) {
        return id == figure.getId();
    }
}
