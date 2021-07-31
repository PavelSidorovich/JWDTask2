package com.epam.jwd.figures.utils.validation;

import java.util.regex.Pattern;

public class PointValidator implements Validator {
    @Override
    public Pattern getPattern() {
        return Pattern.compile("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?");
    }

}
