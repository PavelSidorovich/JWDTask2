package com.epam.jwd.quadrangle.validation;

import java.util.regex.Pattern;

/**
 * The {@code PointValidator} class is designed to check the coordinate
 *
 * @author Pavel Sidorovich
 * @since 1.0
 */
public class PointValidator implements Validator {
    private static final String DOUBLE_REG_EX = "[-+]?[0-9]*\\.?[0-9]+";

    @Override
    public Pattern getPattern() {
        return Pattern.compile(DOUBLE_REG_EX);
    }

}
