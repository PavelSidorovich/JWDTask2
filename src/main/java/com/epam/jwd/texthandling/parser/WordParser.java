package com.epam.jwd.texthandling.parser;

import com.epam.jwd.texthandling.model.TextPart;
import com.epam.jwd.texthandling.model.Word;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class WordParser extends TextParser {

    private static final Logger LOG = LogManager.getLogger(WordParser.class);

    private static final String WORD_WITH_SYMBOLS_REGEX = "\\s";
    private static final String WORD_IS_WORKING_MSG = String.format("%s is working", WordParser.class);

    @Override
    public List<TextPart> parse(String text) {
        LOG.trace(WORD_IS_WORKING_MSG);

        String[] words = text.split(WORD_WITH_SYMBOLS_REGEX);
        List<TextPart> wordList = new ArrayList<>();

        for (String word : words) {
            wordList.add(new Word(word));
        }
        return wordList;
    }
}
