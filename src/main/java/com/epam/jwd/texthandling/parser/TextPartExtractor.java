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
            result = makeSentencesLinkWithSymbols(result);
        } else {
            result = result.stream().filter(part -> part.getLength() != 0).collect(Collectors.toList());
        }

        return result;
    }

    private static void extract(List<TextComponent> result, TextComponent text, TextPart type) {
        if (text.getType() == type) {
            result.add(text);
        } else if (text.getType() == TextPart.SENTENCE
                   || text.getType() == TextPart.PARAGRAPH
                   || text.getType() == TextPart.TEXT) {
            for (TextComponent part : ((TextComposite) text).getParts()) {
                extract(result, part, type);
            }
        } else if (text.getType() == TextPart.SYMBOL
                   && text.toString().equals("!")
                   || text.toString().equals("?")
                   || text.toString().equals(".")
                   || text.toString().equals("...")) {
            result.add(text);
        }
    }

    private static List<TextComponent> makeSentencesLinkWithSymbols(List<TextComponent> parts) {
        List<TextComponent> sentences = new ArrayList<>();
        for (int i = 0; i < parts.size() - 1; i += 2) {
            TextComponent p = parts.get(i);
            ((TextComposite) p).addPart(parts.get(i + 1));
            sentences.add(p);
        }
        return sentences;
    }
}
