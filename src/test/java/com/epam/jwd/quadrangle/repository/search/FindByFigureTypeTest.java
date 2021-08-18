package com.epam.jwd.quadrangle.repository.search;

import com.epam.jwd.quadrangle.model.Figure;
import com.epam.jwd.quadrangle.model.FigurePublisher;
import com.epam.jwd.quadrangle.model.FigureType;
import com.epam.jwd.quadrangle.model.PointFactory;
import com.epam.jwd.quadrangle.reader.FigureReader;
import com.epam.jwd.quadrangle.repository.FigureRepository;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import static org.testng.Assert.*;

public class FindByFigureTypeTest {

    private final FindByFigureType specification = new FindByFigureType(FigureType.POINT);
    private final FigureRepository figureRepository = new FigureRepository();

    @BeforeClass
    public void setUp() throws FileNotFoundException {
        URL url = Thread.currentThread().getContextClassLoader().getResource("quadrangles.txt");
        assert url != null;
        File file = new File(url.getPath());
        Scanner fileScanner = new Scanner(file);
        FigureReader figureReader = new FigureReader(FigureType.QUADRANGLE);
        List<Figure> quadrangles = figureReader.scanFigures(fileScanner);
        PointFactory pointFactory = PointFactory.getInstance();
        figureRepository.create(new FigurePublisher(quadrangles.get(0)));
        figureRepository.create(new FigurePublisher(quadrangles.get(1)));
        figureRepository.create(new FigurePublisher(quadrangles.get(2)));
        figureRepository.create(new FigurePublisher(quadrangles.get(3)));
        figureRepository.create(new FigurePublisher(quadrangles.get(4)));
        figureRepository.create(new FigurePublisher(quadrangles.get(5)));

        figureRepository.create(pointFactory.publisherOf(0, 0));
        figureRepository.create(pointFactory.publisherOf(1, 2));
        figureRepository.create(pointFactory.publisherOf(2, 30));
    }

    @Test
    public void exists_shouldReturnTrue_ifFitsTheCondition() {
        List<Figure> figureList = figureRepository.findBySpecification(specification);

        assertNotNull(figureList);
        assertSame(figureList.size(), 3);
    }
}