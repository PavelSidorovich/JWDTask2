package com.epam.jwd.texthandling.model;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Word implements TextPart {

    private static final String WORD_WITH_SYMBOLS_REGEX = "(.+)([,:]?)";

    private String word;
    private String symbol; // symbol after word ("," or ":")

    public Word(String word) {
        extractSymbols(word);
    }

    public String getWord() {
        return word + symbol;
    }

    public String getWordWithoutSymbols() {
        return word;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public List<TextPart> getParts() { // nothing to return
        return Collections.emptyList();
    }

    @Override
    public void addPart(TextPart textPart) { // ignore
    }

    @Override
    public int countWords() {
        return 1;
    }

    private void extractSymbols(String word) {
        Matcher matcher = Pattern.compile(WORD_WITH_SYMBOLS_REGEX).matcher(word);

        while (matcher.find()) {
            this.word = matcher.group(1);
            this.symbol = matcher.group(2);
        }
    }
}
