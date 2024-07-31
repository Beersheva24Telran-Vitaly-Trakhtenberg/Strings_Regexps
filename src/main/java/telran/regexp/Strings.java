package telran.regexp;

import java.util.regex.*;

public class Strings
{
    static final String javaKeyWords[] = { "abstract", "assert", "boolean",
            "break", "byte", "case", "catch", "char", "class", "const",
            "continue", "default", "do", "double", "else", "enum", "extends", "false",
            "final", "finally", "float", "for", "goto", "if", "implements",
            "import", "instanceof", "int", "interface", "long", "native",
            "new", "null", "package", "private", "protected", "public",
            "return", "short", "static", "strictfp", "super", "switch",
            "synchronized", "this", "throw", "throws", "transient", "true",
            "try", "void", "volatile", "while" };

    /**
     *
     * @param checking_variable
     * @return is the word can be used as a name of variable in Java
     */
    public static boolean isJavaVariable(String checking_variable)
    {
        return checking_variable.matches(regexpJavaVariable()) && !isRegexpJavaReservedNames(checking_variable);
    }

    /**
     *
     * @param checking_string
     * @return is reserved keyword(-s) presents (as a full separate word) in the given string
     */
    public static boolean isRegexpJavaReservedNames(String checking_string)
    {
        String regex = regexpJavaReservedNames();
        Pattern pattern = Pattern.compile(regex); 
        Matcher matcher = pattern.matcher(checking_string);

        return matcher.matches();
    }

    /**
     *
     * @return regExp
     */
    public static String regexpJavaVariable()
    {
        return "((?!_$)[a-zA-Z$_][\\w$]*)";
    }

    /**
     *
     * @return regExp
     */
    public static String regexpJavaReservedNames()
    {
        StringBuilder regexBuilder = new StringBuilder();
        int length = javaKeyWords.length;
        for (int i = 0; i < length; i++) {
            regexBuilder.append("\\b").append(javaKeyWords[i]).append("\\b");
            if (i < length - 1) {
                regexBuilder.append("|");
            }
        }
        return ".*\\b(" + regexBuilder.toString() + ")\\b.*";
    }

    public static boolean isArithmeticExpression(String expr)
    {
        int stack_operations = 0;
        int stack_brackets = 0;
        String operand = "";
        int pos_brackets = -1;
        int pos_operations = -1;

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (c == '(') {
                if (stack_brackets == 0) {
                    pos_brackets = i;
                }
                stack_brackets++;
            } else if (c == ')') {
                stack_brackets--;
                if (stack_brackets == 0 && pos_brackets != -1) {
                    pos_brackets = -1;
                }
            }
        }

        return false;
    }
}
