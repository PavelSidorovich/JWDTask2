package com.epam.jwd.figures;

import com.epam.jwd.figures.model.point.PointFabric;
import com.epam.jwd.figures.model.rectangle.Quadrangle;
import com.epam.jwd.figures.model.rectangle.QuadrangleFabric;
import com.epam.jwd.figures.utils.action.FigureActions;
import com.epam.jwd.figures.utils.action.QuadrangleActions;
import com.epam.jwd.figures.utils.exceptions.PointException;
import com.epam.jwd.figures.utils.exceptions.QuadrangleException;
import com.epam.jwd.figures.utils.validation.PointValidator;
import com.epam.jwd.figures.utils.validation.Validator;
import org.apache.commons.math3.util.Precision;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    public static final String FILEPATH = "files/quadrangles.txt";
    public static final int NUMBER_OF_COORDINATES = 8;

    public static void main(String[] args) {
        try {
            Scanner fileScanner = new Scanner(new File(FILEPATH));
            Validator validator = new PointValidator();
            while (fileScanner.hasNext()) {
                String[] coordinates = fileScanner.nextLine().split(" ");
                if (coordinates.length != NUMBER_OF_COORDINATES) {
                    LOGGER.error(new QuadrangleException(QuadrangleException.NUMBER_OF_ARGUMENTS_ERROR_MSG,
                                                         null, coordinates));
                    continue;
                } else {
                    try {
                        for (String coordinate : coordinates) {
                            if (!validator.isValid(coordinate)) {
                                throw new PointException(PointException.ERROR_MSG, coordinate);
                            }
                            LOGGER.info(coordinate + " " + validator.isValid(coordinate));
                        }
                        Quadrangle quadrangle = QuadrangleFabric.newInstance(
                                PointFabric.newInstance(Double.parseDouble(coordinates[0]),
                                                        Double.parseDouble(coordinates[1])),
                                PointFabric.newInstance(Double.parseDouble(coordinates[2]),
                                                        Double.parseDouble(coordinates[3])),
                                PointFabric.newInstance(Double.parseDouble(coordinates[4]),
                                                        Double.parseDouble(coordinates[5])),
                                PointFabric.newInstance(Double.parseDouble(coordinates[6]),
                                                        Double.parseDouble(coordinates[7]))
                        );
                        FigureActions actions = new QuadrangleActions(quadrangle);
                        LOGGER.trace(Precision.round(actions.perimeter(), 2));
                        LOGGER.trace(Precision.round(actions.square(), 2));
                    } catch (PointException e) {
                        LOGGER.error(e);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage());
            return;
        }
    }
}
