package telran.regexp;

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
        return checking_string.matches(regexpJavaReservedNames());
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

        return false;
    }
}
