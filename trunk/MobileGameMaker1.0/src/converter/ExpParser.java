package converter;

import java.util.Stack;
import java.util.Vector;

//处理表达式的类
public final class ExpParser {

    private static String backString = "";
    private static Stack<String> stack = new Stack<String>();
    private static String expression = " ";
    private static Vector<String> expVector = new Vector<String>();
    private static boolean judge = false;
    private static String[] singleSign = new String[]{"|", "&", "!", ">", "<", "=", "+",
        "-", "*", "/", "(", ")", "%"};
    private static String[] doubleSign = new String[]{"||", "!=", "==", "&&", ">=", "<=",
        "+=", "-=", "*=", "/=", "%="};
    private static Vector<String> tempVector = new Vector<String>();
    private static int start = 0, end = 1;

    private ExpParser() {
    }

    private static void getBackExp() {
        // 通过分析得到表达式的后序序列（除去所有括号），顺序保存在expVector中，
        expVector.removeAllElements();
        String temp1 = "";// 保存从tempVector中读出的元素
        String temp2 = "";// 保存栈顶元素
        for (int i = 0; i < tempVector.size(); i++) {
            temp1 = (String) tempVector.get(i);
            if (!isSingleSign(temp1) && !isDoubleSign(temp1)) { // 如果读出的元素不是操作符则直接存入expVector
                expVector.add(temp1);
            } else if (temp1.equals("(")) { // "(" 直接进栈
                stack.push(temp1);
            } else { // 如果读出的元素是操作符
                if (stack.empty()) // 如果栈为空则直接将元素进栈
                {
                    stack.push(temp1);
                } else if (temp1.equals(")")) { // 如果读出的元素是")"
                    temp2 = stack.pop();
                    while (!temp2.equals("(")) {
                        expVector.add(temp2);
                        temp2 = stack.pop();
                    }
                } else { // 如果读出的不元素是")"
                    while (!stack.empty()) {
                        temp2 = stack.peek();
                        if (getSignLev(temp1) > getSignLev(temp2)) {
                            // 如果当前读出的运算符优先级高于栈顶运算符的优先级
                            stack.push(temp1);
                            break;
                        } else {
                            expVector.add(stack.pop());
                        }
                    }
                    if (stack.empty()) {
                        stack.push(temp1);
                    }
                }
            }
        }
        while (!stack.empty()) {
            expVector.add(stack.pop());
        }
    }

    public static String getBackExp(String exp) {
        expression = exp;
        getTokens();
        getBackExp();
        getBackString();
        return backString;

    }

    private static void getBackString() {
        backString = "";
        for (int i = 0; i < expVector.size(); i++) {
            backString += (expVector.get(i) + " ");
        }
        backString = backString.trim();
    }

    private static int getSignLev(String str) {
        // 获得运算符的优先级
        int lev = 0;
        if (str.equals("!")) {
            lev = 10;
        } else if (str.equals("*") || str.equals("/") || str.equals("%")) {
            lev = 9;
        } else if (str.equals("+") || str.equals("-")) {
            lev = 8;
        } else if (str.equals(">") || str.equals(">=") || str.equals("<")
                || str.equals("<=")) {
            lev = 7;
        } else if (str.equals("!=") || str.equals("==")) {
            lev = 6;
        } else if (str.equals("&&")) {
            lev = 5;
        } else if (str.equals("||")) {
            lev = 4;
        } else if (str.equals("=") || str.equals("+=") || str.equals("-=")
                || str.equals("*=") || str.equals("/=") || str.equals("%=")) {
            lev = 3;
        } else if (str.equals("(") || str.equals(")")) // 由于“（”直接进栈而“）”不进栈，为了后续运算符能正确进栈，所以优先级设置为0
        {
            lev = 0;
        }
        return lev;

    }

    private static void getTokens() {
        // 功能：1、将表达式字符串分解为操作符和操作数，顺序存放在tempVector中
        // 功能：2、将表达式中的负号和正号处理成加号和减号（方法是在前面加0）

        // 第一步
        start = 0;
        end = 1;
        tempVector.removeAllElements();
        String temp = "";
        while (start < expression.length() && end <= expression.length()) {
            temp = expression.substring(end - 1, end);
            if (!isSingleSign(temp)) {
                end++;
            } else {

                if (start != end - 1) {
                    temp = expression.substring(start, end - 1);
                    tempVector.add(temp);
                }
                if (end < expression.length()) {
                    temp = expression.substring(end - 1, end + 1);
                }
                if (isDoubleSign(temp)) {// 如果是双字母运算符
                    temp = expression.substring(end - 1, ++end);
                    tempVector.add(temp);
                    start = end;
                    end++;
                } else {// 如果是单字母运算符
                    temp = expression.substring(end - 1, end);
                    tempVector.add(temp);
                    start = end;
                    end++;
                }
            }
        }
        if (!isSingleSign(expression.substring(expression.length() - 1))) {
            tempVector.add(expression.substring(start, end - 1));
        }

        // 第二步
        for (int i = 0; i < tempVector.size(); i++) {
            String str = tempVector.get(i);
            if (str.equals("+") || str.equals("-")) {
                // 如果检测到的符号是+或-
                // 如果此符号前没有任何东西则是正负号
                // 如果此符号之前是一个符号( ")" 除外 )则也是正负号
                if (i == 0) {
                    tempVector.add(i + 2, ")");
                    tempVector.add(i, "0");
                    tempVector.add(i, "(");
                } else if (!tempVector.get(i - 1).equals(")")
                        && (isSingleSign(tempVector.get(i - 1)) || isDoubleSign(tempVector.get(i - 1)))) {
                    tempVector.add(i + 2, ")");
                    tempVector.add(i, "0");
                    tempVector.add(i, "(");
                }
            }
        }
    }

    private static boolean isDoubleSign(String s) {
        // 若s是双字母运算符则返回true，否则返回false
        judge = false;
        for (int i = 0; i < doubleSign.length; i++) {
            if (s.equals(doubleSign[i])) {
                judge = true;
                break;
            }
        }
        return judge;

    }

    private static boolean isSingleSign(String s) {
        // 若s是单字母运算符则返回true，否则返回false
        judge = false;
        for (int i = 0; i < singleSign.length; i++) {
            if (s.equals(singleSign[i])) {
                judge = true;
                break;
            }
        }
        return judge;
    }
}
