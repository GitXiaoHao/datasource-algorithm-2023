package top.iyuhao.leetcode;

import top.iyuhao.algorithm.heap.MyMinHeap;

/**
 * @author yuhao
 * @time 2024/1/25 9:56
 * @desc 数据流中的第K大元素
 **/
public class LeetCode703 {
    private MyMinHeap heap;
    private int k;

    public LeetCode703(int k, int[] nums) {
        this.k = k;
        heap = new MyMinHeap(k);
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (!heap.isFull()) {
            heap.offer(val);
        } else if (val > heap.peek()) {
            heap.replace(val);
        }
        return heap.peek();
    }

    public static void main(String[] args) {
        LeetCode703 leetCode703 = new LeetCode703(3,
                new int[]{ 4,5,8,2});
        System.out.println(leetCode703.add(3));
        System.out.println(leetCode703.add(5));
        System.out.println(leetCode703.add(10));
        System.out.println(leetCode703.add(9));
        System.out.println(leetCode703.add(4));

    }
}
