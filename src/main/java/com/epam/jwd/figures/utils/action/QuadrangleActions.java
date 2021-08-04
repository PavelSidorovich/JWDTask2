package com.epam.jwd.figures.utils.action;

import com.epam.jwd.figures.model.Figure;
import com.epam.jwd.figures.model.rectangle.TypesOfQuadrangle;

import java.util.LinkedList;

public class QuadrangleActions extends FigureActions {

    private static final int NUMBER_OF_DIAGONALS = 2;

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
                return TypesOfQuadrangle.SQUARE;
            } else if (diagonals.get(0).perpendicular(diagonals.get(1))) {
                return TypesOfQuadrangle.DIAMOND;
            }
        } else if (distanceBetweenTwoPoints(getFigure().getPoints().get(0), getFigure().getPoints().get(1))
                           .equals(distanceBetweenTwoPoints(getFigure().getPoints().get(2),
                                                            getFigure().getPoints().get(3)))
                   && distanceBetweenTwoPoints(getFigure().getPoints().get(1), getFigure().getPoints().get(2))
                           .equals(distanceBetweenTwoPoints(getFigure().getPoints().get(3),
                                                            getFigure().getPoints().get(0)))) {
            return TypesOfQuadrangle.PARALLELOGRAM;
        } else if ((vectors.get(0).multiply(vectors.get(2)) == 0
                    && vectors.get(1).multiply(vectors.get(3)) != 0)
                   || (vectors.get(0).multiply(vectors.get(2)) != 0
                       && vectors.get(1).multiply(vectors.get(3)) == 0)) {
            return TypesOfQuadrangle.TRAPEZOID;
        }
        return TypesOfQuadrangle.ARBITRARY;
    }
}
