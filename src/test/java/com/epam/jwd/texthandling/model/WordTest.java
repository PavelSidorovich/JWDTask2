package com.epam.jwd.texthandling.model;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class WordTest {

    private final Word word = new Word("Test");

    @Test
    public void getWord_shouldReturnString_always() {
        assertEquals(word.getWord(), "Test");
    }

    @Test
    public void getLexemeLength_shouldReturnTheLengthOfWord_always() {
        assertSame(word.getLexemeLength(), 4);
    }

    @Test
    public void getType_shouldReturnWordType_always() {
        assertSame(word.getType(), TextPart.WORD);
    }

    @Test
    public void getMaxWord_shouldReturn0_always() {
        assertSame(word.getMaxWord(), 0);
    }

    @Test
    public void testClone_shouldReturnClone_always() {
        assertEquals(word.clone(), word);
    }
}