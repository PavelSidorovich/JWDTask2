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
 * @see FigureActions2D
 * @since 1.0
 */
public class QuadrangleActions extends FigureActions2D {

    private static final Logger LOG = LogManager.getLogger(QuadrangleActions.class);

    private static final int NUMBER_OF_DIAGONALS = 2;

    public QuadrangleActions(Figure figure) {
        super(figure);
    }

    public QuadrangleType defineTheType() {
        List<MathVector> vectors = getFigure().getVectors();
        List<MathVector> diagonals = getMathVectorsOfDiagonals();

        if (sidesOfTheFigureAreParallel()) {
            if (sidesOfTheFigureArePerpendicular(vectors)) {
                LOG.info(getFigure() + ": " + QuadrangleType.SQUARE);
                return QuadrangleType.SQUARE;
            } else if (diagonals.get(0).perpendicular(diagonals.get(1))) {
                LOG.info(getFigure() + ": " + QuadrangleType.DIAMOND);
                return QuadrangleType.DIAMOND;
            }
        } else if (oppositeSidesOfFigureAreEqual()) {
            LOG.info(getFigure() + ": " + QuadrangleType.PARALLELOGRAM);
            return QuadrangleType.PARALLELOGRAM;
        } else if (twoSidesOfFigureAreParallelTwoOthersNot(vectors)) {
            LOG.info(getFigure() + ": " + QuadrangleType.TRAPEZOID);
            return QuadrangleType.TRAPEZOID;
        }
        LOG.info(getFigure() + ": " + QuadrangleType.ARBITRARY);
        return QuadrangleType.ARBITRARY;
    }

    private List<MathVector> getMathVectorsOfDiagonals() {
        List<MathVector> diagonals = new ArrayList<>();

        //special list (contains vectors of diagonals) for the diamond
        for (int i = 0; i < NUMBER_OF_DIAGONALS; i++) {
            diagonals.add(new MathVector(getFigure().getPoints().get(i),
                                         getFigure().getPoints().get(i + 2)));
        }
        return diagonals;
    }

    private boolean twoSidesOfFigureAreParallelTwoOthersNot(List<MathVector> vectors) {
        return (vectors.get(0).scalarProduct(vectors.get(2)) == 0
                && vectors.get(1).scalarProduct(vectors.get(3)) != 0)
               || (vectors.get(0).scalarProduct(vectors.get(2)) != 0
                   && vectors.get(1).scalarProduct(vectors.get(3)) == 0);
    }

    private boolean oppositeSidesOfFigureAreEqual() {
        return distance(getFigure().getPoints().get(0), getFigure().getPoints().get(1))
                       .equals(distance(getFigure().getPoints().get(2),
                                        getFigure().getPoints().get(3)))
               && distance(getFigure().getPoints().get(1), getFigure().getPoints().get(2))
                       .equals(distance(getFigure().getPoints().get(3),
                                        getFigure().getPoints().get(0)));
    }

    private boolean sidesOfTheFigureArePerpendicular(List<MathVector> vectors) {
        return vectors.get(0).perpendicular(vectors.get(1))
               && vectors.get(2).perpendicular(vectors.get(3));
    }

    private boolean sidesOfTheFigureAreParallel() {
        return distance(getFigure().getPoints().get(0), getFigure().getPoints().get(1))
                       .equals(distance(getFigure().getPoints().get(1),
                                        getFigure().getPoints().get(2)))
               && distance(getFigure().getPoints().get(2), getFigure().getPoints().get(3))
                       .equals(distance(getFigure().getPoints().get(3),
                                        getFigure().getPoints().get(0)))
               && distance(getFigure().getPoints().get(0), getFigure().getPoints().get(1))
                       .equals(distance(getFigure().getPoints().get(2),
                                        getFigure().getPoints().get(3)));
    }
}
