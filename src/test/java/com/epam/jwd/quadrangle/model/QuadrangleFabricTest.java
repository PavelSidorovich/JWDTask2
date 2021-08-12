package com.epam.jwd.quadrangle.model;


import com.epam.jwd.quadrangle.exception.FigureBuildException;
import com.epam.jwd.quadrangle.model.Point;
import com.epam.jwd.quadrangle.model.PointFactory;
import com.epam.jwd.quadrangle.model.Quadrangle;
import com.epam.jwd.quadrangle.model.QuadrangleFactory;
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
    private static final QuadrangleFactory QUADRANGLE_FABRIC = new QuadrangleFactory();

    private static final PointFactory POINT_FABRIC = new PointFactory();
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
            fail("should throw FigureBuildException");
        } catch (FigureBuildException figureBuildException) {
            assertNotNull(figureBuildException);
            assertTrue(figureBuildException.getMessage().contains("Wrong number of coordinates!"));
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
            fail("should throw FigureBuildException");
        } catch (FigureBuildException figureBuildException) {
            assertNotNull(figureBuildException);
            assertTrue(figureBuildException.getMessage().contains("Lines are crossing!"));
        }
    }
}