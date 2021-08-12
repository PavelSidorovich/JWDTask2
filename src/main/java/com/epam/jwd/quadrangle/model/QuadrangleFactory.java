package com.epam.jwd.quadrangle.model;

import com.epam.jwd.quadrangle.exception.FigureBuildException;
import com.epam.jwd.quadrangle.validation.QuadrangleBuildValidator;

import java.util.List;

/**
 * The {@code QuadrangleFactory} class is a fabric designed to create {@link Point} objects
 *
 * @author Pavel Sidorovich
 * @see FigureFactory
 * @since 1.0
 */
public class QuadrangleFactory implements FigureFactory {

    private static final String LINES_ARE_CROSSING_MSG = "Lines are crossing! ";
    private static final String WRONG_NUMBER_OF_COORDINATES_MSG = "Wrong number of coordinates! ";
    private static final String ARGUMENTS_ERROR_MSG = "%s cannot be built from coordinates: %s";

    /**
     * Creates new instance of {@code Quadrangle} class
     *
     * @param pointList list of points
     * @return created object of {@code Quadrangle} class
     */
    @Override
    public Quadrangle newInstance(List<Point> pointList) {
        String errorMsg = String.format(ARGUMENTS_ERROR_MSG, FigureType.QUADRANGLE.name(), pointList);

        if (pointList.size() == FigureType.QUADRANGLE.getNumberOfPoints()) {
            if (new QuadrangleBuildValidator().creatable(pointList)) {
                return new Quadrangle(pointList);
            } else {
                throw new FigureBuildException(LINES_ARE_CROSSING_MSG + errorMsg);
            }
        } else {
            throw new FigureBuildException(WRONG_NUMBER_OF_COORDINATES_MSG + errorMsg);
        }
    }
}
