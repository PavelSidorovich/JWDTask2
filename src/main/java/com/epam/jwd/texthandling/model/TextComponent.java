package com.epam.jwd.texthandling.model;

public interface TextComponent {

    String toString();

    TextPart getType();

    int getLexemeLength();

    int getMaxWord();

    TextComponent clone();

}
