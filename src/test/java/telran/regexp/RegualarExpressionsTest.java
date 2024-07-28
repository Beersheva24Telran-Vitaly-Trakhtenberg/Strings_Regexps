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

        // Invalid names
        assertFalse(javaVariable("1nvalidVariable"));   // starts with digit
        assertFalse(javaVariable("InvalidVariable"));   // starts with UpperCase
        assertFalse(javaVariable("1nvаlidVariable"));   // russian a
        assertFalse(javaVariable("invalid Variable"));   // space
        assertFalse(javaVariable("invalid_variable"));   // non-letter/non-digit
        assertFalse(javaVariable("invalid-variable-4"));   // non-letter/non-digit
    }
}
