package com.epam.jwd.utils.validation;

import com.epam.jwd.quadrangle.model.Point;
import com.epam.jwd.quadrangle.model.PointFactory;
import com.epam.jwd.quadrangle.validation.QuadrangleValidator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class QuadrangleValidatorTest {

    private PointFactory pointFactory = new PointFactory();
    private List<Point> points = new ArrayList<>();
    private QuadrangleValidator validator = null;

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
        validator = new QuadrangleValidator(points);

        assertFalse(validator.canBeBuilt());
    }

    @Test
    public void canBeBuild_shouldReturnTrue_whenPointsAreValid() {
        points.add(pointFactory.newInstance(0, 4));
        points.add(pointFactory.newInstance(3, 4));
        points.add(pointFactory.newInstance(5, 9));
        points.add(pointFactory.newInstance(6, 1));
        validator = new QuadrangleValidator(points);

        assertTrue(validator.canBeBuilt());
    }

    @Test
    public void canBeBuild_shouldReturnFalse_whenPointsOnSameLine() {
        points.add(pointFactory.newInstance(1, 0));
        points.add(pointFactory.newInstance(2, 0));
        points.add(pointFactory.newInstance(3, 0));
        points.add(pointFactory.newInstance(4, 9));
        validator = new QuadrangleValidator(points);

        assertFalse(validator.canBeBuilt());
    }
}