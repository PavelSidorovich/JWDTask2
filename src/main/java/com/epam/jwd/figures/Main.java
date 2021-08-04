package com.epam.jwd.figures;

import com.epam.jwd.figures.model.FigureTypes;
import com.epam.jwd.figures.model.rectangle.Quadrangle;
import com.epam.jwd.figures.utils.action.FigureActions;
import com.epam.jwd.figures.utils.action.QuadrangleActions;
import com.epam.jwd.figures.utils.reader.FigureReader;
import org.apache.commons.math3.util.Precision;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    public static final String FILEPATH = "files/quadrangles.txt";

    public static void main(String[] args) {
        FigureReader figureReader = new FigureReader(FILEPATH, FigureTypes.QUADRANGLE);
        LinkedList<Quadrangle> quadrangles = (LinkedList<Quadrangle>) figureReader.scanFigures();
        QuadrangleActions actions;

        for (Quadrangle quadrangle : quadrangles) {
            actions = new QuadrangleActions(quadrangle);
            LOGGER.trace(Precision.round(actions.perimeter(), 2));
            LOGGER.trace(Precision.round(actions.square(), 2));
            LOGGER.trace(actions.isConvex());
            LOGGER.trace(actions.defineTheType());
        }
    }
}

//0.0 0.0 0.0 1.0 1.0 1.0 1.0 0.0
//0.0 0.0 0.0 1.0 1.0 0.0 1.0 1.0
//0.0 0.0 0.0 1.0 1.0 0.0 1.0 1.0 1.0
//0.0 0.0 0.0 1.0 1.0 1.0
//0.0 f 0.0 1.0 1.0 0.0 1.0 1.0
//0.0 2.0 2.0 1.0 4.0 3.0 2.0 0.5

//0.0 0.0 0.0 1.0 1.0 1.0 1.0 0.0
//        0.0 2.0 2.0 1.0 4.0 3.0 2.0 0.5
//        0.0 0.0 1.0 1.0 3.0 1.0 4.0 0.0