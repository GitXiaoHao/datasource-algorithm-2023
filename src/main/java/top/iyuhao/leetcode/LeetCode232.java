package top.iyuhao.leetcode;

import java.util.LinkedList;

/**
 * @author yuhao
 * @time 2024/1/15 11:39
 * @desc 用栈实现队列
 **/
public class LeetCode232 {
    LinkedList<Integer> s1 = new LinkedList<>();
    LinkedList<Integer> s2 = new LinkedList<>();

    public void push(int x) {
        s2.push(x);
    }

    public int pop() {
        if (s1.isEmpty()) {
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }
        return s1.pop();
    }
    public int peek() {
        if (s1.isEmpty()) {
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }
        return s1.peek();
    }
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}
