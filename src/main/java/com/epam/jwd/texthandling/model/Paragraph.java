package com.epam.jwd.texthandling.model;

import java.util.List;

public class Paragraph implements TextPart {

    private final List<TextPart> sentences;

    public Paragraph(List<TextPart> sentences) {
        this.sentences = sentences;
    }

    @Override
    public void addPart(TextPart textPart) {
        sentences.add(textPart);
    }

    @Override
    public List<TextPart> getParts() {
        return sentences;
    }

    @Override
    public int countWords() {
        return sentences.stream()
                        .mapToInt(TextPart::countWords)
                        .sum();
    }
}
