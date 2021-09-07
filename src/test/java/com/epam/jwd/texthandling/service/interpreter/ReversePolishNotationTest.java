package com.epam.jwd.texthandling.service.interpreter;

import com.epam.jwd.texthandling.exception.InvalidExpressionException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class ReversePolishNotationTest {

    private final ReversePolishNotation reversePolishNotation = ReversePolishNotation.getInstance();

    @Test
    public void getInstance_shouldReturnInstanceOfClass_always() {
        assertNotNull(ReversePolishNotation.getInstance());
        assertSame(ReversePolishNotation.getInstance(), ReversePolishNotation.getInstance());
    }

    @DataProvider(name = "ValidBitExpressionsProvider")
    public Object[][] getValidBitExpressions() {
        return new Object[][] {
                { "13<<2", Arrays.asList("13", "2", "<<") },
                { "3>>5", Arrays.asList("3", "5", ">>") },
                { "~6&9|(3&4)", Arrays.asList("6", "~", "9", "&", "3", "4", "&", "|") },
                {
                        "(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78",
                        Arrays.asList("71", "~", "2",
                                      "3", "&", "3", "2",
                                      "1", "2", ">>", "&",
                                      "2", "|", "2", "&",
                                      "|", "|", "10", "2",
                                      "&", "|", "&", "78", "|")
                },
        };
    }

    @Test(dataProvider = "ValidBitExpressionsProvider")
    public void process_shouldReturnListOfValues_whenInputExpressionIsValid(String input, List<String> result) {
        try {
            assertEquals(reversePolishNotation.process(input), result);
        } catch (InvalidExpressionException e) {
            e.printStackTrace();
            fail();
        }
    }

    @DataProvider(name = "InvalidBitExpressionsProvider")
    public Object[][] getInvalidBitExpressions() {
        return new Object[][] {
                { "5(1&2&(3|(4&(^5|6&47)|3)|2)|1)", },
        };
    }

    @Test(dataProvider = "InvalidBitExpressionsProvider")
    public void process_shouldThrowException_whenInputExpressionIsInvalid(String input) {
        try {
            reversePolishNotation.process(input);
            fail();
        } catch (InvalidExpressionException e) {
            assertNotNull(e);
        }
    }

}