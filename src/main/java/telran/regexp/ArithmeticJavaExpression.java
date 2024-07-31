package telran.regexp;

import java.util.List;
import java.util.ArrayList;
import static telran.regexp.Strings.*;

public class ArithmeticJavaExpression {
    private String sourceExpression;
    List<String> groupsExpression = new ArrayList<>();
    List<String> variables = new ArrayList<>();
    private int stack = 0;

    public ArithmeticJavaExpression(String expression)
    {
        this.sourceExpression = expression;

        check_brackets();
    }

    public boolean isCorrectExpression()
    {
        return this.stack == 0;
    }

    public boolean test()
    {
        int pos_brackets = -1;
        int pos_operands = -1;
        boolean prev_operand = false;
        String var = "";
        boolean res = true;

        for (int i = 0; i < sourceExpression.length() && res; i++) {
            char c = sourceExpression.charAt(i);
            if (c == '(') {
                prev_operand = false;
                if (var.length() > 0) {
                    res = this.check_var(var);
                    var = "";
                }
                if (stack == 0) {
                    pos_brackets = i;
                }
                stack++;
            } else if (c == ')') {
                prev_operand = false;
                stack--;
                if (stack == 0 && pos_brackets != -1) {
                    ArithmeticJavaExpression tmp = new ArithmeticJavaExpression(sourceExpression.substring(pos_brackets + 1, i));
                    res = tmp.test();
                    pos_brackets = -1;
                }
                if (var.length() > 0 && res) {
                    res = this.check_var(var);
                    var = "";
                } else {
                    res =false;
                }
            } else if (("+-*/".indexOf(c) != -1)) {
                if (prev_operand) {
                    res = false;
                } else {
                    prev_operand = true;
                    if (var.length() > 0) {
                        res = this.check_var(var);
                        var = "";
                    }
                }
            } else if (Character.isAlphabetic(c) || Character.isDigit(c)) {
                prev_operand = false;
                var += c;
            } else if (c == ' ') {
                prev_operand = false;
            } else {
                res =false;
                //break;
            }
        }
        if (var.length() > 0) {
            res = this.check_var(var);
        }
        if (res) {
            res = this.stack == 0;
        }
        res = res?true:false;   // TODO

        return res;
    }

    private boolean check_var(String var)
    {
        boolean res = var.matches("\\d+") || isJavaVariable(var);
        return res;
    }

    private void check_brackets()
    {
        int pos = -1;

        for (int i = 0; i < sourceExpression.length(); i++) {
            char c = sourceExpression.charAt(i);

            if (c == '(') {
                if (stack == 0) {
                    pos = i;
                }
                stack++;
            } else if (c == ')') {
                stack--;
                if (stack == 0 && pos != -1) {
                    groupsExpression.add(sourceExpression.substring(pos + 1, i));
                    pos = -1;
                }
            }
        }
    }

    public List<String> getGroupsExpression()
    {
        return this.groupsExpression;
    }

    public List<String> getVariables()
    {
        return this.variables;
    }

    public int getStack()
    {
        return this.stack;
    }
}
