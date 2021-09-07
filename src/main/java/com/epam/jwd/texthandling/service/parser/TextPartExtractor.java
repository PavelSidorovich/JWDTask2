package com.epam.jwd.texthandling.service.parser;

import com.epam.jwd.texthandling.model.TextComponent;
import com.epam.jwd.texthandling.model.TextComposite;
import com.epam.jwd.texthandling.model.TextPart;

import java.util.ArrayList;
import java.util.List;

public class TextPartExtractor {

    public static List<TextComponent> extractParts(TextComposite text, TextPart type) {
        List<TextComponent> result = new ArrayList<>();

        extract(result, text.clone(), type);

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
}
