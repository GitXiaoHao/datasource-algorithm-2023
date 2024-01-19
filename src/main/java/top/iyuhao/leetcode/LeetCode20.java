package top.iyuhao.leetcode;

import top.iyuhao.algorithm.stack.MyArrayStack;

/**
 * @author yuhao
 * @time 2024/1/9 12:10
 * @description leetcode 20 有效的括号
 **/
public class LeetCode20 {
    public boolean isValid(String s) {
        if (s.isEmpty()) {
            return true;
        }
        MyArrayStack<Character> stack = new MyArrayStack<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        LeetCode20 leetCode20 = new LeetCode20();
        System.out.println(leetCode20.isValid("()[]{}"));
        System.out.println(leetCode20.isValid("(]"));
        System.out.println(leetCode20.isValid("([)]"));
        System.out.println(leetCode20.isValid("{[]}"));
        System.out.println(leetCode20.isValid("]"));
        System.out.println(leetCode20.isValid("(){}"));
    }
}
