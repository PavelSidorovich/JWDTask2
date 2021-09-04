package com.epam.jwd.texthandling.service.parser;

public class TextParserFactory {

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
        TextParser textParser = ParagraphParser.getInstance();
        textParser.linkWith(SentenceParser.getInstance())
                  .linkWith(WordParser.getInstance());
        return textParser;
    }
}
