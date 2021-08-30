package com.epam.jwd.texthandling.parser;

public abstract class Parser {
    private Parser next;

    public abstract boolean parse(String text);

    public Parser linkWith(Parser next){
        this.next = next;
        return next;
    }

    protected boolean parseNext() {
        if (next != null) {
            return true;
        }
        return next.parse();
    }
}
