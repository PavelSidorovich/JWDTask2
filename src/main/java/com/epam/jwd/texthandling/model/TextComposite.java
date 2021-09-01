package com.epam.jwd.texthandling.model;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

public class TextComposite implements TextComponent, Cloneable { // represents sentence, paragraph or text

    // contains only 3 white spaces because word will produce another one
    private static final String PARAGRAPH_TABULATION = "   ";
    private static final String NEW_LINE = "\n";

    private final List<TextComponent> textComponents;
    private final TextPart type;

    public TextComposite(List<TextComponent> textComponents, TextPart type) {
        this.textComponents = textComponents;
        this.type = type;
    }

    public TextPart getType() {
        return type;
    }

    public List<TextComponent> getParts() {
        return new ArrayList<>(textComponents);
    }

    public void addPart(TextComponent textComponent) {
        textComponents.add(textComponent);
    }

    @Override
    public int getMaxWord() { // only for sentence
        if (type == TextPart.SENTENCE) {
            OptionalInt max = textComponents.stream().mapToInt(TextComponent::getLexemeLength).max();
            return max.getAsInt();
        }
        return 0;
    }

    @Override
    public int getLexemeLength() {
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

    @Override
    public TextComposite clone() {
        TextComposite clone = new TextComposite(new ArrayList<>(), type);
        for (TextComponent textComponent : textComponents) {
            clone.addPart(textComponent.clone());
        }
        return clone;
    }
}
