package com.epam.jwd.texthandling.parser;

import com.epam.jwd.texthandling.model.TextPart;

import java.util.Collections;
import java.util.List;

public abstract class TextParser {

    private TextParser next;

    public abstract List<TextPart> parse(String text);

    public TextParser linkWith(TextParser next) {
        this.next = next;
        return next;
    }

    protected List<TextPart> parseNext(String text) {
        if (next == null) {
            return Collections.emptyList();
        }
        return next.parse(text);
    }
}
