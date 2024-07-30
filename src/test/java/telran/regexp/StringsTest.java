package telran.regexp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static telran.regexp.Strings.*;


public class StringsTest
{
    @Test
    void javaVariableTest()
    {
        // Valid names
        assertTrue(isJavaVariable("validVariable1"));
        assertTrue(isJavaVariable("_validVariable2"));
        assertTrue(isJavaVariable("$validVariable3"));
        assertTrue(isJavaVariable("$"));
        assertTrue(isJavaVariable("_"));
        assertTrue(isJavaVariable("_$"));
        assertTrue(isJavaVariable("valid_variable"));   // non-letter/non-digit

        // Invalid names
        assertFalse(isJavaVariable("1nvalidVariable"));   // starts with digit
        assertFalse(isJavaVariable("InvalidVariable"));   // starts with UpperCase
        assertFalse(isJavaVariable("1nvаlidVariable"));   // russian a
        assertFalse(isJavaVariable("invalid Variable"));   // space
        assertFalse(isJavaVariable("invalid-variable-4"));   // non-letter/non-digit
    }
}
