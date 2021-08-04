import com.epam.jwd.figures.model.FigureTypes;
import com.epam.jwd.figures.model.rectangle.Quadrangle;
import com.epam.jwd.figures.model.rectangle.TypesOfQuadrangle;
import com.epam.jwd.figures.utils.action.QuadrangleActions;
import com.epam.jwd.figures.utils.reader.FigureReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.LinkedList;

public class TestFigureActions {
    private static final Logger LOGGER = LogManager.getLogger(TestFigureActions.class);
    private static final String FILEPATH = "files/quadrangles.txt";

    private static final LinkedList<Double> PERIMETER_LIST =
            new LinkedList<>(Arrays.asList(11.01, 8.0, 42.36, 42.80, 12.64, 23.15));

    private static final LinkedList<Double> SQUARE_LIST =
            new LinkedList<>(Arrays.asList(6.75, 4.0, 50.0, 60.0, 8.0, 15.0));

    private static final LinkedList<Boolean> CONVEX_LIST =
            new LinkedList<>(Arrays.asList(false, false, false, false, false, true));

    private static final LinkedList<TypesOfQuadrangle> TYPES_LIST =
            new LinkedList<>(
                    Arrays.asList(TypesOfQuadrangle.ARBITRARY, TypesOfQuadrangle.SQUARE,
                                  TypesOfQuadrangle.PARALLELOGRAM, TypesOfQuadrangle.TRAPEZOID,
                                  TypesOfQuadrangle.DIAMOND, TypesOfQuadrangle.ARBITRARY));

    private static final int NUMBER_OF_BUILT_FIGURES = 6;

    private QuadrangleActions actions = null;
    private LinkedList<Quadrangle> quadrangles = null;

    @Test
    public void buildingFigureTest() {
        FigureReader figureReader = new FigureReader(FILEPATH, FigureTypes.QUADRANGLE);
        quadrangles = (LinkedList<Quadrangle>) figureReader.scanFigures();
        Assert.assertEquals(quadrangles.size(), NUMBER_OF_BUILT_FIGURES);
    }

    @Test
    public void perimeterTest() {
        for (int i = 0; i < quadrangles.size(); i++) {
            actions = new QuadrangleActions(quadrangles.get(i));
            LOGGER.info(quadrangles.get(i) + ": " + actions.perimeter());
            Assert.assertEquals(PERIMETER_LIST.get(i), actions.perimeter(), 0.01);
        }
    }

    @Test
    public void squareTest() {
        for (int i = 0; i < quadrangles.size(); i++) {
            actions = new QuadrangleActions(quadrangles.get(i));
            LOGGER.info(quadrangles.get(i) + ": " + actions.square());
            Assert.assertEquals(SQUARE_LIST.get(i), actions.square(), 0.01);
        }
    }

    @Test
    public void convexTest() {
        for (int i = 0; i < quadrangles.size(); i++) {
            actions = new QuadrangleActions(quadrangles.get(i));
            LOGGER.info(quadrangles.get(i) + ": " + actions.isConvex());
            Assert.assertEquals(CONVEX_LIST.get(i), actions.isConvex());
        }
    }

    @Test
    public void typeOfQuadrangleTest() {
        for (int i = 0; i < quadrangles.size(); i++) {
            actions = new QuadrangleActions(quadrangles.get(i));
            LOGGER.info(quadrangles.get(i) + ": " + actions.defineTheType());
            Assert.assertEquals(TYPES_LIST.get(i), actions.defineTheType());
        }
    }

}
