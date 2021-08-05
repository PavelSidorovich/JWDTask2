import com.epam.jwd.figures.model.FigureTypes;
import com.epam.jwd.figures.model.rectangle.Quadrangle;
import com.epam.jwd.figures.model.rectangle.TypesOfQuadrangle;
import com.epam.jwd.figures.utils.action.QuadrangleActions;
import com.epam.jwd.figures.utils.reader.FigureReader;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.LinkedList;

public class TestFigureActions {
    private static final String FILEPATH = "files/quadrangles.txt";

    private static final LinkedList<Double> PERIMETER_LIST =
            new LinkedList<>(Arrays.asList(11.01, 8.0, 42.36, 42.80, 12.64, 23.15));

    private static final LinkedList<Double> SQUARE_LIST =
            new LinkedList<>(Arrays.asList(6.75, 4.0, 50.0, 60.0, 8.0, 15.0));

    private static final LinkedList<Boolean> CONVEX_LIST =
            new LinkedList<>(Arrays.asList(true, true, true, true, true, false));

    private static final LinkedList<TypesOfQuadrangle> TYPES_LIST =
            new LinkedList<>(
                    Arrays.asList(TypesOfQuadrangle.ARBITRARY, TypesOfQuadrangle.SQUARE,
                                  TypesOfQuadrangle.PARALLELOGRAM, TypesOfQuadrangle.TRAPEZOID,
                                  TypesOfQuadrangle.DIAMOND, TypesOfQuadrangle.ARBITRARY));
    private static final FigureReader FIGURE_READER = new FigureReader(FILEPATH, FigureTypes.QUADRANGLE);
    private static final int NUMBER_OF_BUILT_FIGURES = 6;

    private QuadrangleActions actions = null;
    private LinkedList<Quadrangle> quadrangles = null;

    @BeforeTest
    public void scanFigures() {
        quadrangles = (LinkedList<Quadrangle>) FIGURE_READER.scanFigures();
    }

    @Test
    public void buildingFigureTest() {
        Assert.assertEquals(quadrangles.size(), NUMBER_OF_BUILT_FIGURES);
    }

    @Test
    public void perimeterTest() {
        actions = new QuadrangleActions(quadrangles.get(0));
        Assert.assertEquals(PERIMETER_LIST.get(0), actions.perimeter(), 0.01);
    }

    @Test
    public void squareTest() {
        actions = new QuadrangleActions(quadrangles.get(0));
        Assert.assertEquals(SQUARE_LIST.get(0), actions.square(), 0.01);
    }

    @Test
    public void convexTest() {
        actions = new QuadrangleActions(quadrangles.get(5));
        Assert.assertEquals(CONVEX_LIST.get(5), actions.isConvex());
    }

    @Test
    public void notConvexTest() {
        actions = new QuadrangleActions(quadrangles.get(0));
        Assert.assertEquals(CONVEX_LIST.get(0), actions.isConvex());
    }

    @Test
    public void arbitraryFigureTest() {
        actions = new QuadrangleActions(quadrangles.get(0));
        Assert.assertEquals(TYPES_LIST.get(0), actions.defineTheType());
    }

    @Test
    public void squareFigureTest() {
        actions = new QuadrangleActions(quadrangles.get(1));
        Assert.assertEquals(TYPES_LIST.get(1), actions.defineTheType());
    }

    @Test
    public void trapezoidFigureTest() {
        actions = new QuadrangleActions(quadrangles.get(3));
        Assert.assertEquals(TYPES_LIST.get(3), actions.defineTheType());
    }

    @Test
    public void diamondFigureTest() {
        actions = new QuadrangleActions(quadrangles.get(4));
        Assert.assertEquals(TYPES_LIST.get(4), actions.defineTheType());
    }

    @Test
    public void parallelogramFigureTest() {
        actions = new QuadrangleActions(quadrangles.get(2));
        Assert.assertEquals(TYPES_LIST.get(2), actions.defineTheType());
    }
}
