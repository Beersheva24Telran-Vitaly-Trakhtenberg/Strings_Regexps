package telran.regexp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static telran.regexp.RegualarExpressions.javaVariable;


public class RegualarExpressionsTest
{
    @Test
    void javaVariableTest()
    {
        // Valid names
        assertTrue(javaVariable("validVariable1"));
        assertTrue(javaVariable("_validVariable2"));
        assertTrue(javaVariable("$validVariable3"));
        assertTrue(javaVariable("$"));
        assertTrue(javaVariable("_"));
        assertTrue(javaVariable("_$"));
        assertTrue(javaVariable("valid_variable"));   // non-letter/non-digit

        // Invalid names
        assertFalse(javaVariable("1nvalidVariable"));   // starts with digit
        assertFalse(javaVariable("InvalidVariable"));   // starts with UpperCase
        assertFalse(javaVariable("1nvаlidVariable"));   // russian a
        assertFalse(javaVariable("invalid Variable"));   // space
        assertFalse(javaVariable("invalid-variable-4"));   // non-letter/non-digit
    }
}
