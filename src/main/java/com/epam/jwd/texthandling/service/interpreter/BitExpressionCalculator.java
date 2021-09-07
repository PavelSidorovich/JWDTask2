package com.epam.jwd.texthandling.service.interpreter;

import com.epam.jwd.texthandling.exception.UnknownOperatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Optional;

public class BitExpressionCalculator {

    private static final Logger LOG = LogManager.getLogger(BitExpressionCalculator.class);
    public static final String IGNORED_CONTEXT = "ignored";
    public static final String WRONG_EXPRESSION_MSG = "Wrong expression: %s";
    public static final String EXPRESSION_WAS_CALCULATED_MSG = "Expression %s was calculated: %d";

    private BitExpressionCalculator() {
    }

    public static Optional<Integer> calculate(String input) {
        try {
            List<String> values = ReversePolishNotation.getInstance().process(input);

            Expression expression = parse(values);

            int result = expression.interpret(IGNORED_CONTEXT);

            LOG.info(String.format(EXPRESSION_WAS_CALCULATED_MSG, input, result));
            return Optional.of(result);
        } catch (Exception e) {
            LOG.error(String.format(WRONG_EXPRESSION_MSG, input));
            return Optional.empty();
        }
    }

    private static Expression makeExpression(String value, ArrayDeque<Expression> stack) {
        Expression leftValue;
        Expression rightValue;

        try {
            switch (Operator.getOperator(value)) {
            case TILDE:
                leftValue = stack.pop();
                return new TildeExpression(leftValue);
            case LEFT_SHIFT:
                rightValue = stack.pop();
                leftValue = stack.pop();
                return new LeftShiftExpression(leftValue, rightValue);
            case RIGHT_SHIFT:
                rightValue = stack.pop();
                leftValue = stack.pop();
                return new RightShiftExpression(leftValue, rightValue);
            case BITWISE_AND:
                rightValue = stack.pop();
                leftValue = stack.pop();
                return new BitwiseAndExpression(leftValue, rightValue);
            case BITWISE_INCLUSIVE_OR:
                rightValue = stack.pop();
                leftValue = stack.pop();
                return new BitwiseInclusiveOrExpression(leftValue, rightValue);
            case BITWISE_EXCLUSIVE_OR:
                rightValue = stack.pop();
                leftValue = stack.pop();
                return new BitwiseExclusiveOrExpression(leftValue, rightValue);
            default:
                break;
            }
        } catch (UnknownOperatorException exception) {
            return new TerminalExpression(value);
        }
        throw new UnknownOperatorException();
    }

    private static Expression parse(List<String> input) {
        ArrayDeque<Expression> stack = new ArrayDeque<>();
        for (String value : input) {
            stack.push(makeExpression(value, stack));
        }
        return stack.pop();
    }
}
