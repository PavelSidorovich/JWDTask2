package com.epam.jwd.texthandling.service.parser;

import com.epam.jwd.texthandling.model.Symbol;
import com.epam.jwd.texthandling.model.TextComponent;
import com.epam.jwd.texthandling.model.TextComposite;
import com.epam.jwd.texthandling.model.TextPart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends TextParser {

    private static final Logger LOG = LogManager.getLogger(SentenceParser.class);

    private static final String SENTENCE_REGEX = "[.?!]+\\s?";
    private static final String SENTENCE_ENDING_REGEX = "[.?!]+";
    private static final String SENTENCE_PARSER_IS_WORKING_MSG =
            String.format("%s is working", SentenceParser.class);

    private static SentenceParser instance;

    private SentenceParser() {
    }

    public static SentenceParser getInstance() {
        if (instance == null) {
            instance = new SentenceParser();
        }
        return instance;
    }

    @Override
    public List<TextComponent> parse(String text) {
        LOG.trace(SENTENCE_PARSER_IS_WORKING_MSG);

        List<String> endingsOfSentences = getSentencesEndings(text);

        return makeSentencesWithEndings(text, endingsOfSentences);
    }

    private List<TextComponent> makeSentencesWithEndings(String text, List<String> endingsOfSentences) {
        String[] sentences = text.split(SENTENCE_REGEX);
        List<TextComponent> textComponents = new ArrayList<>();

        for (int i = 0; i < sentences.length; i++) {
            if (sentences[i].equals("\n")) {
                continue;
            }

            List<TextComponent> sentenceParts = parseNext(sentences[i]);
            if (!sentenceParts.isEmpty()) {
                // create sentence entity with its ending
                sentenceParts.add(new Symbol(endingsOfSentences.get(i)));
                textComponents.add(new TextComposite(sentenceParts, TextPart.SENTENCE));
            }
        }
        return textComponents;
    }

    private List<String> getSentencesEndings(String text) {
        Matcher matcher = Pattern.compile(SENTENCE_ENDING_REGEX).matcher(text);
        List<String> endingsOfSentences = new ArrayList<>();

        // remember all endings of sentences ("!", "?", "." or "...")
        while (matcher.find()) {
            endingsOfSentences.add(matcher.group());
        }
        return endingsOfSentences;
    }
}
