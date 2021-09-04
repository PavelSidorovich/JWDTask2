package com.epam.jwd.texthandling.service.parser;

import com.epam.jwd.texthandling.model.TextComponent;

import java.util.Collections;
import java.util.List;

public abstract class TextParser {

    private TextParser next;

    public abstract List<TextComponent> parse(String text);

    public TextParser linkWith(TextParser next) {
        this.next = next;
        return next;
    }

    protected List<TextComponent> parseNext(String text) {
        if (next == null) {
            return Collections.emptyList();
        }
        return next.parse(text);
    }
}
