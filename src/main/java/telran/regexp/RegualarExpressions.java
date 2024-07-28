package telran.regexp;

public class RegualarExpressions
{
    public static boolean javaVariable(String checking_variable)
    {
        String regexp = "([a-z_$])([a-zA-Z_$\\d]*)";
        return checking_variable.matches(regexp);
    }
}
