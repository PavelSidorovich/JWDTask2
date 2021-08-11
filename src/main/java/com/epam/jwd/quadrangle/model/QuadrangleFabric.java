package com.epam.jwd.quadrangle.model;

import com.epam.jwd.quadrangle.exception.FigureException;
import com.epam.jwd.quadrangle.validation.QuadrangleValidator;

import java.util.LinkedList;

/**
 * The {@code QuadrangleFabric} class is a fabric designed to
 * create {@link Point} objects
 *
 * @author Pavel Sidorovich
 * @since 1.0
 * @see FigureFabric
 */
public class QuadrangleFabric implements FigureFabric {

    private static final String LINES_ARE_CROSSING_MSG = "Lines are crossing! ";
    private static final String WRONG_NUMBER_OF_COORDINATES_MSG = "Wrong number of coordinates! ";
    private static final String ARGUMENTS_ERROR_MSG = "%s cannot be built on %s coordinates: %s";

    /**
     * Creates new instance of {@code Quadrangle} class
     * @param pointList list of points
     * @return created object of {@code Quadrangle} class
     */
    @Override
    public Quadrangle newInstance(LinkedList<Point> pointList) {
        if (pointList.size() == FigureType.QUADRANGLE.getNumberOfPoints()) {
            if (new QuadrangleValidator(pointList).canBeBuilt()) {
                return new Quadrangle(pointList);
            } else {
                throw new FigureException(LINES_ARE_CROSSING_MSG + ARGUMENTS_ERROR_MSG,
                                          Quadrangle.class, pointList);
            }
        } else {
            throw new FigureException(WRONG_NUMBER_OF_COORDINATES_MSG + ARGUMENTS_ERROR_MSG,
                                      Quadrangle.class, pointList);
        }
    }
}
