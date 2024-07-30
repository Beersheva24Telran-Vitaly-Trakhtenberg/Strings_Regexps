package telran.regexp;

public class Strings
{
    public static boolean isJavaVariable(String checking_variable)
    {
        String regexp = "([a-z_$])([a-zA-Z_$\\d]*)";
        return checking_variable.matches(regexp);
    }
}
