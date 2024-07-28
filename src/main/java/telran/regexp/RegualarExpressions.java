package telran.regexp;

public class RegualarExpressions
{
    public static boolean javaVariable(String checking_variable)
    {
        String regexp = "([a-z_$]{1})([a-zA-Z\\d]+)";
        return checking_variable.matches(regexp);
    }
}
