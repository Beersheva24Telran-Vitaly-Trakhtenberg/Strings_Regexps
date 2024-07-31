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
    }

    @Test
    void isRegexpJavaReservedNamesTest()
    {
        assertTrue(isRegexpJavaReservedNames("test assert isboolean"));
        assertTrue(isRegexpJavaReservedNames("test assert is boolean"));
        assertFalse(isRegexpJavaReservedNames("test_assert isBoolean"));
    }

    @Test
    void isArithmeticExpressionTest()
    {
        String testValidString1 = "(var1 + 25) * (var2 - 12)";
        String testValidString2 = "(var1 + 25) * ((var2 - 12) + var3)";
        String testValidString3 = "(5)";
        String testValidString4 = "(oper1)";
        String testValidString5 = "var1 + 25 * var2 - 12";

        String testInvalidString1 = "(var1 + 25) * ((var2 - 12) + var3";
        String testInvalidString2 = "void + (var1 + 25) * (var2 - 12)";
        String testInvalidString3 = "()";
        String testInvalidString4 = "++";
        String testInvalidString5 = "s++";
        String testInvalidString6 = "++k";
        String testInvalidString7 = "assert";
        String testInvalidString8 = "(+)";

        /** Valid values **/
        ArithmeticJavaExpression exp = new ArithmeticJavaExpression(testValidString1);
        assertTrue(exp.test());

        exp = new ArithmeticJavaExpression(testValidString2);
        assertTrue(exp.test());

        exp = new ArithmeticJavaExpression(testValidString3);
        assertTrue(exp.test());

        exp = new ArithmeticJavaExpression(testValidString4);
        assertTrue(exp.test());

        exp = new ArithmeticJavaExpression(testValidString5);
        assertTrue(exp.test());

        /** Invalid values **/
        exp = new ArithmeticJavaExpression(testInvalidString1);
        assertFalse(exp.test());

        exp = new ArithmeticJavaExpression(testInvalidString2);
        assertFalse(exp.test());

        exp = new ArithmeticJavaExpression(testInvalidString3);
        assertFalse(exp.test());

        exp = new ArithmeticJavaExpression(testInvalidString4);
        assertFalse(exp.test());

        exp = new ArithmeticJavaExpression(testInvalidString5);
        assertFalse(exp.test());

        exp = new ArithmeticJavaExpression(testInvalidString6);
        assertFalse(exp.test());

        exp = new ArithmeticJavaExpression(testInvalidString7);
        assertFalse(exp.test());

        exp = new ArithmeticJavaExpression(testInvalidString8);
        assertFalse(exp.test());
    }
}
