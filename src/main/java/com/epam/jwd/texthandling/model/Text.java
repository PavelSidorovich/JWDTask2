package com.epam.jwd.texthandling.model;

import java.util.List;

public class Text implements TextPart {

    private final List<TextPart> textParts;

    public Text(List<TextPart> textParts) {
        this.textParts = textParts;
    }

    @Override
    public List<TextPart> getParts() {
        return textParts;
    }

    public void addPart(TextPart textPart) {
        textParts.add(textPart);
    }

    @Override
    public int countWords() {
        return textParts.stream()
                        .mapToInt(TextPart::countWords)
                        .sum();
    }

}
