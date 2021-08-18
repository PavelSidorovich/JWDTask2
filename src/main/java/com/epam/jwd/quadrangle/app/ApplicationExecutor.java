package com.epam.jwd.quadrangle.app;

import com.epam.jwd.quadrangle.exception.PointNumberException;
import com.epam.jwd.quadrangle.model.Figure;
import com.epam.jwd.quadrangle.model.FigurePublisher;
import com.epam.jwd.quadrangle.model.FigureType;
import com.epam.jwd.quadrangle.model.Point;
import com.epam.jwd.quadrangle.model.PointFactory;
import com.epam.jwd.quadrangle.model.Quadrangle;
import com.epam.jwd.quadrangle.model.QuadrangleFactory;
import com.epam.jwd.quadrangle.reader.FigureReader;
import com.epam.jwd.quadrangle.repository.FigureRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Simply demonstrates the features of FlowApi implementations
 */
public class ApplicationExecutor {

    private static final Logger LOG = LogManager.getLogger(ApplicationExecutor.class);
    private static final PointFactory POINT_FACTORY = PointFactory.getInstance();
    private static final QuadrangleFactory QUADRANGLE_FACTORY = QuadrangleFactory.getInstance();
    private static final URL url = Thread.currentThread().getContextClassLoader().getResource("quadrangles.txt");

    public static void run() {
        assert url != null;
        File file = new File(url.getPath());
        try {
            Scanner fileScanner = new Scanner(file);
            FigureReader figureReader = new FigureReader(FigureType.QUADRANGLE);
            List<Figure> quadrangles = figureReader.scanFigures(fileScanner);
            List<FigurePublisher> publishers = new ArrayList<>();
            for (Figure quadrangle : quadrangles) {
                publishers.add(new FigurePublisher(quadrangle));
            }

            FigureRepository repository = new FigureRepository(publishers);

            repository.create(POINT_FACTORY.publisherOf(0, 0));
            repository.create(POINT_FACTORY.publisherOf(1, 2));
            repository.create(POINT_FACTORY.publisherOf(2, 30));

            List<Point> points = new ArrayList<>();
            points.add(POINT_FACTORY.of(0, 0));
            points.add(POINT_FACTORY.of(10, 5));
            points.add(POINT_FACTORY.of(30, 5));
            points.add(POINT_FACTORY.of(10, 0));

            FigurePublisher editableQuadrangle = QUADRANGLE_FACTORY.publisherOf(points);
            FigurePublisher editablePoint = POINT_FACTORY.publisherOf(1000, 1000);

            repository.create(editablePoint);
            repository.create(editableQuadrangle);

            LOG.debug("{}", repository.read(10));
            LOG.debug("{}\n",repository.read(9));

            ((Point) editablePoint.getFigure()).setX(1);
            ((Quadrangle) editableQuadrangle.getFigure()).setPoint(2, POINT_FACTORY.of(1, 0));

            LOG.debug("{}", repository.read(10));
            LOG.debug("{}\n",repository.read(9));
        } catch (FileNotFoundException | PointNumberException e) {
            LOG.error(e);
        }
    }
}
