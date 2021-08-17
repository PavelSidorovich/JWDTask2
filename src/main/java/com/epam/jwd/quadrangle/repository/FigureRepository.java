package com.epam.jwd.quadrangle.repository;

import com.epam.jwd.quadrangle.exception.ArgumentNullException;
import com.epam.jwd.quadrangle.model.Figure;
import com.epam.jwd.quadrangle.model.FigureContext;
import com.epam.jwd.quadrangle.model.FigurePublisher;
import com.epam.jwd.quadrangle.repository.search.SearchSpecification;
import com.epam.jwd.quadrangle.repository.sort.SortFigures;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FigureRepository implements Repository<Figure, FigureContext> {

    private static final Logger LOG = LogManager.getLogger(FigureRepository.class);

    private static final String FIGURES_FOUND_BY_SPECIFICATION = "found %d figures according to the specification: %s";

    //private final List<Figure> storage = new ArrayList<>();
    private final List<FigurePublisher> storage = new ArrayList<>();
    private int currentId;

    public FigureRepository() {
    }

    public FigureRepository(List<Figure> figureList) {
        for (Figure figure : figureList) {
            create(figure);
        }
    }

    @Override
    public FigurePublisher create(Figure figure) {
        if (figure != null) {
            figure = figure.withId(++currentId);
            FigurePublisher publisher = new FigurePublisher(figure);
            storage.add(publisher);
            return publisher;
        } else {
            throw new ArgumentNullException();
        }
    }

    @Override
    public Figure read(int index) {
        try {
            return storage.get(index).getFigureContext().getFigure();
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public int read(Figure figure) {
        for (FigurePublisher figurePublisher : storage) {
            if (figurePublisher.getFigureContext().getFigure().equals(figure)) {
                return storage.indexOf(figurePublisher);
            }
        }
        return -1;
    }

    @Override
    public FigurePublisher update(Figure oldFigure, Figure newFigure) {
        for (FigurePublisher figurePublisher : storage) {
            if (figurePublisher.getFigureContext().getFigure().equals(oldFigure)) {
                int oldIndex = storage.indexOf(figurePublisher);
                return getPublisher(oldIndex, newFigure);
            }
        }
        return null;
    }

    @Override
    public FigurePublisher update(int index, Figure newFigure) {
        return getPublisher(index, newFigure);
    }

    @Override
    public boolean delete(int index) {
        try {
            FigurePublisher publisher = storage.get(index);
            publisher.cancel();
            return storage.remove(publisher);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(Figure figure) {
        try {
            for (FigurePublisher publisher : storage) {
                if (publisher.getFigureContext().getFigure().equals(figure)) {
                    publisher.cancel();
                    return storage.remove(publisher);
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Figure> getAll() {
        List<Figure> figures = new ArrayList<>();
        for (FigurePublisher publisher : storage) {
            figures.add(publisher.getFigureContext().getFigure());
        }
        return figures;
    }

    @Override
    public List<Figure> findBySpecification(SearchSpecification<Figure> specification) {
        List<Figure> foundFigures = new ArrayList<>();
        for (FigurePublisher publisher : storage) {
            if (specification.exists(publisher.getFigureContext().getFigure())) {
                foundFigures.add(publisher.getFigureContext().getFigure());
            }
        }
        String message = String.format(FIGURES_FOUND_BY_SPECIFICATION, foundFigures.size(), specification);
        LOG.trace(message);
        return foundFigures;
    }

    @Override
    public List<Figure> sortByComparator(Comparator<Figure> comparator) {
        SortFigures sortFigures = new SortFigures();
        List<Figure> sortedList = new ArrayList<>();
        for (FigurePublisher publisher : storage) {
            sortedList.add(publisher.getFigureContext().getFigure());
        }
        sortFigures.sort(sortedList, comparator);
        return sortedList;
    }

    private FigurePublisher getPublisher(int index, Figure newFigure) {
        int oldId = storage.get(index).getFigureContext().getFigure().getId();
        FigurePublisher publisher = storage.get(index);
        storage.remove(index);
        newFigure = newFigure.withId(oldId);
        publisher.setFigure(newFigure);
        storage.add(index, publisher);
        return publisher;
    }

    public FigurePublisher getPublisher(int index) {
        return storage.get(index);
    }

    public FigurePublisher getPublisher(Figure figure) {
        for (FigurePublisher publisher : storage) {
            if (publisher.getFigureContext().getFigure().equals(figure)) {
                return publisher;
            }
        }
        return null;
    }
}
