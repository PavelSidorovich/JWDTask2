package com.epam.jwd.texthandling.service.interpreter;

@FunctionalInterface
public interface Expression {
    int interpret(String context);
}
