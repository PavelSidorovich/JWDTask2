package com.epam.jwd.quadrangle.reader;

import com.epam.jwd.quadrangle.exception.FigureBuildException;
import com.epam.jwd.quadrangle.exception.PointArgumentException;
import com.epam.jwd.quadrangle.model.Figure;
import com.epam.jwd.quadrangle.model.FigureFactory;
import com.epam.jwd.quadrangle.model.FigureType;
import com.epam.jwd.quadrangle.model.Point;
import com.epam.jwd.quadrangle.model.PointFactory;
import com.epam.jwd.quadrangle.model.QuadrangleFactory;
import com.epam.jwd.quadrangle.validation.PointValidator;
import com.epam.jwd.quadrangle.validation.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The {@code FigureReader} class is designed to extract figures from file
 *
 * @author Pavel Sidorovich
 * @since 1.0
 */
public class FigureReader {
    private static final Logger LOG = LogManager.getLogger(FigureReader.class);
    private static final String RESULT_MSG = "Number of figures in file: %d. Successfully built %d figures.";
    private static final String NOT_ENOUGH_COORDINATES_MSG = "Not enough coordinates to build a figure! ";
    private static final String WRONG_COORDINATE_MSG = "Wrong coordinate! ";
    private static final String FIGURE_WAS_BUILT_MSG = "Figure was built (%s): %s";
    private static final String SUCCESSFUL_INITIALIZATION_MSG = "%s was successfully initialized!";
    private static final String NOT_SUCCESSFUL_INITIALIZATION_MSG = "%s was not successfully initialized!";

    private final Validator validator = new PointValidator();
    private int numberOfCoordinates;
    private final FigureType figureType;
    private int numberOfFiguresInFile = 0;
    private int numberOfBuiltFigures = 0;

    public FigureReader(FigureType figureType) {
        this.figureType = figureType;
        if (figureType != null) {
            this.numberOfCoordinates = 2 * figureType.getNumberOfPoints();
            LOG.trace(String.format(SUCCESSFUL_INITIALIZATION_MSG, getClass().getSimpleName()));
        } else {
            LOG.warn(String.format(NOT_SUCCESSFUL_INITIALIZATION_MSG, getClass().getSimpleName()));
        }
    }

    /**
     * Builds figures from coordinates specified in a file. Each use of this method requires setting a new scanner
     *
     * @return list of built figures
     */
    public ArrayList<? extends Figure> scanFigures(Scanner fileScanner) {
        ArrayList<Figure> figureList = null;
        FigureFactory figureFactory;
        numberOfBuiltFigures = 0;
        numberOfFiguresInFile = 0;

        if (fileScanner != null && numberOfCoordinates > 0 && figureType != null) {
            figureList = new ArrayList<>();

            while (fileScanner.hasNext()) {
                String[] coordinates = fileScanner.nextLine().split(" ");
                numberOfFiguresInFile++;
                ArrayList<Point> points = new ArrayList<>();

                try {
                    figureFactory = new PointFactory();

                    if (coordinates.length % 2 != 0) {
                        throw new PointArgumentException(NOT_ENOUGH_COORDINATES_MSG,
                                                         new ArrayList<>(Arrays.asList(coordinates)));
                    }
                    for (int i = 0; i < coordinates.length; i += 2) {
                        if (validator.isValid(coordinates[i]) && validator.isValid(coordinates[i + 1])) {
                            points.add(((PointFactory) figureFactory).newInstance(
                                    Double.parseDouble(coordinates[i]),
                                    Double.parseDouble(coordinates[i + 1]))
                            );
                        } else {
                            throw new PointArgumentException(WRONG_COORDINATE_MSG,
                                                             new ArrayList<>(Arrays.asList(coordinates)));
                        }
                    }
                    Figure figure;
                    switch (figureType) {
                    case POINT:
                        figureFactory = new PointFactory();
                        break;
                    case LINE:
                    case TRIANGLE:
                        break;
                    case QUADRANGLE:
                        figureFactory = new QuadrangleFactory();
                        break;
                    }
                    figure = figureFactory.newInstance(points);
                    numberOfBuiltFigures++;
                    LOG.trace(String.format(FIGURE_WAS_BUILT_MSG,
                                            figure.getClass().getSimpleName(),
                                            figure.getPoints()));
                    figureList.add(figure);
                } catch (PointArgumentException | FigureBuildException e) {
                    LOG.error(e);
                }
            }
        }
        LOG.trace(String.format(RESULT_MSG, getNumberOfFiguresInFile(), getNumberOfBuiltFigures()));
        return figureList;
    }

    public int getNumberOfFiguresInFile() {
        return numberOfFiguresInFile;
    }

    public int getNumberOfBuiltFigures() {
        return numberOfBuiltFigures;
    }
}
