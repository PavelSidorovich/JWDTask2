package com.epam.jwd.texthandling.model;

import java.util.ArrayList;
import java.util.List;

public class Paragraph implements TextPart {

    private final List<Sentence> sentences = new ArrayList<>();

    @Override
    public int countWords() {
        return sentences.stream()
                        .mapToInt(Sentence::countWords)
                        .sum();
    }
}
