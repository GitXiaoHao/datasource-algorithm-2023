package top.iyuhao.leetcode;

import top.iyuhao.algorithm.heap.MyHeap;

import java.util.Arrays;

/**
 * @author yuhao
 * @time 2024/1/25 11:07
 * @desc 数据流的中位数
 * 使用一个小顶堆和一个大顶堆实现
 * 为了保证两边数据量的平衡
 *  - 两边个数一样时，左边个数加一
 *  - 两边个数不一样时，右边个数加一
 *
 *          - 左边个数加一时，把新元素加在右边，弹出右边最小的加入左边
 *          - 右边个数加一时，把新元素加在左边，弹出左边最大的加入右边
 **/
public class LeetCode295 {
    private MyHeap minRight = new MyHeap(10,false);
    private MyHeap maxLeft = new MyHeap(10,true);
    public void addNum(int num) {
        if(maxLeft.size() == minRight.size()){
            minRight.offer(num);
            maxLeft.offer(minRight.poll());
        }else {
            maxLeft.offer(num);
            minRight.offer(maxLeft.poll());
        }
    }

    /**
     * 两边数据一致，左右各取堆顶元素求平均
     * 左边多一个，取左边堆顶元素
     * @return
     */
    public double findMedian() {
        if(maxLeft.size() == minRight.size()){
            return (maxLeft.peek() + minRight.peek()) / 2.0;
        }else {
            return maxLeft.peek();
        }
    }

    @Override
    public String toString() {
        int[] left = new int[maxLeft.size()];
        for (int i = 0; i < left.length; i++) {
            left[this.maxLeft.size() - 1 - i] = this.maxLeft.array[i];
        }
        int[] right = Arrays.copyOf(this.minRight.array, this.minRight.size());
        return Arrays.toString(left) + " <-> " + Arrays.toString(right);
    }

    public static void main(String[] args) {
        LeetCode295 leetCode295 = new LeetCode295();
        leetCode295.addNum(1);
        leetCode295.addNum(2);
        leetCode295.addNum(3);
        //leetCode295.addNum(4);
        //leetCode295.addNum(5);
        //leetCode295.addNum(6);
        leetCode295.addNum(7);
        leetCode295.addNum(8);
        leetCode295.addNum(9);
        System.out.println(leetCode295);
        System.out.println(leetCode295.findMedian());
        leetCode295.addNum(10);
        System.out.println(leetCode295);
        System.out.println(leetCode295.findMedian());
        leetCode295.addNum(4);
        System.out.println(leetCode295);
        System.out.println(leetCode295.findMedian());
    }
}
