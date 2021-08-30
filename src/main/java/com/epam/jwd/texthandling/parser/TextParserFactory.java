package com.epam.jwd.texthandling.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextParserFactory {

    private static final Logger LOG = LogManager.getLogger(TextParserFactory.class);

    private static final String UNEXPECTED_VALUE_OF_PARSE_LEVEL = "Unexpected value of parse level: %s";

    private static TextParserFactory factory;

    private TextParserFactory() {
    }

    public static TextParserFactory getInstance() {
        if (factory == null) {
            factory = new TextParserFactory();
        }
        return factory;
    }

    public TextParser of() {
        TextParser textParser = new ParagraphParser();
        textParser.linkWith(new SentenceParser())
                  .linkWith(new WordParser());
        return textParser;
    }
}
