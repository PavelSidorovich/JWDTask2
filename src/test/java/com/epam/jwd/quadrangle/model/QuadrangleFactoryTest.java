package com.epam.jwd.quadrangle.model;

import com.epam.jwd.quadrangle.exception.FigureBuildException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.LinkedList;

import static org.testng.Assert.*;

public class QuadrangleFactoryTest {

    private final QuadrangleFactory quadrangleFactory = QuadrangleFactory.getInstance();
    private final PointFactory pointFactory = PointFactory.getInstance();
    private final LinkedList<Point> points = new LinkedList<>();

    @AfterMethod
    public void clearList() {
        points.clear();
    }

    @Test
    public void newInstance_shouldReturnQuadrangle_whenCoordinatesAreValid() {
        points.add(pointFactory.of(0, 4));
        points.add(pointFactory.of(3, 4));
        points.add(pointFactory.of(5, 9));
        points.add(pointFactory.of(6, 1));
        Quadrangle quadrangle = quadrangleFactory.of(points);

        assertNotNull(quadrangle);
    }

    @Test
    public void newInstance_throwFigureException_whenNumberOfCoordinatesIsInvalid() {
        points.add(pointFactory.of(0, 4));
        points.add(pointFactory.of(3, 4));
        points.add(pointFactory.of(5, 9));

        try {
            quadrangleFactory.of(points);
            fail("should throw FigureBuildException");
        } catch (FigureBuildException figureBuildException) {
            assertNotNull(figureBuildException);
            assertTrue(figureBuildException.getMessage().contains("Wrong number of coordinates!"));
        }
    }

    @Test
    public void newInstance_throwFigureException_whenLinesAreCrossing() {
        points.add(pointFactory.of(0, 0));
        points.add(pointFactory.of(1, 0));
        points.add(pointFactory.of(2, 0));
        points.add(pointFactory.of(4, 9));

        try {
            quadrangleFactory.of(points);
            fail("should throw FigureBuildException");
        } catch (FigureBuildException figureBuildException) {
            assertNotNull(figureBuildException);
            assertTrue(figureBuildException.getMessage().contains("Lines are crossing!"));
        }
    }
}