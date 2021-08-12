package com.epam.jwd.quadrangle.action;

import com.epam.jwd.quadrangle.model.Figure;
import com.epam.jwd.quadrangle.model.FigureType;
import com.epam.jwd.quadrangle.reader.FigureReader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class FigureActions2DTest {

    private FigureActions2D actions = null;
    private List<? extends Figure> quadrangles = null;

    @BeforeClass
    public void setUp() throws FileNotFoundException {
        URL url = Thread.currentThread().getContextClassLoader().getResource("quadrangles.txt");
        File file = new File(url.getPath());
        Scanner fileScanner = new Scanner(file);
        FigureReader figureReader = new FigureReader(FigureType.QUADRANGLE);
        quadrangles = figureReader.scanFigures(fileScanner);
    }

    @Test(dataProvider = "PerimeterProvider")
    public void perimeter_shouldReturnFigurePerimeter_always(Figure figure, Double perimeter) {
        actions = new FigureActions2D(figure);

        Assert.assertEquals(actions.perimeter(), perimeter, 0.01);
    }

    @Test(dataProvider = "SquareProvider")
    public void square_shouldReturnFigureSquare_always(Figure figure, Double square) {
        actions = new FigureActions2D(figure);

        Assert.assertEquals(actions.square(), square, 0.01);
    }

    @Test(dataProvider = "ConvexProvider")
    public void isConvex_shouldReturnTrue_whenFigureIsConvex(Figure figure, Boolean isConvex) {
        actions = new FigureActions2D(figure);

        Assert.assertEquals(actions.isConvex(), isConvex);
    }

    @DataProvider(name = "ConvexProvider")
    public Object[][] getConvexFromProvider() {
        return new Object[][] {
                { quadrangles.get(0), true },
                { quadrangles.get(1), true },
                { quadrangles.get(2), true },
                { quadrangles.get(3), true },
                { quadrangles.get(4), true },
                { quadrangles.get(5), false },
        };
    }

    @DataProvider(name = "PerimeterProvider")
    public Object[][] getPerimetersFromProvider() {
        return new Object[][] {
                { quadrangles.get(0), 11.01 },
                { quadrangles.get(1), 8.0 },
                { quadrangles.get(2), 42.36 },
                { quadrangles.get(3), 42.80 },
                { quadrangles.get(4), 12.64 },
                { quadrangles.get(5), 23.15 },
        };
    }

    @DataProvider(name = "SquareProvider")
    public Object[][] getSquaresFromProvider() {
        return new Object[][] {
                { quadrangles.get(0), 6.75 },
                { quadrangles.get(1), 4.0 },
                { quadrangles.get(2), 50.0 },
                { quadrangles.get(3), 60.0 },
                { quadrangles.get(4), 8.0 },
                { quadrangles.get(5), 15.0 },
        };
    }
}