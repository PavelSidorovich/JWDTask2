package com.epam.jwd.quadrangle.validation;

import com.epam.jwd.quadrangle.model.Point;
import com.epam.jwd.quadrangle.model.PointFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.testng.Assert.*;

public class QuadrangleBuildValidatorTest {

    private final PointFactory pointFactory = PointFactory.getInstance();
    private final List<Point> points = new ArrayList<>();
    private final QuadrangleBuildValidator validator = QuadrangleBuildValidator.getInstance();

    @AfterMethod
    public void clearList() {
        points.clear();
    }

    @Test(dataProvider = "StringProvider")
    public void getPattern_shouldReturnValidPattern_always(String string, boolean isValid) {
        Pattern pattern = validator.getPattern();

        assertNotNull(pattern);
        assertEquals(pattern.matcher(string).matches(), isValid);
    }

    @Test
    public void creatable_shouldReturnFalse_whenNumberOfPointsIsInvalid() {
        points.add(pointFactory.of(0, 4));
        points.add(pointFactory.of(3, 4));
        points.add(pointFactory.of(5, 9));
        points.add(pointFactory.of(6, 1));
        points.add(pointFactory.of(8, 4));

        assertFalse(validator.creatable(points));
    }

    @Test
    public void creatable_shouldReturnTrue_whenPointsAreValid() {
        points.add(pointFactory.of(0, 4));
        points.add(pointFactory.of(3, 4));
        points.add(pointFactory.of(5, 9));
        points.add(pointFactory.of(6, 1));

        assertTrue(validator.creatable(points));
    }

    @Test
    public void creatable_shouldReturnFalse_whenPointsOnSameLine() {
        points.add(pointFactory.of(1, 0));
        points.add(pointFactory.of(2, 0));
        points.add(pointFactory.of(3, 0));
        points.add(pointFactory.of(4, 9));

        assertFalse(validator.creatable(points));
    }

    @DataProvider(name = "StringProvider")
    public Object[][] getPerimetersFromProvider() {
        return new Object[][] {
                { "0.0 0.0 10.0 5.0 20.0 5.0 10.0 0.0", true },
                { "  0.0 0.0 10.0 5.0 20.0 5.0 10.0 0.0", false },
                { "0.0 0.0 10.0 5.0 20.0 5.0 10.0 0.0   ", true },
                { "0.0 0.0   10.0 5.0 20.0 5.0   10.0 0.0   ", true },
                { "0.0 0.0   10.0 5.0 z 5.0   10.0 0.0   ", false },
        };
    }
}