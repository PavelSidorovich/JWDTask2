package com.epam.jwd.quadrangle.repository.sort;

import com.epam.jwd.quadrangle.action.FigureActions;
import com.epam.jwd.quadrangle.action.FigureActions2D;
import com.epam.jwd.quadrangle.model.Figure;
import com.epam.jwd.quadrangle.model.FigureType;
import com.epam.jwd.quadrangle.model.PointFactory;
import com.epam.jwd.quadrangle.reader.FigureReader;
import com.epam.jwd.quadrangle.repository.FigureRepository;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import static org.testng.Assert.*;

public class SortFiguresByPerimeterTest {

    private final SortFiguresByPerimeter comparator = new SortFiguresByPerimeter();
    private FigureRepository figureRepository = null;

    @BeforeClass
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
    public void compare_shouldReturnPositiveNumber_ifPerimeterOfTheFirstFigureIsLessThanSecondOne() {
        List<Figure> sorted = figureRepository.sortByComparator(comparator);

        FigureActions actions1 = new FigureActions2D(sorted.get(0));
        FigureActions actions2 = new FigureActions2D(sorted.get(1));
        FigureActions actions3 = new FigureActions2D(sorted.get(2));
        FigureActions actions4 = new FigureActions2D(sorted.get(3));

        assertTrue(actions1.perimeter() <= actions2.perimeter());
        assertTrue(actions2.perimeter() <= actions3.perimeter());
        assertTrue(actions3.perimeter() <= actions4.perimeter());
    }

    @Test
    public void reversed_shouldReturnNegativeNumber_ifPerimeterOfTheFirstFigureIsLessThanSecondOne() {
        List<Figure> sorted = figureRepository.sortByComparator(comparator.reversed());

        FigureActions actions1 = new FigureActions2D(sorted.get(0));
        FigureActions actions2 = new FigureActions2D(sorted.get(1));
        FigureActions actions3 = new FigureActions2D(sorted.get(2));
        FigureActions actions4 = new FigureActions2D(sorted.get(3));

        assertTrue(actions1.perimeter() >= actions2.perimeter());
        assertTrue(actions2.perimeter() >= actions3.perimeter());
        assertTrue(actions3.perimeter() >= actions4.perimeter());
    }
}