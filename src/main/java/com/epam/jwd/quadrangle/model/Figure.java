package com.epam.jwd.quadrangle.model;

import java.util.ArrayList;

/**
 * This interface was designed in case the hierarchy of the project increase.
 */
public interface Figure {
    int getId();

    Figure withId(int id);

    ArrayList<Point> getPoints();

    default int getNumberOfPoints() {
        return getPoints().size();
    }

    default ArrayList<MathVector> getVectors() {
        ArrayList<MathVector> vectors = new ArrayList<>();

        for (int i = 0; i < this.getNumberOfPoints() - 1; i++) {
            vectors.add(new MathVector(this.getPoints().get(i),
                                       this.getPoints().get(i + 1)));
        }
        vectors.add(new MathVector(this.getPoints().get(this.getNumberOfPoints() - 1),
                                   this.getPoints().get(0)));
        return vectors;
    }
}
