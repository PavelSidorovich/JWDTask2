package com.epam.jwd.quadrangle.repository.sort;

import com.epam.jwd.quadrangle.action.FigureActions;
import com.epam.jwd.quadrangle.action.FigureActions2D;
import com.epam.jwd.quadrangle.model.Figure;
import com.epam.jwd.quadrangle.model.FigurePublisher;
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

public class SortFiguresBySquareTest {

    private final SortFiguresBySquare comparator = new SortFiguresBySquare();
    private FigureRepository figureRepository = new FigureRepository();

    @BeforeClass
    public void setUp() throws FileNotFoundException {
        URL url = Thread.currentThread().getContextClassLoader().getResource("quadrangles.txt");
        File file = new File(url.getPath());
        Scanner fileScanner = new Scanner(file);
        FigureReader figureReader = new FigureReader(FigureType.QUADRANGLE);
        List<Figure> quadrangles = figureReader.scanFigures(fileScanner);
        PointFactory pointFactory = PointFactory.getInstance();

        figureRepository.create(new FigurePublisher(quadrangles.get(0)));
        figureRepository.create(new FigurePublisher(quadrangles.get(1)));
        figureRepository.create(new FigurePublisher(quadrangles.get(2)));
        figureRepository.create(new FigurePublisher(quadrangles.get(3)));
        figureRepository.create(new FigurePublisher(quadrangles.get(4)));
        figureRepository.create(new FigurePublisher(quadrangles.get(5)));
        figureRepository.create(pointFactory.publisherOf(0, 0));
        figureRepository.create(pointFactory.publisherOf(1, 2));
        figureRepository.create(pointFactory.publisherOf(2, 30));
    }

    @Test
    public void compare_shouldReturnPositiveNumber_ifSquareOfTheFirstFigureIsLessThanSecondOne() {
        List<Figure> sorted = figureRepository.sortByComparator(comparator);
        FigureActions actions1 = new FigureActions2D(sorted.get(0));
        FigureActions actions2 = new FigureActions2D(sorted.get(1));
        FigureActions actions3 = new FigureActions2D(sorted.get(2));
        FigureActions actions4 = new FigureActions2D(sorted.get(3));

        assertTrue(actions1.square() <= actions2.square());
        assertTrue(actions2.square() <= actions3.square());
        assertTrue(actions3.square() <= actions4.square());
    }

    @Test
    public void reversed_shouldReturnNegativeNumber_ifSquareOfTheFirstFigureIsLessThanSecondOne() {
        List<Figure> sorted = figureRepository.sortByComparator(comparator.reversed());
        FigureActions actions1 = new FigureActions2D(sorted.get(0));
        FigureActions actions2 = new FigureActions2D(sorted.get(1));
        FigureActions actions3 = new FigureActions2D(sorted.get(2));
        FigureActions actions4 = new FigureActions2D(sorted.get(3));

        assertTrue(actions1.square() >= actions2.square());
        assertTrue(actions2.square() >= actions3.square());
        assertTrue(actions3.square() >= actions4.square());
    }
}