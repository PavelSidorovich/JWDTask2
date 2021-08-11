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
import java.util.List;
import java.util.Scanner;

import static org.testng.Assert.*;

public class FigureReaderTest {
    private FigureReader figureReader = new FigureReader(FigureType.QUADRANGLE);

    private Scanner fileScanner = null;
    private File file = null;

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
        List<? extends Figure> figures = figureReader.scanFigures(fileScanner);

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
        figureReader.scanFigures(fileScanner);

        assertSame(figureReader.getNumberOfFiguresInFile(), 12);
    }

    @Test
    public void testGetNumberOfBuiltFigures() {
        figureReader.scanFigures(fileScanner);

        assertSame(figureReader.getNumberOfBuiltFigures(), 6);
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