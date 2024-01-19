package top.iyuhao.leetcode;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author yuhao
 * @time 2024/1/15 13:43
 * @desc 用队列实现栈
 **/
public class LeetCode225 {
    Queue<Integer> queue = new PriorityQueue<>();

    /**
     * 将新加入元素，前面的所有元素从队列头移动到队列尾
     *
     * @param x
     */
    public void push(int x) {
        queue.offer(x);
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            queue.offer(queue.poll());
        }
    }
    public int pop() {
        return queue.poll();
    }
    public int top() {
        return queue.peek();
    }
    public boolean empty() {
        return queue.isEmpty();
    }
}
