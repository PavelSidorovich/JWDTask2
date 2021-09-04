package com.epam.jwd.texthandling.service.interpreter;

import com.epam.jwd.texthandling.exception.InvalidExpressionException;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReversePolishNotation {

    private static final String NUMBER_REGEX = "\\d+";
    private static final String OPERATOR_REGEX = "[~|&^()]|[<]{2}|[>]{2}";
    //private static final String exp_REGEX = "\\d+[~|&^)]|[<]{2}|[>]{2}";
    private static final String exp_REGEX = "\\d+([~|&^)]|[<]{2}|[>]{2}|$)";

    private static ReversePolishNotation instance;

    private ReversePolishNotation() {
    }

    public static ReversePolishNotation getInstance() {
        if (instance == null) {
            instance = new ReversePolishNotation();
        }
        return instance;
    }

    public List<String> process(String input) throws InvalidExpressionException {
        Matcher numberMatcher = Pattern.compile(NUMBER_REGEX).matcher(input);
        Matcher operatorMather = Pattern.compile(OPERATOR_REGEX).matcher(input);
        Map<Integer, String> values = new HashMap<>();
        List<String> output = new ArrayList<>();
        ArrayDeque<Operator> operatorStack = new ArrayDeque<>();

        try {
            extractValues(numberMatcher, values);
            checkInputExpression(input, values);
            extractValues(operatorMather, values);

            for (String value : values.values()) {
                if (numberMatcher.reset(value).matches()) {
                    output.add(value);
                } else if (operatorMather.reset(value).matches()) {
                    processIfValueIsOperator(output, operatorStack, value);
                }
            }
            addRemainedElements(output, operatorStack);
        } catch (Exception e) {
            throw new InvalidExpressionException(input);
        }
        return output;
    }

    private void checkInputExpression(String input, Map<Integer, String> values) throws InvalidExpressionException {
        int numberOfNumbers = values.size();
        if (!checkIfInputIsValid(numberOfNumbers, input)) {
            throw new InvalidExpressionException();
        }
    }

    private void addRemainedElements(List<String> output, ArrayDeque<Operator> operatorStack) {
        while (!operatorStack.isEmpty()) {
            output.add(operatorStack.pop().toString());
        }
    }

    private void processIfValueIsOperator(List<String> output, ArrayDeque<Operator> operatorStack, String value) {
        if ("~".equals(value) || "(".equals(value)) {
            operatorStack.push(Operator.getOperator(value));
        } else if (")".equals(value)) {
            addElementsToOutputUntilOpenBrace(output, operatorStack);
            deleteOpenBrace(operatorStack);
        } else {
            processIfOperatorIsBinary(output, operatorStack, value);
        }
    }

    private void addElementsToOutputUntilOpenBrace(List<String> output, ArrayDeque<Operator> operatorStack) {
        while (Operator.OPEN_BRACE != operatorStack.peek()) {
            output.add(operatorStack.pop().toString());
        }
    }

    private void deleteOpenBrace(ArrayDeque<Operator> operatorStack) {
        if (Operator.OPEN_BRACE == operatorStack.peek()) {
            operatorStack.pop();
        }
    }

    private void processIfOperatorIsBinary(List<String> output, ArrayDeque<Operator> operatorStack, String value) {
        while (!operatorStack.isEmpty() &&
               (Operator.TILDE == operatorStack.peek()
                || hasMorePriorityThanSecondOperator(operatorStack, value)
                || hasSamePriorityAndLeftAssociative(operatorStack, value))) {
            output.add(operatorStack.pop().toString());
        }
        operatorStack.push(Operator.getOperator(value));
    }

    private boolean hasSamePriorityAndLeftAssociative(ArrayDeque<Operator> operatorStack, String value) {
        return operatorStack.peek().getPriority() == Operator.getOperator(value).getPriority()
               && operatorStack.peek().isLeftAssociative();
    }

    private boolean hasMorePriorityThanSecondOperator(ArrayDeque<Operator> operatorStack, String value) {
        return operatorStack.peek().getPriority() > Operator.getOperator(value).getPriority();
    }

    private void extractValues(Matcher numberMatcher, Map<Integer, String> values) {
        while (numberMatcher.find()) {
            values.put(numberMatcher.start(), numberMatcher.group());
        }
    }

    private boolean checkIfInputIsValid(int number, String str) {
        Matcher matcher = Pattern.compile(exp_REGEX).matcher(str);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count == number;
    }
}
