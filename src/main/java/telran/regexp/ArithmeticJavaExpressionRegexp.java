package telran.regexp;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import static telran.regexp.Strings.*;

public class ArithmeticJavaExpressionRegexp
{
    private String sourceExpression;

    public ArithmeticJavaExpressionRegexp(String expression)
    {
        this.sourceExpression = expression;
    }

    public boolean test()
    {
        boolean res = true;
        String expression = sourceExpression.replaceAll("\\s+", "");

        if (!areBracketsBalanced(expression)) {
            res = false;
        }

        if (res) {
            String regex = "^(\\(*\\d+\\)*|\\(*[a-zA-Z$_][\\w$]*\\)*)([+\\-*/](\\(*\\d+\\)*|\\(*[a-zA-Z$_][\\w$]*\\)*))*$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(expression);

            res = matcher.matches() && checkVariables(expression);
        }
        return res;
    }

    private boolean areBracketsBalanced(String expression) {
        int balance = 0;
        for (char c : expression.toCharArray()) {
            if (c == '(') {
                balance++;
            } else if (c == ')') {
                balance--;
                if (balance < 0) {
                    break;
                }
            }
        }
        return balance == 0;
    }

    private boolean checkVariables(String expression) {
        String varRegex = "[a-zA-Z$_][\\w$]*";
        Pattern pattern = Pattern.compile(varRegex);
        Matcher matcher = pattern.matcher(expression);

        boolean res = true;
        while (matcher.find()) {
            String var = matcher.group();
            if (!isJavaVariable(var)) {
                res = false;
                break;
            }
        }
        return res;
    }
}
