package com.epam.jwd.quadrangle.model;

import com.epam.jwd.quadrangle.exception.FigureBuildException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.LinkedList;

import static org.testng.Assert.*;

public class QuadrangleFactoryTest {

    private final QuadrangleFactory QUADRANGLE_FABRIC = QuadrangleFactory.getInstance();
    private final PointFactory POINT_FABRIC = PointFactory.getInstance();
    private final LinkedList<Point> POINTS = new LinkedList<>();

    @AfterMethod
    public void clearList() {
        POINTS.clear();
    }

    @Test
    public void newInstance_shouldReturnQuadrangle_whenCoordinatesAreValid() {
        POINTS.add(POINT_FABRIC.of(0, 4));
        POINTS.add(POINT_FABRIC.of(3, 4));
        POINTS.add(POINT_FABRIC.of(5, 9));
        POINTS.add(POINT_FABRIC.of(6, 1));
        Quadrangle quadrangle = QUADRANGLE_FABRIC.of(POINTS);

        assertNotNull(quadrangle);
    }

    @Test
    public void newInstance_throwFigureException_whenNumberOfCoordinatesIsInvalid() {
        POINTS.add(POINT_FABRIC.of(0, 4));
        POINTS.add(POINT_FABRIC.of(3, 4));
        POINTS.add(POINT_FABRIC.of(5, 9));

        try {
            QUADRANGLE_FABRIC.of(POINTS);
            fail("should throw FigureBuildException");
        } catch (FigureBuildException figureBuildException) {
            assertNotNull(figureBuildException);
            assertTrue(figureBuildException.getMessage().contains("Wrong number of coordinates!"));
        }
    }

    @Test
    public void newInstance_throwFigureException_whenLinesAreCrossing() {
        POINTS.add(POINT_FABRIC.of(0, 0));
        POINTS.add(POINT_FABRIC.of(1, 0));
        POINTS.add(POINT_FABRIC.of(2, 0));
        POINTS.add(POINT_FABRIC.of(4, 9));

        try {
            QUADRANGLE_FABRIC.of(POINTS);
            fail("should throw FigureBuildException");
        } catch (FigureBuildException figureBuildException) {
            assertNotNull(figureBuildException);
            assertTrue(figureBuildException.getMessage().contains("Lines are crossing!"));
        }
    }
}