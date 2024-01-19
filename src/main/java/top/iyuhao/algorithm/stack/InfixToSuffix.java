package top.iyuhao.algorithm.stack;

import java.util.LinkedList;

/**
 * @author yuhao
 * @time 2024/1/15 10:05
 * 中缀表达式转后缀
 * 1. 遇到非运算符 直接拼串
 * 2. 遇到 + - * /
 *      - 优先级比栈顶运算符高，入栈。如：栈中是 + 当前是 *
 *      - 优先级比栈顶运算符低，将栈顶运算符弹出，并拼串 然后将此字符入栈
 * 3. 遍历完成，栈里剩余运算符依次出栈
 * 4. 带（）
 *      - 左括号直接入栈 左括号优先设置为0
 *      - 右括号就把栈里到左括号为止的所有运算符出栈
 **/
public class InfixToSuffix {
     static int priority(char ch) {
        return switch (ch) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '(' -> 0;
            default -> throw new IllegalArgumentException("不合法");
        };
    }

    public static String infixToSuffix(String infix) {
        LinkedList<Character> characterLinkedList = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);
            switch (c) {
                case '+', '-', '*', '/' -> {
                    if (characterLinkedList.isEmpty()) {
                        characterLinkedList.push(c);
                    } else if (priority(c) > priority(characterLinkedList.peek())) {
                        characterLinkedList.push(c);
                    } else {
                        while (!characterLinkedList.isEmpty() && priority(c) <= priority(characterLinkedList.peek())) {
                            sb.append(characterLinkedList.pop());
                        }
                        characterLinkedList.push(c);
                    }
                }
                case '(' -> characterLinkedList.push(c);
                case ')' -> {
                    while (!characterLinkedList.isEmpty() && characterLinkedList.peek() != '(') {
                        sb.append(characterLinkedList.pop());
                    }
                    characterLinkedList.pop(); // 弹出左括号
                }
                default -> sb.append(c);
            }
        }
        while (!characterLinkedList.isEmpty()) {
            sb.append(characterLinkedList.pop());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(infixToSuffix("(a+b)*c"));
        System.out.println(infixToSuffix("a+b*c"));
        System.out.println(infixToSuffix("a+b*c+d"));
        System.out.println(infixToSuffix("a+b*c+d*e"));
    }
}
