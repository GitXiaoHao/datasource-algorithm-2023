package top.iyuhao.leetcode;

import java.util.LinkedList;

/**
 * @author yuhao
 * @time 2024/1/15 9:24
 * @desc leetcode150. 逆波兰表达式求值
 **/
public class LeetCode150 {
    public int evalRPN(String[] tokens) {
        LinkedList<Integer> stack = new LinkedList<>();
        for (String token : tokens) {
            if ("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token)) {
                int num2 = stack.pop();
                int num1 = stack.pop();
                stack.push(calculate(num1, num2, token));
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
    private int calculate(int num1, int num2, String operator) {
        return switch (operator) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> num1 / num2;
            default -> 0;
        };
    }
}
