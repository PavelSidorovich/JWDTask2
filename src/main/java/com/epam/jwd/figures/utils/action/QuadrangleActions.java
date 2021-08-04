package com.epam.jwd.figures.utils.action;

import com.epam.jwd.figures.model.Figure;
import com.epam.jwd.figures.model.FigureTypes;
import com.epam.jwd.figures.model.rectangle.TypesOfQuadrangle;

import java.util.LinkedList;

public class QuadrangleActions extends FigureActions {

    private static final int NUMBER_OF_DIAGONALS = 2;

    public QuadrangleActions(Figure figure) {
        super(figure);
    }

    public TypesOfQuadrangle defineTheType() {
        LinkedList<MathVector> vectors = new LinkedList<>();
        LinkedList<MathVector> diagonals = new LinkedList<>();

        for (int i = 0; i < FigureTypes.QUADRANGLE.getNumberOfPoints(); i++) {
            if (i != FigureTypes.QUADRANGLE.getNumberOfPoints() - 1) {
                vectors.add(new MathVector(getFigure().getPoints().get(i),
                                           getFigure().getPoints().get(i + 1)));
            } else {
                vectors.add(new MathVector(getFigure().getPoints().get(i),
                                           getFigure().getPoints().get(0)));
            }
        }

        //special list(contains vectors of diagonals) for the diamond
        for (int i = 0; i < NUMBER_OF_DIAGONALS; i++) {
            diagonals.add(new MathVector(getFigure().getPoints().get(i),
                                         getFigure().getPoints().get(i + 2)));
        }

        if (vectors.get(0).multiply(vectors.get(2)) == 0
            && vectors.get(1).multiply(vectors.get(3)) == 0) {
            return TypesOfQuadrangle.SQUARE;
        } else if (vectors.get(0).multiply(vectors.get(2)) == 0
                   || vectors.get(1).multiply(vectors.get(3)) == 0) {
            return TypesOfQuadrangle.TRAPEZOID;
        } else if (diagonals.get(0).multiply(diagonals.get(1)) == 0
                   && (distanceBetweenTwoPoints(getFigure().getPoints().get(0), getFigure().getPoints().get(1))
                               .equals(distanceBetweenTwoPoints(getFigure().getPoints().get(1),
                                                                getFigure().getPoints().get(2)))
                       && distanceBetweenTwoPoints(getFigure().getPoints().get(2), getFigure().getPoints().get(3))
                               .equals(distanceBetweenTwoPoints(getFigure().getPoints().get(3),
                                                                getFigure().getPoints().get(0)))
                       && distanceBetweenTwoPoints(getFigure().getPoints().get(0), getFigure().getPoints().get(1))
                               .equals(distanceBetweenTwoPoints(getFigure().getPoints().get(2),
                                                                getFigure().getPoints().get(3))))) {
            return TypesOfQuadrangle.DIAMOND;
        } else {
            return TypesOfQuadrangle.ARBITRARY;
        }
    }
}
