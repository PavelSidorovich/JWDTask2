package com.epam.jwd.texthandling.service.sort;

import com.epam.jwd.texthandling.model.TextComponent;
import com.epam.jwd.texthandling.model.TextComposite;
import com.epam.jwd.texthandling.model.TextPart;
import com.epam.jwd.texthandling.service.parser.TextPartExtractor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Sorts text both by word and lexeme (sentences) length (specified by comparator)
 */
public class SortTextByLexemeLength implements SortService {

    private static SortTextByLexemeLength instance;

    private SortTextByLexemeLength() {
    }

    public static SortTextByLexemeLength getInstance() {
        if (instance == null) {
            instance = new SortTextByLexemeLength();
        }
        return instance;
    }

    @Override
    public TextComposite sortText(TextComposite text, Comparator<TextComponent> comparator) {
        List<TextComponent> sentences =
                TextPartExtractor.extractParts(text, TextPart.SENTENCE);

        sentences = sentences.stream()
                             .sorted(comparator)
                             .collect(Collectors.toList());
        return new TextComposite(sentences, TextPart.TEXT);
    }

}
