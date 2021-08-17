package com.epam.jwd.quadrangle.model;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.testng.Assert.*;

public class PointFactoryTest {

    private final PointFactory pointFactory = PointFactory.getInstance();

    @Test
    public void getInstance_shouldReturnPointFactoryInstance_always() {
        assertNotNull(PointFactory.getInstance());
    }

    @Test
    public void of_shouldReturnPoint_whenParameterIsValid() {
        assertNotNull(pointFactory.of(1, 4));
        assertNotNull(pointFactory.of(new ArrayList<>(Collections.singletonList(pointFactory.of(2, 2)))));
    }

    @Test
    public void of_shouldReturnNull_whenNumberOfPointsIsInvalid() {
        assertNull(pointFactory.of(new ArrayList<>(Arrays.asList(pointFactory.of(1, 2),
                                                                 pointFactory.of(2, 2)))));
    }
}