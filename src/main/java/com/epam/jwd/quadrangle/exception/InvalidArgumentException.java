package com.epam.jwd.quadrangle.exception;

public class InvalidArgumentException extends RuntimeException {

    public InvalidArgumentException() {
    }

    public InvalidArgumentException(String message) {
        super(message);
    }
}
