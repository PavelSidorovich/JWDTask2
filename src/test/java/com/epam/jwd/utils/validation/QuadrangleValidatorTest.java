package com.epam.jwd.utils.validation;

import com.epam.jwd.quadrangle.model.Point;
import com.epam.jwd.quadrangle.model.PointFabric;
import com.epam.jwd.quadrangle.validation.QuadrangleValidator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.LinkedList;

import static org.testng.Assert.*;

public class QuadrangleValidatorTest {

    private static final PointFabric POINT_FABRIC = new PointFabric();
    private static final LinkedList<Point> points = new LinkedList<>();
    private static QuadrangleValidator validator = null;

    @AfterMethod
    public void clearList() {
        points.clear();
    }

    @Test
    public void canBeBuild_shouldReturnFalse_whenNumberOfPointsIsInvalid() {
        points.add(POINT_FABRIC.newInstance(0, 4));
        points.add(POINT_FABRIC.newInstance(3, 4));
        points.add(POINT_FABRIC.newInstance(5, 9));
        points.add(POINT_FABRIC.newInstance(6, 1));
        points.add(POINT_FABRIC.newInstance(8, 4));
        validator = new QuadrangleValidator(points);

        assertFalse(validator.canBeBuilt());
    }

    @Test
    public void canBeBuild_shouldReturnTrue_whenPointsAreValid() {
        points.add(POINT_FABRIC.newInstance(0, 4));
        points.add(POINT_FABRIC.newInstance(3, 4));
        points.add(POINT_FABRIC.newInstance(5, 9));
        points.add(POINT_FABRIC.newInstance(6, 1));
        validator = new QuadrangleValidator(points);

        assertTrue(validator.canBeBuilt());
    }

    @Test
    public void canBeBuild_shouldReturnFalse_whenPointsOnSameLine() {
        points.add(POINT_FABRIC.newInstance(1, 0));
        points.add(POINT_FABRIC.newInstance(2, 0));
        points.add(POINT_FABRIC.newInstance(3, 0));
        points.add(POINT_FABRIC.newInstance(4, 9));
        validator = new QuadrangleValidator(points);

        assertFalse(validator.canBeBuilt());
    }
}