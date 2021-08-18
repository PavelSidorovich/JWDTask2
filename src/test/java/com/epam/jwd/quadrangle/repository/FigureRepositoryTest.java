package com.epam.jwd.quadrangle.repository;

import com.epam.jwd.quadrangle.exception.ArgumentNullException;
import com.epam.jwd.quadrangle.exception.InvalidArgumentException;
import com.epam.jwd.quadrangle.model.Figure;
import com.epam.jwd.quadrangle.model.FigurePublisher;
import com.epam.jwd.quadrangle.model.PointFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

// FIXME: 8/18/2021
public class FigureRepositoryTest {

    private final FigureRepository repository = new FigureRepository();
    private final PointFactory pointFactory = PointFactory.getInstance();

    @Test
    public void create_shouldCreateFigureInRepository_whenFigurePublisherIsValid() {
        FigurePublisher point = pointFactory.publisherOf(0, 0);

        repository.create(point);

        assertTrue(repository.read(point.getFigure()) >= 0);
    }

    @Test(expectedExceptions = ArgumentNullException.class)
    public void create_shouldThrowException_whenNullAddedToRepository() {
        repository.create(null);
    }

    @Test
    public void read_shouldReturnFigureOrIndex_whenFigureExistsInRepository() {
        FigurePublisher point = pointFactory.publisherOf(1, 1);
        repository.create(pointFactory.publisherOf(0, 1));
        repository.create(point);
        int index = repository.read(point.getFigure());

        assertEquals(repository.read(index), point.getFigure());
        assertSame(repository.read(-1), null);
        assertSame(repository.read(pointFactory.of(0, 33)), -1);
    }

    @Test
    public void update_shouldUpdateFigure_whenPublisherIsValid() {
        FigurePublisher oldFigure = pointFactory.publisherOf(5, 2);
        Figure newFigure = pointFactory.of(10, 10);
        repository.create(pointFactory.publisherOf(2, 1));
        repository.create(pointFactory.publisherOf(3, 5));
        repository.create(oldFigure);

        repository.update(oldFigure, newFigure);
    }

    @Test(expectedExceptions = InvalidArgumentException.class)
    public void update_shouldThrowException_whenFigurePublisherDoesNotExistsInRepository() {
        FigurePublisher figurePublisher = pointFactory.publisherOf(52, 62);
        Figure newFigure = pointFactory.of(2, 62);

        repository.update(figurePublisher, newFigure);
    }

    @DataProvider(name = "NullArgumentProvider")
    public Object[][] getConstructorArgumentsFromProvider() {
        return new Object[][] {
                { null, pointFactory.of(36, 39) },
                { null, null },
        };
    }

    @Test(dataProvider = "NullArgumentProvider", expectedExceptions = NullPointerException.class)
    public void update_shouldReturnFalse_whenOldFigureIsInvalid(FigurePublisher publisher, Figure figure) {
        repository.update(publisher, figure);
    }

    @Test
    public void delete_shouldReturnTrue_whenElementWasDeleted() {
        FigurePublisher publisher1 = pointFactory.publisherOf(30, 30);
        FigurePublisher publisher2 = pointFactory.publisherOf(40, 40);
        repository.create(publisher1);
        repository.create(publisher2);

        assertFalse(repository.delete(null));
        assertFalse(repository.delete(-1));
        assertTrue(repository.delete(publisher1.getFigure()));

        int index = repository.read(publisher2.getFigure());

        assertTrue(repository.delete(index));
    }

    @Test
    public void getAll_shouldReturnFiguresList_always() {
        assertNotNull(repository.getAll());
    }
}