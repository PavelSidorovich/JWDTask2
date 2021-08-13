package com.epam.jwd.quadrangle.repository;

import com.epam.jwd.quadrangle.model.Figure;
import com.epam.jwd.quadrangle.model.FigureType;
import com.epam.jwd.quadrangle.reader.FigureReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import static org.testng.Assert.*;

public class FindByFigureSquareRangeTest {

    private final FindByFigureSquareRange specification = new FindByFigureSquareRange(8, 50);
    private FigureRepository figureRepository = null;

    @BeforeClass
    public void setUp() throws FileNotFoundException {
        URL url = Thread.currentThread().getContextClassLoader().getResource("quadrangles.txt");
        File file = new File(url.getPath());
        Scanner fileScanner = new Scanner(file);
        FigureReader figureReader = new FigureReader(FigureType.QUADRANGLE);
        List<Figure> quadrangles = figureReader.scanFigures(fileScanner);
        figureRepository = new FigureRepository(quadrangles);
    }

    @Test
    public void exists_shouldReturnList_always() {
        List<Figure> figureList = figureRepository.findBySpecification(specification);

        assertNotNull(figureList);
        assertSame(figureList.size(), 3);
    }
}