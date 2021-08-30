package com.epam.jwd.texthandling.model;

import java.util.List;

public interface TextPart {
    int countWords();

    void addPart(TextPart textPart);

    List<TextPart> getParts();
}
