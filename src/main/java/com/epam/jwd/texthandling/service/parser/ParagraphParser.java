package com.epam.jwd.texthandling.service.parser;

import com.epam.jwd.texthandling.model.TextComponent;
import com.epam.jwd.texthandling.model.TextComposite;
import com.epam.jwd.texthandling.model.TextPart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ParagraphParser extends TextParser {

    private static final Logger LOG = LogManager.getLogger(ParagraphParser.class);

    private static final String TABULATION_REGEX = "[ ]{4}";
    private static final String PARAGRAPH_REGEX = "\n[ ]{4}";
    private static final String PARAGRAPH_PARSER_IS_WORKING_MSG = String.format("%s is working", ParagraphParser.class);

    private static ParagraphParser instance;

    private ParagraphParser() {
    }

    public static ParagraphParser getInstance() {
        if (instance == null) {
            instance = new ParagraphParser();
        }
        return instance;
    }

    @Override
    public List<TextComponent> parse(String text) {
        LOG.trace(PARAGRAPH_PARSER_IS_WORKING_MSG);

        String[] paragraphs = text.split(PARAGRAPH_REGEX);
        List<TextComponent> paragraphList = new ArrayList<>();

        //need to get rid of 4 spaces in the beginning of the first paragraph (due to split method)
        paragraphs[0] = paragraphs[0].replaceAll(TABULATION_REGEX, "");

        for (String paragraph : paragraphs) {
            paragraphList.add(
                    new TextComposite(parseNext(paragraph), TextPart.PARAGRAPH)); // add paragraphs into text entity
        }
        return paragraphList;
    }
}
