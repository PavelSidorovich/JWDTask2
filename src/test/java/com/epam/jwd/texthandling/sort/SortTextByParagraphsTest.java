package com.epam.jwd.texthandling.sort;

import com.epam.jwd.texthandling.model.TextComponent;
import com.epam.jwd.texthandling.model.TextComposite;
import com.epam.jwd.texthandling.model.TextPart;
import com.epam.jwd.texthandling.parser.TextParser;
import com.epam.jwd.texthandling.parser.TextParserFactory;
import com.epam.jwd.texthandling.reader.TextFile;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Comparator;

import static org.testng.Assert.*;

public class SortTextByParagraphsTest {

    private final SortTextByParagraphs sortService = SortTextByParagraphs.getInstance();
    private TextComposite textObject;

    @BeforeClass
    public void setUp() {
        TextFile fileProcessor = TextFile.getInstance();
        TextParser textParser = TextParserFactory.getInstance().of();
        String text = fileProcessor.read("src\\test\\resources\\text.txt");
        textObject = new TextComposite(textParser.parse(text), TextPart.TEXT);
    }

    @Test
    public void getInstance_shouldReturnInstanceOfClass_always() {
        assertNotNull(SortTextByParagraphs.getInstance());
        assertSame(SortTextByParagraphs.getInstance(), SortTextByParagraphs.getInstance());
    }

    @Test
    public void sortText_shouldSortParagraphsNumberOfSentences_whenTextConsistsOfParagraphs() {
        TextComposite sortedText = sortService.sort(textObject, Comparator.comparing(TextComponent::getLexemeLength));

        assertSame(sortedText.getType(), TextPart.TEXT);
        assertSame(sortedText.getParts().get(0).getType(), TextPart.PARAGRAPH);
        assertTrue(sortedText.getParts().get(0).getLexemeLength()
                   <= sortedText.getParts().get(1).getLexemeLength());
        assertTrue(sortedText.getParts().get(1).getLexemeLength()
                   <= sortedText.getParts().get(2).getLexemeLength());
        assertTrue(sortedText.getParts().get(2).getLexemeLength()
                   <= sortedText.getParts().get(3).getLexemeLength());
    }
}