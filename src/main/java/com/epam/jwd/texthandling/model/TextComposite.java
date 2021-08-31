package com.epam.jwd.texthandling.model;

import java.util.List;

public class TextComposite implements TextComponent {

    // contains only 3 white spaces because word will produce another one
    private static final String PARAGRAPH_TABULATION = "   ";
    private static final String NEW_LINE = "\n";

    private final List<TextComponent> textComponents;
    private final TextPart type;

    public TextComposite(List<TextComponent> textComponents, TextPart type) {
        this.textComponents = textComponents;
        this.type = type;
    }

    public void addPart(TextComponent textComponent) {
        textComponents.add(textComponent);
    }

    public List<TextComponent> getParts() {
        return textComponents;
    }

    @Override
    public int getLength() {
        return textComponents.size();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (type == TextPart.PARAGRAPH) {
            stringBuilder.append(PARAGRAPH_TABULATION);
        }
        textComponents.forEach(stringBuilder::append);
        if (type == TextPart.PARAGRAPH) {
            stringBuilder.append(NEW_LINE);
        }
        return stringBuilder.toString();
    }
}
