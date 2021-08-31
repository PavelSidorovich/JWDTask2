package com.epam.jwd.texthandling.model;

public class Symbol implements TextComponent {

    private final String symbol; // symbol after word or sentence (",", ":", "?", "!", "." or "...")

    public Symbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public int getLength() {
        return 0;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
