package com.epam.jwd.texthandling.service.interpreter;

import com.epam.jwd.texthandling.exception.UnknownOperatorException;

public enum Operator {
    BITWISE_AND("&", 8, true),
    BITWISE_EXCLUSIVE_OR("^", 7, true),
    BITWISE_INCLUSIVE_OR("|", 6, true),
    LEFT_SHIFT("<<", 11, true),
    RIGHT_SHIFT(">>", 11, false),
    TILDE("~", 14, true),

    // brace priority set to -1 because it should not be considered in ReversePolishNotation
    OPEN_BRACE("(", -1, true),
    CLOSE_BRACE(")", -1, true);

    private final String representation;
    private final int priority;
    private final boolean isLeftAssociative;

    Operator(String representation, int priority, boolean isLeftAssociative) {
        this.representation = representation;
        this.priority = priority;
        this.isLeftAssociative = isLeftAssociative;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isLeftAssociative() {
        return isLeftAssociative;
    }

    public static Operator getOperator(String string) {
        switch (string) {
        case "~":
            return Operator.TILDE;
        case "<<":
            return Operator.LEFT_SHIFT;
        case ">>":
            return Operator.RIGHT_SHIFT;
        case "&":
            return Operator.BITWISE_AND;
        case "|":
            return Operator.BITWISE_INCLUSIVE_OR;
        case "^":
            return Operator.BITWISE_EXCLUSIVE_OR;
        case "(":
            return Operator.OPEN_BRACE;
        case ")":
            return Operator.CLOSE_BRACE;
        }
        throw new UnknownOperatorException();
    }

    @Override
    public String toString() {
        return representation;
    }
}
