package com.epam.jwd.figures.utils.action;

import com.epam.jwd.figures.model.Figure;
import com.epam.jwd.figures.model.rectangle.TypesOfQuadrangle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;

/**
 * The {@code QuadrangleActions} class contains some actions which can be applied only to quadrangles
 *
 * @author Pavel Sidorovich
 * @since 1.0
 * @see FigureActions
 */
public class QuadrangleActions extends FigureActions {

    private static final int NUMBER_OF_DIAGONALS = 2;
    private static final Logger LOGGER = LogManager.getLogger(QuadrangleActions.class);

    public QuadrangleActions(Figure figure) {
        super(figure);
    }

    public TypesOfQuadrangle defineTheType() {
        LinkedList<MathVector> vectors = getFigure().getVectors();
        LinkedList<MathVector> diagonals = new LinkedList<>();

        //special list(contains vectors of diagonals) for the diamond
        for (int i = 0; i < NUMBER_OF_DIAGONALS; i++) {
            diagonals.add(new MathVector(getFigure().getPoints().get(i),
                                         getFigure().getPoints().get(i + 2)));
        }

        if (distanceBetweenTwoPoints(getFigure().getPoints().get(0), getFigure().getPoints().get(1))
                    .equals(distanceBetweenTwoPoints(getFigure().getPoints().get(1),
                                                     getFigure().getPoints().get(2)))
            && distanceBetweenTwoPoints(getFigure().getPoints().get(2), getFigure().getPoints().get(3))
                    .equals(distanceBetweenTwoPoints(getFigure().getPoints().get(3),
                                                     getFigure().getPoints().get(0)))
            && distanceBetweenTwoPoints(getFigure().getPoints().get(0), getFigure().getPoints().get(1))
                    .equals(distanceBetweenTwoPoints(getFigure().getPoints().get(2),
                                                     getFigure().getPoints().get(3)))) {
            if (vectors.get(0).perpendicular(vectors.get(1))
                && vectors.get(2).perpendicular(vectors.get(3))) {
                LOGGER.info(getFigure() + ": " + TypesOfQuadrangle.SQUARE);
                return TypesOfQuadrangle.SQUARE;
            } else if (diagonals.get(0).perpendicular(diagonals.get(1))) {
                LOGGER.info(getFigure() + ": " + TypesOfQuadrangle.DIAMOND);
                return TypesOfQuadrangle.DIAMOND;
            }
        } else if (distanceBetweenTwoPoints(getFigure().getPoints().get(0), getFigure().getPoints().get(1))
                           .equals(distanceBetweenTwoPoints(getFigure().getPoints().get(2),
                                                            getFigure().getPoints().get(3)))
                   && distanceBetweenTwoPoints(getFigure().getPoints().get(1), getFigure().getPoints().get(2))
                           .equals(distanceBetweenTwoPoints(getFigure().getPoints().get(3),
                                                            getFigure().getPoints().get(0)))) {
            LOGGER.info(getFigure() + ": " + TypesOfQuadrangle.PARALLELOGRAM);
            return TypesOfQuadrangle.PARALLELOGRAM;
        } else if ((vectors.get(0).multiply(vectors.get(2)) == 0
                    && vectors.get(1).multiply(vectors.get(3)) != 0)
                   || (vectors.get(0).multiply(vectors.get(2)) != 0
                       && vectors.get(1).multiply(vectors.get(3)) == 0)) {
            LOGGER.info(getFigure() + ": " + TypesOfQuadrangle.TRAPEZOID);
            return TypesOfQuadrangle.TRAPEZOID;
        }
        LOGGER.info(getFigure() + ": " + TypesOfQuadrangle.ARBITRARY);
        return TypesOfQuadrangle.ARBITRARY;
    }
}
