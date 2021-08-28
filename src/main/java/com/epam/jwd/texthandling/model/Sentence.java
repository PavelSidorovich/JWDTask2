package com.epam.jwd.texthandling.model;

import java.util.ArrayList;
import java.util.List;

public class Sentence implements TextPart {

    private final List<Word> words = new ArrayList<>();

    @Override
    public int countWords() {
        return words.size();
    }
}
