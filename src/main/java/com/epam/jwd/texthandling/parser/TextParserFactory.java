package com.epam.jwd.texthandling.parser;

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
        TextParser textParser = new ParagraphParser();
        textParser.linkWith(new SentenceParser())
                  .linkWith(new WordParser());
        return textParser;
    }
}
