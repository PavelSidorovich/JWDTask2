package com.epam.jwd.texthandling.model;

public interface TextComponent {

    String toString();

    TextPart getType();

    /**
     * Lexeme - text part, surrounded by spaces
     *
     * @return lexeme length
     */
    int getLexemeLength();

    int getMaxWord();

    TextComponent clone();

}
