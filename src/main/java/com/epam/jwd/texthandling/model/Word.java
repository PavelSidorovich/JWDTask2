package com.epam.jwd.texthandling.model;

public class Word implements TextComponent {


    private static final String WHITE_SPACE = " ";

    private final String word;

    public Word(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    @Override
    public int getLength() {
        return 1;
    }

    @Override
    public String toString() {
        return WHITE_SPACE + word;
    }

    //    private void extractSymbols(String word) {
//        Matcher matcher = Pattern.compile(WORD_WITH_SYMBOLS_REGEX).matcher(word);
//
//        while (matcher.find()) {
//            this.word = matcher.group(1);
//            this.symbol = matcher.group(2);
//        }
//    }
}
