package com.epam.jwd.texthandling.model;

import java.util.List;

public class Sentence implements TextPart {

    private final List<TextPart> words;
    private final String sentenceEnding;

    public Sentence(List<TextPart> words, String sentenceEnding) {
        this.words = words;
        this.sentenceEnding = sentenceEnding;
    }

    @Override
    public List<TextPart> getParts() {
        return words;
    }

    public String getSentenceEnding() {
        return sentenceEnding;
    }

    @Override
    public void addPart(TextPart textPart) {
        words.add(textPart);
    }

    @Override
    public int countWords() {
        return words.size();
    }
}
