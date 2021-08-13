package com.epam.jwd.quadrangle.repository;

import com.epam.jwd.quadrangle.model.Figure;

import java.util.ArrayList;
import java.util.List;

public class FigureRepository implements Repository<Figure> {

    private final List<Figure> storage;
    private int currentId;

    public FigureRepository() {
        storage = new ArrayList<>();
    }

    public FigureRepository(List<Figure> figureList) {
        this.storage = new ArrayList<>(figureList);
    }

    @Override
    public boolean create(Figure figure) {
        if (figure != null) {
            figure = figure.withId(++currentId);
            storage.add(figure);
        }
        return figure != null;
    }

    @Override
    public Figure read(int index) {
        try {
            return storage.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public int read(Figure figure) {
        if (storage.contains(figure)) {
            return storage.indexOf(figure);
        }
        return -1;
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
    public boolean update(int index, Figure newFigure) {
        try {
            Figure figure = storage.get(index);
            storage.add(index, newFigure);
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    @Override
    public boolean delete(int index) {
        try {
            Figure figure = storage.get(index);
            return storage.remove(figure);
        } catch (Exception e) {
            return false;
        }
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
