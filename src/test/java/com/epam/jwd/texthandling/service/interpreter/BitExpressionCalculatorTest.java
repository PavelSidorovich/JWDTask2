package com.epam.jwd.texthandling.service.interpreter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BitExpressionCalculatorTest {

    @DataProvider(name = "ValidBitExpressionsProvider")
    public Object[][] getValidBitExpressions() {
        return new Object[][] {
                { "13<<2", 13 << 2 },
                { "3>>5", 3 >> 5 },
                { "~6&9|(3&4)", ~6 & 9 | (3 & 4) },
                { "(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78", (~71 & (2 & 3 | (3 | (2 & 1 >> 2 | 2) & 2) | 10 & 2)) | 78 },
        };
    }

    @Test(dataProvider = "ValidBitExpressionsProvider")
    public void calculate_shouldReturnCountedValue_whenExpressionIsValid(String input, int result) {
        assertTrue(BitExpressionCalculator.calculate(input).isPresent());
        assertSame(BitExpressionCalculator.calculate(input).get(), result);
    }

    @DataProvider(name = "InvalidBitExpressionsProvider")
    public Object[][] getInvalidBitExpressions() {
        return new Object[][] {
                { "5(1&2&(3|(4&(^5|6&47)|3)|2)|1)" },
                { "(^5|1&2<<(2|5>>2&71))|1200" },
        };
    }

    @Test(dataProvider = "InvalidBitExpressionsProvider")
    public void calculate_shouldThrowException_whenExpressionIsInvalid(String input) {
        assertFalse(BitExpressionCalculator.calculate(input).isPresent());
    }
}