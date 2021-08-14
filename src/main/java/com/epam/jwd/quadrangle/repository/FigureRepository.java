package com.epam.jwd.quadrangle.repository;

import com.epam.jwd.quadrangle.model.Figure;
import com.epam.jwd.quadrangle.repository.search.SearchSpecification;
import com.epam.jwd.quadrangle.repository.sort.SortFigures;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FigureRepository implements Repository<Figure> {

    private static final Logger LOG = LogManager.getLogger(FigureRepository.class);

    private static final String FIGURES_FOUND_BY_SPECIFICATION = "found %d figures according to the specification: %s";

    private final List<Figure> storage = new ArrayList<>();
    private int currentId;

    public FigureRepository() {}

    public FigureRepository(List<Figure> figureList) {
        for (Figure figure : figureList) {
            create(figure);
        }
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
    public List<Figure> findBySpecification(SearchSpecification specification) {
        List<Figure> foundFigures = new ArrayList<>();
        for (Figure figure : storage) {
            if (specification.exists(figure)) {
                foundFigures.add(figure);
            }
        }
        String message = String.format(FIGURES_FOUND_BY_SPECIFICATION, foundFigures.size(), specification);
        LOG.trace(message);
        return foundFigures;
    }

    @Override
    public List<Figure> sortByComparator(Comparator<Figure> comparator) {
        SortFigures sortFigures = new SortFigures();
        List<Figure> sortedList = new ArrayList<>(storage);
        sortFigures.sort(sortedList, comparator);
        return sortedList;
    }
}
