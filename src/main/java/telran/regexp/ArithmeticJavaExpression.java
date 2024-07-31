package telran.regexp;

import static telran.regexp.Strings.*;

public class ArithmeticJavaExpression {
    private String sourceExpression;
    private int stack = 0;

    public ArithmeticJavaExpression(String expression)
    {
        this.sourceExpression = expression;
    }

    public boolean test()
    {
        int pos_brackets = -1;
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
            }
        }
        if (var.length() > 0) {
            res = this.check_var(var);
        }
        if (res) {
            res = this.stack == 0;
        }

        return res;
    }

    private boolean check_var(String var)
    {
        boolean res = var.matches("\\d+") || isJavaVariable(var);
        return res;
    }
}
