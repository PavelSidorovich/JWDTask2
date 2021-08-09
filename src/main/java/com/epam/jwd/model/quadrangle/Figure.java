package com.epam.jwd.model.quadrangle;

import com.epam.jwd.utils.action.MathVector;

import java.util.LinkedList;

/**
 * This interface was designed in case the hierarchy of the project increase.
 */
public interface Figure {
    int getNumberOfPoints();

    LinkedList<Point> getPoints();

    default LinkedList<MathVector> getVectors() {
        LinkedList<MathVector> vectors = new LinkedList<>();

        for (int i = 0; i < this.getNumberOfPoints(); i++) {
            if (i != this.getNumberOfPoints() - 1) {
                vectors.add(new MathVector(this.getPoints().get(i),
                                           this.getPoints().get(i + 1)));
            } else {
                vectors.add(new MathVector(this.getPoints().get(i),
                                           this.getPoints().get(0)));
            }
        }
        return vectors;
    }
}
