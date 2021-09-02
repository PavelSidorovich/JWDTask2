package com.epam.jwd.texthandling.parser;

import com.epam.jwd.texthandling.model.TextComponent;
import com.epam.jwd.texthandling.model.TextComposite;
import com.epam.jwd.texthandling.model.TextPart;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TextPartExtractor {

    public static List<TextComponent> extractParts(TextComposite text, TextPart type) {
        List<TextComponent> result = new ArrayList<>();

        extract(result, text.clone(), type);

        if (type == TextPart.SENTENCE) {
            result = linkSentencesWithSymbols(result);
        } else {
            result = result.stream().filter(part -> part.getLexemeLength() != 0).collect(Collectors.toList());
        }

        return result;
    }

    /**
     * Recursively extracts text parts of provided type from text
     *
     * @param result result list
     * @param text   text
     * @param type   type to be extracted
     */
    private static void extract(List<TextComponent> result, TextComponent text, TextPart type) {
        if (text.getType() == type) {
            result.add(text);
        } else if (text.getType() == TextPart.SENTENCE // if one of these types -> extract their parts
                   || text.getType() == TextPart.PARAGRAPH
                   || text.getType() == TextPart.TEXT) {
            for (TextComponent part : ((TextComposite) text).getParts()) {
                extract(result, part, type);
            }
        } else if (text.getType() == TextPart.SYMBOL
                   && ("!".equals(text.toString())
                       || "?".equals(text.toString())
                       || ".".equals(text.toString())
                       || "...".equals(text.toString()))) {
            // It is needed to include symbols in a result so that sentences remain with their endings
            result.add(text);
        }
    }

    /**
     * Method links sentences with symbols on a "word" level. It is needed to make right sort of sentences
     */
    private static List<TextComponent> linkSentencesWithSymbols(List<TextComponent> parts) {
        List<TextComponent> sentences = new ArrayList<>();
        for (int i = 0; i < parts.size() - 1; i += 2) {
            TextComponent p = parts.get(i);
            ((TextComposite) p).addPart(parts.get(i + 1));
            sentences.add(p);
        }
        return sentences;
    }
}
