package com.epam.jwd.figures.utils.reader;

import com.epam.jwd.figures.model.Figure;
import com.epam.jwd.figures.model.FigureFabric;
import com.epam.jwd.figures.model.FigureTypes;
import com.epam.jwd.figures.model.point.Point;
import com.epam.jwd.figures.model.point.PointFabric;
import com.epam.jwd.figures.model.rectangle.QuadrangleFabric;
import com.epam.jwd.figures.utils.exceptions.FigureException;
import com.epam.jwd.figures.utils.exceptions.PointException;
import com.epam.jwd.figures.utils.validation.PointValidator;
import com.epam.jwd.figures.utils.validation.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class FigureReader {
    private static final Logger LOGGER = LogManager.getLogger(FigureReader.class);

    private final Validator validator = new PointValidator();
    private Scanner fileScanner;
    private int numberOfCoordinates;
    private FigureTypes figureType;

    public FigureReader(String filepath, FigureTypes figureType) {
        try {
            LOGGER.trace(String.format("Trying to open file <%s>", filepath));
            fileScanner = new Scanner(new File(filepath));
            this.figureType = figureType;
            this.numberOfCoordinates = 2 * figureType.getNumberOfPoints();
            LOGGER.info(String.format("Successfully opened file <%s>", filepath));
        } catch (FileNotFoundException e) {
            LOGGER.error(String.format("Error occurred while opening file <%s>", filepath));
        }
    }

    public LinkedList<? extends Figure> scanFigures() {
        LinkedList<Figure> figureList = new LinkedList<>();
        FigureFabric figureFabric;

        if (fileScanner != null && numberOfCoordinates > 0) {
            while (fileScanner.hasNext()) {
                String[] coordinates = fileScanner.nextLine().split(" ");
                LinkedList<Point> points = new LinkedList<>();

                try {
                    figureFabric = new PointFabric();

                    if (coordinates.length % 2 != 0) {
                        throw new PointException("Not enough coordinates to build a figure! ",
                                                 new LinkedList<>(Arrays.asList(coordinates)));
                    }
                    for (int i = 0; i < coordinates.length; i += 2) {
                        if (validator.isValid(coordinates[i])
                            && validator.isValid(coordinates[i + 1])) {
                            points.add(((PointFabric) figureFabric).newInstance(
                                    Double.parseDouble(coordinates[i]),
                                    Double.parseDouble(coordinates[i + 1]))
                            );
                        } else {
                            throw new PointException("Wrong coordinate! ",
                                                     new LinkedList<>(Arrays.asList(coordinates)));
                        }
                    }
                    Figure figure;
                    switch (figureType) {
                    case POINT:
                        figureFabric = new PointFabric();
                        break;
                    case LINE:
                    case TRIANGLE:
                        break;
                    case QUADRANGLE:
                        figureFabric = new QuadrangleFabric();
                        break;
                    }
                    figure = figureFabric.newInstance(points);
                    figureList.add(figure);
                } catch (PointException | FigureException e) {
                    LOGGER.error(e);
                }
            }
        }
        return figureList;
    }
}
