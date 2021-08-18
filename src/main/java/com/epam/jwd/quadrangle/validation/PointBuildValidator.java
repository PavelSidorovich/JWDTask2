package com.epam.jwd.quadrangle.validation;

import java.util.regex.Pattern;

/**
 * The {@code PointBuildValidator} class is designed to check the coordinate
 *
 * @author Pavel Sidorovich
 * @since 1.0
 */
public class PointBuildValidator implements Validator {
    private static final String DOUBLE_REG_EX = "[-+]?[0-9]*\\.?[0-9]+";

    private PointBuildValidator() {
    }

    private static PointBuildValidator validator;

    public static PointBuildValidator getInstance() {
        if (validator == null) {
            validator = new PointBuildValidator();
        }
        return validator;
    }

    @Override
    public Pattern getPattern() {
        return Pattern.compile(DOUBLE_REG_EX);
    }

}
