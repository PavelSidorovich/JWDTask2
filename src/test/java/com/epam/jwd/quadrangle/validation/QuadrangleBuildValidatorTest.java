package com.epam.jwd.quadrangle.validation;

import com.epam.jwd.quadrangle.model.Point;
import com.epam.jwd.quadrangle.model.PointFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class QuadrangleBuildValidatorTest {

    private final PointFactory pointFactory = new PointFactory();
    private final List<Point> points = new ArrayList<>();
    private final QuadrangleBuildValidator validator = new QuadrangleBuildValidator();;

    @AfterMethod
    public void clearList() {
        points.clear();
    }

    @Test
    public void canBeBuild_shouldReturnFalse_whenNumberOfPointsIsInvalid() {
        points.add(pointFactory.newInstance(0, 4));
        points.add(pointFactory.newInstance(3, 4));
        points.add(pointFactory.newInstance(5, 9));
        points.add(pointFactory.newInstance(6, 1));
        points.add(pointFactory.newInstance(8, 4));

        assertFalse(validator.creatable(points));
    }

    @Test
    public void canBeBuild_shouldReturnTrue_whenPointsAreValid() {
        points.add(pointFactory.newInstance(0, 4));
        points.add(pointFactory.newInstance(3, 4));
        points.add(pointFactory.newInstance(5, 9));
        points.add(pointFactory.newInstance(6, 1));

        assertTrue(validator.creatable(points));
    }

    @Test
    public void canBeBuild_shouldReturnFalse_whenPointsOnSameLine() {
        points.add(pointFactory.newInstance(1, 0));
        points.add(pointFactory.newInstance(2, 0));
        points.add(pointFactory.newInstance(3, 0));
        points.add(pointFactory.newInstance(4, 9));

        assertFalse(validator.creatable(points));
    }
}