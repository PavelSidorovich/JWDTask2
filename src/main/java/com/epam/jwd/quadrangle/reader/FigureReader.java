package com.epam.jwd.quadrangle.reader;

import com.epam.jwd.quadrangle.exception.FigureBuildException;
import com.epam.jwd.quadrangle.exception.PointArgumentException;
import com.epam.jwd.quadrangle.model.Figure;
import com.epam.jwd.quadrangle.model.FigureFactory;
import com.epam.jwd.quadrangle.model.FigureType;
import com.epam.jwd.quadrangle.model.Point;
import com.epam.jwd.quadrangle.model.PointFactory;
import com.epam.jwd.quadrangle.model.QuadrangleFactory;
import com.epam.jwd.quadrangle.validation.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    private static final String FIGURE_WAS_BUILT_MSG = "Figure was built (%s): %s";
    private static final String FIGURE_CAN_NOT_BE_BUILT_MSG = "%s can't be built from coordinates: %s";
    private static final String SUCCESSFUL_INITIALIZATION_MSG = "%s was successfully initialized!";
    private static final String NOT_SUCCESSFUL_INITIALIZATION_MSG = "%s was not successfully initialized!";

    private Validator validator = null;
    private final FigureType figureType;
    private int numberOfFiguresInFile = 0;
    private int numberOfBuiltFigures = 0;

    public FigureReader(FigureType figureType) {
        this.figureType = figureType;
        if (figureType != null) {
            validator = figureType.getValidator();
            LOG.trace(String.format(SUCCESSFUL_INITIALIZATION_MSG, getClass().getSimpleName()));
        } else {
            LOG.warn(String.format(NOT_SUCCESSFUL_INITIALIZATION_MSG, getClass().getSimpleName()));
        }
    }

    public int getNumberOfBuiltFigures() {
        return numberOfBuiltFigures;
    }

    public int getNumberOfFiguresInFile() {
        return numberOfFiguresInFile;
    }

    /**
     * Builds figures from coordinates specified in a file. Each use of this method requires setting a new scanner
     *
     * @return list of built figures
     */
    public ArrayList<Figure> scanFigures(Scanner fileScanner) {
        ArrayList<Figure> figureList = null;
        PointFactory pointFactory = new PointFactory();
        numberOfBuiltFigures = 0;
        numberOfFiguresInFile = 0;

        if (fileScanner != null && figureType != null
            && (2 * figureType.getNumberOfPoints() > 0)) {
            figureList = new ArrayList<>();

            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] coordinates = line.split("\\s+");
                List<Point> points = new ArrayList<>();

                numberOfFiguresInFile++;
                try {
                    if (!validator.isValid(line)) {
                        String errorMsg = String.format(FIGURE_CAN_NOT_BE_BUILT_MSG, figureType.name(),
                                                        new ArrayList<>(Arrays.asList(coordinates)));
                        throw new PointArgumentException(errorMsg);
                    }

                    makeListOfCoordinates(pointFactory, coordinates, points);
                    Figure figure = buildFigures(pointFactory, points);
                    figureList.add(figure);
                } catch (PointArgumentException | FigureBuildException e) {
                    LOG.error(e);
                }
            }
            LOG.trace(String.format(RESULT_MSG, getNumberOfFiguresInFile(), getNumberOfBuiltFigures()));
        }
        return figureList;
    }

    private Figure buildFigures(FigureFactory figureFactory, List<Point> points) {
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
        return figure;
    }

    private void makeListOfCoordinates(PointFactory figureFactory, String[] coordinates, List<Point> points) {

        for (int i = 0; i < coordinates.length; i += 2) {
            points.add(figureFactory.newInstance(
                    Double.parseDouble(coordinates[i]),
                    Double.parseDouble(coordinates[i + 1]))
            );
        }
    }
}
