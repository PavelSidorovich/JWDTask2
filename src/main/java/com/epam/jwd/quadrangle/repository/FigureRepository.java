package com.epam.jwd.quadrangle.repository;

import com.epam.jwd.quadrangle.model.Figure;

import java.util.ArrayList;
import java.util.List;

public class FigureRepository implements Repository<Figure> {

    private final List<Figure> storage;

    public FigureRepository() {
        storage = new ArrayList<>();
    }

    public FigureRepository(List<Figure> figureList) {
        this.storage = new ArrayList<>(figureList);
    }

    @Override
    public void create(Figure figure) {
        storage.add(figure);
    }

    @Override
    public Figure read(int id) {
        for (Figure figure : storage) {
            if (figure.getId() == id) {
                return figure;
            }
        }
        return null;
    }

    @Override
    public boolean update(Figure oldFigure, Figure newFigure) {
        if (storage.contains(oldFigure)) {
            int oldIndex = storage.indexOf(oldFigure);
            storage.add(oldIndex, newFigure);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(int oldId, Figure newFigure) {
        for (Figure figure : storage) {
            if (figure.getId() == oldId) {
                int oldIndex = storage.indexOf(figure);
                storage.add(oldIndex, newFigure);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        for (Figure figure : storage) {
            if (figure.getId() == id) {
                return storage.remove(figure);
            }
        }
        return false;
    }

    @Override
    public boolean delete(Figure figure) {
        return storage.remove(figure);
    }

    @Override
    public List<Figure> getAll() {
        return new ArrayList<>(storage);
    }

    @Override
    public List<Figure> findBySpecification(Specification specification) {
        List<Figure> foundFigures = new ArrayList<>();
        for (Figure figure : storage) {
            if (specification.exists(figure)) {
                foundFigures.add(figure);
            }
        }
        return foundFigures;
    }
}
