package com.epam.jwd.quadrangle.action;

import com.epam.jwd.quadrangle.model.Figure;
import com.epam.jwd.quadrangle.model.FigureType;
import com.epam.jwd.quadrangle.model.QuadrangleType;
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

public class QuadrangleActionsTest {

    private List<? extends Figure> quadrangles = null;

    @BeforeClass
    public void setUp() throws FileNotFoundException {
        URL url = Thread.currentThread().getContextClassLoader().getResource("quadrangles.txt");
        File file = new File(url.getPath());
        Scanner fileScanner = new Scanner(file);
        FigureReader figureReader = new FigureReader(FigureType.QUADRANGLE);
        quadrangles = figureReader.scanFigures(fileScanner);
    }

    @Test(dataProvider = "TypeProvider")
    public void defineTheType_shouldReturnTheTypeOfQuadrangle_always(Figure figure, QuadrangleType quadrangleType) {
        QuadrangleActions actions = new QuadrangleActions(figure);

        Assert.assertEquals(actions.defineTheType(), quadrangleType);
    }

    @DataProvider(name = "TypeProvider")
    public Object[][] getTypesFromProvider() {
        return new Object[][] {
                { quadrangles.get(0), QuadrangleType.ARBITRARY },
                { quadrangles.get(1), QuadrangleType.SQUARE },
                { quadrangles.get(2), QuadrangleType.PARALLELOGRAM },
                { quadrangles.get(3), QuadrangleType.TRAPEZOID },
                { quadrangles.get(4), QuadrangleType.DIAMOND },
                { quadrangles.get(5), QuadrangleType.ARBITRARY },
        };
    }
}