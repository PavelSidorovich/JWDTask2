package com.epam.jwd.model.quadrangle;

import com.epam.jwd.utils.exceptions.FigureException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.LinkedList;

import static org.testng.Assert.*;

public class QuadrangleFabricTest {

    private static final QuadrangleFabric QUADRANGLE_FABRIC = new QuadrangleFabric();
    private static final PointFabric POINT_FABRIC = new PointFabric();
    private static LinkedList<Point> points = new LinkedList<>();

    @AfterMethod
    public void clearList() {
        points.clear();
    }

    @Test
    public void newInstance_shouldReturnQuadrangle_whenCoordinatesAreValid() {
        points.add(POINT_FABRIC.newInstance(0, 4));
        points.add(POINT_FABRIC.newInstance(3, 4));
        points.add(POINT_FABRIC.newInstance(5, 9));
        points.add(POINT_FABRIC.newInstance(6, 1));

        assertNotNull(QUADRANGLE_FABRIC.newInstance(points));
    }

    @Test
    public void newInstance_throwFigureException_whenNumberOfCoordinatesIsInvalid() {
        points.add(POINT_FABRIC.newInstance(0, 4));
        points.add(POINT_FABRIC.newInstance(3, 4));
        points.add(POINT_FABRIC.newInstance(5, 9));

        try {
            QUADRANGLE_FABRIC.newInstance(points);
            fail("should throw FigureException");
        } catch (FigureException figureException) {
            assertNotNull(figureException);
            assertTrue(figureException.getMessage().contains("Wrong number of coordinates!"));
        }
    }

    @Test
    public void newInstance_throwFigureException_whenLinesAreCrossing() {
        points.add(POINT_FABRIC.newInstance(0, 0));
        points.add(POINT_FABRIC.newInstance(1, 0));
        points.add(POINT_FABRIC.newInstance(2, 0));
        points.add(POINT_FABRIC.newInstance(4, 9));

        try {
            QUADRANGLE_FABRIC.newInstance(points);
            fail("should throw FigureException");
        } catch (FigureException figureException) {
            assertNotNull(figureException);
            assertTrue(figureException.getMessage().contains("Lines are crossing!"));
        }
    }
}