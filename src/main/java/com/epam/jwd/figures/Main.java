package com.epam.jwd.figures;

import com.epam.jwd.figures.model.point.PointFabric;
import com.epam.jwd.figures.model.rectangle.Quadrangle;
import com.epam.jwd.figures.model.rectangle.QuadrangleFabric;
import com.epam.jwd.figures.utils.action.FigureActions;
import com.epam.jwd.figures.utils.action.QuadrangleActions;
import org.apache.commons.math3.util.Precision;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Quadrangle quadrangle = QuadrangleFabric.newInstance(
                PointFabric.newInstance(0, 0),
                PointFabric.newInstance(0, 1),
                PointFabric.newInstance(1, 1),
                PointFabric.newInstance(1, 0)
        );
        FigureActions actions = new QuadrangleActions(quadrangle);
        LOGGER.trace(Precision.round(actions.perimeter(), 2));
        LOGGER.trace(Precision.round(actions.square(), 2));
    }
}
