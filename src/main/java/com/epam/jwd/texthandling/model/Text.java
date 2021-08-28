package com.epam.jwd.texthandling.model;

import java.util.ArrayList;
import java.util.List;

public class Text implements TextPart {

    private final List<TextPart> fromParts = new ArrayList<>();

    @Override
    public int countWords() {
        return fromParts.stream()
                        .mapToInt(TextPart::countWords)
                        .sum();
    }

//    public int countParagraphs() {
//        return countParts(Paragraph.class);
//    }
//
//    private int countParts(Class<?> partClass) {
//        int count = 0;
//        for (TextPart fromPart : fromParts) {
//            if (partClass.isInstance(fromPart)) {
//                count++;
//            }
//        }
//        return count;
//    }
}
