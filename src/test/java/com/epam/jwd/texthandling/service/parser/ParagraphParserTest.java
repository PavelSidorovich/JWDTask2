package com.epam.jwd.texthandling.service.parser;

import com.epam.jwd.texthandling.model.TextPart;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ParagraphParserTest {

    private final ParagraphParser parser = ParagraphParser.getInstance();

    @Test
    public void getInstance_shouldReturnInstanceOfClass_always() {
        assertNotNull(ParagraphParser.getInstance());
        assertSame(ParagraphParser.getInstance(), ParagraphParser.getInstance());
    }

    @Test
    public void parse_shouldReturnListOfParagraphs_always() {
        parser.linkWith(SentenceParser.getInstance())
              .linkWith(WordParser.getInstance());
        String text = "It is a (^5|1&2<<(2|5>>2&71))|1200 established " +
                      "fact that a reader will be of a page when\n" +
                      "looking at its layout...";

        assertNotNull(parser.parse(text));
        assertSame(parser.parse(text).size(), 1);
        assertSame(parser.parse(text).get(0).getType(), TextPart.PARAGRAPH);
    }
}