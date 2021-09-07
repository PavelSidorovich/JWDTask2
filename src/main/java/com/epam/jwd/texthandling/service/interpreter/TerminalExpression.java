package com.epam.jwd.texthandling.service.interpreter;

public class TerminalExpression implements Expression {

    private final String data;

    public TerminalExpression(String data) {
        this.data = data;
    }

    /**
     * @param context ignored
     * @return number
     */
    @Override
    public int interpret(String context) {
        return Integer.parseInt(data);
    }
}
