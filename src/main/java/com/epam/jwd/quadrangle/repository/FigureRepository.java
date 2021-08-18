package com.epam.jwd.quadrangle.repository;

import com.epam.jwd.quadrangle.exception.ArgumentNullException;
import com.epam.jwd.quadrangle.exception.InvalidArgumentException;
import com.epam.jwd.quadrangle.model.Figure;
import com.epam.jwd.quadrangle.model.FigurePublisher;
import com.epam.jwd.quadrangle.model.FigureSubscriber;
import com.epam.jwd.quadrangle.repository.search.SearchSpecification;
import com.epam.jwd.quadrangle.repository.sort.SortFigures;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FigureRepository implements Repository<Figure, FigurePublisher> {

    private static final Logger LOG = LogManager.getLogger(FigureRepository.class);

    private static final String FIGURES_FOUND_BY_SPECIFICATION = "found %d figures according to the specification: %s";

    private final List<FigureSubscriber> storage = new ArrayList<>();
    private int currentId;

    public FigureRepository() {
    }

    public FigureRepository(List<FigurePublisher> figureList) {
        for (FigurePublisher figurePublisher : figureList) {
            create(figurePublisher);
        }
    }

    @Override
    public void create(FigurePublisher figurePublisher) {
        if (figurePublisher != null) {
            Figure figure = figurePublisher.getFigure().withId(++currentId);
            figurePublisher.setFigure(figure);
            FigureSubscriber subscriber = new FigureSubscriber();
            figurePublisher.subscribe(subscriber);
            storage.add(subscriber);
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
        for (FigureSubscriber figureSubscriber : storage) {
            if (figureSubscriber.getFigureContext().getFigure().equals(figure)) {
                return storage.indexOf(figureSubscriber);
            }
        }
        return -1;
    }

    @Override
    public void update(FigurePublisher publisher, Figure newFigure) {
        if (publisher == null || newFigure == null) {
            throw new NullPointerException();
        }
        for (FigureSubscriber subscriber : storage) {
            if (subscriber.getFigureContext().getFigure().equals(publisher.getFigure())) {
                int oldIndex = storage.indexOf(subscriber);
                if (oldIndex != -1) {
                    publisher.setFigure(newFigure);
                    return;
                }
            }
        }
        throw new InvalidArgumentException();
    }

    @Override
    public boolean delete(int index) {
        try {
            FigureSubscriber subscriber = storage.get(index);
            subscriber.onComplete();
            return storage.remove(subscriber);
        } catch (Exception e) {
            LOG.error(e);
            return false;
        }
    }

    @Override
    public boolean delete(Figure figure) {
        try {
            for (FigureSubscriber subscriber : storage) {
                if (subscriber.getFigureContext().getFigure().equals(figure)) {
                    subscriber.onComplete();
                    return storage.remove(subscriber);
                }
            }
        } catch (Exception e) {
            LOG.error(e);
        }
        return false;
    }

    @Override
    public List<Figure> getAll() {
        List<Figure> figures = new ArrayList<>();
        for (FigureSubscriber subscriber : storage) {
            figures.add(subscriber.getFigureContext().getFigure());
        }
        return figures;
    }

    @Override
    public List<Figure> findBySpecification(SearchSpecification<Figure> specification) {
        List<Figure> foundFigures = new ArrayList<>();
        for (FigureSubscriber subscriber : storage) {
            if (specification.exists(subscriber.getFigureContext().getFigure())) {
                foundFigures.add(subscriber.getFigureContext().getFigure());
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
        for (FigureSubscriber subscriber : storage) {
            sortedList.add(subscriber.getFigureContext().getFigure());
        }
        sortFigures.sort(sortedList, comparator);
        return sortedList;
    }
}
