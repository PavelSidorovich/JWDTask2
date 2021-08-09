package com.epam.jwd.utils.action;

import com.epam.jwd.model.quadrangle.Figure;
import com.epam.jwd.model.quadrangle.FigureTypes;
import com.epam.jwd.model.quadrangle.Quadrangle;
import com.epam.jwd.model.quadrangle.TypesOfQuadrangle;
import com.epam.jwd.utils.reader.FigureReader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Scanner;

public class FigureActionsTest {

    private QuadrangleActions actions = null;
    private LinkedList<Quadrangle> quadrangles = null;

    @BeforeClass
    public void setUp() throws FileNotFoundException {
        URL url = Thread.currentThread().getContextClassLoader().getResource("quadrangles.txt");
        File file = new File(url.getPath());
        Scanner fileScanner = new Scanner(file);
        FigureReader figureReader = new FigureReader(fileScanner, FigureTypes.QUADRANGLE);
        quadrangles = (LinkedList<Quadrangle>) figureReader.scanFigures();
    }

    @Test(dataProvider = "PerimeterProvider")
    public void perimeter_shouldReturnFigurePerimeter_always(Figure figure, Double perimeter) {
        actions = new QuadrangleActions(figure);
        Assert.assertEquals(actions.perimeter(), perimeter, 0.01);
    }

    @Test(dataProvider = "SquareProvider")
    public void square_shouldReturnFigureSquare_always(Figure figure, Double square) {
        actions = new QuadrangleActions(figure);
        Assert.assertEquals(actions.square(), square, 0.01);
    }

    @Test(dataProvider = "ConvexProvider")
    public void isConvex_shouldReturnTrue_whenFigureIsConvex(Figure figure, Boolean isConvex) {
        actions = new QuadrangleActions(figure);
        Assert.assertEquals(actions.isConvex(), isConvex);
    }

    @Test(dataProvider = "TypeProvider")
    public void defineTheType_shouldReturnTheTypeOfQuadrangle_always(Figure figure, TypesOfQuadrangle quadrangleType) {
        actions = new QuadrangleActions(figure);
        Assert.assertEquals(quadrangleType, actions.defineTheType());
    }

    @DataProvider(name = "TypeProvider")
    public Object[][] getTypesFromProvider() {
        return new Object[][] {
                { quadrangles.get(0), TypesOfQuadrangle.ARBITRARY },
                { quadrangles.get(1), TypesOfQuadrangle.SQUARE },
                { quadrangles.get(2), TypesOfQuadrangle.PARALLELOGRAM },
                { quadrangles.get(3), TypesOfQuadrangle.TRAPEZOID },
                { quadrangles.get(4), TypesOfQuadrangle.DIAMOND },
                { quadrangles.get(5), TypesOfQuadrangle.ARBITRARY },
        };
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