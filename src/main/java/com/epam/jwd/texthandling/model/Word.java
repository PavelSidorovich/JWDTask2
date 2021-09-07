package com.epam.jwd.texthandling.model;

import java.util.Objects;

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
    public int getLexemeLength() {
        return word.length();
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

    @Override
    public String toString() {
        return WHITE_SPACE + word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Word word1 = (Word) o;
        return Objects.equals(word, word1.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word);
    }
}
