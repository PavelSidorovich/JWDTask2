package com.epam.jwd.texthandling.model;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class TextCompositeTest {

    private TextComposite textComposite;
    private final List<TextComponent> sentences = new ArrayList<>();

    @BeforeClass
    public void setUp() {
        List<TextComponent> words1 = new ArrayList<>();
        List<TextComponent> words2 = new ArrayList<>();

        words1.add(new Word("hello"));
        words1.add(new Word("car"));
        words1.add(new Symbol("..."));

        words2.add(new Word("Best"));
        words2.add(new Word("language"));
        words2.add(new Word("is"));
        words2.add(new Word("java"));
        words2.add(new Symbol("!"));

        TextComposite sentence1 = new TextComposite(words1, TextPart.SENTENCE);
        TextComposite sentence2 = new TextComposite(words2, TextPart.SENTENCE);

        sentences.add(sentence1);
        sentences.add(sentence2);

        textComposite = new TextComposite(sentences, TextPart.TEXT);
    }

    @Test
    public void getType_shouldReturnType_always() {
        assertSame(textComposite.getType(), TextPart.TEXT);
    }

    @Test
    public void getParts_shouldReturnTextParts_always() {
        assertEquals(textComposite.getParts(), sentences);
    }

    @Test
    public void addPart_shouldAddTextParts_always() {
        textComposite.addPart(new Word("add"));

        assertSame(textComposite.getParts().size(), 3);
    }

    @Test
    public void getMaxWord_shouldReturnTheLongestLengthOfAWord_always() {
        assertSame(textComposite.getParts().get(1).getMaxWord(), 8);
    }

    @Test
    public void getLexemeLength_shouldReturnTheLongestLengthOfASentence_always() {
        assertSame(textComposite.getParts().get(0).getLexemeLength(), 3);
    }

}