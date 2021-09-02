package com.epam.jwd.texthandling.model;

import java.util.Objects;

public class Symbol implements TextComponent, Cloneable {

    private final String symbol; // symbol after word or sentence (",", ":", "?", "!", "." or "...")

    public Symbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public int getLexemeLength() {
        return 0;
    }

    @Override
    public String toString() {
        return symbol;
    }

    @Override
    public TextPart getType() {
        return TextPart.SYMBOL;
    }

    @Override
    public int getMaxWord() { // ignore
        return 0;
    }

    @Override
    public Symbol clone() {
        return new Symbol(symbol);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Symbol symbol1 = (Symbol) o;
        return Objects.equals(symbol, symbol1.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol);
    }
}
