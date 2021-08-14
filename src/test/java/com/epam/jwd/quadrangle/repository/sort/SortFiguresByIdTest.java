package com.epam.jwd.quadrangle.repository.sort;

import com.epam.jwd.quadrangle.action.FigureActions;
import com.epam.jwd.quadrangle.action.FigureActions2D;
import com.epam.jwd.quadrangle.model.Figure;
import com.epam.jwd.quadrangle.model.FigureType;
import com.epam.jwd.quadrangle.model.PointFactory;
import com.epam.jwd.quadrangle.reader.FigureReader;
import com.epam.jwd.quadrangle.repository.FigureRepository;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import static org.testng.Assert.*;

public class SortFiguresByIdTest {

    private final SortFiguresByPerimeter comparator = new SortFiguresByPerimeter();
    private FigureRepository figureRepository = null;

    @BeforeMethod
    public void setUp() throws FileNotFoundException {
        URL url = Thread.currentThread().getContextClassLoader().getResource("quadrangles.txt");
        File file = new File(url.getPath());
        Scanner fileScanner = new Scanner(file);
        FigureReader figureReader = new FigureReader(FigureType.QUADRANGLE);
        List<Figure> quadrangles = figureReader.scanFigures(fileScanner);
        PointFactory pointFactory = new PointFactory();
        figureRepository = new FigureRepository(quadrangles);
        figureRepository.create(pointFactory.newInstance(0, 0));
        figureRepository.create(pointFactory.newInstance(1, 2));
        figureRepository.create(pointFactory.newInstance(2, 30));
    }

    @Test
    public void compare_shouldReturnPositiveNumber_ifIdOfTheFirstFigureIsLessThanSecondOne() {
        List<Figure> sorted = figureRepository.sortByComparator(comparator);

        assertTrue(sorted.get(0).getId() < sorted.get(1).getId());
        assertTrue(sorted.get(1).getId() < sorted.get(2).getId());
        assertTrue(sorted.get(2).getId() < sorted.get(3).getId());
    }

    @Test
    public void reversed_shouldReturnNegativeNumber_ifIdOfTheFirstFigureIsLessThanSecondOne() {
        List<Figure> sorted = figureRepository.sortByComparator(comparator.reversed());

        assertTrue(sorted.get(0).getId() > sorted.get(1).getId());
        assertTrue(sorted.get(1).getId() > sorted.get(2).getId());
        assertTrue(sorted.get(2).getId() > sorted.get(3).getId());
    }

}