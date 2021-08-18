package com.epam.jwd.quadrangle.repository.search;

import com.epam.jwd.quadrangle.model.Figure;
import com.epam.jwd.quadrangle.model.PointFactory;
import com.epam.jwd.quadrangle.repository.FigureRepository;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class FindByFigureIdTest {

    private final FigureRepository figureRepository = new FigureRepository();
    private final PointFactory pointFactory = PointFactory.getInstance();

    @BeforeClass
    public void setUp() {
        figureRepository.create(pointFactory.publisherOf(1, 1));
        figureRepository.create(pointFactory.publisherOf(2, 2));
        figureRepository.create(pointFactory.publisherOf(3, 3));
        figureRepository.create(pointFactory.publisherOf(4, 4));
    }

    @Test(dataProvider = "SpecificationProvider")
    public void exists_shouldReturnTrue_ifFitsTheCondition(SearchSpecification<Figure> specification, int result) {
        List<Figure> figureList = figureRepository.findBySpecification(specification);

        assertNotNull(figureList);
        assertSame(figureList.size(), result);
    }

    @DataProvider(name = "SpecificationProvider")
    public Object[][] getSpecificationFromProvider() {
        return new Object[][] {
                { new FindByFigureId(2), 1 },
                { new FindByFigureId(10), 0 },
        };
    }
}