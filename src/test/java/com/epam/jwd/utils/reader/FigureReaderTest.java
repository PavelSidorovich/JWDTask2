package com.epam.jwd.utils.reader;

import com.epam.jwd.quadrangle.model.Figure;
import com.epam.jwd.quadrangle.model.FigureType;
import com.epam.jwd.quadrangle.reader.FigureReader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import static org.testng.Assert.*;

public class FigureReaderTest {
    private static final FigureReader FIGURE_READER = new FigureReader(FigureType.QUADRANGLE);

    private static Scanner fileScanner = null;
    private static File file = null;

    @BeforeClass
    public void setUp() {
        URL url = Thread.currentThread().getContextClassLoader().getResource("quadrangles.txt");
        file = new File(url.getPath());
    }

    @BeforeMethod
    public void setUpFileScanner() throws FileNotFoundException {
        fileScanner = new Scanner(file);
    }

    @AfterClass
    public void tearDown() {
        fileScanner.close();
    }

    @Test
    public void scanFigures_shouldReturnList_whenArgumentsAreValid() {
        ArrayList<? extends Figure> figures = FIGURE_READER.scanFigures(fileScanner);

        assertNotNull(figures);
        assertSame(figures.size(), 6);
    }

    @Test(dataProvider = "ConstructorArgumentsProvider")
    public void scanFigures_shouldReturnNull_whenArgumentsAreInvalid(Scanner scanner, FigureType figureType) {
        FigureReader figureReader = new FigureReader(figureType);

        assertNull(figureReader.scanFigures(scanner));
    }

    @Test
    public void testGetNumberOfFiguresInFile() {
        FIGURE_READER.scanFigures(fileScanner);

        assertSame(FIGURE_READER.getNumberOfFiguresInFile(), 12);
    }

    @Test
    public void testGetNumberOfBuiltFigures() {
        FIGURE_READER.scanFigures(fileScanner);

        assertSame(FIGURE_READER.getNumberOfBuiltFigures(), 6);
    }

    @DataProvider(name = "ConstructorArgumentsProvider")
    public Object[][] getConstructorArgumentsFromProvider() {
        return new Object[][] {
                { null, null },
                { null, FigureType.QUADRANGLE },
                { fileScanner, null },
        };
    }
}