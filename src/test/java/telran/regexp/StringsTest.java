package telran.regexp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        assertTrue(isJavaVariable("_$"));
        assertTrue(isJavaVariable("valid_variable"));   // underscore is valid
        assertTrue(isJavaVariable("ValidVariable"));   // starts with UpperCase

        // Invalid names
        assertFalse(isJavaVariable("1nvalidVariable"));   // starts with digit
        assertFalse(isJavaVariable("1nvаlidVariable"));   // russian a
        assertFalse(isJavaVariable("extends"));   // use reserved Java keyword
        assertFalse(isJavaVariable("invalid Variable"));   // space
        assertFalse(isJavaVariable("invalid-variable-4"));   // non-letter/non-digit
        assertFalse(isJavaVariable("_"));    // but underscore only is invalid

        // +++++++++++++++++++++++++++++++++++++++
        assertTrue(isRegexpJavaReservedNames("test assert isboolean"));
        assertTrue(isRegexpJavaReservedNames("test assert is boolean"));
        assertFalse(isRegexpJavaReservedNames("test_assert isBoolean"));
    }
}
