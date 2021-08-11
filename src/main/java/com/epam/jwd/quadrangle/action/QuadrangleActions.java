package com.epam.jwd.quadrangle.action;

import com.epam.jwd.quadrangle.model.Figure;
import com.epam.jwd.quadrangle.model.MathVector;
import com.epam.jwd.quadrangle.model.QuadrangleType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code QuadrangleActions} class contains some actions which can be applied only to quadrangles
 *
 * @author Pavel Sidorovich
 * @see FigureActions
 * @since 1.0
 */
public class QuadrangleActions extends FigureActions {

    private static final int NUMBER_OF_DIAGONALS = 2;
    private static final Logger LOG = LogManager.getLogger(QuadrangleActions.class);

    public QuadrangleActions(Figure figure) {
        super(figure);
    }

    public QuadrangleType defineTheType() {
        List<MathVector> vectors = getFigure().getVectors();
        List<MathVector> diagonals = new ArrayList<>();

        //special list (contains vectors of diagonals) for the diamond
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
                LOG.info(getFigure() + ": " + QuadrangleType.SQUARE);
                return QuadrangleType.SQUARE;
            } else if (diagonals.get(0).perpendicular(diagonals.get(1))) {
                LOG.info(getFigure() + ": " + QuadrangleType.DIAMOND);
                return QuadrangleType.DIAMOND;
            }
        } else if (distanceBetweenTwoPoints(getFigure().getPoints().get(0), getFigure().getPoints().get(1))
                           .equals(distanceBetweenTwoPoints(getFigure().getPoints().get(2),
                                                            getFigure().getPoints().get(3)))
                   && distanceBetweenTwoPoints(getFigure().getPoints().get(1), getFigure().getPoints().get(2))
                           .equals(distanceBetweenTwoPoints(getFigure().getPoints().get(3),
                                                            getFigure().getPoints().get(0)))) {
            LOG.info(getFigure() + ": " + QuadrangleType.PARALLELOGRAM);
            return QuadrangleType.PARALLELOGRAM;
        } else if ((vectors.get(0).scalarProduct(vectors.get(2)) == 0
                    && vectors.get(1).scalarProduct(vectors.get(3)) != 0)
                   || (vectors.get(0).scalarProduct(vectors.get(2)) != 0
                       && vectors.get(1).scalarProduct(vectors.get(3)) == 0)) {
            LOG.info(getFigure() + ": " + QuadrangleType.TRAPEZOID);
            return QuadrangleType.TRAPEZOID;
        }
        LOG.info(getFigure() + ": " + QuadrangleType.ARBITRARY);
        return QuadrangleType.ARBITRARY;
    }
}
