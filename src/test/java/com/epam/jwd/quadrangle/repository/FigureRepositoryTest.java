package com.epam.jwd.quadrangle.repository;

import com.epam.jwd.quadrangle.model.Figure;
import com.epam.jwd.quadrangle.model.PointFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FigureRepositoryTest {

    private final FigureRepository repository = new FigureRepository();
    private final PointFactory pointFactory = PointFactory.getInstance();

    @Test(dataProvider = "FigureToAddProvider")
    public void create_shouldReturnTrue_whenFigureAddedToRepository(Figure figure, boolean canBeAdded) {
        assertSame(repository.create(figure), canBeAdded);
    }

    @Test
    public void read() {
        Figure figure = pointFactory.of(1, 1);
        repository.create(pointFactory.of(0, 1));
        repository.create(figure);
        int index = repository.read(figure);

        assertEquals(repository.read(index), figure);
        assertSame(repository.read(-1), null);
        assertSame(repository.read(pointFactory.of(0, 33)), -1);
    }

    @Test
    public void update() {
        Figure oldFigure = pointFactory.of(5, 2);
        Figure newFigure = pointFactory.of(10, 10);
        repository.create(pointFactory.of(2, 1));
        repository.create(pointFactory.of(3, 5));
        repository.create(oldFigure);
        int index = repository.read(oldFigure);

        assertTrue(repository.update(oldFigure, newFigure));
        assertTrue(repository.update(index, pointFactory.of(53, 5)));
        assertFalse(repository.update(-1, newFigure));
        assertFalse(repository.update(null, newFigure));
    }

    @Test
    public void delete() {
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
    public void getAll() {
        assertNotNull(repository.getAll());
    }

    @DataProvider(name = "FigureToAddProvider")
    public Object[][] getFigureToAddFromProvider() {
        return new Object[][] {
                { pointFactory.of(0, 0), true },
                { null, false },
        };
    }
}