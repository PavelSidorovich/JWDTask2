package com.epam.jwd.texthandling.model;

public class Word implements TextComponent, Cloneable {

    private static final String WHITE_SPACE = " ";

    private final String word;

    public Word(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    @Override
    public int getLength() {
        return word.length();
    }

    @Override
    public String toString() {
        return WHITE_SPACE + word;
    }

    @Override
    public TextPart getType() {
        return TextPart.WORD;
    }

    @Override
    public int getMaxWord() {
        return 0;
    }

    @Override
    public Word clone() {
        return new Word(getWord());
    }

}
