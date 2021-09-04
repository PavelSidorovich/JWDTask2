package com.epam.jwd.texthandling.service.interpreter;

public class TildeExpression implements Expression {

    private final Expression expr1;

    public TildeExpression(Expression expr1) {
        this.expr1 = expr1;
    }

    @Override
    public int interpret(String context) {
        return ~expr1.interpret(context);
    }

}
