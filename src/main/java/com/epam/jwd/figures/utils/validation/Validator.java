package com.epam.jwd.figures.utils.validation;

import java.util.regex.Pattern;

public interface Validator {
    Pattern getPattern();

    default boolean isValid(String string) {
        return getPattern().matcher(string).matches();
    }
}
