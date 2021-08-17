package com.epam.jwd.quadrangle.repository;

import com.epam.jwd.quadrangle.exception.ArgumentNullException;
import com.epam.jwd.quadrangle.model.Figure;
import com.epam.jwd.quadrangle.model.PointFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FigureRepositoryTest {

    private final FigureRepository repository = new FigureRepository();
    private final PointFactory pointFactory = PointFactory.getInstance();

    @Test
    public void create_shouldReturnPublisher_whenFigureAddedToRepository() {
        assertNotNull(repository.create(pointFactory.of(0, 0)));
    }

    @Test(expectedExceptions = ArgumentNullException.class)
    public void create_shouldThrowException_whenNullAddedToRepository() {
        repository.create(null);
    }

    @Test
    public void read_shouldReturnPublisher_whenUpdateIsSuccessful() {
        Figure figure = pointFactory.of(1, 1);
        repository.create(pointFactory.of(0, 1));
        repository.create(figure);
        int index = repository.read(figure);

        assertEquals(repository.read(index), figure);
        assertSame(repository.read(-1), null);
        assertSame(repository.read(pointFactory.of(0, 33)), -1);
    }

    @Test
    public void update_shouldReturnPublisher_whenIndexIsValid() {
        Figure oldFigure = pointFactory.of(5, 2);
        Figure newFigure = pointFactory.of(10, 10);
        repository.create(pointFactory.of(2, 1));
        repository.create(pointFactory.of(3, 5));
        repository.create(oldFigure);
        int index = repository.read(oldFigure);

        assertNotNull(repository.update(oldFigure, newFigure));
        assertNotNull(repository.update(index, pointFactory.of(53, 5)));
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void update_shouldThrowException_whenIndexIsInvalid() {
        Figure newFigure = pointFactory.of(16, 10);

        repository.update(-1, newFigure);
    }

    @Test
    public void update_shouldReturnNull_whenOldFigureIsInvalid() {
        Figure newFigure = pointFactory.of(16, 10);

        assertNull(repository.update(null, newFigure));
        assertNull(repository.update(null, null));
    }

    @Test
    public void delete_shouldReturnTrue_whenElementWasDeleted() {
        Figure figure1 = pointFactory.of(30, 30);
        Figure figure2 = pointFactory.of(40, 40);
        repository.create(figure1);
        repository.create(figure2);

        assertFalse(repository.delete(null));
        assertFalse(repository.delete(-1));
        assertTrue(repository.delete(figure1));

        int index = repository.read(figure2);

        assertTrue(repository.delete(index));
    }

    @Test
    public void getAll_shouldReturnFiguresList_always() {
        assertNotNull(repository.getAll());
    }

    @Test
    public void getPublisher_shouldReturnPublisher_whenParameterIsValid() {
        Figure figure = pointFactory.of(230, 40);
        Figure figure1 = pointFactory.of(6, 40);
        repository.create(figure);
        repository.create(figure1);

        assertNotNull(repository.getPublisher(1));
        assertNotNull(repository.getPublisher(figure));
        assertNull(repository.getPublisher(null));
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void getPublisher_shouldThrowException_whenIndexIsInvalid() {
        repository.getPublisher(-1);
    }
}