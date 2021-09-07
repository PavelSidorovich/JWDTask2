package com.epam.jwd.texthandling.service.sort;

import com.epam.jwd.texthandling.model.TextComponent;
import com.epam.jwd.texthandling.model.TextComposite;
import com.epam.jwd.texthandling.model.TextPart;
import com.epam.jwd.texthandling.service.parser.TextPartExtractor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortTextByParagraphs implements SortService {

    private static SortTextByParagraphs instance;

    private SortTextByParagraphs() {
    }

    public static SortTextByParagraphs getInstance() {
        if (instance == null) {
            instance = new SortTextByParagraphs();
        }
        return instance;
    }

    @Override
    public TextComposite sortText(TextComposite text, Comparator<TextComponent> comparator) {
        List<TextComponent> paragraphs = TextPartExtractor.extractParts(text, TextPart.PARAGRAPH);
        paragraphs = paragraphs.stream()
                               .sorted(comparator)
                               .collect(Collectors.toList());
        return new TextComposite(paragraphs, TextPart.TEXT);
    }
}
