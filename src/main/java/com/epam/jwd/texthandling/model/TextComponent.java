package com.epam.jwd.texthandling.model;

public interface TextComponent {

    int getLength();

    String toString();

    TextPart getType();

    int getMaxWord();

    TextComponent clone();

}
