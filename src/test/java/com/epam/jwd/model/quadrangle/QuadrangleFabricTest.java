package com.epam.jwd.model.quadrangle;


import com.epam.jwd.quadrangle.exception.FigureException;
import com.epam.jwd.quadrangle.model.Point;
import com.epam.jwd.quadrangle.model.PointFabric;
import com.epam.jwd.quadrangle.model.Quadrangle;
import com.epam.jwd.quadrangle.model.QuadrangleFabric;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.LinkedList;

import static org.testng.Assert.*;

@ExtendWith(MockitoExtension.class)
public class QuadrangleFabricTest {

    @InjectMocks
    private static final QuadrangleFabric QUADRANGLE_FABRIC = new QuadrangleFabric();

    private static final PointFabric POINT_FABRIC = new PointFabric();
    private static final LinkedList<Point> POINTS = new LinkedList<>();

    @AfterMethod
    public void clearList() {
        POINTS.clear();
    }

    @Test
    public void newInstance_shouldReturnQuadrangle_whenCoordinatesAreValid() {
        POINTS.add(POINT_FABRIC.newInstance(0, 4));
        POINTS.add(POINT_FABRIC.newInstance(3, 4));
        POINTS.add(POINT_FABRIC.newInstance(5, 9));
        POINTS.add(POINT_FABRIC.newInstance(6, 1));
        Quadrangle quadrangle = QUADRANGLE_FABRIC.newInstance(POINTS);

        assertNotNull(quadrangle);
    }

    @Test
    public void newInstance_throwFigureException_whenNumberOfCoordinatesIsInvalid() {
        POINTS.add(POINT_FABRIC.newInstance(0, 4));
        POINTS.add(POINT_FABRIC.newInstance(3, 4));
        POINTS.add(POINT_FABRIC.newInstance(5, 9));

        try {
            QUADRANGLE_FABRIC.newInstance(POINTS);
            fail("should throw FigureException");
        } catch (FigureException figureException) {
            assertNotNull(figureException);
            assertTrue(figureException.getMessage().contains("Wrong number of coordinates!"));
        }
    }

    @Test
    public void newInstance_throwFigureException_whenLinesAreCrossing() {
        POINTS.add(POINT_FABRIC.newInstance(0, 0));
        POINTS.add(POINT_FABRIC.newInstance(1, 0));
        POINTS.add(POINT_FABRIC.newInstance(2, 0));
        POINTS.add(POINT_FABRIC.newInstance(4, 9));

        try {
            QUADRANGLE_FABRIC.newInstance(POINTS);
            fail("should throw FigureException");
        } catch (FigureException figureException) {
            assertNotNull(figureException);
            assertTrue(figureException.getMessage().contains("Lines are crossing!"));
        }
    }
}