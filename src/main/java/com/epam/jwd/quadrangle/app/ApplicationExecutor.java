package com.epam.jwd.quadrangle.app;

import com.epam.jwd.quadrangle.model.Figure;
import com.epam.jwd.quadrangle.model.FigureSubscriber;
import com.epam.jwd.quadrangle.model.FigureType;
import com.epam.jwd.quadrangle.model.Point;
import com.epam.jwd.quadrangle.model.PointFactory;
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

public class ApplicationExecutor {

    private static final Logger LOG = LogManager.getLogger(ApplicationExecutor.class);
    private static final PointFactory POINT_FACTORY = PointFactory.getInstance();
    private static final QuadrangleFactory QUADRANGLE_FACTORY = QuadrangleFactory.getInstance();
    private static final URL url = Thread.currentThread().getContextClassLoader().getResource("quadrangles.txt");

    public static void run() {
        File file = new File(url.getPath());
        try {
            Scanner fileScanner = new Scanner(file);
            FigureReader figureReader = new FigureReader(FigureType.QUADRANGLE);
            List<Figure> quadrangles = figureReader.scanFigures(fileScanner);
            FigureRepository repository = new FigureRepository(quadrangles);

            repository.create(POINT_FACTORY.of(0, 0));
            repository.create(POINT_FACTORY.of(1, 2));
            repository.create(POINT_FACTORY.of(2, 30));

            FigureSubscriber subscriber1 = new FigureSubscriber();
            repository.getPublisher(0).subscribe(subscriber1);

            repository.create(POINT_FACTORY.of(100, 800));

            List<Point> points = new ArrayList<>();
            points.add(POINT_FACTORY.of(0, 0));
            points.add(POINT_FACTORY.of(10, 5));
            points.add(POINT_FACTORY.of(30, 5));
            points.add(POINT_FACTORY.of(10, 0));

            repository.update(0, QUADRANGLE_FACTORY.of(points));

            repository.delete(4);
        } catch (FileNotFoundException e) {
            LOG.error(e);
        }
    }
}
