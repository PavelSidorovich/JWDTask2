package com.epam.jwd.texthandling.parser;

import com.epam.jwd.texthandling.model.Sentence;
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
    private static final String SENTENCE_ENDING_REGEX = "[.?!]";
    private static final String SENTENCE_PARSER_IS_WORKING_MSG =
            String.format("%s is working", SentenceParser.class);

    @Override
    public List<TextPart> parse(String text) {
        LOG.trace(SENTENCE_PARSER_IS_WORKING_MSG);

        List<String> endingsOfSentences = getSentencesEndings(text);

        return makeSentencesWithEndings(text, endingsOfSentences);
    }

    private List<TextPart> makeSentencesWithEndings(String text, List<String> endingsOfSentences) {
        String[] sentences = text.split(SENTENCE_REGEX);
        List<TextPart> textParts = new ArrayList<>();

        for (int i = 0; i < sentences.length; i++) {
            if (sentences[i].equals("\n")) {
                continue;
            }
            // create all sentences entities with their endings
            textParts.add(new Sentence(parseNext(sentences[i]),
                                       endingsOfSentences.get(i)));
        }
        return textParts;
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
