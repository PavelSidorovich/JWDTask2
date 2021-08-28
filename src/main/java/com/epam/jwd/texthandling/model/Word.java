package com.epam.jwd.texthandling.model;

public class Word implements TextPart {

    private String word;

    @Override
    public int countWords() {
        return 1;
    }
}
