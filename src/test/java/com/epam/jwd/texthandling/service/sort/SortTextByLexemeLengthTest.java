package com.epam.jwd.texthandling.sort;

import com.epam.jwd.texthandling.model.TextComponent;
import com.epam.jwd.texthandling.model.TextComposite;
import com.epam.jwd.texthandling.model.TextPart;
import com.epam.jwd.texthandling.service.parser.TextParser;
import com.epam.jwd.texthandling.service.parser.TextParserFactory;
import com.epam.jwd.texthandling.service.reader.TextFile;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Comparator;

import static org.testng.Assert.*;

public class SortTextByLexemeLengthTest {

    private final SortTextByLexemeLength sortService = SortTextByLexemeLength.getInstance();
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
        assertNotNull(SortTextByLexemeLength.getInstance());
        assertSame(SortTextByLexemeLength.getInstance(), SortTextByLexemeLength.getInstance());
    }

    @Test
    public void sortText_shouldSortSentencesByLexemeLength_whenTextConsistsOfParagraphs() {
        TextComposite sortedText = sortService.sort(textObject, Comparator.comparing(TextComponent::getLexemeLength));

        assertSame(sortedText.getType(), TextPart.TEXT);
        assertSame(sortedText.getParts().get(0).getType(), TextPart.SENTENCE);
        assertTrue(sortedText.getParts().get(0).getLexemeLength()
                   <= sortedText.getParts().get(1).getLexemeLength());
        assertTrue(sortedText.getParts().get(1).getLexemeLength()
                   <= sortedText.getParts().get(2).getLexemeLength());
        assertTrue(sortedText.getParts().get(2).getLexemeLength()
                   <= sortedText.getParts().get(3).getLexemeLength());
    }

    @Test
    public void sortText_shouldSortSentencesByWordLength_whenTextConsistsOfParagraphs() {
        TextComposite sortedText = sortService.sort(textObject, Comparator.comparing(TextComponent::getMaxWord));

        assertSame(sortedText.getType(), TextPart.TEXT);
        assertSame(sortedText.getParts().get(0).getType(), TextPart.SENTENCE);
        assertTrue(sortedText.getParts().get(0).getMaxWord()
                   <= sortedText.getParts().get(1).getMaxWord());
        assertTrue(sortedText.getParts().get(1).getMaxWord()
                   <= sortedText.getParts().get(2).getMaxWord());
        assertTrue(sortedText.getParts().get(2).getMaxWord()
                   <= sortedText.getParts().get(3).getMaxWord());
    }

    @Test
    public void sortText_shouldReturnTheSameTextComposite_whenTextDoesNotConsistOfParagraphs() {
        TextComposite sortedText1 = sortService.sort(textObject, Comparator.comparing(TextComponent::getMaxWord));
        TextComposite sortedText2 = sortService.sort(sortedText1, Comparator.comparing(TextComponent::getMaxWord));

        assertSame(sortedText1, sortedText2);
    }
}