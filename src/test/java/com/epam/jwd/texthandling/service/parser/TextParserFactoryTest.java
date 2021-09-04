package com.epam.jwd.texthandling.parser;

import com.epam.jwd.texthandling.service.parser.ParagraphParser;
import com.epam.jwd.texthandling.service.parser.SentenceParser;
import com.epam.jwd.texthandling.service.parser.TextParser;
import com.epam.jwd.texthandling.service.parser.TextParserFactory;
import com.epam.jwd.texthandling.service.parser.WordParser;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TextParserFactoryTest {

    private final TextParserFactory parserFactory = TextParserFactory.getInstance();

    @Test
    public void getInstance_shouldReturnInstanceOfClass_always() {
        assertNotNull(TextParserFactory.getInstance());
        assertSame(TextParserFactory.getInstance(), TextParserFactory.getInstance());
    }

    @Test
    public void of_shouldReturnChainOfParsers_always() {
        TextParser textParser = ParagraphParser.getInstance();
        textParser.linkWith(SentenceParser.getInstance())
                  .linkWith(WordParser.getInstance());

        assertNotNull(parserFactory.of());
        assertEquals(parserFactory.of(), textParser);
    }
}